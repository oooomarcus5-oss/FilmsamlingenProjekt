import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    MovieCollection collection = new MovieCollection();
    FileHandler fileHandler = new FileHandler("movies.csv");

    private boolean changed;

    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        changed = true;
        collection.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        return collection.searchMovies(searchText);
    }

    public boolean editMovie(Movie movie, String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        boolean edited = collection.editMovie(movie, title, director, yearCreated, inColor, lengthInMinutes, genre);
        if(edited){
            changed = true;
        }
        return edited;

    }

    public ArrayList<Movie> getMovies() {
        return collection.getMovies();
    }

    public boolean deleteMovie(Movie movie) {
        boolean deleted = collection.deleteMovie(movie);
        if(deleted){
            changed = true;
        }
        return deleted;
    }

    public void markAsWatched(Movie movie) {
        movie.markAsWatched(LocalDate.now());
        changed = true;
    }

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days) {
        LocalDate limit = LocalDate.now().minusDays(days);
        return collection.getMoviesNotWatchedSince(limit);
    }

    public long getDaysSinceWatched(Movie movie) {
        return movie.getDaysSinceWatched(LocalDate.now());
    }

    public boolean getChanged(){
        return changed;
    }
    public void setChanged(boolean setChanged){
        this.changed = setChanged;
    }

    public boolean saveMoviesIfChanged() throws FileNotFoundException {
        if(!changed){
            return false;
        }
        fileHandler.saveMovies(collection.getMovies());
        changed = false;
        return true;
    }

    public int loadMovies() throws FileNotFoundException {
        ArrayList<Movie> movies = fileHandler.loadMovies();
        for(Movie movie : movies){
            collection.addMovie(movie);
        }
        return movies.size();
    }

    public int getSkippedLineCount(){
        return fileHandler.getSkippedLineCount();
    }

}

