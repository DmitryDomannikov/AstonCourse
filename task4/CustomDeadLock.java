package task4;

public class CustomDeadLock {
    static Object resourceA = new Object();
    static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("тред1 залочил resA");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("тред1 ждём  resB");
                synchronized (resourceB) {
                    System.out.println("тред1 залочил resB");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println("тред2 залочил resB");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("тред2 ждём resA");
                synchronized (resourceA) {
                    System.out.println("тред2 залочил resA");
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}