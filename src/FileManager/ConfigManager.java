package FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class ConfigManager {
    private boolean debug; //if set to true, then enters debug mode
    private boolean mode; //if set to true, then enters manual mode
    private static final String path = "../../config/configuration.conf";
    private String fileIn;
    private String fileOut;


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
     * @param debug
     */
    public void setDebug(boolean debug){
        this.debug = debug;
    }

    /**
     * <p>Setter for mode's value</p>
     * @param mode
     */
    public void setMode(boolean mode){
        this.mode = mode;
    }


    public void setIn(String string){
        fileIn = string;
    }

    public void setOut(String string){
        fileOut =string;
    }

    public static String getPath() {
        return path;
    }

    public String getFileIn() {
        return fileIn;
    }

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
     * @param debugLine
     * @return true if String contains "true", false otherwise
     */
    public boolean parseString(String string){
       return string.toLowerCase().contains("true");
    }


    /**
     * <p>Initializes the value in this by reading the configuration file</p>
     * @throws IOException
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
