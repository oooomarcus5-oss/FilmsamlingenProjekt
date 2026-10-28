import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Movie {
    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;
    private LocalDate lastWatched; // null = filmen er aldrig set

    public Movie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.inColor = inColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;
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

    public LocalDate getLastWatched() {
        return lastWatched;
    }

    public boolean hasBeenWatched() {
        return lastWatched != null;
    }

    public void markAsWatched(LocalDate date) {
        lastWatched = date;
    }

    // Må kun kaldes, hvis hasBeenWatched() er true
    public long getDaysSinceWatched(LocalDate today) {
        return ChronoUnit.DAYS.between(lastWatched, today);
    }

    public String printMovie() {
        String isColored;
        if (isInColor()) {
            isColored = "farver";
        } else {
            isColored = "sort-hvid";
        }

        String lastWatchedText;
        if (!hasBeenWatched()) {
            lastWatchedText = "Sidst set: aldrig";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            lastWatchedText = "Sidst set: " + lastWatched.format(formatter)
                    + " (for " + getDaysSinceWatched(LocalDate.now()) + " dage siden)";
        }

        return getTitle() + " (" + getYearCreated() + ")" +
                "\n" + getDirector() +
                "\n" + getGenre() +
                "\n" + getLengthInMinutes() + " Minutter, " + isColored +
                "\n" + lastWatchedText;
    }
}