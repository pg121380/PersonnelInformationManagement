package pub.liyf.repository;

import pub.liyf.bean.Person;
import pub.liyf.exception.PersonOpException;

import java.io.*;
import java.util.ArrayList;

public class FileOperate {

    private String repositoryPath;

    public FileOperate(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public void saveText(ArrayList<Person> list) throws PersonOpException {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(repositoryPath));
            for (Person person:list) {
                output.writeObject(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
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
            }
            return null;
        }
        ObjectInputStream input;
        ArrayList<Person> list = new ArrayList<>();
        try {
            input = new ObjectInputStream(new FileInputStream(repositoryPath));
            Object object;
            while ((object = input.readObject()) != null){
                Person person = (Person) object;
                list.add(person);
            }
        } catch (EOFException e){

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return list;
    }
}
