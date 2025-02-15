package com.tr4ncer.removeoldfile;

import com.tr4ncer.core.*;
import com.tr4ncer.factory.*;

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
