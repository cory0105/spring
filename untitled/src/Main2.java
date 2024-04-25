import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 4.4),
                new Book("Moby Dick", "Herman Melville", 1851, 3.9),
                new Book("To Kill a Mockingbird", "Harper Lee", 1960, 4.3),
                new Book("Pride and Prejudice", "Jane Austen", 1813, 4.2),
                new Book("1984", "George Orwell", 1949, 4.6)
        );

//        4.0 이상의 평점을 받은 책들의 제목을 알파벳순으로 정렬하여 반환하세요.
        System.out.println(
                books.stream().filter(book -> book.getRating() >= 4).
                        sorted(Comparator.comparing(Book::getTitle)).map(Book::getTitle).collect(Collectors.toList())
        );

//        각 저자가 집필한 책들의 평균 평점을 계산하여, 저자의 이름과 해당 평균 평점을 맵 형태로 반환하세요.
        System.out.println(
                books.stream().collect(Collectors.groupingBy(Book::getTitle, Collectors.averagingDouble(Book::getRating)))
        );

//        가장 오래된 책을 반환하세요.
        System.out.println(
                books.stream().max(Comparator.comparing(Book::getPublicationYear)).map(Book::getTitle).get()
        );

//        title이 특정 문자열을 포함하는 모든 책을 추출하여 반환하세요.
        System.out.println(
                books.stream().filter(book -> book.getTitle().contains("a")).collect(Collectors.toList())
        );
    }
}

class Book{
    private String title;
    private String author;
    private int publicationYear;
    private double rating;

    public Book() {
    }

    public Book(String title, String author, int publicationYear, double rating) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" + title + '\'' + author + '\'' + publicationYear + rating + '}';
    }
}