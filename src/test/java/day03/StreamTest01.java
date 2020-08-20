package day03;

import org.junit.Test;
import pojo.Employee;
import pojo.EmployeeData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream的实例化
 */
public class StreamTest01 {
    // 创建方式一：通过集合 Collection
    @Test
    public void test01() {
        List<Employee> employees = EmployeeData.getEmployees();
        // default Stream<E> stream():返回一个顺序流
        Stream<Employee> employeeStream = employees.stream();
        // default Stream<E> parallelStream():返回一个并行流
        Stream<Employee> employeeParallelStream = employees.parallelStream();
    }

    // 创建方式二：通过数组
    @Test
    public void test02() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        // 调用Arrays类的static <T> Stream<T> stream(T[] array)：返回一个流
        IntStream intStream = Arrays.stream(arr);

        Employee e1 = new Employee("Tom", 18, 200);
        Employee e2 = new Employee("Jack", 23, 400);
        Employee[] employees = new Employee[]{e1, e2};
        Stream<Employee> employeeStream = Arrays.stream(employees);
    }

    // 创建方式三：通过Stream的static<T> Stream<T> of(T... values)
    @Test
    public void test03() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7);
    }

    // 创建方式四：创建无限流
    @Test
    public void test04() {
        // 迭代
        // public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        // 遍历前10个偶数
        Stream.iterate(0, t -> t + 2)
                .limit(10)
                .forEach(System.out::println);
        // 生成
        // public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

    }
}
