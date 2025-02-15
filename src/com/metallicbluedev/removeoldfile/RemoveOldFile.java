package com.metallicbluedev.removeoldfile;

import com.metallicbluedev.core.*;
import com.metallicbluedev.factory.*;
import com.metallicbluedev.logger.*;

/**
 *
 * @author Sébastien Villemain
 */
public class RemoveOldFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FactoryManager.register(DefaultMainManager.class);
        FactoryManager.register(RemoveOldFileSettingManager.class);

        MainManager instance = MainManager.getInstance();
        instance.setCommands(args);
        instance.setMainAppClass(RemoveOldFile.class);
        instance.addMainProcess(RemoveFileController.class);

        try {
            instance.start();
            instance.listen();
        } catch (Exception e) {
            LoggerManager.getInstance().addError(e);
        }
    }

}
