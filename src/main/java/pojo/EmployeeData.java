package pojo;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    public static List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("欧阳雪", 18, 100));
        employeeList.add(new Employee("Tom", 24, 200));
        employeeList.add(new Employee("Harley", 22, 300));
        employeeList.add(new Employee("向天笑", 20, 400));
        employeeList.add(new Employee("李康", 22, 500));
        employeeList.add(new Employee("小梅", 20, 600));
        employeeList.add(new Employee("何雪", 21, 700));
        employeeList.add(new Employee("李康", 22, 800));
        return employeeList;
    }
}
