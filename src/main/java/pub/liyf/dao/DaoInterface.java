package pub.liyf.dao;

import pub.liyf.bean.Employee;
import pub.liyf.bean.Person;
import pub.liyf.exception.PersonNotFoundException;

import java.util.ArrayList;

public interface DaoInterface {
    void add();

    void list();

    Person selectOne(String id) throws PersonNotFoundException;

    void delete(String id) throws PersonNotFoundException;

    Person edit(String id) throws PersonNotFoundException;

    ArrayList<Person> selectByLike(String partOfName) throws PersonNotFoundException ;

}
