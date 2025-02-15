package com.metallicbluedev.removeoldfile;

import com.metallicbluedev.core.*;
import com.metallicbluedev.factory.*;

/**
 *
 * @author Sébastien Villemain
 */
public class RemoveOldFileSettingManager extends XmlSettingManager<RemoveOldFileSettings> {

    protected RemoveOldFileSettingManager() {
        super(RemoveOldFileSettings.class);
    }

    public static RemoveOldFileSettingManager getInstance() {
        return FactoryManager.getInstance(RemoveOldFileSettingManager.class);
    }

}
