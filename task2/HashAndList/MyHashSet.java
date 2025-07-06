package task2.HashAndList;

public class MyHashSet<E> {
    private Node<E>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    // Внутренний класс для узла
    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    // Конструктор
    @SuppressWarnings("unchecked")
    public MyHashSet() {
        table = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // Метод для вычисления индекса в table на основе хэша
    private int getIndex(Object element) {
        int hash = element == null ? 0 : element.hashCode();
        return Math.abs(hash % table.length);
    }

    // Метод добавления элемента
    public boolean add(E element) {
        // Проверка необходимости расширения table
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(element);

        // Если бакет пустой, создаем новый узел
        if (table[index] == null) {
            table[index] = new Node<>(element, null);
            size++;
            return true;
        }

        // Проверяем, есть ли элемент в цепочке
        Node<E> current = table[index];
        Node<E> prev = null;

        while (current != null) {
            if ((element == null && current.element == null) ||
                    (element != null && element.equals(current.element))) {
                return false; // Элемент уже существует
            }
            prev = current;
            current = current.next;
        }

        // Добавляем новый узел в конец цепочки
        prev.next = new Node<>(element, null);
        size++;
        return true;
    }

    // Метод удаления элемента
    public boolean remove(Object element) {
        int index = getIndex(element);

        Node<E> current = table[index];
        Node<E> prev = null;

        // Поиск элемента в цепочке
        while (current != null) {
            if ((element == null && current.element == null) ||
                    (element != null && element.equals(current.element))) {
                // Удаляем узел
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false; // Элемент не найден
    }

    // Метод для получения текущего размера
    public int size() {
        return size;
    }

    // Метод для расширения table
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<E>[] oldTable = table;
        table = (Node<E>[]) new Node[oldTable.length * 2];
        size = 0;

    }
}

