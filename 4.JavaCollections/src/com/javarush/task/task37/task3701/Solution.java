package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* 
Круговой итератор
*/

public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T> {

        private final Supplier<Iterator<T>> nativeIteratorSupplier;
        private Iterator<T> nativeIterator;

        public RoundIterator() {
            this.nativeIteratorSupplier = Solution.super::iterator;
        }


        @Override
        public boolean hasNext() {
            if (nativeIterator == null || !nativeIterator.hasNext()) {
                nativeIterator = nativeIteratorSupplier.get();
            }
            return nativeIterator.hasNext();
        }

        @Override
        public T next() {
            return nativeIterator.next();
        }

        @Override
        public void remove() {
            nativeIterator.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
        }


    }
}
