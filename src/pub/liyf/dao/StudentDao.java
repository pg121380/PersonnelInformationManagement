package pub.liyf.dao;

import pub.liyf.bean.Person;
import pub.liyf.bean.Student;
import pub.liyf.exception.PersonIdDuplicatedException;
import pub.liyf.exception.StudentNotFoundException;
import pub.liyf.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 对学生的操作类
 */
public class StudentDao implements DaoInterface {

    private Scanner scan = new Scanner(System.in);
    private final int MAX_STUDENT_NUMBER = 10;
    private ArrayList<Student> students = new ArrayList<>(MAX_STUDENT_NUMBER);
    private final int UNFOUND_INDEX = -1;

    @Override
    public void add() {
        if(students.size() == MAX_STUDENT_NUMBER){
            System.err.println("人数已经超过" + MAX_STUDENT_NUMBER + "！");
            return;
        }
        for(int i = students.size();i < MAX_STUDENT_NUMBER;i++){
            System.out.println("请输入第" + (i + 1) + "位学生的信息：");
            Student student;
            try {
                student = createStudent();
            } catch (PersonIdDuplicatedException e){
                System.err.println(e.getMessage());
                i--;
                continue;
            }
            students.add(student);
            System.out.println("是否继续输入学生:Y/N(yes/no)");
            String choice = scan.next();
            if(!(choice.equals("Y") || choice.equals("y"))){
                break;
            }
        }
    }

    @Override
    public void list() {
        if(students.isEmpty()){
            System.err.println("系统中还没有数据，请先进行输入！");
        }
        System.out.println(CommonUtil.spiltLine);
        for (Student student: students) {
            System.out.println(student);
        }
        System.out.println(CommonUtil.spiltLine);
    }

    @Override
    public Person selectOne(String id) throws StudentNotFoundException {
        Student student;
        int targetIndex = getStudentIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new StudentNotFoundException("没有找到id为" + id + "的学生");
        }
        student = students.get(targetIndex);
        return student;
    }

    @Override
    public ArrayList<Person> selectByLike(String partOfName) throws StudentNotFoundException {
        ArrayList<Person> list = getPersonsByLike(partOfName);
        if(list.isEmpty()){
            throw new StudentNotFoundException("没有找到姓名包含'" + partOfName + "'的学生！");
        }
        return list;
    }

    @Override
    public void delete(String id) throws StudentNotFoundException {
        int targetIndex = getStudentIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new StudentNotFoundException("没有找到id为" + id + "的学生");
        }
        students.remove(targetIndex);
        System.out.println("删除成功！");
    }

    @Override
    public Person edit(String id) throws StudentNotFoundException {
        int targetIndex = getStudentIndex(id);
        if(targetIndex == UNFOUND_INDEX){
            throw new StudentNotFoundException("没有找到id为" + id + "的学生");
        }
        Student student = students.get(targetIndex);
        System.out.println("修改前学生的信息为: " + student);

        System.out.println("请输入新的学生姓名:");
        String name = scan.nextLine();
        System.out.println("请输入新的学生年龄:");
        int age = scan.nextInt();
        System.out.println("请输入新的学生成绩:");
        double score = scan.nextDouble();

        Student newStudent = new Student(id, name, age, score);
        students.set(targetIndex, newStudent);
        return newStudent;
    }

    private Student createStudent() throws PersonIdDuplicatedException{
        System.out.println("请输入学生id:");
        String id = scan.next();
        if(isIdDuplicated(id)){
            throw new PersonIdDuplicatedException("该ID:" + id + "已重复！请重新输入");
        }
        System.out.println("请输入学生姓名:");
        String name = scan.next();
        System.out.println("请输入学生年龄:");
        int age = scan.nextInt();
        System.out.println("请输入学生成绩:");
        double score = scan.nextDouble();
        return new Student(id, name, age, score);
    }

    private boolean isIdDuplicated(String id){
        if(students.isEmpty()){
            return false;
        }
        boolean isDuplicated = false;
        for (Student student:students) {
            if(student.getId().equals(id)){
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }

    private int getStudentIndex(String id){
        Student student = new Student();
        student.setId(id);
        return students.indexOf(student);
    }
    private ArrayList<Person> getPersonsByLike(String partOfName){
        ArrayList<Person> students = new ArrayList<>();

        for (Student student:
                this.students) {
            if(student.getName().contains(partOfName)){
                students.add(student);
            }
        }
        return students;
    }
}
