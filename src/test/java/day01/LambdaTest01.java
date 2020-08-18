package day01;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * <p>Lambda表达式的使用</p>
 *      <p>1.举例：<code>(o1, o2) -> Integer.compare(o1, o2)</code></p>
 *      <p>2.格式：</p>
 *          <p>-> ：Lambda操作符 或 箭头操作符</p>
 *          <p>->左侧：Lambda形参列表(其实就是接口中的抽象方法的形参列表)</p>
 *          <p>->右侧：Lambda体(其实就是重写的抽象方法的方法体)</p>
 *      <p>3.Lambda表达式的使用：6种场合</p>
 *      <p>4.Lambda本质：作为接口（函数式接口）的实例</p>
 *      <p>5.函数式接口：当一个接口中，只声明了一个抽象方法，则此接口称为“函数式接口”</p>
 * <p>总结：</p>
 *      <p>->左侧：</p>
 *          <p>Lambda形参列表的参数类型可以省略</p>
 *          <p>当参数列表只有一个参数时，可以省略()</p>
 *      <p>->右侧：</p>
 *          <p>当Lambda体只有一条执行语句（可能是return语句），可以省略{}和return关键字</p>
 *
 */
@SuppressWarnings("all")
public class LambdaTest01 {

    /**
     * 语法格式一：无参，无返回值
     */
    @Test
    public void test01() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("This is general ");
            }
        };
        r1.run();
        System.out.println("语法格式一：无参，无返回值");
        Runnable r2 = () -> System.out.println("This is lambda");
        r2.run();
    }

    /**
     * 语法格式二：有参，无返回值
     */
    @Test
    public void test02() {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("Test");
        System.out.println("语法格式二：有参，无返回值");
        Consumer<String> con02 = (String s) -> System.out.println(s);
        con02.accept("Test");
    }

    /**
     * 语法格式三：数据类型可以省略，因为可以由编译器推断得出，称为“类型推断”
     */
    @Test
    public void test03() {
        System.out.println("语法格式三：数据类型可以省略，因为可以由编译器推断得出，称为“类型推断”");
//        Consumer<String> con02 = (String s) -> System.out.println(s);
        Consumer<String> con02 = (s) -> System.out.println(s);
        con02.accept("Test");
    }

    /**
     * 语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略
     */
    @Test
    public void test04() {
        System.out.println("语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略");
//        Consumer<String> con02 = (s) -> System.out.println(s);
        Consumer<String> con02 = s -> System.out.println(s);
        con02.accept("Test");
    }

    /**
     * 语法格式五：Lambda 需要两个或者两个以上的参数，多条执行语句，并且有返回值时
     */
    @Test
    public void test05() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(12, 21));
        System.out.println("语法格式五：Lambda 需要两个或者两个以上的参数，多条执行语句，并且有返回值时");
        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(12, 21));
    }

    /**
     * 语法格式六：当Lambda体只有一条语句时，return与大括号都可以省略
     */
    @Test
    public void test06() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println("语法格式六：当Lambda体只有一条语句时，return与大括号都可以省略");
        Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);
    }
}
