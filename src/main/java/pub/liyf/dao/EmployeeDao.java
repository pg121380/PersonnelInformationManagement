package pub.liyf.dao;

import pub.liyf.bean.Employee;
import pub.liyf.bean.Person;
import pub.liyf.exception.EmployeeNotFoundException;
import pub.liyf.exception.PersonIdDuplicatedException;
import pub.liyf.exception.PersonOpException;
import pub.liyf.repository.FileOperate;
import pub.liyf.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeDao implements DaoInterface {

    private Scanner scan = new Scanner(System.in);
    private final int MAX_EMPLOYEE_NUMBER = 10;
    private ArrayList<Employee> employees = new ArrayList<>(MAX_EMPLOYEE_NUMBER);
    private final int UNFOUND_INDEX = -1;
    private FileOperate fileOperate = new FileOperate(CommonUtil.employeeRepositoryPath);

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void add() {
        loadMsg();
        if(employees.size() == MAX_EMPLOYEE_NUMBER){
            System.err.println("人数已经超过" + MAX_EMPLOYEE_NUMBER + "！");
            saveMsg();
            return;
        }
        for(int i = employees.size();i < MAX_EMPLOYEE_NUMBER;i++){
            System.out.println("请输入第" + (i + 1) + "位职工的信息：");
            Employee employee;
            try {
                employee = createEmployee();
            } catch (PersonIdDuplicatedException e){
                System.err.println(e.getMessage());
                i--;
                continue;
            }
            employees.add(employee);
            System.out.println("是否继续输入职工:Y/N(yes/no)");
            String choice = scan.next();
            if(!(choice.equals("Y") || choice.equals("y"))){
                break;
            }
        }
        saveMsg();
    }

    @Override
    public void list() {
        loadMsg();
        if(employees.isEmpty()){
            System.err.println("系统中还没有数据，请先进行输入！");
            saveMsg();
            return;
        }
        System.out.println(CommonUtil.spiltLine);
        for (Employee employee: employees) {
            System.out.println(employee);
        }
        System.out.println(CommonUtil.spiltLine);
        saveMsg();
    }

    @Override
    public Person selectOne(String id) throws EmployeeNotFoundException {
        loadMsg();
        Employee employee;
        int targetIndex = getEmployeeIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            saveMsg();
            throw new EmployeeNotFoundException("没有找到id为" + id + "的职工");
        }
        employee = employees.get(targetIndex);
        saveMsg();
        return employee;
    }

    @Override
    public ArrayList<Person> selectByLike(String partOfName) throws EmployeeNotFoundException {
        loadMsg();
        ArrayList<Person> list = getEmployeesByLike(partOfName);
        if(list.isEmpty()){
            saveMsg();
            throw new EmployeeNotFoundException("没有找到名字中包含'" + partOfName + "'的职工！");
        }
        saveMsg();
        return list;
    }

    @Override
    public void delete(String id) throws EmployeeNotFoundException {
        loadMsg();
        int targetIndex = getEmployeeIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            saveMsg();
            throw new EmployeeNotFoundException("没有找到id为" + id + "的职工");
        }
        employees.remove(targetIndex);
        System.out.println("删除成功！");
        saveMsg();
    }

    @Override
    public Person edit(String id) throws EmployeeNotFoundException {
        loadMsg();
        int targetIndex = getEmployeeIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            saveMsg();
            throw new EmployeeNotFoundException("没有找到id为" + id + "的职工");
        }
        Employee employee = employees.get(targetIndex);
        System.out.println("修改前职工的信息为: " + employee);

        System.out.println("请输入新的职工姓名:");
        String name = scan.next();
        System.out.println("请输入新的职工年龄:");
        int age = scan.nextInt();
        System.out.println("请输入新的职工薪水:");
        double salary = scan.nextDouble();
        System.out.println("请输入新的职工职位:");
        String job = scan.next();

        Employee newEmployee = new Employee(employee.getId(), name, age, salary, job);
        employees.set(targetIndex, newEmployee);
        saveMsg();
        return newEmployee;
    }

    private Employee createEmployee() throws PersonIdDuplicatedException{
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

    private boolean isIdDuplicated(String id){
        if(employees.isEmpty()){
            return false;
        }
        boolean isDuplicated = false;
        for (Employee employee:employees) {
            if(employee.getId().equals(id)){
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }

    private int getEmployeeIndex(String id){
        Employee employee = new Employee();
        employee.setId(id);
        return employees.indexOf(employee);
    }

    private ArrayList<Person> getEmployeesByLike(String partOfName){
        ArrayList<Person> employees = new ArrayList<>();

        for (Employee employee:
             this.employees) {
            if(employee.getName().contains(partOfName)){
                employees.add(employee);
            }
        }
        return employees;
    }

    private void loadMsg(){
        this.employees.clear();

        try {
            ArrayList<Person> fileList = fileOperate.loadText();
            if(fileList.isEmpty() || fileList == null){
                return;
            }
            for (Person person:
                 fileList) {
                if(person instanceof Employee){
                    this.employees.add((Employee) person);
                }
            }
        } catch (PersonOpException e) {
            e.printStackTrace();
        }
    }

    private void saveMsg(){
        ArrayList<Person> list = new ArrayList<>();
        for (Employee employee:
             this.employees) {
            list.add(employee);
        }

        try {
            fileOperate.saveText(list);
        } catch (PersonOpException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

}
