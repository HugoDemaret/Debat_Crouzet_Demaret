package Argumentation;

import com.sun.source.tree.Tree;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ArgumentationFramework {
    private Map<Argument,Argument> argumentSet;
    private Map<Argument,Argument> solutionSet;

    public ArgumentationFramework(Map<Argument,Argument> arguments, Map<Argument,Argument> solutions) {
        this.argumentSet = arguments;
        this.solutionSet = solutions;
    }

    public ArgumentationFramework(){
        this(new TreeMap<>(), new TreeMap<>());
    }

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


    public boolean verifySolution(){
       boolean existsDef = true, existsContradict = true;
       existsContradict = existsContradiction();
       if (!existsContradict)
           existsDef = existsDefense();
       return existsContradict && existsDef;
    }

}
