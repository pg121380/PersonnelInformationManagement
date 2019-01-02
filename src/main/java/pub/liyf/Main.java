package pub.liyf;

import pub.liyf.bean.*;
import pub.liyf.dao.EmployeeDao;
import pub.liyf.dao.StudentDao;
import pub.liyf.exception.EmployeeNotFoundException;
import pub.liyf.exception.PersonNotFoundException;
import pub.liyf.exception.StudentNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static EmployeeDao employeeDao = new EmployeeDao();
    private static StudentDao studentDao = new StudentDao();
    private static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        int choice;
        while (true){
            System.out.println("=============学校信息管理系统=============");
            System.out.println("1.学生信息管理");
            System.out.println("2.职工信息管理");
            System.out.println("3.退出系统");
            System.out.println("请输入:");
            choice = scan.nextInt();
            switch (choice){
                case 1:{
                    studentMenu();
                    break;
                }
                case 2:{
                    employeeMenu();
                    break;
                }
                case 3:{
                    System.out.println("感谢使用！");
                    System.exit(0);
                    break;
                }
                default:{
                    System.err.println("请输入正确的选项!");
                    break;
                }
            }
        }
    }

    private static void studentMenu(){
        int choice;
        while (true){
            System.out.println("=============学生信息管理=============");
            System.out.println("1.增加学生信息");
            System.out.println("2.列出全部学生信息");
            System.out.println("3.查询学生信息");
            System.out.println("4.根据学生姓名进行模糊查询");
            System.out.println("5.删除学生信息");
            System.out.println("6.修改学生信息");
            System.out.println("7.返回上一级菜单");
            System.out.println("请输入:");
            choice = scan.nextInt();

            switch (choice){
                case 1:{
                    studentDao.add();
                    break;
                }
                case 2:{
                    studentDao.list();
                    break;
                }
                case 3:{
                    System.out.println("请输入要查找的ID");
                    String selectId = scan.next();
                    Student selectStudent;
                    try{
                        selectStudent = (Student) studentDao.selectOne(selectId);
                        System.out.println(selectStudent);
                    } catch (PersonNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 4:{
                    System.out.println("请输入需要查找的学生的姓名的一部分");
                    String partOfName = scan.next();
                    try {
                        ArrayList<Person> list = studentDao.selectByLike(partOfName);
                        for (Person person:
                             list) {
                            System.out.println(person);
                        }
                    } catch (StudentNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 5:{
                    System.out.println("请输入要删除的ID");
                    String deleteId = scan.next();
                    try{
                        studentDao.delete(deleteId);
                    } catch (PersonNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 6:{
                    System.out.println("请输入要修改的ID");
                    String editId = scan.next();
                    try {
                        studentDao.edit(editId);
                    } catch (PersonNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 7:{
                    return;
                }
                default:{
                    System.err.println("请输入正确的选项！");
                    break;
                }
            }
        }
    }

    private static void employeeMenu(){
        int choice;
        while (true){
            System.out.println("=============职工信息管理=============");
            System.out.println("1.增加职工信息");
            System.out.println("2.列出全部职工信息");
            System.out.println("3.查询职工信息");
            System.out.println("4.根据职工姓名的一部分进行模糊查询");
            System.out.println("5.删除职工信息");
            System.out.println("6.修改职工信息");
            System.out.println("7.返回上一级菜单");
            System.out.println("请输入:");
            choice = scan.nextInt();

            switch (choice){
                case 1:{
                    employeeDao.add();
                    break;
                }
                case 2:{
                    employeeDao.list();
                    break;
                }
                case 3:{
                    System.out.println("请输入要查找的ID");
                    String selectId = scan.next();
                    Employee selectEmployee;
                    try{
                        selectEmployee = (Employee) employeeDao.selectOne(selectId);
                        System.out.println(selectEmployee);
                    } catch (PersonNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 4:{
                    System.out.println("请输入需要查找的职工的姓名的一部分");
                    String partOfName = scan.next();
                    try {
                        ArrayList<Person> list = employeeDao.selectByLike(partOfName);
                        for (Person person:
                                list) {
                            System.out.println(person);
                        }
                    } catch (EmployeeNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 5:{
                    System.out.println("请输入要删除的ID");
                    String deleteId = scan.next();
                    try{
                        employeeDao.delete(deleteId);
                    } catch (PersonNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 6:{
                    System.out.println("请输入要修改的ID");
                    String editId = scan.next();
                    try {
                        employeeDao.edit(editId);
                    } catch (PersonNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 7:{
                    return;
                }
                default:{
                    System.err.println("请输入正确的选项！");
                    break;
                }
            }
        }
    }
}
