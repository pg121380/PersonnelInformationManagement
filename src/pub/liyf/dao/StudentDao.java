package pub.liyf.dao;

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
    private Scanner scan = new Scanner(System.in);

    @Override
    public void add() {
        if (count == MAX_STUDENT_NUMBER - 1){
            System.err.println("人数已经超过" + MAX_STUDENT_NUMBER + "!");
            return;
        }
        for(int i = count;i < 10;i++){
            System.out.println("请输入第" + (count + 1) + "位学生的信息:");
            Student student = null;
            try{
                student = createStudent();
            } catch (PersonIdDuplicatedException e){
                System.err.println(e.getMessage());
                i--;
                continue;
            }
            students[count++] = student;
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
        if(count == 0){
            System.err.println("系统中没有数据，请先进行输入！");
            return;
        }
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
        System.out.println("删除成功！");
    }

    @Override
    public Person edit(String id) throws StudentNotFoundException {
        int targetIndex = getStudentIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new StudentNotFoundException("没有找到id为" + id + "的学生");
        }
        Student student = students[targetIndex];
        System.out.println("修改前学生的信息为: " + student);
        System.out.println("请输入新的学生姓名:");
        String name = scan.next();
        System.out.println("请输入新的学生年龄:");
        int age = scan.nextInt();
        System.out.println("请输入新的学生成绩:");
        double score = scan.nextDouble();

        Student newStudent = new Student(student.getId(), name, age, score);
        students[targetIndex] = newStudent;
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
        System.out.println("请输入学生id:");
        String id = scan.next();
        if(isIdDuplicated(id)){
            throw new PersonIdDuplicatedException("该id:" + id + "已重复！请重新输入");
        }
        System.out.println("请输入学生姓名:");
        String name = scan.next();
        System.out.println("请输入学生年龄:");
        int age = scan.nextInt();
        System.out.println("请输入学生成绩:");
        double score = scan.nextDouble();
        return new Student(id, name, age, score);
    }

    public boolean isIdDuplicated(String id){
        if(count == 0){
            return false;
        }
        boolean isDuplicated = false;
        for(int i = 0;i < count;i++){
            if(students[i].getId().equals(id)){
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }
}
