package Argumentation;



import java.util.*;

public class ArgumentationFramework {
    private Map<Argument,Argument> argumentSet;
    private Map<Argument,Argument> solutionSet;

    /**
     * <p>Constructor</p>
     * @param arguments
     * @param solutions
     */
    public ArgumentationFramework(Map<Argument,Argument> arguments, Map<Argument,Argument> solutions) {
        this.argumentSet = arguments;
        this.solutionSet = solutions;
    }

    /**
     * <p>Empty constructor</p>
     */
    public ArgumentationFramework(){
        this(new HashMap<>(), new HashMap<>());
    }

    /**
     *
     * @param a
     */
    public void addArgument(Argument a){
        argumentSet.put(a, a);
    }



    /**
     * <p>
     *     the first parameter (a) attacks the second (b). This method modifies these arguments attacker and attack
     *     attributes so they contain the new values
     * </p>
     * @param a
     * @param b
     */
    public void addContradiction(Argument a, Argument b){
        if(!(argumentSet.containsKey(a) || argumentSet.containsKey(b))) {
            //gestion des exceptions
            System.out.println("Error : arguments not in the set\n");
        } else {
            /*
            Set<Argument> attack = argumentSet.get(a.getIdentifier()).getAttack();
            Set<Argument> attacker = argumentSet.get(b.getIdentifier()).getAttacker();
            attack.add(b);
            attacker.add(a);
            argumentSet.put(a.getIdentifier(),attack);
            */
            argumentSet.get(a).addAttack(b);
            argumentSet.get(b).addAttacker(a);
        }
    }

    /**
     * <p>This method verifies if a proposed solution contains contradictions</p>
     * @return true if no contradiction exists, false otherwise
     */
    public boolean existsContradiction(){
        boolean ret = true;
        for(Argument arg : solutionSet.keySet()){
            for (Argument attacker : arg.getAttacker()){
                if (solutionSet.containsKey(attacker)){
                    ret = !ret;
                    System.out.println("There is a contradiction between Argument A" + attacker.getIdentifier() + "and Argument A" + arg.getIdentifier());
                    return ret;
                }
            }
        }
        return ret;
    }

    /***
     * <p>This method verifies if a defense relation exists for every attacked argument in the proposed solution</p>
     * @return true if a defense exists, false otherwise
     */
    public boolean existsDefense(){
        boolean ret = true;
        for (Argument arg : solutionSet.keySet()){
            for (Argument attacker : arg.getAttacker()){
                for(Argument defense : solutionSet.keySet()){
                    if (!attacker.getAttacker().contains(defense)){
                        ret = !ret;
                        System.out.println("Argument A" + arg.getIdentifier() + "is not defended from Argument A" + attacker.getIdentifier());
                        return ret;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * <p>Adds a proposed solution to the solution set</p>
     * @param a
     */
    public void addSolution(Argument a){
        solutionSet.put(a,a);
    }

    /**
     * <p>Removes a given argument from the user proposed solution</p>
     * @param a
     */
    public void removeSolution(Argument a){
        solutionSet.remove(a);
    }

    public void removeSolution(){
        System.out.println("Enter the contradiction in the form A1");
        Scanner scanner = new Scanner(System.in);
        String answer;
        answer = scanner.nextLine();
        String[] args = answer.split("A");
        int first = Integer.parseInt(args[1]);
        Argument a = new Argument(first);
        removeSolution(a);
        //scanner.close();
    }

    /**
     * <p>Reads a user proposed contradiction between two arguments, and add a contradiction between said arguments</p>
     */
    public void readContradiction(){
        System.out.println("Enter the contradiction in the form A1A2");
        Scanner scanner = new Scanner(System.in);
        String answer;
        answer = scanner.nextLine();
        String[] args = answer.split("A");
        //System.out.println(args[1] + args[2]);
        int first = Integer.parseInt(args[1]);
        int second = Integer.parseInt(args[2]);
        Argument a = new Argument(first);
        Argument b = new Argument(second);
        addContradiction(a,b);
        //scanner.close();
    }

    /**
     * <p>Reads a user proposed solution from System.in, and adds it to the set of solutions</p>
     */
    public void readSolution(){
        System.out.println("Enter argument in the form A1");
        Scanner scanner = new Scanner(System.in);
        String answer;
        answer = scanner.nextLine();
        String[] args = answer.split("A");
        int first = Integer.parseInt(args[1]);
        Argument a = new Argument(first);
        addSolution(a);
        //scanner.close();
    }

    /**
     * <p>This method verifies if a proposed solution is admissible or not</p>
     * @return true if it is admissible, false otherwise
     */
    public boolean verifySolution(){
       boolean existsDef = true, existsContradict = true;
       existsContradict = existsContradiction();
       if (existsContradict)
           existsDef = existsDefense();
       return existsContradict && existsDef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArgumentationFramework that = (ArgumentationFramework) o;
        return Objects.equals(argumentSet, that.argumentSet) && Objects.equals(solutionSet, that.solutionSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(argumentSet, solutionSet);
    }
}
