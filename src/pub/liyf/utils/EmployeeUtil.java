package pub.liyf.utils;

import pub.liyf.bean.Employee;

import java.util.Scanner;

public class EmployeeUtil {

    private EmployeeUtil(){}

    public static Employee createEmployee(){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入职工id:");
        String id = scan.nextLine();
        System.out.println("请输入职工姓名:");
        String name = scan.nextLine();
        System.out.println("请输入职工年龄:");
        int age = scan.nextInt();
        System.out.println("请输入职工薪水:");
        double salary = scan.nextDouble();
        System.out.println("请输入职工职位:");
        String job = scan.nextLine();
        scan.close();
        return new Employee(id, name, age, salary, job);
    }
}
