package task2.StudentsAndBooks;

public class Book {
    private String title;
    private int year;
    private int pages;

    public Book(String title, int year, int pages) {
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return title + " (" + year + ", " + pages + " pages)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Проверка на идентичность объектов
        if (o == null || getClass() != o.getClass()) return false; // Проверка на null и тип объекта
        Book book = (Book) o;
        return year == book.year &&
                pages == book.pages &&
                title.equals(book.title);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + year;
        result = 31 * result + pages;
        return result;
    }
}