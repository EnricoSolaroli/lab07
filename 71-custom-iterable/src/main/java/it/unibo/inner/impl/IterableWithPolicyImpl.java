package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    final private T[] elements;

    public IterableWithPolicyImpl(T[] elements){
        this.elements = elements; 
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void setIterationPolicy(Predicate filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

   public String toString(){
        return this.elements.toString();
   }

    class IteratorImpl implements Iterator<T>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            return IterableWithPolicyImpl.this.elements.length > this.current;
        }
        @Override
        public T next() { 
            return IterableWithPolicyImpl.this.elements[current++];
        }

    }
    
}
