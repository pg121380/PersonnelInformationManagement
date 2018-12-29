package pub.liyf.dao;

import pub.liyf.bean.Person;
import pub.liyf.bean.Student;

public class StudentDao implements DaoInterface {

    private Student[] students = new Student[50];
    private int count = 0;

    @Override
    public void add(Person person) {
        students[count++] = (Student)person;
    }

    @Override
    public void list() {
        for(Student student:students){
            if(student == null){
                continue;
            }
            System.out.println(student);
        }
    }

    @Override
    public Person selectOne() {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Person edit(String id) {
        return null;
    }
}
