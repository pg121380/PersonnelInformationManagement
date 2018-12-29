package pub.liyf.dao;

import pub.liyf.bean.Person;
import pub.liyf.bean.Student;
import pub.liyf.exception.StudentNotFoundException;
import pub.liyf.utils.CommonUtil;

import java.util.Scanner;

/**
 * 对学生的操作类
 */
public class StudentDao implements DaoInterface {

    private int MAX_STUDENT_NUMBER = 50;
    private Student[] students = new Student[MAX_STUDENT_NUMBER];
    private int count = 0;
    private static final int UNFOUND_INDEX = -1;

    @Override
    public void add(Person person) {
        students[count++] = (Student)person;
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

}
