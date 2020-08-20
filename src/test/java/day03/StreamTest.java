package day03;

import org.junit.Test;
import pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
    @Test
    public void test01() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("欧阳雪", 18, 100));
        employeeList.add(new Employee("Tom", 24, 200));
        employeeList.add(new Employee("Harley", 22, 300));
        employeeList.add(new Employee("向天笑", 20, 400));
        employeeList.add(new Employee("李康", 22, 500));
        employeeList.add(new Employee("小梅", 20, 600));
        employeeList.add(new Employee("何雪", 21, 700));
        employeeList.add(new Employee("李康", 22, 800));

        // 1）找到年龄大于18岁的人并输出；
        /*Java 7 的取值实现*/
        List<Employee> groceryTransactions = new ArrayList<>();
        for (Employee e : employeeList) {
            if (e.getAge() > 18) {
                groceryTransactions.add(e);
            }
        }
        for (Employee employee : groceryTransactions) {
            System.out.println(employee);
        }
        System.out.println("-------------------------------------------");
        /*Java 8 的取值实现*/
        employeeList.stream() // 创建流
                .filter(employee -> employee.getAge() > 18) // 中间操作
                .forEach(System.out::println);// 终止操作
        System.out.println("-------------------------------------------");
        // 2）找出所有薪资大于500的数量
        long count = employeeList.stream().filter(employee -> employee.getSalary() > 500).count();
        System.out.println(count);
    }

}
