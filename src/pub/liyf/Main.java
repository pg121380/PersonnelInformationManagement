package pub.liyf;

import pub.liyf.bean.Student;
import pub.liyf.dao.StudentDao;

public class Main {

    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        dao.add(new Student("s-1", "学生1", 19, 12.5));
        dao.list();
    }
}
