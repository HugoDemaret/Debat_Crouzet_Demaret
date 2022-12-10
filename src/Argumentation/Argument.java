package Argumentation;



import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Argument implements Comparable<Argument>{
    private String name;
    private int identifier;
    private Set<Argument> attacker, attack; //in = attacker; out = attack

    public Argument(int identifier, Set<Argument> in, Set<Argument> out, String name) {
        this.identifier = identifier;
        this.attacker = in;
        this.attack = out;
        this.name = name;
    }

    public Argument(int id, String string){
        this(id, new HashSet<>(), new HashSet<>(), string);
    }

    public Argument(int id){
        this(id, new HashSet<>(), new HashSet<>(), "" + id);
    }

    public Argument(){
        this(0, new HashSet<Argument>(), new HashSet<>(), ""+0);
    }

    /**
     * <p>Add an attack from <strong>this</strong> to <strong>given argument</strong></p>
     * @param a
     */
    public void addAttack(Argument a){

        this.attack.add(a);
    }

    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append(name);
        return st.toString();
    }

    public void printArgument(){
        System.out.print(toString());
    }

    /**
     * <p>Add an attacker to <strong>this</strong> from <strong>given argument</strong></p>
     * @param a
     */
    public void addAttacker(Argument a){

        this.attacker.add(a);
    }


    /**
     * @return identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     *
     * @return attacker argument set
     */
    public Set<Argument> getAttacker() {
        return attacker;
    }

    /**
     *
     * @return attack argument set
     */
    public Set<Argument> getAttack() {
        return attack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return identifier == argument.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    /**
     * <p></p>
     * @param o
     * @return
     */
    @Override
    public int compareTo(Argument o) {
        //Modern problems require modern solutions
        return this.identifier - o.getIdentifier();
    }
}
