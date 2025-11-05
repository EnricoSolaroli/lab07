package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final T[] elements;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(T[] elements){
        this(elements, new Predicate<T>(){
            public boolean test(T elem){
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(T[] elements, Predicate<T> predicate){
       this.elements = elements;
       this.predicate = predicate;
    }


    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }

    class IteratorImpl implements Iterator<T>{

        private int current = 0;
        private T nextValidElement = null;

        public IteratorImpl() {
            advance(); 
        }

        private void advance(){
            this.nextValidElement = null;
            while(current <  IterableWithPolicyImpl.this.elements.length){
                T currentItem = IterableWithPolicyImpl.this.elements[current];
                current ++;

                if(IterableWithPolicyImpl.this.predicate.test(currentItem)){
                    this.nextValidElement = currentItem;
                    return;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return this.nextValidElement != null;
        }

        @Override
        public T next() { 
            if (!hasNext()) {
                throw new NoSuchElementException("Nessun altro elemento soddisfa il filtro.");
            }
            T itemToReturn = this.nextValidElement;
            advance(); 
            return itemToReturn;
        }
    }
}
