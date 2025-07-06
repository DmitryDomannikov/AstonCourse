package task2;
import java.util.LinkedList;
public class CustomHashSet {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private LinkedList<String>[] fruit;
    private int size;
    // Конструктор
    @SuppressWarnings("unchecked")
    public CustomHashSet() {
        fruit = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            fruit[i] = new LinkedList<>();
        }
        size = 0;
    }
    // Получение индекса бакета на основе хэш-кода
    private int getBucketIndex(String element) {
        if (element == null) return 0;
        return Math.abs(element.hashCode() % fruit.length);
    }
    // Добавление элемента
    public boolean add(String element) {
        int index = getBucketIndex(element);
        LinkedList<String> bucket = fruit[index];
        // Проверка на дубликат
        if (!bucket.contains(element)) {
            bucket.add(element);
            size++;
            // Проверка необходимости расширения
            if ((double) size / fruit.length > LOAD_FACTOR) {
                resize();
            }
            return true;
        }
        return false;
    }
    // Удаление элемента
    public boolean remove(String element) {
        int index = getBucketIndex(element);
        LinkedList<String> bucket = fruit[index];
        if (bucket.remove(element)) {
            size--;
            return true;
        }
        return false;
    }
    // Расширение хэш-таблицы
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<String>[] oldBuckets = fruit;
        fruit = new LinkedList[oldBuckets.length * 2];
        for (int i = 0; i < fruit.length; i++) {
            fruit[i] = new LinkedList<>();
        }
        size = 0;
        // Перехэширование элементов
        for (LinkedList<String> bucket : oldBuckets) {
            for (String element : bucket) {
                add(element);
            }
        }
    }
}