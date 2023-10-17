package lab05.ex3;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Movie m = new Movie.Builder("The Matrix", 1999)
            .director(new Person("Wachowski", 1965))
            .writer(new Person("Wachowski", 1965))
            .cast(Arrays.asList(
                new Person("Keanu Reeves", 1964),
                new Person("Laurence Fishburne", 1961),
                new Person("Carrie-Anne Moss", 1967)
            ))
            .locations(Arrays.asList(
                new Place("San Francisco", 37.7749f, 122.4194f),
                new Place("Los Angeles", 34.0522f, 118.2437f),
                new Place("New York", 40.7128f, 74.0060f)
            ))
            .languages(Arrays.asList("English", "Japanese"))
            .genres(Arrays.asList("Action", "Sci-Fi"))
            .isTelevision(false)
            .isNetflix(false)
            .isIndependent(false)
            .build();
        System.out.println(m);
    }
}