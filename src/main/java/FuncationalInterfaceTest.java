import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FuncationalInterfaceTest {
    public static void main(String[] args) {
        //函数型接口
        Function function = new Function<Integer, String>() { // 传入一个int类型，输出一个String类型
            @Override
            public String apply(Integer i) {
                return "str" + i;
            }
        };
        System.out.println(function.apply(1));

        //表达式写法
        Function<Integer, String> function1 = i -> {
            return "str0" + i;
        };
        System.out.println(function1.apply(1));

        //断定型接口
        //判断字符串是否为空
        Predicate<String> predicate = new Predicate<String>() {//只有一个参数，返回boolean值
            @Override
            public boolean test(String str) {
                return str.isEmpty();
            }
        };
        System.out.println(predicate.test(""));

        //表达式写法
        Predicate<String> predicate0 = (str) -> {
            return str.isEmpty();
        };
        System.out.println(predicate0.test("123"));

        //消费型接口
        Consumer<String> consumer = new Consumer<String>() {//只有输入没有返回值
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };
        consumer.accept("stdad");

        //表达式写法
        Consumer<String> consumer0 = str -> {
            System.out.println(str);
        };
        consumer.accept("stdad0");

        Supplier<Integer> supplier = new Supplier<Integer>() {//没有参数只有返回值
            @Override
            public Integer get() {
                System.out.println("get()");
                return 1024;
            }
        };
        System.out.println(supplier.get());
        Supplier<Integer> supplier0 = () -> {
            System.out.println("get0()");
            return 1025;
        };
        System.out.println(supplier0.get());
    }
}