package task5;

import java.util.ArrayList;
import java.util.List;
interface NewShape {
    double getArea();       // Площадь в квадратных метрах
}
class OldShape {           // Старая система для фигур
    private double width; // Ширина в сантиметрах
    private double height; // Высота в сантиметрах
    public OldShape(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Длинна и ширина должны быть положительными");
        }
        this.width = width;
        this.height = height;
    }
    public double computeArea() { // Площадь в квадратных сантиметрах
        return width * height;
    }
}
class ShapeAdapter implements NewShape {      // Адаптер
    private OldShape oldShape;
    public ShapeAdapter(OldShape oldShape) {
        this.oldShape = oldShape;
    }
    @Override
    public double getArea() {
        // Преобразуем площадь из квадратных сантиметров в квадратные метры (1 м² = 10000 см²)
        return oldShape.computeArea() / 10000.0;
    }
}
public class AdapterExample {
    public static void main(String[] args) {

        OldShape oldRectangle = new OldShape(200, 300);  // Создаём прямоугольник 200 см x 300 см

        NewShape newRectangle = new ShapeAdapter(oldRectangle);        // Адаптируем её к новому интерфейсу

        System.out.printf("Площадь в квадратных метрах: %.2f m²%n", newRectangle.getArea());

        List<NewShape> shapes = new ArrayList<>();      // Пример с несколькими фигурами
        shapes.add(new ShapeAdapter(new OldShape(100, 100)));
        shapes.add(new ShapeAdapter(new OldShape(10, 10)));

        for (NewShape shape : shapes) {
            System.out.printf("площадь фигуры: %.2f m²%n", shape.getArea());
        }
    }
}