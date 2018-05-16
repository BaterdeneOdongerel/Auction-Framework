package Framework.IteratorPattern;

import java.util.List;

public class ConcreteIterator<T> implements Aggregate {


    List<T> iList;

    public ConcreteIterator(List<T> iList) {
        this.iList = iList;
    }

    @Override
    public Iterator getIterator() {
        return new ProcessIterator();
    }

    private class ProcessIterator implements Iterator {
        // maintains curr pos of iterator over the array
        int pos = 0;

        @Override
        public boolean hasNext() {
            if (pos >= iList.size())
                return false;
            else
                return true;
        }

        @Override
        public Object next() {
            // return next element in the array and increment pos
            if (hasNext()) {
                T t = iList.get(pos++);
                //iList.add(t);
                //  pos++;
                return t;
            }

            return null;
        }
    }
}
