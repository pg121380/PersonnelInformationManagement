package pub.liyf.bean;

public class Employee extends Person {
    private double salary;
    private String job;

    public Employee() {

    }

    public Employee(double salary, String job) {
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
}
