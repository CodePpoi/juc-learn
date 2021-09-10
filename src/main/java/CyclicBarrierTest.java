import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    //加法计数器

    public static void main(String[] args) {
        //集齐七颗龙珠召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });
        for (int i = 0; i < 7; i++) {
            final int temp = i;// 因为new Thread里面的参数是一个new Runnable变量，i不能传进去
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集" + temp + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
