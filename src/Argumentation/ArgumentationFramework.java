package Argumentation;

import com.sun.source.tree.Tree;

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
        this(new TreeMap<>(), new TreeMap<>());
    }

    /**
     *
     * @param a
     */
    public void addArgument(Argument a){
        argumentSet.put(a,a);
    }

    public void readContradiction(){
        Scanner sc = new Scanner(System.in);
        String answer;
        answer = sc.nextLine();
        String[] args = answer.split("A");
        int first = Integer.parseInt(args[0]);
        int second = Integer.parseInt(args[1]);
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
            Argument tmp1,tmp2;
            tmp1 = argumentSet.get(a);
            tmp1.addAttack(b);
            tmp2 = argumentSet.get(b);
            tmp2.addAttacker(a);
            argumentSet.put(tmp1,tmp1);
            argumentSet.put(tmp2,tmp2);
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
                        return ret;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * <p>This method verifies if a proposed solution is admissible or not</p>
     * @return true if it is admissible, false otherwise
     */
    public boolean verifySolution(){
       boolean existsDef = true, existsContradict = true;
       existsContradict = existsContradiction();
       if (!existsContradict)
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
