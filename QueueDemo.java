package dsaa;

public class QueueDemo<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> front;
    private Node<T> rear;

    public void enqueue(T item) {
        Node<T> node = new Node<>(item);
        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
    }

    public T dequeue() {
        if (front == null) return null;
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        return data;
    }

    public T peek() {
        return (front == null) ? null : front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
