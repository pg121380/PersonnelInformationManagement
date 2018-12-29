package pub.liyf.dao;

import pub.liyf.bean.Employee;
import pub.liyf.bean.Person;
import pub.liyf.bean.Student;
import pub.liyf.exception.PersonIdDuplicatedException;
import pub.liyf.exception.StudentNotFoundException;
import pub.liyf.utils.CommonUtil;

import java.util.Scanner;

/**
 * 对学生的操作类
 */
public class StudentDao implements DaoInterface {

    private int MAX_STUDENT_NUMBER = 10;
    private Student[] students = new Student[MAX_STUDENT_NUMBER];
    private int count = 0;
    private static final int UNFOUND_INDEX = -1;

    @Override
    public void add(Person person) {
        if (count == MAX_STUDENT_NUMBER - 1){
            System.err.println("人数已经超过" + MAX_STUDENT_NUMBER + "!");
            return;
        }
        for(int i = 0;i < 10;i++){
            System.out.println("请输入第" + (i + 1) + "位学生的信息:");
            Student student = null;
            try{
                student = createStudent();
            } catch (PersonIdDuplicatedException e){
                System.out.println(e.getMessage());
                i--;
                break;
            }
            students[count++] = student;
        }
    }

    @Override
    public void list() {
        System.out.println(CommonUtil.spiltLine);
        for(Student student:students){
            if(student == null){
                continue;
            }
            System.out.println(student);
        }
        System.out.println(CommonUtil.spiltLine);
    }

    @Override
    public Person selectOne(String id) throws StudentNotFoundException {
        Student student = null;
        int targetIndex = getStudentIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new StudentNotFoundException("没有找到id为" + id + "的学生");
        }
        student = students[targetIndex];
        return student;
    }

    @Override
    public void delete(String id) throws StudentNotFoundException {
        int targetIndex = getStudentIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new StudentNotFoundException("没有找到id为" + id + "的学生");
        }
        for(int i = targetIndex;i <= count;i++){
            students[i] = students[i + 1];
        }
        count--;
    }

    @Override
    public Person edit(String id) throws StudentNotFoundException {
        int targetIndex = getStudentIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new StudentNotFoundException("没有找到id为" + id + "的学生");
        }
        Scanner scan = new Scanner(System.in);
        Student student = students[targetIndex];
        System.out.println("修改前学生的信息为: " + student);
        System.out.println("请输入新的学生姓名:");
        String name = scan.nextLine();
        System.out.println("请输入新的学生年龄:");
        int age = scan.nextInt();
        System.out.println("请输入新的学生成绩:");
        double score = scan.nextDouble();

        Student newStudent = new Student(student.getId(), name, age, score);
        students[targetIndex] = newStudent;
        scan.close();
        return newStudent;
    }

    /* 以下是一些辅助方法 */

    private int getStudentIndex(String id){
        int index = UNFOUND_INDEX;
        for(int i = 0;i <= count;i++) {
            if(students[i].getId().equals(id)){
                index = i;
                break;
            }
        }
        return index;
    }
    public Student createStudent() throws PersonIdDuplicatedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入学生id:");
        String id = scan.nextLine();
        if(isIdDuplicated(id)){
            throw new PersonIdDuplicatedException("该id:" + id + "已重复！请重新输入");
        }
        System.out.println("请输入学生姓名:");
        String name = scan.nextLine();
        System.out.println("请输入学生年龄:");
        int age = scan.nextInt();
        System.out.println("请输入学生成绩:");
        double score = scan.nextDouble();
        scan.close();
        return new Student(id, name, age, score);
    }

    public boolean isIdDuplicated(String id){
        boolean isDuplicated = false;
        for(Student student:students){
            if(student.getId().equals(id)){
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }
}
