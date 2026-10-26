import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    MovieCollection collection = new MovieCollection();


    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre){
        collection.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
    }

    public ArrayList<Movie> searchMovies(String searchText){ return collection.searchMovies(searchText);
    }

    public boolean editMovie(Movie movie, String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre){
        return collection.editMovie(movie, title, director, yearCreated, inColor, lengthInMinutes, genre);

    }

    public ArrayList<Movie> getMovies(){
        return collection.getMovies();
    }
    public boolean deleteMovie(Movie movie){
        return collection.deleteMovie(movie);
    }
    public void markAsWatched(Movie movie) {
        movie.markAsWatched(LocalDate.now());
    }
    public ArrayList<Movie> getMoviesNotWatchedForDays(int days) {
        LocalDate limit = LocalDate.now().minusDays(days);
        return collection.getMoviesNotWatchedSince(limit);
    }
}
