import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 *  独占锁(写锁)
 *  共享锁(读锁)
 *  读-读 可以共存
 *  读-写 不能共存
 * ***/
public class ReadWriteLockTest {
    //可以多个线程读，但是只有一个能写

    public static void main(String[] args) {
//        MyCache myCache = new MyCache();
//
//        for (int i = 0; i < 5; i++) {
//            final int temp = i;
//            new Thread(() -> {
//                myCache.put(temp +"", temp + "");
//    }, String.valueOf(i)).start();
//        }
//        for (int i = 0; i < 5; i++) {
//            final int temp = i;
//            new Thread(() -> {
//                myCache.get(temp + "");
//            }).start();
//        }


        MyCacheLock myCacheLock = new MyCacheLock();

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.put(temp +"", temp + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}


class MyCacheLock{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //存，写
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        } catch (Exception e) {

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    //取， 读
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK");
        } catch (Exception e) {

        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}


class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();

    //存，写
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入OK");
    }

    //取， 读
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取OK");
    }
}
