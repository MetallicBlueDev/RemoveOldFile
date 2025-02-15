package com.metallicbluedev.removeoldfile;

import jakarta.xml.bind.annotation.*;

/**
 *
 * @author Sébastien Villemain
 */
public class FileConfig {

    private String path = null;
    private int daysToKeep = -1;
    private String name = null;

    private int maxDepth = -1;

    public int getDaysToKeep() {
        return daysToKeep;
    }

    public int getMaxDepth() {
        return maxDepth > 0 ? maxDepth : Integer.MAX_VALUE;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setDaysToKeep(int daysToKeep) {
        this.daysToKeep = daysToKeep;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @XmlAttribute()
    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path.trim();
    }

    @Override
    public String toString() {
        return getName();
    }

}
