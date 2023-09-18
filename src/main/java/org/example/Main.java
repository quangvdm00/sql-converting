package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(new Employee(1, "Barbara", "IT", 1000), new Employee(2, "John", "HR", 3000), new Employee(3, "Doe", "IT", 1000), new Employee(4, "Cole", "FI", 2000), new Employee(5, "Paul", "HR", 2000), new Employee(6, "Ted", "FI", 3000), new Employee(7, "Tom", "IT", 4000)

        );

        // GROUP BY EMPLOYEE'S DEPARTMENT
        Map<String, Employee> groupByDepartment = getGroupByDepartment(employees);
        for (Map.Entry<String, Employee> entries : groupByDepartment.entrySet()) {
            out.println(entries.getKey() + " : " + entries.getValue().id + " - " + entries.getValue().getName());
        }
        out.println("=============== GROUP BY ===================");

        // SUM() SALARY BY DEPARTMENT
        Map<String, Integer> sumByDepartment = new HashMap<>();
        for (Employee e : employees) {
            sumByDepartment.put(
                    e.getDepartment(),
                    sumByDepartment.getOrDefault(e.getDepartment(), 0) + e.salary);
        }
        for (Map.Entry<String, Integer> entries : sumByDepartment.entrySet()) {
            out.println(entries.getKey() + ": " + entries.getValue());
        }

        out.println("============= SUM() =======================");

        // AVG() SALARY BY DEPARTMENT
        Map<String, avgFunc> avgByDepartment = new HashMap<>();
        for (Employee e : employees) {
            avgFunc avgFunc = avgByDepartment.getOrDefault(e.getDepartment(), new avgFunc(0, 0));
            avgFunc.sum += e.salary;
            avgFunc.count++;
            avgByDepartment.put(e.getDepartment(), avgFunc);

        }
        for (Map.Entry<String, avgFunc> entries : avgByDepartment.entrySet()) {
            out.println(entries.getKey() + ": " + entries.getValue().sum / entries.getValue().count + ", count: " + entries.getValue().count);
        }
        out.println("============= AVG() =======================");


    }

    private static Map<String, Employee> getGroupByDepartment(List<Employee> employees) {
        Map<String, Employee> groupByDepartment = new HashMap<>();
        for (Employee e : employees) {
            if (!groupByDepartment.containsKey(e.getDepartment())) {
                groupByDepartment.put(e.getDepartment(), e);
            }
        }
        return groupByDepartment;
    }
}

class avgFunc {
    Integer sum;
    Integer count;

    public avgFunc(Integer sum, Integer count) {
        this.sum = sum;
        this.count = count;
    }
}

class Employee {
    Integer id;
    String name;
    String department;
    Integer salary;

    public Employee(Integer id, String name, String department, Integer salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getSalary() {
        return salary;
    }
}
