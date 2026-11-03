package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
    }

    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        addMovie(new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre));
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        sortMovies();
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public int getNumberOfMovies() {
        return movies.size();
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        ArrayList<Movie> matches = new ArrayList<>();
        String text = searchText.toLowerCase();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(text)) {
                matches.add(movie);
            }
        }
        return matches;
    }

    public boolean editMovie(Movie movie, String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        if (!movies.contains(movie)) {
            return false;
        }
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setYearCreated(yearCreated);
        movie.setInColor(inColor);
        movie.setLengthInMinutes(lengthInMinutes);
        movie.setGenre(genre);
        sortMovies();
        return true;
    }

    public boolean deleteMovie(Movie movie) {
        return movies.remove(movie);
    }

    public ArrayList<Movie> getMoviesNotWatchedSince(LocalDate date) {
        ArrayList<Movie> matches = new ArrayList<>();
        for (Movie movie : movies) {
            if (!movie.hasBeenWatched() || movie.getLastWatched().isBefore(date)) {
                matches.add(movie);
            }
        }
        return matches;
    }

    private void sortMovies() {
        movies.sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
    }
    public ArrayList<Movie> getMoviesSortedByTitle() {
        ArrayList<Movie> sorted = new ArrayList<>(movies);
        Collections.sort(sorted);
        return sorted;
    }
    public ArrayList<Movie> getMoviesSorted(Comparator<Movie> comparator) {
        ArrayList<Movie> sorted = new ArrayList<>(movies);
        sorted.sort(comparator);
        return sorted;
    }

}