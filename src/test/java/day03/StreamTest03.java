package day03;

import org.junit.Test;
import pojo.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 终止操作
 */
public class StreamTest03 {
    /**
     * 匹配与查找
     */
    @Test
    public void test01() {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99, Employee.Status.FREE),
                new Employee("李四", 58, 5555.55, Employee.Status.BUSY),
                new Employee("王五", 26, 3333.33, Employee.Status.VOCATION),
                new Employee("赵六", 36, 6666.66, Employee.Status.FREE),
                new Employee("田七", 12, 8888.88, Employee.Status.BUSY)
        );
        // allMatch-检查是否匹配所有元素
        boolean allMatch = employees
                .stream()
                .allMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(allMatch);
        System.out.println("*********************************");
        // anyMatch-检查是否至少匹配一个元素
        boolean anyMatch = employees
                .stream()
                .anyMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(anyMatch);
        System.out.println("*********************************");
        // noneMatch-检查是否没有匹配所有元素
        boolean noneMatch = employees
                .stream()
                .noneMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(noneMatch);
        System.out.println("*********************************");
        // findFirst-返回第一个元素
        // Optional是Java8中避免空指针异常的容器类
        Optional<Employee> first = employees
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .findFirst();
        System.out.println(first.get());
        System.out.println("*********************************");
        // count() 返回流中元素的总数
        long count = employees.stream().filter(employee -> employee.getSalary() > 5000).count();
        System.out.println(count);
        System.out.println("*********************************");
        // max(Comparator c) 返回流中最大值
        System.out.println("返回最高的工资：");
        Optional<Double> max = employees.stream().map(Employee::getSalary)
                .max(Double::compareTo);
        System.out.println(max);
        System.out.println("*********************************");
        // min(Comparator c) 返回流中最小值
        System.out.println("返回工资最低的员工");
        Optional<Employee> min = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(min);
        System.out.println("*********************************");
    }
}
