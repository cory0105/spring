import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("John", 20, 3.5),
                new Student("Jane", 22, 2.8),
                new Student("Emily", 19, 3.2),
                new Student("Michael", 21, 4.0),
                new Student("Sarah", 18, 3.1)
        );

//        학점이 3.0 이상인 학생들의 이름을 추출하여 목록으로 반환하세요.
        System.out.println(students.stream().filter(s->s.grade >= 3));
//        학생들의 나이 평균을 구하세요.

//        나이가 가장 많은 학생을 찾아 반환하세요.
        System.out.println(students.stream().sorted(Comparator.comparing(Student::getAge).reversed()).limit(1));
//        나이가 18세 이상인 학생들이 있는지 여부를 확인하세요.

    }
}