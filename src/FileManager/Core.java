package FileManager;

import Argumentation.Argument;
import Argumentation.ArgumentationFramework;
import Argumentation.ArgumentationSet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Core {

    private String output;
    private ArgumentationFramework af;
    private ArgumentationSet solution;
    private int nbArg;
    private static Core core = new Core();


    public static Core getInstance(){
        return core;
    }

    private Core(){
        this.af = new ArgumentationFramework();
        nbArg = 0;
    }

    public void printAdmissible(){
        Set<ArgumentationSet> argsets =  af.getAdmissibleSets();
        Random random = new Random();
        int index = random.nextInt(argsets.size());
        //Unfortunately we have to iterate through the set, since Java creators did not deem the get method as useful...
        //It is quite disgusting!
        int  i =0;
        for (ArgumentationSet argset : argsets){
            if (i==index) {
                solution = argset;
                break;
            }
            i++;
        }
        System.out.println(solution);
    }

    public void printPrefered(){
        Set<ArgumentationSet> argsets =  af.getPreferedSets();
        Random random = new Random();
        int index = random.nextInt(argsets.size());
        //Unfortunately we have to iterate through the set, since Java creators did not deem the get method as useful...
        //It is quite disgusting!
        int  i =0;
        for (ArgumentationSet argset : argsets){
            if (i==index) {
                solution = argset;
                break;
            }
            i++;
        }
        System.out.println(solution);
    }


    public void writeToOutput(){
        ConfigManager configManager = ConfigManager.getInstance();
        FileWriter file;
        try {
            file = new FileWriter(configManager.getFileIn());
            file.write(output);
            file.close();
            System.out.println("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
