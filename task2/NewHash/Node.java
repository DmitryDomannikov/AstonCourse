package task2.NewHash;

import java.util.Objects;

class Node<T> {
    T data;
    Node<T> next;
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    public Node() {
    }
    public Node(T data) {
        this.data = data;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(data, node.data) && Objects.equals(next, node.next);
    }
    @Override
    public int hashCode() {
        return Objects.hash(data, next);
    }
    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}

