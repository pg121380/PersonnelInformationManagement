package pub.liyf.bean;

public class Employee extends Person {
    private double salary;
    private String job;

    public Employee() {

    }

    public Employee(String id, String name, int age, double salary, String job) {
        super(id, name, age);
        this.salary = salary;
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + getId() +"\'" +
                "name='" + getName() + '\'' +
                "age=" + getAge() +
                ",salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }
}
