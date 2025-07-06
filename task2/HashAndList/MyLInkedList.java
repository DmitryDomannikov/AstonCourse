package task2.HashAndList;

import java.util.Arrays;
public class MyLInkedList {
    private Node head;
    private int size;
    public void add(int value){  // Добавление элемента
        if (head == null){
            this.head = new Node(value);
        } else {
            Node temp = head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(new Node(value));
        }
        size++;
    }
    public int get(int index) { // Получение элемента по индексу
        int curentIndex = 0;
        Node temp = head;

        while (temp != null) {
            if (curentIndex == index) {
                return temp.getValue();
            } else {
                temp = temp.getNext();
                curentIndex++;
            }
        }
        throw new IllegalArgumentException();
    }
    public void remove(int index){// Удаление элемента по индексу
        if (index == 0) {
            head = head.getNext();
            size --;
            return;
        }
        int currentIndex = 0;
        Node temp = head;
        while (temp != null){
            if (currentIndex == index-1) {
                temp.setNext(temp.getNext().getNext());
                size--;
                return;
            } else {
                temp = temp.getNext();
                currentIndex++;
            }
        }
    }
    public void addAll(MyLInkedList other) { // Добавляем другой лист
        for (int i = 0; i < other.size(); i++) {
            add(other.get(i));
        }
    }

    public int size() {
        return size;
    }

    public String toString(){
        int[] result = new int [size];
        int idx = 0;
        Node temp = head;

        while (temp != null){
            result[idx++] = temp.getValue();
            temp = temp.getNext();
        }
        return Arrays.toString(result);
    }
    private static class Node {
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
  }
}
