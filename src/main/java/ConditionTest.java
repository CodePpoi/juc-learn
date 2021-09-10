import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    //实现打印完A，打印B，打印C，再打印A
    //实际场景 先下单 再支付 再配送 再签收
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "A").start();
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "C").start();
    }
}

class Data3{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1;

    public void printA() {
        lock.lock();
        try {
            while (number != 1) {
               condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "AAAA");
            //唤醒，唤醒指定的人B
            number = 2;
            condition2.signal();;

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "BBBB");
            //唤醒，唤醒指定的人B
            number = 3;
            condition3.signal();;
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {

            while (number != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "CCCC");
            //唤醒，唤醒指定的人A
            number = 1;
            condition1.signal();;
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

}
