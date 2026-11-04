classDiagram
    direction LR

    namespace ui {
        class Main {
            +main(String[] args)$ void
        }
        class UserInterface {
            -Scanner scanner
            -Controller controller
            -DateTimeFormatter dateFormatter
            +startProgram() void
        }
    }

    namespace domainmodel {
        class Controller {
            ~MovieCollection collection
            ~FileHandler fileHandler
            -boolean changed
            +addMovie(...) void
            +searchMovies(String) ArrayList~Movie~
            +editMovie(...) boolean
            +getMovies() ArrayList~Movie~
            +deleteMovie(Movie) boolean
            +markAsWatched(Movie, LocalDate) void
            +getMoviesNotWatchedForDays(int) ArrayList~Movie~
            +getDaysSinceWatched(Movie) long
            +getChanged() boolean
            +setChanged(boolean) void
            +saveMoviesIfChanged() boolean
            +loadMovies() int
            +getSkippedLineCount() int
            +getMoviesSortedBy(SortField) ArrayList~Movie~
            +getMoviesSortedByTitle() ArrayList~Movie~
        }

        class MovieCollection {
            -ArrayList~Movie~ movies
            +addMovie(...) void
            +addMovie(Movie) void
            +getMovies() ArrayList~Movie~
            +getNumberOfMovies() int
            +searchMovies(String) ArrayList~Movie~
            +editMovie(...) boolean
            +deleteMovie(Movie) boolean
            +getMoviesNotWatchedSince(LocalDate) ArrayList~Movie~
            +getMoviesSortedByTitle() ArrayList~Movie~
            +getMoviesSorted(Comparator~Movie~) ArrayList~Movie~
        }

        class Movie {
            -String title
            -String director
            -int yearCreated
            -boolean inColor
            -int lengthInMinutes
            -String genre
            -LocalDate lastWatched
            +setTitle(String) void
            +getTitle() String
            +setDirector(String) void
            +getDirector() String
            +setYearCreated(int) void
            +getYearCreated() int
            +setInColor(boolean) void
            +isInColor() boolean
            +setLengthInMinutes(int) void
            +getLengthInMinutes() int
            +setGenre(String) void
            +getGenre() String
            +getLastWatched() LocalDate
            +hasBeenWatched() boolean
            +markAsWatched(LocalDate) void
            +getDaysSinceWatched(LocalDate) long
            +printMovie() String
            +compareTo(Movie) int
        }

        class SortField {
            <<enumeration>>
            TITLE
            DIRECTOR
            YEAR
            LENGTH
            GENRE
        }

        class TitleComparator {
            +compare(Movie, Movie) int
        }
        class DirectorComparator {
            +compare(Movie, Movie) int
        }
        class YearComparator {
            +compare(Movie, Movie) int
        }
        class LengthComparator {
            +compare(Movie, Movie) int
        }
        class GenreComparator {
            +compare(Movie, Movie) int
        }
    }

    namespace datasource {
        class FileHandler {
            -String fileName
            -int skippedLineCount
            +FileHandler(String)
            +saveMovies(ArrayList~Movie~) void
            +loadMovies() ArrayList~Movie~
            +getSkippedLineCount() int
        }
    }

    %% Relationer / Forbindelser
    Main --> UserInterface : opretter og starter
    UserInterface --> Controller : bruger
    Controller --> MovieCollection : indeholder
    Controller --> FileHandler : bruger
    MovieCollection --> Movie : indeholder (ArrayList)
    FileHandler ..> Movie : indlæser/gemmer
    
    Comparator <|.. TitleComparator : implements
    Comparator <|.. DirectorComparator : implements
    Comparator <|.. YearComparator : implements
    Comparator <|.. LengthComparator : implements
    Comparator <|.. GenreComparator : implements
    Comparable <|.. Movie : implements

