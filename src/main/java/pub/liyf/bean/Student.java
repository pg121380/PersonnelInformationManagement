package pub.liyf.bean;

import java.io.Serializable;
import java.util.Objects;

public class Student extends Person implements Serializable {


    private static final long serialVersionUID = 4261112403499008253L;

    private double score;

    public Student() {

    }

    public Student(String id, String name, int age, double score) {
        super(id, name, age);
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", score=" + getScore() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(student.getId(), this.getId());
    }

}
