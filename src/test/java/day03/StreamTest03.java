package day03;

import org.junit.Test;
import pojo.Employee;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 归约
     */
    @Test
    public void test02() {
        // reduce(T iden,BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回T
        // 练习1：计算1-10的自然数之和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce01 = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce01);
        System.out.println("*********************************");
        // reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回Optional
        // 练习2：计算公司所有员工工资的总和
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 58, 5555.55),
                new Employee("王五", 26, 3333.33),
                new Employee("赵六", 36, 6666.66),
                new Employee("田七", 12, 8888.88)
        );
        Optional<Double> reduce02 = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(reduce02);
        System.out.println("*********************************");
    }

    /**
     * 收集
     */
    @Test
    public void test03() {
        // collect(Collector c) 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
        // 练习1：查找工资大于6000的员工，结果返回为一个List或Set
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 58, 5555.55),
                new Employee("王五", 26, 3333.33),
                new Employee("赵六", 36, 6666.66),
                new Employee("田七", 12, 8888.88)
        );
        System.out.println("**********List**********");
        List<Employee> employeeList = employees.stream()
                .filter(employee -> employee.getSalary() > 6000)
                .collect(Collectors.toList());
        employeeList.forEach(System.out::println);
        System.out.println("**********Set**********");
        Set<Employee> employeeSet = employeeList.stream()
                .filter(employee -> employee.getSalary() > 6000)
                .collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
    }
}
