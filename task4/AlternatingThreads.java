package task4;
public class AlternatingThreads {
    private static final Object lock = new Object();
    private static volatile boolean turnOne = true; // true — очередь потока 1
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!turnOne) { // Ждем, пока очередь потока 1
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("1");
                    turnOne = false; // Передаем очередь потоку 2
                    lock.notify(); // Будим другой поток
                }
            }
        }, "Thread-1");
        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (turnOne) { // Ждем, пока очередь потока 2
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("2");
                    turnOne = true; // Передаем очередь потоку 1
                    lock.notify(); // Будим другой поток
                }
            }
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}