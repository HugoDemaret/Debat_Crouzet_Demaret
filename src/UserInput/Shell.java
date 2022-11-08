package UserInput;



import java.awt.*;
import java.util.Locale;
import java.util.Scanner;

public class Shell {

    public static <ArgumentationFramework> void shell(){
        ArgumentationFramework af = new ArgumentationFramework();
        boolean ret = true;
        String quit = "quit";
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();

        for(int i = 0; i<n; ++i){
            af.addArgument(new Argument(i));
        }


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
                        af.readContradiction();
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
