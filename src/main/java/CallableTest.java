import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {

//        new Thread(new Runnable()).start();
//        new Thread(new FutureTask<V>() implements Runnable).start();
//        new Thread(new FutureTask<>(callable)).start();
        MyThread thread = new MyThread();
        Callable callable;
        FutureTask futureTask = new FutureTask<>(thread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        try {
            Integer integer = (Integer) futureTask.get();
            System.out.println(integer);
            integer = (Integer) futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("call()");
        return 1024;
    }
}
