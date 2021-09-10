import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    //countdownlatch 减法计数器


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " GO OUT");
                countDownLatch.countDown();//数量-1
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();//等待计数器归零，再向下执行

        System.out.println("Close Door");
    }
}
