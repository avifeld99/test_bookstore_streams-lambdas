package be.intecbrussel;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Stream;

public class BookApp {
    public static void main(String[] args) {

        Person person = new Person("Pearl", "De Smet", LocalDate.of(1991,3,1));
        Person person1 = new Person("Roald", "Dahl", LocalDate.of(1950,3,1));
        Person person2 = new Person("Stephenie", "Meyer", LocalDate.of(1980,3,1));

        Book book = new Book("Best book ever", person, LocalDate.of(2022,4,3), "horror");
        Book book2 = new Book("Matilda", person1, LocalDate.of(1980,4,3), "kids");
        Book book3 = new Book("BFG", person1, LocalDate.of(1990,4,3), "adventure");
        Book book4 = new Book("Twilight", person2, LocalDate.of(2002,4,3), "romance");
        Book book5 = new Book("Breaking Dawn", person2, LocalDate.of(2016,5,3), "romance");

        Book[] books = {book,book2,book3,book4,book5};
        Book recentBook = getNewestBook(books);
        //System.out.println(recentBook);

        //printYoungestWriter(books);

        //printSortedByTitle(books);

        printBooksReleasedIn2016(books);
    }

    public static Book getNewestBook(Book[] books) {
        Book[] sortedBooks = Stream.of(books)
                .sorted(Comparator.comparing(Book::getReleaseDate))
                .toArray(Book[]::new);

        return sortedBooks [sortedBooks.length -1];
    }

    public static void printYoungestWriter(Book[] books) {
        Person[] people = Stream.of(books)
                .map(Book::getAuthor)
                .distinct()
                .sorted(Comparator.comparing(Person::getDateOfBirth).reversed())
                .toArray(Person[]::new);

        System.out.println(people[0]);
    }

    public static void printSortedByTitle(Book[] books) {
        Stream.of(books)
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

    public static void printBooksReleasedIn2016(Book[] books) {
        Stream.of(books)
                .filter(book -> book.releaseDate.getYear() == 2016)
                .forEach(System.out::println);
    }
}
