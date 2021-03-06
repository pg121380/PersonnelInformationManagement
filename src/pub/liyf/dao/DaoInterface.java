package pub.liyf.dao;

import pub.liyf.bean.Person;
import pub.liyf.exception.PersonNotFoundException;

public interface DaoInterface {
    public void add();

    public void list();

    public Person selectOne(String id) throws PersonNotFoundException;

    public void delete(String id) throws PersonNotFoundException;

    public Person edit(String id) throws PersonNotFoundException;

}
