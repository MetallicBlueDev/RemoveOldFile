package com.metallicbluedev.removeoldfile;

import com.metallicbluedev.logger.*;
import com.metallicbluedev.utils.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.*;
import java.time.temporal.*;

/**
 *
 * @author Sébastien Villemain
 */
public class FileLogic {

    private final FileConfig config;
    private final Instant now;
    private final Path path;
    private final File file;

    public FileLogic(FileConfig config, Instant now) {
        this.config = config;
        path = Path.of(config.getPath());
        file = path.toFile();
        this.now = now;
    }

    public boolean canExecute() {
        return file.exists() && file.canRead() && file.canWrite();
    }

    public void execute() throws IOException {
        try {
            checkFiles();
            checkDirectories();
        } catch (IOException ex) {
            LoggerManager.getInstance().addError(ex);
        }
    }

    private void checkDirectories() throws IOException {
        Files.find(path, config.getMaxDepth(), (filePath, fileAttr) -> fileAttr.isDirectory()).forEach((filePath) -> {
            try {
                checkDirectory(filePath);
            } catch (IOException ex) {
                LoggerManager.getInstance().addError(ex);
            }
        });
    }

    private void checkDirectory(Path directory) throws IOException {
        boolean empty = Files.find(directory, Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile()).findAny().isEmpty();

        if (empty) {
            LoggerManager.getInstance().addInformation("Remove folder: " + directory);

            if (!FileHelper.deleteDirectories(directory)) {
                LoggerManager.getInstance().addWarning("Unable to remove empty folder: " + directory);
            }
        }
    }

    private void checkFiles() throws IOException {
        Files.find(path, config.getMaxDepth(), (filePath, fileAttr) -> fileAttr.isRegularFile()).forEach((filePath) -> {
            try {
                checkFile(filePath);
            } catch (IOException ex) {
                LoggerManager.getInstance().addError(ex);
            }
        });
    }

    private void checkFile(Path filePath) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);

        if (attributes == null) {
            throw new IOException("Unable to read attributes: " + filePath);
        }

        if (isExpired(attributes)) {
            LoggerManager.getInstance().addInformation("Remove file: " + filePath);

            if (!filePath.toFile().delete()) {
                LoggerManager.getInstance().addWarning("Unable to remove file: " + filePath);
            }
        }
    }

    private boolean isExpired(BasicFileAttributes attributes) {
        return now.isAfter(getExpirationDate(attributes));
    }

    private Instant getExpirationDate(BasicFileAttributes attributes) {
        return attributes.lastModifiedTime().toInstant().plus(config.getDaysToKeep(), ChronoUnit.DAYS);
    }
}
