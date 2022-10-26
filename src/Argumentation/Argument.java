package Argumentation;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Argument {
    private int identifier;
    private Set<Argument> attacker, attack; //in = attacker; out = attack

    public Argument(Set<Argument> in, Set<Argument> out) {
        this.attacker = in;
        this.attack = out;
    }

    public Argument(){
        this(new TreeSet<Argument>(), new TreeSet<Argument>());
    }

    public void addAttack(Argument a){
        this.attack.add(a);
    }
    public void addAttacker(Argument a){
        this.attacker.add(a);
    }

    public int getIdentifier() {
        return identifier;
    }

    public Set<Argument> getAttacker() {
        return attacker;
    }

    public Set<Argument> getAttack() {
        return attack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return Objects.equals(attacker, argument.attacker) && Objects.equals(attack, argument.attack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attacker, attack);
    }
}
