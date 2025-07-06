package task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Pizza {
    private final String size; // Размер пиццы
    private final String dough; // Тип теста
    private final String sauce; // Соус
    private final List<String> toppings; // Начинка
    private Pizza(PizzaBuilder builder) {    // Приватный конструктор для создания через Builder
        this.size = builder.size;
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.toppings = new ArrayList<>(builder.toppings); // Копия для неизменяемости
    }
    @Override
    public String toString() {
        return "Пицца [размер=" + size + ", тесто=" + dough + ", соус=" + sauce + ", начинки=" + toppings + "]";
    }
    public static class PizzaBuilder {
        private final String size; // Обязательное поле
        private final String dough; // Обязательное поле
        private String sauce; // Необязательное
        private List<String> toppings = new ArrayList<>(); // Необязательное
        public PizzaBuilder(String size, String dough) {
            if (!Arrays.asList("Маленькая", "Средняя", "Большая").contains(size)) {// Валидация размера
                throw new IllegalArgumentException("Размер должен быть маленькая, средняя или большая");
            }
            if (!Arrays.asList("тонкое", "толстое").contains(dough)) {// Валидация теста
                throw new IllegalArgumentException("тесто должно быть тонкое или толстое");
            }
            this.size = size;
            this.dough = dough;
        }
        public PizzaBuilder setSauce(String sauce) {     // Добавление соуса

            if (sauce != null && !Arrays.asList("Томатный", "сливочный").contains(sauce)) { // Валидация соуса
                throw new IllegalArgumentException("соус должен быть томатным, сливочным или без него");
            }
            this.sauce = sauce;
            return this;
        }
        public PizzaBuilder addTopping(String topping) {   // Добавление начинки
            if (topping == null || topping.isEmpty()) {
                throw new IllegalArgumentException("начинка не может быть пустой");
            }
            this.toppings.add(topping);
            return this;
        }

        public PizzaBuilder margherita() {   // Пресет для пиццы Margherita
            this.sauce = "Томатный";
            this.toppings.clear(); // Очищаем начинку, если была
            this.toppings.addAll(Arrays.asList("Сыр", "Базелик"));
            return this;
        }
        public Pizza build() {
            return new Pizza(this);
        }
    }
}
public class BuilderExample {
    public static void main(String[] args) {
        // Создание пиццы с полной конфигурацией
        Pizza pizza1 = new Pizza.PizzaBuilder("Большая", "тонкое")
                .setSauce("Томатный")
                .addTopping("Сыр")
                .addTopping("Пеперони")
                .build();
        System.out.println("Пицца 1: " + pizza1);

        // Создание пиццы Margherita с пресетом
        Pizza pizza2 = new Pizza.PizzaBuilder("Средняя", "толстое")
                .margherita()
                .build();
        System.out.println("Пицца 2 (Маргарита): " + pizza2);

        // Создание пиццы с минимальными параметрами
        Pizza pizza3 = new Pizza.PizzaBuilder("Маленькая", "толстое")
                .build();
        System.out.println("Пицца 3: " + pizza3);

        // Пример с ошибкой валидации
        try {
            Pizza pizza4 = new Pizza.PizzaBuilder("ExtraLarge", "Thin").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}