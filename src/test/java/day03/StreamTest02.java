package day03;

import org.junit.Test;
import pojo.Employee;
import pojo.EmployeeData;

import java.util.List;

/**
 * 测试Stream的中间操作
 */
public class StreamTest02 {
    /**
     * 筛选与切片
     */
    @Test
    public void test01() {
        List<Employee> employeeList = EmployeeData.getEmployees();
        // filter(Predicate p)  接受Lambda，从流中排除（筛选）某些元素
        // 工资大于500
        employeeList.stream()
                .filter(employee -> employee.getSalary() > 500)
                .forEach(System.out::println);
        System.out.println("*********************************");
        // limit(long maxSize)  截断流，使其元素不超过给定的数量（maxSize）
        employeeList.stream()
                .limit(3)
                .forEach(System.out::println);
        System.out.println("*********************************");
        // skip(long n) 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流，与limit(n)互补
        employeeList.stream()
                .skip(3)
                .forEach(System.out::println);
        System.out.println("*********************************");
        // distinct() 通过流所生成元素的hashCode()和equals(),去除重复元素
        Employee employee = new Employee("Test", 18, 100);
        employeeList.add(employee);
        employeeList.add(employee);
        employeeList.add(employee);
        System.out.println("************去重之前***************");
        employeeList.stream().forEach(System.out::println);
        System.out.println("************去重之后***************");
        employeeList.stream().distinct().forEach(System.out::println);

    }
}
