import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FileHandler {
    private static final String HEADER = "title;director;yearCreated;inColor;lengthInMinutes;genre;lastWatched";
    private String fileName;

    public FileHandler(String fileName){
    }

    public void saveMovie(ArrayList<Movie> movies) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(fileName));
        output.println(HEADER);
        for(Movie movie : movies){
            output.println(toCsvLine(movie));
        }
        output.close();
    }

    private String toCsvLine(Movie movie){
        String lastWatched = "";
        if(movie.hasBeenWatched()){
            lastWatched = movie.getLastWatched().toString();
        }
        return movie.getTitle() + ";" + movie.getDirector() + ";" + movie.getYearCreated() + ";" + movie.isInColor() + ";" + movie.getLengthInMinutes() + ";" + movie.getGenre() + ";" + lastWatched;
    }

    private Movie parseMovie(String line){
        String[] fields = line.split(";", -1);
        if(fields.length != 7){
            return null;
        }
        try{
            String title = fields[0];
            String director = fields[1];
            int yearCreated = Integer.parseInt(fields[2]);
            boolean inColor = Boolean.parseBoolean(fields[3]);
            int lengthInMinutes = Integer.parseInt(fields[4]);
            String genre = fields[5];

            Movie movie = new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);
            if(!fields[6].isEmpty()){
                movie.markAsWatched(LocalDate.parse(fields[6]));
            }
            return movie;
        }
        catch(IllegalArgumentException e){
            return null;
        }
        catch(DateTimeParseException e){
            return null;
        }
    }

}
