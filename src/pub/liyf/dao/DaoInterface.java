package pub.liyf.dao;

import pub.liyf.bean.Person;
import pub.liyf.exception.PersonNotFoundException;
import pub.liyf.exception.StudentNotFoundException;

public interface DaoInterface {
    public void add(Person person);

    public void list();

    public Person selectOne(String id) throws PersonNotFoundException;

    public void delete(String id) throws PersonNotFoundException;

    public Person edit(String id) throws PersonNotFoundException;

}
