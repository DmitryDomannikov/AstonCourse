package task2.StudentsAndBooks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Создание коллекции студентов
        List<Student> students = Arrays.asList(
                new Student("Алексей", Arrays.asList(
                        new Book("Война и мир", 1865, 1225),
                        new Book("Преступление и наказание", 1866, 671),
                        new Book("Гарри Поттер", 1997, 400),
                        new Book("Мастер и Маргарита", 1967, 384),
                        new Book("Программирование на Java", 2010, 700)
                )),
                new Student("Мария", Arrays.asList(
                        new Book("Программирование на Java", 2010, 700),
                        new Book("1984", 1949, 328),
                        new Book("Гарри Поттер", 1997, 400),
                        new Book("Скотный двор", 1945, 112),
                        new Book("Книга джунглей", 2005, 250)
                )),
                new Student("Иван", Arrays.asList(
                        new Book("Гарри Поттер", 2001, 400),
                        new Book("Книга джунглей", 2005, 250),
                        new Book("Программирование на Java", 2010, 700),
                        new Book("Сияние", 2015, 447),
                        new Book("Оно", 2017, 1138)
                ))
        );
        Optional<Integer> result = students.stream()
                .peek(System.out::println) // Вывод студентов
                .flatMap(student -> student.getBooks().stream()) // Получение списка книг
                .sorted(Comparator.comparingInt(Book::getPages).reversed()) // Сортировка по убыванию страниц
                .distinct() // Уникальные книги
                .filter(book -> book.getYear() > 2000) // Книги после 2000 года
                .limit(3) // Ограничение до 3 элементов
                .peek(book -> System.out.println("Год выпуска: " + book.getYear())) // Вывод годов выпуска
                .map(Book::getYear)
                .findFirst(); // Возврат Optional<Book>
        System.out.println("Год первой книги в Optional: " + result.orElse(null)); // Вывод результата
    }
}