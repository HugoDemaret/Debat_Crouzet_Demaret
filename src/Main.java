import FileManager.ConfigManager;
import UserInput.Shell;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            ConfigManager.configManager.initConfig();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Could not read \"configuration.conf\" file. Default values are : true for Debug and true for Mode");
        }
        if (ConfigManager.configManager.getMode())
            Shell.shell();
        else
            return;
    }
}
