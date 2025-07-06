package task4;

public class Threads {
    private static int value = 1;

    public static void main(String[] args) throws InterruptedException {
        doWork();
    }
    public static synchronized void change(){
        if (value % 2 != 0){
            System.out.println("1");
        }else {
            System.out.println("2");
        }
        value++;
    }
      public static void doWork() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (true) {
                change();
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                change();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
