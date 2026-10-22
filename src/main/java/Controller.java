import java.util.ArrayList;

public class Controller {
    MovieCollection collection = new MovieCollection();


    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre){
        collection.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
    }

    public ArrayList<Movie> searchMovies(String searchText){
        return collection.searchMovies(searchText);
    }

    public boolean deleteMovie(Movie movie){
        return collection.deleteMovie(movie);
    }

}
