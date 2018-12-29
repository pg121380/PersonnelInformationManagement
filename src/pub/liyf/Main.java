package pub.liyf;

import com.sun.xml.internal.bind.v2.model.core.ID;
import pub.liyf.bean.*;
import pub.liyf.dao.EmployeeDao;
import pub.liyf.dao.StudentDao;
import pub.liyf.exception.PersonNotFoundException;
import pub.liyf.exception.StudentNotFoundException;
import pub.liyf.utils.CommonUtil;

import java.util.Scanner;

public class Main {

    public static EmployeeDao employeeDao = new EmployeeDao();
    public static StudentDao studentDao = new StudentDao();
    public static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) throws StudentNotFoundException {
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
                    System.out.println("系统将在3秒内退出!");
                    System.out.println("3");
                    CommonUtil.sleep(1000);
                    System.out.println("2");
                    CommonUtil.sleep(1000);
                    System.out.println("1");
                    CommonUtil.sleep(1000);
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

    public static void studentMenu(){
        int choice;
        while (true){
            System.out.println("=============学生信息管理=============");
            System.out.println("1.增加学生信息");
            System.out.println("2.列出全部学生信息");
            System.out.println("3.查询学生信息");
            System.out.println("4.删除学生信息");
            System.out.println("5.修改学生信息");
            System.out.println("6.返回上一级菜单");
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
                    Student selectStudent = null;
                    try{
                        selectStudent = (Student) studentDao.selectOne(selectId);
                        System.out.println(selectStudent);
                    } catch (PersonNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 4:{
                    System.out.println("请输入要删除的ID");
                    String deleteId = scan.next();
                    try{
                        studentDao.delete(deleteId);
                    } catch (PersonNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 5:{
                    System.out.println("请输入要修改的ID");
                    String editId = scan.next();
                    try {
                        studentDao.edit(editId);
                    } catch (PersonNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                }
                case 6:{
                    return;
                }
                default:{
                    System.err.println("请输入正确的选项！");
                    break;
                }
            }
        }
    }

    public static void employeeMenu(){
        int choice;
        while (true){
            System.out.println("=============职工信息管理=============");
            System.out.println("1.增加职工信息");
            System.out.println("2.列出全部职工信息");
            System.out.println("3.查询职工信息");
            System.out.println("4.删除职工信息");
            System.out.println("5.修改职工信息");
            System.out.println("6.返回上一级菜单");
            System.out.println("请输入:");
            choice = scan.nextInt();

            switch (choice){
                case 1:{
                    break;
                }
                case 2:{
                    break;
                }
                case 3:{
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    break;
                }
                case 6:{
                    return;
                }
            }
        }
    }
}
