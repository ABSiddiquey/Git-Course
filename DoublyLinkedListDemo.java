package dsaa;

public class DoublyLinkedListDemo<T> {

    public static class Node<T> {
        public T data;
        public Node<T> prev;
        public Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head;
    private Node<T> tail;

    public void addLast(T item) {
        Node<T> node = new Node<>(item);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public Node<T> getHead() { return head; }
    public Node<T> getTail() { return tail; }
}

