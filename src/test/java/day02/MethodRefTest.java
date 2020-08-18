package day02;

import org.junit.Test;
import pojo.Employee;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 1.使用场景：当要传递给Lambda体的操作，已经有实现的方法时，可以使用方法引用。
 * 2.方法引用，本质上就是Lambda表达式，也就是函数式接口的一个实例。
 * 3.使用格式：类（对象） :: 方法名
 * 4.具体分为如下的三种情况：
 *     对象 :: 非静态方法
 *     类 :: 静态方法
 *     类 :: 非静态方法
 * 5.要求：
 *     实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法参数列表和返回值类型保持一致。
 */
public class MethodRefTest {
    /**
     * 情况一：
     * 对象 :: 非静态方法
     * Consumer中的 void accept(T t)
     * PrintStream中的 public void println(String x)
     */
    @Test
    public void test01() {
        // Lambda表达式
        Consumer<String> consumer01 = str -> System.out.println(str);
        consumer01.accept("Test");
        // 方法引用
        Consumer<String> consumer02 = System.out::println;
        consumer02.accept("Test");
    }

    /**
     * Supplier 中的 T get()
     * Employee 中的 public String getName()
     */
    @Test
    public void test02() {
        Employee emp = new Employee("Tom", 12, 1000);
        // Lambda表达式
        Supplier<String> supplier01 = () -> emp.getName();
        System.out.println(supplier01.get());
        // 方法引用
        Supplier<String> supplier02 = emp::getName;
        System.out.println(supplier02.get());
    }

    /**
     * 情况二：
     * 类 :: 静态方法
     * Comparator 中的 int compare(T o1, T o2)
     * Integer 中的 public static int compare(int x, int y)
     */
    @Test
    public void test03() {
        // Lambda表达式
        Comparator<Integer> comparator01 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(comparator01.compare(12, 21));
        // 方法引用
        Comparator<Integer> comparator02 = Integer::compareTo;
        System.out.println(comparator02.compare(12, 21));
    }

    /**
     * Function 中的 R apply(T t)
     * Math 中的 public static long round(double a)
     */
    @Test
    public void test04() {
        // Lambda表达式
        Function<Double, Long> function01 = d -> Math.round(d);
        System.out.println(function01.apply(12.3));
        // 方法引用
        Function<Double, Long> function02 =Math::round;
        System.out.println(function02.apply(12.3));
    }

    /**
     * 情况三：
     * 类 :: 非静态方法
     * Comparator 中的 int compare(T o1, T o2)
     * String 中的 public int compareTo(String anotherString)
     */
    @Test
    public void test05() {
        // Lambda表达式
        Comparator<String> comparator01 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(comparator01.compare("abc", "abd"));
        // 方法引用
        Comparator<String> comparator02 = String::compareTo;
        System.out.println(comparator02.compare("abc", "abd"));
    }
}
