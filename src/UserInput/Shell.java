package UserInput;

import java.awt.*;
import java.util.Locale;
import java.util.Scanner;

public class Shell {

    public static void shell(){
        boolean ret = true;
        String quit = "quit";
        Scanner sc = new Scanner(System.in);
        while(ret){
            //commandes et lire input
            //si command == quit => ret =!ret;
            sc.nextLine();

            if (sc.toString().toLowerCase().equals(quit.toLowerCase())){
                ret = !ret;
            } else {
                switch (sc.toString().toLowerCase()){
                    case "1":
                        //todo
                        break;
                    case "2":
                        //todo
                        break;
                    default:
                        System.out.println("Error : wrong input! \n");
                        break;
                }
            }


        }
        sc.close();
    }

}
