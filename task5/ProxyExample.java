package task5;
import java.util.ArrayList;
import java.util.List;
interface Image {
    void display();
}
class RealImage implements Image { // операция загрузки изображения
    private String filename;
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + filename);
        // Симуляция операции загрузки
        try {
            Thread.sleep(1000); // Имитация задержки
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}
class ProxyImage implements Image { // Прокси для ленивой загрузки
    private RealImage realImage;
    private String filename;
    public ProxyImage(String filename) {
        this.filename = filename;
    }
    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // Ленивая загрузка
        }
        realImage.display();
    }
}
public class ProxyExample {
    public static void main(String[] args) {

        List<Image> images = new ArrayList<>(); // Создаём список изображений
        images.add(new ProxyImage("photo1.jpg"));
        images.add(new ProxyImage("photo2.jpg"));

        System.out.println("First display:");// Первое отображение (загрузка произойдёт)
        images.get(0).display();

        System.out.println("\nSecond display:");  // Повторное отображение (загрузка не произойдёт)
        images.get(0).display();

        System.out.println("\nDisplaying another image:"); // Отображение другого изображения
        images.get(1).display();
    }
}