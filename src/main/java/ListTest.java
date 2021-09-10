import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
        // Aratlist线程不安全
        // List<String> list = new ArrayList<>();
        // Vector线程安全
        // List<String> list = new Vector<>();
        // synchronizedList也线程安全
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        // 写入时复制，看看CopyOnWriteArrayList的add方法就知道
        // 比Vector牛逼在哪? Vector用的synchronized关键字，性能较低
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
