package pub.liyf.dao;

import pub.liyf.bean.Employee;
import pub.liyf.bean.Person;
import pub.liyf.exception.EmployeeNotFoundException;
import pub.liyf.exception.PersonIdDuplicatedException;
import pub.liyf.utils.CommonUtil;

import java.util.Scanner;

public class EmployeeDao implements DaoInterface {

    private int MAX_EMPLOYEE_NUMBER = 10;
    private Employee[] employees = new Employee[MAX_EMPLOYEE_NUMBER];
    private int count = 0;
    private static final int UNFOUND_INDEX = -1;
    private Scanner scan = new Scanner(System.in);

    @Override
    public void add() {
        if (count == MAX_EMPLOYEE_NUMBER - 1){
            System.err.println("人数已经超过" + MAX_EMPLOYEE_NUMBER + "!");
            return;
        }
        for(int i = count;i < 10;i++){
            System.out.println("请输入第" + (count + 1) + "位职工的信息:");
            Employee employee = null;
            try{
                 employee = createEmployee();
            } catch (PersonIdDuplicatedException e){
                System.err.println(e.getMessage());
                i--;
                continue;
            }
            employees[count++] = employee;
            System.out.println("是否继续输入学生:Y/N(yes/no)");
            String choice = scan.next();
            if(choice.equals("Y") || choice.equals("y")){
                continue;
            } else {
                break;
            }
        }
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
        System.out.println("删除成功！");
    }

    @Override
    public Person edit(String id) throws EmployeeNotFoundException {
        int targetIndex = getEmployeeIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new EmployeeNotFoundException("没有找到id为" + id + "的职工");
        }
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
        return newEmployee;
    }

    /* 以下是一些辅助方法 */

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

    public Employee createEmployee() throws PersonIdDuplicatedException{
        System.out.println("请输入职工id:");
        String id = scan.next();
        if(isIdDuplicated(id)){
            throw new PersonIdDuplicatedException("该ID:" + id + "已重复！请重新输入");
        }
        System.out.println("请输入职工姓名:");
        String name = scan.next();
        System.out.println("请输入职工年龄:");
        int age = scan.nextInt();
        System.out.println("请输入职工薪水:");
        double salary = scan.nextDouble();
        System.out.println("请输入职工职位:");
        String job = scan.next();
        return new Employee(id, name, age, salary, job);
    }

    public boolean isIdDuplicated(String id){
        if(employees.length == 0){
            return false;
        }
        boolean isDuplicated = false;
        for(int i = 0;i < count;i++){
            if(employees[i].getId().equals(id)){
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }
}
