import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection(){
        movies = new ArrayList<>();
    }

    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre){
        movies.add(new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre));
    }

    public ArrayList<Movie> getMovies(){
        return null;
    }
    public int getNumberOfMovies(){
        int counter = 0;
        for(Movie movie : movies){
            counter++;
        }
        return counter;
    }
    public ArrayList<String> searchMovies(String searchText){
        ArrayList<String> matches = new ArrayList<>();
        String text = searchText.toLowerCase();
        for(Movie movie : movies){
            if(movie.getTitle().toLowerCase().contains(text)){
                matches.add(movie.getTitle());
            }
        }
        return matches;
    }
    public boolean editMovie(Movie movie, String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre){
        return false;
    }
    public boolean deleteMovie(Movie movie){
        return movies.remove(movie);
    }

}
