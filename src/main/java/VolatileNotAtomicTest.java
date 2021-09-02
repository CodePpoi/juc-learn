public class VolatileNotAtomicTest {
    private volatile static int num = 0;
    public static void add() {
        ++num;// ++num和num++都是非原子操作，一行对应3-4行汇编指令
    }
    public static void main(String[] args) {

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for(int j= 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(num); // num 一般小于 20000，因为没有原子性
    }
}
