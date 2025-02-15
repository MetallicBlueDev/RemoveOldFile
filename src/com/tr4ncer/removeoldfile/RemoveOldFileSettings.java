package com.tr4ncer.removeoldfile;

import com.tr4ncer.dto.*;
import jakarta.xml.bind.annotation.*;
import java.util.*;

/**
 *
 * @author Sébastien Villemain
 */
public class RemoveOldFileSettings extends AppSettings {

    private List<FileConfig> fileConfigs;

    public List<FileConfig> getFileConfigs() {
        return fileConfigs;
    }

    @XmlElementWrapper()
    @XmlElement(name = "fileConfig")
    public void setFileConfigs(List<FileConfig> fileConfigs) {
        this.fileConfigs = fileConfigs;
    }
}
