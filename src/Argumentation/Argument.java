package Argumentation;

import com.sun.source.tree.Tree;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Argument {
    private int identifier;
    private Set<Argument> attacker, attack; //in = attacker; out = attack

    public Argument(int identifier, Set<Argument> in, Set<Argument> out) {
        this.identifier = identifier;
        this.attacker = in;
        this.attack = out;
    }

    public Argument(int id){
        this(id, new TreeSet<>(), new TreeSet<>());
    }

    public Argument(){
        this(0, new TreeSet<Argument>(), new TreeSet<Argument>());
    }

    /**
     * <p>Add an attack from <strong>this</strong> to <strong>given argument</strong></p>
     * @param a
     */
    public void addAttack(Argument a){

        this.attack.add(a);
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
        return identifier == argument.identifier && Objects.equals(attacker, argument.attacker) && Objects.equals(attack, argument.attack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, attacker, attack);
    }
}
