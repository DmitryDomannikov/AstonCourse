package task2.NewHash;

public class MyLinkedList<T> {
    private Node<T> head;
    private int size;

    public MyLinkedList() {
    }
    void add(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) { //if its first
            head = node;
            size++;
            return;
        }
        Node<T> lastNode = getLast();
        lastNode.next = node;
        size++;
    }
    T get(int index) {
        if (head == null) {
            throw new RuntimeException("АШИПКА СПИСОК ПУСТОЙ");
        }
        if (size <= index) {
            throw new RuntimeException("АШИПКА СПИСОК МЕНЬШЕ");
        }
        Node<T> node = head;
        int i = 0;
        while (i != index) {
            node = node.next;
            i++;
        }
        return node.data;
    }

    boolean remove(int index) {    //remove
        if (head == null) {
            throw new RuntimeException("АШИПКА СПИСОК ПУСТОЙ");
        }
        if (size <= index) {
            throw new RuntimeException("АШИПКА СПИСОК МЕНЬШЕ");
        }
        Node<T> node = head;
        int i = 0;
        while (i != index -1) {
            node = node.next;
            i++;
        }
        node.next = node.next.next;
        i--;
        return true;
    }
    boolean addAll(MyLinkedList<T> list) {    //addAll
        Node<T> last = getLast();
        last.next = list.head;
        size += list.size;
        return true;
    }
    private Node<T> getLast() {   //getLast
        Node<T> last = head;
        while (last.next != null) {
            last = last.next;
        }
        return last;
    }
    int getSize() {
        return size;
    }
    @Override
    public String toString() {
        return "MyLinkedList{" +
                "head=" + head +
                '}';
    }
}
