package task2;
public class Main {
    public static void main(String[] args) {
        // Тестирование CustomArrayList
        CustomArrayList list = new CustomArrayList();
        list.add("Яблоко");
        list.add("Киви");
        list.add("Банан");
        list.add("Груша");
        list.add("Ананас");
        System.out.println("Элемент по индексу 4: " + list.get(4)); // Ананас
        System.out.println("Размер ArrayList: " + list.size()); // 5
        System.out.println("Удаляем элемент по индексу 1: " + list.remove(1)); // Киви
        System.out.println("Размер ArrayList после удаления: " + list.size()); // 4
        // Тестирование addAll
        CustomArrayList anotherList = new CustomArrayList();
        anotherList.add("Апельсин");
        anotherList.add("Манго");
        anotherList.add("Яблоко");
        list.addAll(anotherList);
        System.out.println("После addAll, элемент по индексу 2: " + list.get(2)); // Груша
        System.out.println("Итоговый размер ArrayList: " + list.size()); // 6

    }

}