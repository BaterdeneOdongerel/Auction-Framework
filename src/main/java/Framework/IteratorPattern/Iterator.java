package Framework.IteratorPattern;

public interface Iterator {

    // indicates whether there are more elements to
    // iterate over
    boolean hasNext();

    // returns the next element
    Object next();
}
