package pub.liyf;

import pub.liyf.bean.Student;
import pub.liyf.dao.StudentDao;
import pub.liyf.exception.StudentNotFoundException;
import pub.liyf.utils.CommonUtil;

public class Main {

    public static void main(String[] args) throws StudentNotFoundException {
        StudentDao dao = new StudentDao();
        Student student1 = new Student("1", "学生1", 18, 99.9);
        dao.add(student1);
        Student student2 = new Student("2", "学生2", 18, 99.9);
        dao.add(student2);
        Student student3 = new Student("3", "学生3", 18, 99.9);
        dao.add(student3);
        Student student4 = new Student("4", "学生4", 18, 99.9);
        dao.add(student4);
        dao.list();
        dao.delete("2");
        dao.list();
    }
}
