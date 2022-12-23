package Argumentation;



import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>Argument class : class to represent an argument</p>
 */
public class Argument implements Comparable<Argument>{
    /**
     * name of the argument
     */
    private String name;
    /**
     * identifier of the argument
     */
    private int identifier;
    /**
     * Set of attacker and set of attack
     */
    private Set<Argument> attacker, attack; //in = attacker; out = attack

    /**
     * <p>Constructor</p>
     * @param identifier the argument id
     * @param in the argument's attackers
     * @param out the argument's attacks
     * @param name the name of the argument
     */
    public Argument(int identifier, Set<Argument> in, Set<Argument> out, String name) {
        this.identifier = identifier;
        this.attacker = in;
        this.attack = out;
        this.name = name;
    }

    /**
     * <p>Constructor</p>
     * @param id the argument's id
     * @param string the argument's name
     */
    public Argument(int id, String string){
        this(id, new HashSet<>(), new HashSet<>(), string);
    }

    /**
     * <p>Constructor</p>
     * @param id the argument's id
     */
    public Argument(int id){
        this(id, new HashSet<>(), new HashSet<>(), "" + id);
    }

    /**
     *<p>Empty constructor</p>
     */
    public Argument(){
        this(0, new HashSet<Argument>(), new HashSet<>(), ""+0);
    }

    /**
     * <p>Adds an attack from <strong>this</strong> to <strong>given argument</strong></p>
     * @param a Argument to attack
     */
    public void addAttack(Argument a){

        this.attack.add(a);
    }

    /**
     * <p>Classic to string</p>
     * @return string
     */
    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append(name);
        return st.toString();
    }

    /**
     * <p>Prints the argument</p>
     */
    public void printArgument(){
        System.out.print(toString());
    }

    /**
     * <p>Adds an attacker to <strong>this</strong> from <strong>given argument</strong></p>
     * @param a Add attacker
     */
    public void addAttacker(Argument a){

        this.attacker.add(a);
    }

    /**
     * <p>Gets the argument's name</p>
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * <p>Gets the argument's identifier</p>
     * @return identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     *<p>Gets the attackers set</p>
     * @return attacker argument set
     */
    public Set<Argument> getAttacker() {
        return attacker;
    }

    /**
     * <p>Gets the attacks set</p>
     * @return attack argument set
     */
    public Set<Argument> getAttack() {
        return attack;
    }


    /**
     * <p>Checks whether to arguments are the same or not</p>
     * @param o Argument to copare
     * @return true if they are, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return identifier == argument.identifier;
    }

    /**
     * <p>Computes the hash value of this</p>
     * @return the hash value of the Argument
     */
    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    /**
     * <p>Compares two arguments</p>
     * @param o Argument to compare
     * @return +int if larger, -int if smaller, 0 otherwise
     */
    @Override
    public int compareTo(Argument o) {
        //Modern problems require modern solutions
        return this.identifier - o.getIdentifier();
    }
}
