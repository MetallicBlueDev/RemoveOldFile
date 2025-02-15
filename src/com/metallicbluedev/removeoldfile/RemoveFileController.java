package com.metallicbluedev.removeoldfile;

import com.metallicbluedev.core.*;
import com.metallicbluedev.logger.*;
import java.io.*;
import java.time.*;
import java.util.*;

/**
 *
 * @author Sébastien Villemain
 */
public class RemoveFileController extends GenericMainProcess {

    private final List<FileConfig> fileConfigs;
    private Instant now;

    public RemoveFileController() {
        fileConfigs = RemoveOldFileSettingManager.getInstance().getSettings().getFileConfigs();
    }

    @Override
    protected void onRunning() {
        now = Instant.now();

        checkFiles();
        MainManager.getInstance().stop();
    }

    private void checkFiles() {
        for (FileConfig fileConfig : fileConfigs) {
            if (fileConfig == null || fileConfig.getPath() == null || fileConfig.getPath().isBlank()) {
                continue;
            }

            checkFiles(fileConfig);
        }
    }

    private void checkFiles(FileConfig fileConfig) {
        FileLogic logic = new FileLogic(fileConfig, now);

        if (logic.canExecute()) {
            try {
                logic.execute();
            } catch (IOException ex) {
                LoggerManager.getInstance().addError(ex);
            }
        }
    }

}
