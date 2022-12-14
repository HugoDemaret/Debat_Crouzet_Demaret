package UserInput;




import Argumentation.*;
import FileManager.ConfigManager;
import FileManager.Core;
import FileManager.InputOutput;


public class Shell {

    public static void shell() {

        ConfigManager configManager = ConfigManager.getInstance();

        if (configManager.getMode()) {
            System.out.println("Enter the number of arguments : ");
            ArgumentationFramework af = new ArgumentationFramework();
            boolean ret = true;
            String quit = "quit";
            int n;
            n = InputReader.scanner.nextInt();
            //to remove the \n of nextInt()
            InputReader.scanner.nextLine();
            System.out.println("The number of arguments is : " + n);

            for (int i = 1; i < n + 1; ++i) {
                af.addArgument(new Argument(i));
            }

            String answer;

            do {
                System.out.println("1 : add contradiction in the form A1A2");
                System.out.println("2 | quit : quit");


                answer = InputReader.scanner.nextLine();

                switch (answer.toLowerCase()) {
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
            } while (ret);

            ret = !ret;

            while (ret) {
                System.out.println("1 : add an argument to the solution (single integer)");
                System.out.println("2 : remove an argument from the solution (single integer)");
                System.out.println("3 : verifies if the solution is admissible");
                System.out.println("4 | quit : quit");
                answer = InputReader.scanner.nextLine();
                boolean res;
                switch (answer.toLowerCase()) {
                    case "1":
                        af.readSolution();
                        break;
                    case "2":
                        af.removeSolution();
                        break;
                    case "3":
                        res = af.verifySolution(true);
                        System.out.println("Resultat : " + res);
                        break;
                    case "4":
                    case "quit":
                        ret = !ret;
                        res = af.verifySolution(true);
                        System.out.println("Resultat : " + res);
                        break;
                    default:
                        System.out.println("Error : wrong input!");
                        break;
                }
            }


            InputReader.scanner.close();
        } else {
            Core core = Core.getInstance();
            boolean ret = true;
            String answer;
            while (ret) {
                System.out.println("1 : Search for an admissible solution");
                System.out.println("2 : Search for a prefered solution");
                System.out.println("3 : Save the solution");
                System.out.println("5 : Print all admissible solutions");
                System.out.println("6 : Print all prefered solutions");
                System.out.println("7 : Save all solutions");
                System.out.println("4 | quit | q : quit");
                answer = InputReader.scanner.nextLine();
                boolean res;
                switch (answer.toLowerCase()) {
                    case "1":
                        core.printAdmissible();
                        break;
                    case "2":
                        core.printPrefered();
                        break;
                    case "3":
                        core.writeToOutput();
                        break;
                    case "5":
                        core.printAllAdmissible();
                        break;
                    case "6":
                        core.printAllPrefered();
                        break;
                    case "7":
                        core.saveAllSolutions();
                        break;
                    case "4":
                    case "q":
                    case "quit":
                        ret = !ret;
                        res = af.verifySolution(true);
                        System.out.println("Resultat : " + res);
                        break;
                    default:
                        System.out.println("Error : wrong input!");
                        break;
                }
            }
        }
    }

}
