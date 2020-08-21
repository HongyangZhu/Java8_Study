package day03;

import org.junit.Test;
import pojo.Employee;
import pojo.EmployeeData;

import java.util.Arrays;
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

    /**
     * 映射
     */
    @Test
    public void test02() {
        // map(Function f)
        /* 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素*/
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        System.out.println("Lambda 表达式：");
        list.stream().map(s -> s.toUpperCase()).forEach(System.out::println);
        System.out.println("方法引用：");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("*********************************");
        // 练习：获取员工姓名长度大于3的员工姓名
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream()
                .map(Employee::getName)
                .filter(name -> name.length() > 3)
                .forEach(System.out::println);

        // flatMap(Function f)
        /*接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流*/

    }

    /**
     * 排序
     */
    @Test
    public void test03() {
        // sorted()	产生一个新流，其中按自然顺序排序
        List<String> list = Arrays.asList("ccc", "bbb", "aaa");
        list.stream().sorted().forEach(System.out::println);
        System.out.println("*********************************");
        // sorted(Comparator comp)	产生一个新流，其中按比较器顺序排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        }).forEach(System.out::println);
    }
}
