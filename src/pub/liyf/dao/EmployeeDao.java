package pub.liyf.dao;

import pub.liyf.bean.Employee;
import pub.liyf.bean.Person;
import pub.liyf.exception.EmployeeNotFoundException;
import pub.liyf.utils.CommonUtil;

import java.util.Scanner;

public class EmployeeDao implements DaoInterface {

    private int MAX_EMPLOYEE_NUMBER = 50;
    private Employee[] employees = new Employee[MAX_EMPLOYEE_NUMBER];
    private int count = 0;
    private static final int UNFOUND_INDEX = -1;

    @Override
    public void add(Person person) {
        employees[count++] = (Employee) person;
    }

    @Override
    public void list() {
        System.out.println(CommonUtil.spiltLine);
        for(Employee employee:employees){
            if(employee == null){
                continue;
            }
            System.out.println(employee);
        }
        System.out.println(CommonUtil.spiltLine);
    }

    @Override
    public Person selectOne(String id) throws EmployeeNotFoundException {
        Employee employee = null;
        int targetIndex = getEmployeeIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new EmployeeNotFoundException("没有找到id为" + id + "的职工");
        }
        employee = employees[targetIndex];
        return employee;
    }

    @Override
    public void delete(String id) throws EmployeeNotFoundException {
        int targetIndex = getEmployeeIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new EmployeeNotFoundException("没有找到id为" + id + "的职工");
        }
        for(int i = targetIndex;i <= count;i++){
            employees[i] = employees[i + 1];
        }
        count--;
    }

    @Override
    public Person edit(String id) throws EmployeeNotFoundException {
        int targetIndex = getEmployeeIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new EmployeeNotFoundException("没有找到id为" + id + "的职工");
        }
        Scanner scan = new Scanner(System.in);
        Employee employee = employees[targetIndex];
        System.out.println("修改前职工的信息为: " + employee);
        System.out.println("请输入新的职工姓名:");
        String name = scan.nextLine();
        System.out.println("请输入新的职工年龄:");
        int age = scan.nextInt();
        System.out.println("请输入新的职工薪水:");
        double salary = scan.nextDouble();
        System.out.println("请输入新的职工职位:");
        String job = scan.nextLine();

        Employee newEmployee = new Employee(employee.getId(), name, age, salary, job);
        employees[targetIndex] = newEmployee;
        scan.close();
        return newEmployee;
    }

    private int getEmployeeIndex(String id){
        int index = UNFOUND_INDEX;
        for(int i = 0;i <= count;i++) {
            if(employees[i].getId().equals(id)){
                index = i;
                break;
            }
        }
        return index;
    }

}
