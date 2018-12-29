package pub.liyf.bean;

public class Student extends Person {
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
                '}' + super.toString();
    }
}
