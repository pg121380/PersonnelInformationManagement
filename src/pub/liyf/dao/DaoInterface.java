package pub.liyf.dao;

import pub.liyf.bean.Person;

public interface DaoInterface {
    public void add(Person person);

    public void list();

    public Person selectOne();

    public void delete(String id);

    public Person edit(String id);

}
