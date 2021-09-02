import java.util.concurrent.*;

public class CustomThreadPool {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()); //默认拒绝策略，如果队列满了，还有线程要创建，则不处理，并且抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy()); //哪来的哪里去，哪个程序发出的请求，哪个程序就自己执行，比如在这个地方，就是main去执行这个请求， run的时候会打印出"main ok"
//        new ThreadPoolExecutor.DiscardPolicy()); //如果队列满了，还有线程创建的请求，则不处理，但是！不抛出异常
        new ThreadPoolExecutor.DiscardOldestPolicy()); //如果队列满了，还有线程创建的请求，则不处理，但是！不抛出异常

        try {
            // 当i>5， 也即要处理的任务数，大于 核心线程数 + 队列的长度， 那么机会新建线程，所以在i=6的时候，有3个线程， i=7时有4个线程
            // 最大承载: 队列长度 + maxPoolSize =  3 + 5 = 8， 当对线程池请求数大于8的时候，线程数达到最大，而且阻塞队列已满， 并且拒绝策略为AbortPolicy， 那么会抛出异常
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
