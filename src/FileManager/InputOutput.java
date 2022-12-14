package FileManager;

import Argumentation.Argument;
import Argumentation.ArgumentationFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class InputOutput {

    private ArgumentationFramework af;
    private int nbArg;
    private static InputOutput inputOutput = new InputOutput();


    public static InputOutput getInstance(){
        return inputOutput;
    }

    private InputOutput(){
        this.af = new ArgumentationFramework();
        nbArg = 0;
    }

    public void readFromInput(){
        ConfigManager configManager = ConfigManager.getInstance();
        FileInputStream file;
        try {
            file = new FileInputStream(configManager.getFileIn());
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                execute(scanner.nextLine());
            }
            scanner.close();

        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("The input file does not exist");
            fileNotFoundException.printStackTrace();
        }


    }


    public void execute(String command){
        command.replaceAll("\\s","");
        if (command.toLowerCase().startsWith("argument")){
            String parsing = command.toLowerCase().substring("argument(".length()-1, command.length()-3);
            nbArg++;
            Argument argument = new Argument(nbArg,parsing);
            af.addArgument(argument);
        } else if (command.toLowerCase().startsWith("contradiction")){
            String parsing = command.toLowerCase().substring("contradiction(".length() -1, command.length()-3);
            String[] args = parsing.split(",");
            if (args.length == 2)
                af.addContradiction(args[0],args[1]);
            else System.out.println("File is ill formed!");
        } else {
            System.out.println("File is ill formed!");
        }
    }


    public void reset(){
        this.af.clear();
    }
}
