package task5;
interface ShippingStrategy {
    double calculateShippingCost(double orderWeight, double distance);
}
class CourierShipping implements ShippingStrategy {     // Курьерская доставка
    @Override
    public double calculateShippingCost(double orderWeight, double distance) {
        return orderWeight * 5.0 + distance * 0.5; // $5 за кг + $0.5 за км
    }
}
class PostalShipping implements ShippingStrategy {  // Почтовая доставка
    @Override
    public double calculateShippingCost(double orderWeight, double distance) {
        return orderWeight * 2.0 + 10.0; // $2 за кг + фиксированная плата $10
    }
}
class PickupShipping implements ShippingStrategy {     // Самовывоз
    @Override
    public double calculateShippingCost(double orderWeight, double distance) {
        return 0.0; // Бесплатно
    }
}
class Order {
    private ShippingStrategy shippingStrategy;
    private double orderWeight;
    private double distance;
    public Order(double orderWeight, double distance) {
        this.orderWeight = orderWeight;
        this.distance = distance;
    }
    public void setShippingStrategy(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }
    public double calculateTotalShippingCost() {
        if (shippingStrategy == null) {
            throw new IllegalStateException("Shipping strategy not set");
        }
        return shippingStrategy.calculateShippingCost(orderWeight, distance);
    }
}
public class StrategyExample {
    public static void main(String[] args) {
        Order order = new Order(10.0, 10.0); // Заказ весом 10 кг, расстояние 100 км
        // Используем курьерскую доставку
        order.setShippingStrategy(new CourierShipping());
        System.out.printf("Courier Shipping Cost: $%.2f%n", order.calculateTotalShippingCost());
        // Переключаемся на почтовую доставку
        order.setShippingStrategy(new PostalShipping());
        System.out.printf("Postal Shipping Cost: $%.2f%n", order.calculateTotalShippingCost());
        // Переключаемся на самовывоз
        order.setShippingStrategy(new PickupShipping());
        System.out.printf("Pickup Shipping Cost: $%.2f%n", order.calculateTotalShippingCost());
    }
}