import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreeTypeThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool2 = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
//                threadPool.execute(() -> {
//                    System.out.println(Thread.currentThread().getName() + " ok");
//                });
//                threadPool1.execute(() -> {
//                    System.out.println(Thread.currentThread().getName() + " ok");
//                });
                threadPool2.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
            threadPool1.shutdown();
            threadPool2.shutdown();
        }
    }
}
