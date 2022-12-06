package Argumentation;

import java.util.TreeSet;

/**
 * <p>This class extends TreeSet in order to be easier to use, but also faster.
 * It allows to compare two TreeSets of arguments between one another
 * It implements a hash value
 * </p>
 */
public class ArgumentationSet extends TreeSet<Argument> implements Comparable<ArgumentationSet>{


    private int hash;

    /**
     * <p>Constructor given a hashvalue</p>
     * @param hash
     */
    public ArgumentationSet(int hash){
        this.hash = hash;
    }

    /**
     * <p>Default constructor, sets the hash value to 0</p>
     */
    public ArgumentationSet(){
        this(0);
    }

    /**
     * <p>The hash value is the sum of all argument's identifier</p>
     * @return int : the hash value
     */
    public int getHash(){
        return hash;
    }

    /**
     * <p>Adds an argument to ArgumentSet. Increments the hash by the argument's identifie</p>
     * @param argument
     * @return
     */
    @Override
    public boolean add(Argument argument) {
        hash+=argument.getIdentifier();
        return super.add(argument);
    }

    /**
     * <p>Allows for comparison between two TreeSets of Arguments</p>
     * @param o the argument to be compared to this
     * @return -1 if this is smaller, 0 if equals, 1 if larger
     */
    @Override
    public int compareTo(ArgumentationSet o) {
        if (this.size() < o.size())
            return -1;
        else if (this.size() > o.size())
            return 1;
        if (this.hash < o.hash)
            return -1;
        else if (this.hash > o.hash)
            return 1;
        else return 0;
    }
}