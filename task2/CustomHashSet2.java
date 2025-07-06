package task2;

public class CustomHashSet2<E> {
    // Внутренний класс для узла связного списка
    private transient Node<E>[] table;
    private static class Node<E> {
        E value; // Значение, хранимое в узле
        Node<E> next; // Ссылка на следующий узел

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private static final int INITIAL_CAPACITY = 16; // Начальная ёмкость
    private static final double LOAD_FACTOR = 0.75; // Коэффициент загрузки
    private Node<E>[] buckets; // Массив бакетов
    private int size; // Количество элементов

    // Конструктор
    @SuppressWarnings("unchecked")
    public CustomHashSet2() {
        buckets = new Node[INITIAL_CAPACITY]; // Создание массива бакетов
        size = 0; // Инициализация размера
    }

    // Получение индекса бакета на основе хэш-кода
    private int getBucketIndex(E element) {
        if (element == null) return 0; // null помещается в бакет 0
        return Math.abs(element.hashCode() % buckets.length); // Индекс бакета
    }

    // Добавление элемента
    public boolean add(E element) {
        int index = getBucketIndex(element);
        Node<E> current = buckets[index];

        // Проверка на дубликат
        while (current != null) {
            if ((element == null && current.value == null) ||
                    (element != null && element.equals(current.value))) {
                return false; // Элемент уже существует
            }
            current = current.next;
        }

        // Добавление нового узла в начало списка
        buckets[index] = new Node<>(element, buckets[index]);
        size++;

        // Проверка необходимости расширения
        if ((double) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
        return true;
    }

    // Удаление элемента
    public boolean remove(E element) {
        int index = getBucketIndex(element);
        Node<E> current = buckets[index];
        Node<E> prev = null;

        // Поиск элемента
        while (current != null) {
            if ((element == null && current.value == null) ||
                    (element != null && element.equals(current.value))) {
                if (prev == null) {
                    buckets[index] = current.next; // Удаление из начала списка
                } else {
                    prev.next = current.next; // Удаление из середины/конца
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false; // Элемент не найден
    }

    // Расширение хэш-таблицы
    // Получение размера

    public int size() {
        return size;
    }
    // Пример использования

    public static void main(java.lang.String[] args) {
        CustomHashSet2<java.lang.String> set = new CustomHashSet2<>();
        System.out.println("Добавлено 'Яблоко': " + set.add("Яблоко")); // true
        System.out.println("Добавлено 'Груша': " + set.add("Груша")); // true
        System.out.println("Добавлено 'Яблоко': " + set.add("Яблоко")); // false
        System.out.println("Размер: " + set.size()); // 2
        System.out.println("Удалено 'Груша': " + set.remove("Груша")); // true
        System.out.println("Размер после удаления: " + set.size()); // 1
        System.out.println("Удалено 'Банан': " + set.remove("Банан")); // false
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<E>[] oldBuckets = buckets;
        buckets = new Node[oldBuckets.length * 2]; // Удвоение размера
        size = 0;

        // Перехэширование элементов
        for (Node<E> node : oldBuckets) {
            while (node != null) {
                add(node.value); // Повторное добавление элементов
                node = node.next;
            }
        }
    }
}
