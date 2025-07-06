package task2;

import java.util.Arrays;

public class CustomArrayList {
    private static final int INITIAL_CAPACITY = 10;
    private String[] array;
    private int size;
    // Конструктор
    public CustomArrayList() {
        array = new String[INITIAL_CAPACITY];
        size = 0;
    }
    // Получение размера
    public int size() {
        return size;
    }
    // Добавление элемента в конец
    public void add(String element) {
        if (size == array.length) {
            resize();
        }
        array[size] = element;
        size++;
    }
    // Получение элемента по индексу
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }
    // Удаление элемента по индексу
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        String removed = array[index];
        // Сдвиг элементов влево
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return removed;
    }
    // Добавление всех элементов из другого CustomArrayList
    public void addAll(CustomArrayList other) {
        for (int i = 0; i < other.size(); i++) {
            add(other.get(i));
        }
    }
    // Расширение массива
    private void resize() {
        String[] newArray = new String[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}