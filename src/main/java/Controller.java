import java.util.ArrayList;

public class Controller {
    MovieCollection collection = new MovieCollection();


    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre){
        collection.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
    }

    public ArrayList<String> searchMovies(String searchText){
        return collection.searchMovies(searchText);
    }

}
