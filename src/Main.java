import FileManager.ConfigManager;
import UserInput.Shell;

import java.io.IOException;

public class Main {
    public static void main(String[] args){

        ConfigManager configManager = ConfigManager.getInstance();

        try {
            configManager.initConfig();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Could not read \"configuration.conf\" file. Default values are : true for Debug and true for Mode");
        }


        configManager.setIn("");
        configManager.setOut("");


        if (!configManager.getMode()){
            int i = 0;
            /*
            for (String string : args){
                if (string.equals("-i") && !args[i+1].equals("-o") && args[i+1] != null)
                    configManager.setIn(args[i+1]);
                else if (string.equals("-o") && args[i+1] != null)
                    configManager.setOut(args[i+1]);


                else {
                    System.out.println(string + " is an invalid parameter");
                    System.out.println("Shutting down the programme...");
                    System.exit(-1);
                }



            }
            */
            if (args.length<4) {
                System.out.println("Error : no input/output!");
                System.out.println("Shutting down!");
                System.exit(-1);
            } else {
                if (args[0].equals("-i") && args[2].equals("-o")){
                    configManager.setIn(args[1]);
                    configManager.setOut(args[3]);
                }
            }
        }
        Shell.shell();
    }
}
