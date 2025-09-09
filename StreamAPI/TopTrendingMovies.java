import java.util.*;
import java.util.stream.*;

class Movie {
    private String name;
    private double rating;
    private int year;

    public Movie(String name, double rating, int year) {
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

    public String getName() { return name; }
    public double getRating() { return rating; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return name + " (" + year + ") - Rating: " + rating;
    }
}

public class TopTrendingMovies {
    public static void main(String[] args) {
        List<Movie> movies = Arrays.asList(
            new Movie("Movie A", 8.5, 2024),
            new Movie("Movie B", 7.9, 2023),
            new Movie("Movie C", 9.0, 2022),
            new Movie("Movie D", 8.8, 2024),
            new Movie("Movie E", 9.2, 2025),
            new Movie("Movie F", 7.5, 2025),
            new Movie("Movie G", 8.0, 2024)
        );

        // Get Top 5 Trending Movies (higher rating first, then recent year)
        List<Movie> top5 = movies.stream()
                .filter(m -> m.getRating() >= 7.5) // filter condition (optional)
                .sorted(Comparator.comparingDouble(Movie::getRating).reversed()
                        .thenComparing(Movie::getYear, Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Top 5 Trending Movies:");
        top5.forEach(System.out::println);
    }
}
