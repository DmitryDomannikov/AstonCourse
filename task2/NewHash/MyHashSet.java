package task2.NewHash;


public class MyHashSet<T> {
    private Node<T>[] buckets;
    private int size;
    private int CAPACITY = 16;

    private static final float LOAD_FACTOR = 0.75f;

    public MyHashSet() {                                          //Конструктор
        buckets = new Node[CAPACITY];                            // Создание массива бакетов
        size = 0;                                               // Инициализация размера
    }
    private int getIndex(T value){                             //Получение индекса бакета
        if (value == null) return 0;                          // null помещается в бакет 0
        return Math.abs(value.hashCode() % buckets.length);  //  индекса бакета
    }

    public boolean add(T value) {                          // Добавление элементов
        int index = getIndex(value);
        Node<T> current = buckets[index];

        while (current != null) {                          //Проверка на дубликат
            if (value == null && current.data== null ||
            value != null && value.equals(current.data)){
                return false;
            }
            current = current.next;
        }
        buckets[index] = new Node<>(value, buckets[index]);    // Добавление нового узла
            size++;

        if ((double) size / buckets.length > LOAD_FACTOR) {   // Проверка необходимости расширения
            resize();
        }
        return true;
    }
    public boolean remove(T value){                           // Удаление элемента
        int index = getIndex(value);
        Node<T> current = buckets[index];
        Node<T> prev = null;

        while (current != null) {
            if (value == null && current.data == null ||
                    value != null && value.equals(current.data)) {
                if (prev == null) {
                    buckets[index] = current.next;                   // Удаление из начала списка
                } else {
                    prev.next = current.next;                        // Удаление из середины/конца
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;                                                // Элемент не найден
    }
    private void resize(){                                           // Расширение хэш-таблицы
        Node<T>[] odlBuckets = buckets;
        buckets = new Node[odlBuckets.length*2];
        size =0;

        for (Node<T> node : odlBuckets){
            while (node != null){
                add(node.data);
                node = node.next;
            }
        }
    }
    public int size() {
        return size;
    }
}

