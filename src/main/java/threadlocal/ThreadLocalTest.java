package threadlocal;

public class ThreadLocalTest {
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    /***threadLocal 场景
     比如t1，到t2， 一直到tn，都需要处理User对象，如果每次都传User作为形参，很麻烦，所以我们可以定义一个static，t1到tn都使用static User
     这样单线程是没问题的，但是多线程可能会出现并发问题，此时就轮到ThreadLoca了
     需要注意的是，每个线程的ThreadLocal都不会自动清除，所以需要我们在finally里手动调用ThreadLocal的remove方法清除

    **/
    public static void main(String[] args) throws InterruptedException {
        /**
         * 每个Thread都有一个变量叫threadLocals的Map<ThreadLocal的弱引用, 变量副本的值(value, 比如下面threadLocal.set()时的0)>
         *     具体结构见img.png
         *     所以线程池中，threadLocals这个Map其实不会自动清除，需要调用remove清除
         *      而且ThreadLocal可能出现内存泄漏问题，因为key是ThreadLocal的弱引用，很有可能被gc掉，而value是强引用，不会被gc，会一直存在
         *      所以必须手动调用remove，来讲value的强引用释放掉
         *  */
        Thread t1 = new Thread(() -> {
            System.out.println(threadLocal.get());
            threadLocal.set(0);
            System.out.println(threadLocal.get());
        });
        Thread t2 = new Thread(() -> {
            System.out.println(threadLocal.get());
            threadLocal.set(1);
            System.out.println(threadLocal.get());
        });
        t1.start();
        t1.join();
        t2.start();

    }
}
