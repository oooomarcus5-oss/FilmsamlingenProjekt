import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;

public class Movie {
    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;
    private String isColored;
    private LocalDate lastWatched;

    public Movie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.inColor = inColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
    }

    public LocalDate getLastWatched(){
        return lastWatched;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setInColor(boolean inColor) {
        this.inColor = inColor;
    }

    public boolean isInColor() {
        return inColor;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public String printMovie() {
        if (isInColor()) {
            isColored = "farvet";
        } else {
            isColored = "sort/hvid";
        }
        String watchedInfo;
        if(lastWatched != null){
            LocalDate today = LocalDate.now();
            long days = ChronoUnit.DAYS.between(lastWatched, today);
            watchedInfo = "Sidst set: " + days + " dage siden " + lastWatched;
        }
        else{
            watchedInfo = "Sidst set: Aldrig";
        }
        return getTitle() + " " + "(" + getYearCreated() + ") " + "\n" + getDirector() + "\n" + getGenre() + "\n" + getLengthInMinutes() + " Minutter, " + isColored + "\n" + watchedInfo;

    }
    public boolean hasBeenWatched(){
        return lastWatched != null;
    }
    public void markAsWatched(LocalDate date){
        lastWatched = date;
    }
    public long getDaysSinceWatched(LocalDate today){
        return ChronoUnit.DAYS.between(lastWatched, today);
    }

}
