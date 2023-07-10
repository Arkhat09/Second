
import java.util.AbstractList;
import java.util.List;

public class MyLinkedList<E> extends AbstractList<E> implements List<E> {

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        Node(E element, Node<E> next, Node<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public boolean add(E element) {
        Node<E> newNode = new Node<>(element, null, tail);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    public E get(int index) {
        validateIndex(index);
        Node<E> current = getNode(index);
        return current.element;
    }

    public E remove(int index) {
        validateIndex(index);
        Node<E> current = getNode(index);

        if (current.previous != null) {
            current.previous.next = current.next;
        } else {
            head = current.next;
        }

        if (current.next != null) {
            current.next.previous = current.previous;
        } else {
            tail = current.previous;
        }

        size--;
        return current.element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private Node<E> getNode(int index) {
        if (index < size / 2) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node<E> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
            return current;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("List: " + list);

        int element = list.get(2);
        System.out.println("Element at index 2: " + element);

        int removedElement = list.remove(1);
        System.out.println("Removed element: " + removedElement);
        System.out.println("List after removal: " + list);

        int size = list.size();
        System.out.println("Size of the list: " + size);
    }
}
