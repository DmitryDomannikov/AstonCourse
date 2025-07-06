package task5;

// Интерфейс обработчика
interface OrderHandler {
    void handleOrder(String orderType, String orderDetails);
    void setNextHandler(OrderHandler nextHandler);
}
abstract class AbstractOrderHandler implements OrderHandler { // Абстрактный класс
    protected OrderHandler nextHandler;
    @Override
    public void setNextHandler(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    @Override
    public void handleOrder(String orderType, String orderDetails) {
        if (canHandle(orderType)) {
            process(orderDetails);
        } else if (nextHandler != null) {
            nextHandler.handleOrder(orderType, orderDetails);
        } else {
            System.out.println("У нас такое не делают: " + orderDetails);
        }
    }
    abstract boolean canHandle(String orderType);
    abstract void process(String orderDetails);
}
class BaristaHandler extends AbstractOrderHandler {   //  Бариста (напитки)
    @Override
    boolean canHandle(String orderType) {
        return orderType.equalsIgnoreCase("напиток");
    }
    @Override
    void process(String orderDetails) {
        System.out.println("Бариста делает напиток: - " + orderDetails);
    }
}
class ChefHandler extends AbstractOrderHandler { // : Повар (еда)
    @Override
    boolean canHandle(String orderType) {
        return orderType.equalsIgnoreCase("еда");
    }
    @Override
    void process(String orderDetails) {
        System.out.println("Шеф готовит: - " + orderDetails);
    }
}
class PastryChefHandler extends AbstractOrderHandler { // Кондитер (десерты)
    @Override
    boolean canHandle(String orderType) {
        return orderType.equalsIgnoreCase("десерт");
    }
    @Override
    void process(String orderDetails) {
        System.out.println("Кондитер делает десерт: - " + orderDetails);
    }
}
class OrderChainExample {
    public static void main(String[] args) {

        OrderHandler barista = new BaristaHandler();
        OrderHandler chef = new ChefHandler();
        OrderHandler pastryChef = new PastryChefHandler();
        // цепочка Бариста -> Повар -> Кондитер
        barista.setNextHandler(chef);
        chef.setNextHandler(pastryChef);
        // Тестируем цепочку
        barista.handleOrder("напиток", "Каппучино");
        barista.handleOrder("еда", "Паста карбонара");
        barista.handleOrder("десерт", "Шоколадный пирог");
        barista.handleOrder("перекус", "чипсы");
    }
}