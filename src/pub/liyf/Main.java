package pub.liyf;

import pub.liyf.bean.Student;
import pub.liyf.dao.StudentDao;
import pub.liyf.exception.StudentNotFoundException;

public class Main {

    public static void main(String[] args) throws StudentNotFoundException {
        StudentDao dao = new StudentDao();
        Student student1 = StudentDao.createStudent();
        dao.add(student1);
        Student student2 = StudentDao.createStudent();
        dao.add(student2);
        Student student3 = StudentDao.createStudent();
        dao.add(student3);
        dao.list();
        System.out.println(dao.selectOne("1"));

        dao.edit("1");
    }
}
