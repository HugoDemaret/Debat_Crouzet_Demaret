package Argumentation;



import UserInput.InputReader;

import java.util.*;

public class ArgumentationFramework {
    private Map<Integer, Argument> argumentSet;
    private Map<Integer,Argument> solutionSet;
    private Set<ArgumentationSet> admissibleSets;
    private Set<ArgumentationSet> preferedSets;
    private Map<String, Integer> dictionnary; //name of the argument to its identifier



    /**
     * <p>Constructor</p>
     * @param arguments
     * @param solutions
     */
    public ArgumentationFramework(
            Map<Integer,Argument> arguments,
            Map<Integer,Argument> solutions,
            Set<ArgumentationSet> preferedSet,
            Set<ArgumentationSet> admissibleSet,
            Map<String,Integer> dictionnary
    ) {
        this.argumentSet = arguments;
        this.solutionSet = solutions;
        this.preferedSets = preferedSet;
        this.admissibleSets = admissibleSet;
        this.dictionnary = dictionnary;
    }



    /**
     * <p>Empty constructor</p>
     */
    public ArgumentationFramework(){
        this(new HashMap<>(), new HashMap<>(), new TreeSet<>(), new TreeSet<>(), new HashMap<>());
    }

    /**
     *
     * @param a
     */
    public void addArgument(Argument a){
        argumentSet.put(a.getIdentifier(), a);
        dictionnary.put(a.getName(), a.getIdentifier());
    }
    /**
     * <p>
     *     the first parameter (a) attacks the second (b). This method modifies these arguments attacker and attack
     *     attributes so they contain the new values
     * </p>
     * @param a (int)
     * @param b (int)
     */
    public void addContradiction(int a, int b){
        if(!(argumentSet.containsKey(a) && argumentSet.containsKey(b))) {
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
            argumentSet.get(a).addAttack(argumentSet.get(b));
            argumentSet.get(b).addAttacker(argumentSet.get(a));
        }
    }
    /**
     * <p>
     *     the first parameter (a) attacks the second (b). This method modifies these arguments attacker and attack
     *     attributes so they contain the new values
     * </p>
     * @param a (String)
     * @param b (String)
     */
    public void addContradiction(String a, String b){
        int A, B;
        A = dictionnary.get(a);
        B = dictionnary.get(b);
        addContradiction(A,B);
    }

    /**
     * <p>
     *     the first parameter (a) attacks the second (b). This method modifies these arguments attacker and attack
     *     attributes so they contain the new values
     * </p>
     * @param a (Argument)
     * @param b (Argument)
     */
    public void addContradiction(Argument a, Argument b){
        if(!(argumentSet.containsKey(a.getIdentifier()) && argumentSet.containsKey(b.getIdentifier()))) {
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
            argumentSet.get(a.getIdentifier()).addAttack(b);
            argumentSet.get(b.getIdentifier()).addAttacker(a);
        }
    }

    /**
     * <p>This method verifies if a proposed solution contains contradictions</p>
     * NOTE TO SELF : CAN BE OPTIMIZED WITH DFS/BFS
     * @return true if no contradiction exists, false otherwise
     */
    public boolean existsContradiction(boolean mode){
        boolean ret = true;
        for(Integer arg : solutionSet.keySet()){
            for (Argument attacker : argumentSet.get(arg).getAttacker()){
                if (solutionSet.containsKey(attacker.getIdentifier())){
                    ret = !ret;
                    if (mode)
                        System.out.println("There is a contradiction between Argument A" + attacker.getIdentifier() + " and Argument A" + arg);
                    return ret;
                }
            }
        }
        return ret;
    }



    /***
     * <p>This method verifies if a defense relation exists for every attacked argument in the proposed solution</p>
     * NOTE TO SELF : can be optimized with a BFS/DFS
     * @return true if a defense exists, false otherwise
     */
    public boolean existsDefense(boolean mode){
        boolean ret = true;
        int sum;

        for (Integer arg: solutionSet.keySet()){
            for (Argument attacker : argumentSet.get(arg).getAttacker()){
                sum = 0;
                for(Integer defense : solutionSet.keySet()){
                    //solutionSet.get(defense).getAttack().contains(attacker.getIdentifier()
                    if (attacker.getAttacker().contains(solutionSet.get(defense))){
                        //ret =!ret;
                        sum++;
                        break;
                    }
                }
                if (sum == 0){
                    ret = !ret;
                    if (mode)
                        System.out.println("Argument A" + arg + " is not defended from Argument A" + attacker.getIdentifier());
                    return ret;
                }
            }
        }

        return ret;
    }

    public Set<ArgumentationSet> getAdmissibleSets() {
        return admissibleSets;
    }

    public Set<ArgumentationSet> getPreferedSets() {
        return preferedSets;
    }

    /**
     * <p>Adds a proposed solution to the solution set</p>
     * @param a
     */
    public void addSolution(Argument a){
        solutionSet.put(a.getIdentifier(),a);
    }

    /**
     * <p>Removes a given argument from the user proposed solution</p>
     * @param a
     */
    public void removeSolution(Argument a){
        solutionSet.remove(a.getIdentifier());
    }

    /**
     * <p>Reads an argument from stdin, removes it from solution</p>
     */
    public void removeSolution(){
        System.out.println("Enter the contradiction in the form A1");
        String answer;
        answer = InputReader.scanner.nextLine();
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
        String answer;
        answer = InputReader.scanner.nextLine();
        String[] args = answer.split("A");
        //System.out.println(args[1] + args[2]);
        int first = Integer.parseInt(args[1]);
        int second = Integer.parseInt(args[2]);
        Argument a = argumentSet.get(first);
        Argument b = argumentSet.get(second);
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
        Argument a = argumentSet.get(first);
        addSolution(a);
        //scanner.close();
    }

    /**
     * <p>This method verifies if a proposed solution is admissible or not</p>
     * @return true if it is admissible, false otherwise
     */
    public boolean verifySolution(boolean mode){
       boolean existsDef = true, existsContradict = true;
       existsContradict = existsContradiction(mode);
       if (existsContradict)
           existsDef = existsDefense(mode);
       return existsContradict && existsDef;
    }


    /**
     * <p>Checks if a set of arguments contains a contradiction</p>
     * @param arguments
     * @return true if the set doesn't contain any contradiction, false otherwise
     */
    public boolean isContradicted(ArgumentationSet arguments){
        boolean ret = true;
        for (Argument argument : arguments){
            for (Argument attacker : argument.getAttacker()){
                if (arguments.contains(attacker))
                    return !ret;
            }
        }
        return ret;
    }

    /**
     * <p>Checks if a set of arguments defends itself</p>
     * @param arguments
     * @return true if the set defends itself, false otherwise
     */
    public boolean isDefended(ArgumentationSet arguments){
        boolean ret = true;
        int sum;
        for (Argument argument : arguments){
            for (Argument attacker : argument.getAttacker()){
                sum = 0;
                for(Argument defense : arguments){
                    //solutionSet.get(defense).getAttack().contains(attacker.getIdentifier()
                    if (attacker.getAttacker().contains(defense)){
                        //ret =!ret;
                        sum++;
                        break;
                    }
                }
                if (sum == 0)
                    return !ret;
            }
        }
        return ret;
    }

    /**
     * <p>Clears all the attributes (resets them)</p>
     */
    public void clear(){
        solutionSet.clear();
        admissibleSets.clear();
        argumentSet.clear();
        preferedSets.clear();
    }

    /**
     *<p>Checks if a given set of argument is admissible or not</p>
     * @param arguments
     * @return true if the set is admissible, false otherwise
     */
    public boolean isAdmissible(ArgumentationSet arguments){
        boolean existsDef = true, existsContradict = true;
        existsContradict = isContradicted(arguments);
        if (existsContradict)
            existsDef = isDefended(arguments);
        return existsContradict && existsDef;
    }




    /**
     *<p>Constructs the set of (all) admissible sets of the AF</p>
     */
    //One could optimise this part. Though it is mandatory to test 2^n sets, we can infer that if a set {a,b} contradicts itself, then {a,b,c} also does
    //with that in our head, one could memoise (cache) the previously tested set that contains (at least) a contradiction, and test accordingly. This would reduce the
    //time complexity by -O(n^2). Verifying if a set is a subset is in O(n), but with some tricks (hash), one could do it in O(1) in average (O(n) worst case, still)
    public void constructAdmissible(){
        admissibleSets.clear();
        List<Argument> list = new ArrayList<Argument>(argumentSet.values());
        int n = list.size();
        preferedSets.add(new ArgumentationSet());
        for (int i = 0; i< (1<<n); ++i){
            ArgumentationSet arguments = new ArgumentationSet();
            for (int j = 0; j<n;++j){
                if (((i>>j) &1)==1) arguments.add(list.get(j));
            }
            if (isAdmissible(arguments))
                admissibleSets.add(arguments);
        }
    }

    /**
     *<p>Constructs the set of (all) prefered sets of the AF</p>
     */
    public void constructPrefered(){
        preferedSets.clear();
        for (ArgumentationSet subSet : admissibleSets){
            boolean isSubSet = true;
            if (!subSet.isEmpty()) {
                for (ArgumentationSet argSet : admissibleSets) {
                    if (subSet.isSmallerSubset(argSet))
                        isSubSet = false;
                }
                if (isSubSet) preferedSets.add(subSet);
            }
        }
    }

    /**
     * <p>Prints all the admissible sets</p>
     */
    public void printAdmissible(){
        StringBuilder st = new StringBuilder();
        st.append("Admissible sets : \n");

        for (ArgumentationSet argset : admissibleSets){
            st.append(argset.toString());
            st.append("\n");
        }

        System.out.println(st.toString());
    }

    /**
     * <p>Prints all the prefered sets</p>
     */
    public void printPrefered(){
        StringBuilder st = new StringBuilder();
        st.append("Prefered sets : \n");
        for (ArgumentationSet argset : preferedSets){
            st.append(argset.toString());
            st.append("\n");
        }
        System.out.println(st.toString());
    }

    /**
     * <p>Checks whether two AF are the same or not</p>
     * @param o
     * @return true if equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArgumentationFramework that = (ArgumentationFramework) o;
        return Objects.equals(argumentSet, that.argumentSet) && Objects.equals(solutionSet, that.solutionSet);
    }


    /**
     *<p>Computes the hash value of the AF</p>
     * @return hash value of the AF
     */
    @Override
    public int hashCode() {
        return Objects.hash(argumentSet, solutionSet);
    }
}
