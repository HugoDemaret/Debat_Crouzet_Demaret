package TestCode;

import Argumentation.Argument;
import Argumentation.ArgumentationFramework;
import Argumentation.ArgumentationSet;
import UserInput.*;

public class TestCode {
    public static void main(String[] args){
        //Shell.shell();
        ArgumentationFramework af = new ArgumentationFramework();

        //test 1
        System.out.println("Test 1");
        for (int i = 1 ; i< 6; ++i){
            af.addArgument(new Argument(i));
        }

        af.addContradiction(1,2);
        af.addContradiction(1,5);
        af.addContradiction(2,3);
        af.addContradiction(3,4);
        af.addContradiction(4,3);
        af.addContradiction(3,2);
        af.addContradiction(2,1);
        af.addContradiction(5,4);

        af.constructAdmissible();
        af.constructPrefered();

        af.printAdmissible();
        af.printPrefered();

        af.clear();
    //test2
        System.out.println("Test 2");
        for (int i = 1; i<4;++i){
            af.addArgument(new Argument(i));
        }
        af.addContradiction(1,2);
        af.addContradiction(2,3);
        af.addContradiction(2,1);
        af.constructAdmissible();
        af.constructPrefered();
        af.printAdmissible();
        af.printPrefered();
        af.clear();
    //test3
        System.out.println("Test 3");
        for (int i = 1; i<5; ++i){
            af.addArgument(new Argument(i));
        }
        af.addContradiction(1,2);
        af.addContradiction(2,1);
        af.addContradiction(1,4);
        af.addContradiction(4,2);
        af.addContradiction(2,3);
        af.addContradiction(4,3);
        af.constructAdmissible();
        af.constructPrefered();
        af.printAdmissible();
        af.printPrefered();
        af.clear();
        //test4
        System.out.println("Test 4");
        for (int i = 1; i<5; ++i){
            af.addArgument(new Argument(i));
        }

        af.addContradiction(1,2);
        af.addContradiction(2,3);
        af.addContradiction(3,4);
        af.addContradiction(4,1);
        af.constructAdmissible();
        af.constructPrefered();
        af.printAdmissible();
        af.printPrefered();
        af.clear();

        //test5
        System.out.println("Test 5");
        for (int i = 1; i<8; ++i){
            af.addArgument(new Argument(i));
        }

        af.addContradiction(1,2);
        af.addContradiction(2,3);
        af.addContradiction(3,4);
        af.addContradiction(4,3);
        af.addContradiction(4,5);
        af.addContradiction(5,6);
        af.addContradiction(6,7);
        af.addContradiction(7,5);
        af.constructAdmissible();
        af.constructPrefered();
        af.printAdmissible();
        af.printPrefered();
        af.clear();

    }
}
