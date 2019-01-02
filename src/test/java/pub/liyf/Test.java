package pub.liyf;

import pub.liyf.bean.Employee;
import pub.liyf.bean.Person;
import pub.liyf.bean.Student;
import pub.liyf.exception.PersonOpException;
import pub.liyf.repository.FileOperate;

import java.util.ArrayList;
import java.util.List;

public class Test {

    @org.junit.Test
    public void testOutput() throws PersonOpException {
        ArrayList<Person> list = new ArrayList<>();
        Person person = new Student("1", "1", 1, 1);
        list.add(person);
        Person person2 = new Student("2", "2", 1, 1);
        list.add(person2);

        Person person3 = new Student("3", "3", 3, 3);
        list.add(person3);

        Person person1 = new Employee("4", "4", 1, 2, "4");
        list.add(person1);

    }

}
