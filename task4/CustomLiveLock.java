package task4;

public class CustomLiveLock {
    static boolean resourceA = false;
    static boolean resourceB = false;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                System.out.println("тред1 Пробуем залочить resA");
                synchronized (CustomLiveLock.class) {
                    if (!resourceA) {
                        resourceA = true;
                        System.out.println("залочен resA");
                        try { Thread.sleep(100); } catch (InterruptedException e) {}
                        if (resourceB) {
                            System.out.println("ResourceB залочен, освобождаем resA");
                            resourceA = false;
                            try { Thread.sleep(100); } catch (InterruptedException e) {}
                        } else {
                            System.out.println("тред1 - Success");
                            break;
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                System.out.println("тред2 пробуем залочить resB");
                synchronized (CustomLiveLock.class) {
                    if (!resourceB) {
                        resourceB = true;
                        System.out.println("залочен resB");
                        try { Thread.sleep(100); } catch (InterruptedException e) {}
                        if (resourceA) {
                            System.out.println("ResourceA залочен, освобождаем resB");
                            resourceB = false;
                            try { Thread.sleep(100); } catch (InterruptedException e) {}
                        } else {
                            System.out.println("тред2 - Success");
                            break;
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}