package FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

/**
 * <p>The configuration manager class</p>
 */
public class ConfigManager {
    /**
     * <p>debug value</p>
     */
    private boolean debug; //if set to true, then enters debug mode
    /**
     * <p>mode value</p>
     */
    private boolean mode; //if set to true, then enters manual mode
    /**
     * <p>mode value</p>
     */
    private static final String path = "./configuration.conf";
    /**
     * <p>input file</p>
     */
    private String fileIn;
    /**
     * <p>output file</p>
     */
    private String fileOut;


    /**
     * <p>Singleton</p>
     */
    private static ConfigManager configManager = new ConfigManager();

    /**
     * <p>Only one instance of this, initializes the values to true by default (in case of failure)</p>
     */
    private ConfigManager(){
        debug = true;
        mode = true;
    }

    /**
     * <p>Gets the instance of this</p>
     * @return this
     */
    public static ConfigManager getInstance(){
        return configManager;
    }

    /**
     * <p>Setter for debug's value</p>
     * @param debug the debug value
     */
    public void setDebug(boolean debug){
        this.debug = debug;
    }

    /**
     * <p>Setter for mode's value</p>
     * @param mode the mode value
     */
    public void setMode(boolean mode){
        this.mode = mode;
    }


    /**
     * <p>Sets the in file</p>
     * @param string input file
     */
    public void setIn(String string){
        fileIn = string;
    }

    /**
     * <p>Sets the out file</p>
     * @param string the string for the output file
     */
    public void setOut(String string){
        fileOut =string;
    }

    /**
     * <p>Gets the path value</p>
     * @return the path value
     */
    public static String getPath() {
        return path;
    }

    /**
     * <p>Gets the path for input file</p>
     * @return the path
     */
    public String getFileIn() {
        return fileIn;
    }
    /**
     * <p>Gets the path for output file</p>
     * @return the path
     */
    public String getFileOut() {
        return fileOut;
    }

    /**
     * <p>Getter for the mode value</p>
     * @return mode's value
     */
    public boolean getMode(){
        return this.mode;
    }

    /**
     * <p>Getter for the debug value</p>
     * @return debug's value
     */
    public boolean getDebug(){
        return this.debug;
    }

    /**
     * <p>Parses the string (naively) to see if it contains "true"</p>
     * @param string the string to parse
     * @return true if String contains "true", false otherwise
     */
    public boolean parseString(String string){
        if (string != null)
            return string.toLowerCase().contains("true");
        return false;
    }


    /**
     * <p>Initializes the value in this by reading the configuration file</p>
     * @throws IOException input output exception
     */
    public void initConfig() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        try {
            String debugLine = bufferedReader.readLine();
            String modeLine = bufferedReader.readLine();
            boolean debugValue = parseString(debugLine);
            boolean modeValue = parseString(modeLine);
            setDebug(debugValue);
            setMode(modeValue);
        } finally {
            bufferedReader.close();
        }
    }


}
