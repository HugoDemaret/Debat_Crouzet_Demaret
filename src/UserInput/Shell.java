package UserInput;




import Argumentation.*;

import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class Shell {

    public static void shell(){
        System.out.println("Enter the number of arguments : ");
        ArgumentationFramework af = new ArgumentationFramework();
        boolean ret = true;
        String quit = "quit";
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        //to remove the \n of nextInt()
        sc.nextLine();
        System.out.println("The number of arguments is : " + n);

        for(int i = 1; i<n+1; ++i){
            af.addArgument(new Argument(i));
        }

        String answer;

        do {
            System.out.println("1 : add contradiction in the form A1A2");
            System.out.println("2 | quit : quit");


            answer = sc.nextLine();

            switch (answer.toLowerCase()){
                case "quit":
                    ret = !ret;
                    break;
                case "1":
                    af.readContradiction();
                    break;
                case "2":
                    ret = !ret;
                    break;
                default:
                    System.out.println("Error : wrong input!");
                    break;
            }
        }while(ret);

        ret = !ret;

        while(ret){
            System.out.println("1 : add an argument to the solution (single integer)");
            System.out.println("2 : remove an argument from the solution (single integer)");
            System.out.println("3 : verifies if the solution is admissible");
            System.out.println("4 | quit : quit");
            answer = sc.nextLine();
            boolean res;
            switch (answer.toLowerCase()){
                case "1":
                    af.readSolution();
                    break;
                case "2":
                    af.removeSolution();
                    break;
                case "3":
                    res = af.verifySolution();
                    System.out.println("Resultat : " + res);
                    break;
                case "4": case "quit":
                    ret = !ret;
                    res = af.verifySolution();
                    System.out.println("Resultat : " + res);
                    break;
                default:
                    System.out.println("Error : wrong input!");
                    break;
            }
        }


        sc.close();
    }


}
