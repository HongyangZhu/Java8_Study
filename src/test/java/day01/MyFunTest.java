package day01;

import org.junit.Test;
import pojo.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyFunTest {
    @Test
    public void test() {
        Integer num = operation(100, (x) -> x * x);
        System.out.println(num);
        System.out.println(operation(200, (y) -> y + 200));
    }

    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }


    @Test
    public void test1() {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9496.2),
                new Employee("李四", 52, 2396.2),
                new Employee("王五", 56, 996.2),
                new Employee("赵六", 8, 94.2)
        );
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
