import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        //线程数量: 停车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();//获得，如果已经满了，等待，等到被释放为止
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放，会将当前的信号量释放， +1，然后唤醒等待的线程
                }
            }, String.valueOf(i)).start();
        }
    }
    //并发限流，控制最大线程数
}
