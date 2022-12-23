import FileManager.ConfigManager;
import UserInput.InputReader;
import UserInput.Shell;

import java.io.IOException;

/**
 * <p>The Main class</p>
 */
public class Main {
    /**
     * <p>The main entry point into the programme</p>
     * @param args the arguments read from the CLI
     */
    public static void main(String[] args){

        printWelcome();

        ConfigManager configManager = ConfigManager.getInstance();

        try {
            configManager.initConfig();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Could not read \"configuration.conf\" file. Default values are : true for Debug and true for Mode");
        }
        //configManager.setMode(false);

        configManager.setIn("");
        configManager.setOut("");


        if (!configManager.getMode()){
            int i = 0;



            if (args.length<4) {
                System.out.println("Error : no input/output!");
                System.out.println("Shutting down!");
                System.exit(-1);
            } else {
                if (args[0].equals("-i") && args[2].equals("-o")){
                    //configManager.setIn("/Users/hugodemaret/cours/paa/Project/inout/in.txt");
                    //configManager.setOut("/Users/hugodemaret/cours/paa/Project/inout/out.txt");
                    configManager.setIn(args[1]);
                    configManager.setOut(args[3]);
                }
            }
        }
        //configManager.setIn("/Users/hugodemaret/cours/paa/Project/inout/in.txt");
        //configManager.setOut("/Users/hugodemaret/cours/paa/Project/inout/out.txt");
        Shell.shell();
        InputReader.scanner.close();
    }

    /**
     * <p>Prints a very welcoming welcome to the welcomed user (that is you)</p>
     */
    public static void printWelcome(){
        System.out.println("ThierryDebateModerator (TDM) says welcome to the Grand Débat on TF1.");
        System.out.println("I, Thierry, will say who has the best arguments for this debate!");
        System.out.println("Best of luck to all of you :)");
    }
}
