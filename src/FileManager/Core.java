package FileManager;

import Argumentation.Argument;
import Argumentation.ArgumentationFramework;
import Argumentation.ArgumentationSet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * <p>The core of the programme</p>
 */
public class Core {

    /**
     * <p>The argumentation framework</p>
     */
    private ArgumentationFramework af;
    /**
     * <p>The last solution to be written and printed</p>
     */
    private ArgumentationSet solution;
    /**
     * <p>The number of arguments in the AF</p>
     */
    private int nbArg;
    /**
     * <p>Singleton</p>
     */
    private static Core core = new Core();

    /**
     * <p>Prints all the prefered and admissible sets</p>
     */
    public void printAll(){
        af.printPrefered();
        af.printAdmissible();
    }

    /**
     * <p>Getter for the instance</p>
     * @return the instance
     */
    public static Core getInstance(){
        return core;
    }

    /**
     * <p>Default constructor</p>
     */
    private Core(){
        this.af = new ArgumentationFramework();
        nbArg = 0;
    }

    /**
     * <p>Prints on of (randomly chosen) admissible sets</p>
     */
    public void printAdmissible(){
        Random generator = new Random();
        Integer[] randomArray = af.getAdmissibleSets().keySet().toArray(new Integer[0]);
        int rndIndex = generator.nextInt(randomArray.length);
        int rndKey = randomArray[rndIndex];
        if (!af.getAdmissibleSets().containsKey(rndKey)) System.out.println("HERE");
        solution = af.getAdmissibleSets().get(rndKey);
        if (solution.isEmpty()) System.out.println("(Example) of an admissible set : {}");
        else System.out.println("(Example) of an admissible set : " + solution);
    }

    /**
     * <p>Calculates all the admissibles and prefered sets</p>
     */
    public void calculate(){
        af.constructAdmissible();
        af.constructPrefered();
    }

    /**
     * <p>Prints one of (randomly chosen) prefered sets</p>
     */
    public void printPrefered(){
        Random generator = new Random();
        Integer[] randomArray = af.getPreferedSets().keySet().toArray(new Integer[0]);
        if (randomArray.length == 0) {
            System.out.println("Not prefered solution!");
            return;
        }
        int rndIndex = generator.nextInt(randomArray.length);
        int rndKey = randomArray[rndIndex];
        solution = af.getPreferedSets().get(rndKey);
        System.out.println("(Example) of an preferable set : " + solution);
    }

    /**
     * <p>Writes to the output file, given in parameter</p>
     */
    public void writeToOutput(){
        ConfigManager configManager = ConfigManager.getInstance();
        FileWriter file;
        try {
            file = new FileWriter(configManager.getFileOut());
            if (solution.isEmpty()) file.write("{}");
            file.write(solution.toString());
            file.close();
            System.out.println("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Reads from the input file, given in parameter</p>
     */
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


    /**
     * <p>Executes the command and parses it</p>
     * @param command the command to be executed
     */
    //Execute order 66
    public void execute(String command){
        command = command.replaceAll("\\s","");
        if (command.toLowerCase().startsWith("argument")){
            String parsing = command.toLowerCase().substring("argument(".length(), command.length()-2);
            nbArg++;
            Argument argument = new Argument(nbArg,parsing);
            af.addArgument(argument);
        } else if (command.toLowerCase().startsWith("contradiction")){
            String parsing = command.toLowerCase().substring("contradiction(".length() , command.length()-2);
            String[] args = parsing.split(",");
            if (args.length == 2)
                af.addContradiction(args[0],args[1]);
            else System.out.println("File is ill formed!");
        } else {
            System.out.println("File is ill formed!");
        }
    }

    /**
     * <p>Resets the Argumentation Framework</p>
     */
    public void reset(){
        this.af.clear();
    }
}
