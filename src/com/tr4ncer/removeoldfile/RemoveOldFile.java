package com.tr4ncer.removeoldfile;

import com.tr4ncer.core.*;
import com.tr4ncer.factory.*;
import com.tr4ncer.logger.*;

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
