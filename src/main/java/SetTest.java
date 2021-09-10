import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {
    public static void main(String[] args) {
        //存在线程安全问题
        //Set<String> set = new HashSet<>();
        //使用集合类解决线程安全
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());

        //CopyOnWrite解决
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }


}
