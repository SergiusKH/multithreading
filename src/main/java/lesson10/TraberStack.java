package lesson10;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Sergius on 23.06.2015.
 */



public class TraberStack<T> {

    private AtomicReference<Node<T>> tail = null;

    public void push(T newElem) {
        Node<T> newTail = new Node<>(newElem, null);
        while (true) {
            Node<T> oldTail = this.tail.get();
            newTail = oldTail;
            if (tail.compareAndSet(oldTail, newTail)) {
                break;
            }
        }
        //this.tail = new Node<T>(newElem, tail);
    }
    public T pop() {
        while (true) {
            Node<T> oldTail = tail.get();
            Node<T> newTail = oldTail.next;
            if (tail.compareAndSet(oldTail, newTail)) {
                return oldTail.value;
            }
        }
    }

    private static class Node<E> {
        final E value;
        Node<E> next;

        private Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
