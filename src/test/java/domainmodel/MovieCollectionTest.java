package domainmodel;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    @Test
    void moviesAreSortedByTitleIgnoringCase() {
        // Arrange
        MovieCollection collection = new MovieCollection();
        collection.addMovie("banana", "Instruktør", 2000, true, 100, "Drama");
        collection.addMovie("Apple", "Instruktør", 2000, true, 100, "Drama");
        collection.addMovie("cherry", "Instruktør", 2000, true, 100, "Drama");

        // Act
        ArrayList<Movie> movies = collection.getMovies();

        // Assert
        assertEquals("Apple", movies.get(0).getTitle());
        assertEquals("banana", movies.get(1).getTitle());
        assertEquals("cherry", movies.get(2).getTitle());
    }

    @Test
    void neverWatchedMovieIsIncludedInNotWatchedSince() {
        // Arrange
        MovieCollection collection = new MovieCollection();
        Movie neverWatched = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");
        collection.addMovie(neverWatched);

        // Act
        ArrayList<Movie> result = collection.getMoviesNotWatchedSince(LocalDate.of(2025, 6, 1));

        // Assert
        assertTrue(result.contains(neverWatched));
    }

    @Test
    void notWatchedSinceExcludesMoviesWatchedOnOrAfterTheLimit() {
        // Arrange
        MovieCollection collection = new MovieCollection();
        Movie before = new Movie("Before", "Instruktør", 2000, true, 100, "Drama");
        Movie onLimit = new Movie("OnLimit", "Instruktør", 2000, true, 100, "Drama");
        Movie after = new Movie("After", "Instruktør", 2000, true, 100, "Drama");
        before.markAsWatched(LocalDate.of(2025, 5, 31));
        onLimit.markAsWatched(LocalDate.of(2025, 6, 1));
        after.markAsWatched(LocalDate.of(2025, 6, 2));
        collection.addMovie(before);
        collection.addMovie(onLimit);
        collection.addMovie(after);

        // Act
        ArrayList<Movie> result = collection.getMoviesNotWatchedSince(LocalDate.of(2025, 6, 1));

        // Assert
        assertTrue(result.contains(before));
        assertFalse(result.contains(onLimit));
        assertFalse(result.contains(after));
    }
}