import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class main{

}


public class Employee {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 28, 52000),
                new Employee("Bob", 35, 48000),
                new Employee("Charlie", 40, 60000),
                new Employee("Diana", 30, 49000),
                new Employee("Edward", 45, 70000)
        );

//        연봉이 5만 달러 이상인 직원들의 이름을 목록으로 반환하세요.
        System.out.println(employees.stream().filter(e->e.getSalary() >= 50000).collect(Collectors.toList()));

//        직원들의 연봉의 합계를 구하세요.
        System.out.println(employees.stream().mapToDouble(Employee::getSalary).sum());

//        가장 나이가 많은 직원을 찾아 반환하세요.
        System.out.println(employees.stream().max(Comparator.comparing(Employee::getAge)));

//        나이가 30세 미만인 직원들이 있는지 여부를 확인하세요.
        System.out.println(!employees.stream().filter(e->e.getAge() < 30).collect(Collectors.toList()).isEmpty());
    }

    private String name;
    private int age;
    private double salary;

    public Employee() {
    }

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
