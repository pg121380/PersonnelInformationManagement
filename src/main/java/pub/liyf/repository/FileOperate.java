package pub.liyf.repository;

import pub.liyf.bean.Employee;
import pub.liyf.bean.Person;
import pub.liyf.bean.Student;
import pub.liyf.exception.PersonOpException;

import java.io.*;
import java.util.ArrayList;

public class FileOperate {

    private String repositoryPath;

    public FileOperate(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public void saveText(ArrayList<Person> list) throws PersonOpException {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(repositoryPath));
            for (Person person:
                 list) {
                writer.write(person.buildRepositoryString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Person> loadText() throws PersonOpException {
        File file = new File(repositoryPath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return new ArrayList<Person>();
            }
        }

        ArrayList<Person> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine()) != null){
                Person person = buildObject(temp);
                list.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Person buildObject(String string) throws PersonOpException {
        String[] personMsg = string.split(",");
        if(repositoryPath.toLowerCase().startsWith("s")){
            //返回学生
            return new Student(personMsg[0], personMsg[1], Integer.parseInt(personMsg[2]), Double.parseDouble(personMsg[3]));
        } else if(repositoryPath.toLowerCase().startsWith("e")){
            //返回职工
            return new Employee(personMsg[0], personMsg[1], Integer.parseInt(personMsg[2]), Double.parseDouble(personMsg[3]), personMsg[4]);
        } else {
            throw new PersonOpException("请以'Student.txt'或'Employee.txt'命名用于保存数据的文本文件");
        }
    }
}
