package day02;

import org.junit.Test;
import pojo.Employee;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用
 *      和方法引用类似，函数式接口的抽象方法的形参列表跟构造器的形参列表一致
 *      抽象方法的返回值类即为构造器所属的类的类型
 */
public class ConstructorRefTest {
    /**
     * 构造器引用
     * Supplier 中的 T get()
     * Employee 中的 public Employee()
     */
    @Test
    public void test01() {
        // Lambda表达式
        Supplier<Employee> sup01 = () -> new Employee();
        sup01.get();
        // 方法引用
        Supplier<Employee> sup02 = Employee::new;
        sup02.get();
    }

    /**
     * 构造器引用
     * Function 中的 R apply(T t);
     * Employee 中的 public Employee(String name)
     */
    @Test
    public void test02() {
        // Lambda表达式
        Function<String, Employee> sup01 = (s) -> new Employee(s);
        System.out.println(sup01.apply("Tom"));
        // 方法引用
        Function<String, Employee> sup02 = Employee::new;
        System.out.println(sup01.apply("Tom"));
    }

    /**
     * 构造器引用
     * BiFunction 中的 R apply(T t, U u);
     * Employee 中的 public Employee(String name, int age)
     */
    @Test
    public void test03() {
        // Lambda表达式
        BiFunction<String, Integer, Employee> biFunction01 = (name, age) -> new Employee(name, age);
        System.out.println(biFunction01.apply("Tom", 18));
        // 方法引用
        BiFunction<String, Integer, Employee> biFunction02 = Employee::new;
        System.out.println(biFunction02.apply("Tom", 18));
    }

    /**
     * 数组引用
     * Function 中的 R apply(T t);
     */
    @Test
    public void test04() {
        // Lambda表达式
        Function<Integer, String[]> function01 = (length) -> new String[length];
        System.out.println(function01.apply(10).length);
        // 方法引用
        Function<Integer, String[]> function02 = String[]::new;
        System.out.println(function02.apply(10).length);
    }
}
