package pub.liyf.utils;

import pub.liyf.bean.Student;

import java.util.Scanner;

public class StudentUtil {

    private StudentUtil(){}

    public static Student createStudent(){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入学生id:");
        String id = scan.nextLine();
        System.out.println("请输入学生姓名:");
        String name = scan.nextLine();
        System.out.println("请输入学生年龄:");
        int age = scan.nextInt();
        System.out.println("请输入学生成绩:");
        double score = scan.nextDouble();
        scan.close();
        return new Student(id, name, age, score);
    }
}
