import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();
    private boolean running = true;

    public void startProgram() {

        while (running) {
            System.out.println("1. Opret en film");
            System.out.println("2. Vis alle film");
            System.out.println("3. Søg efter film");
            System.out.println("4. Rediger en film");
            System.out.println("5. Slet en film");
            System.out.println("6. Marker en film som set i dag");
            System.out.println("7. Find film, du ikke har set længe");
            System.out.println("0. Afslut");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addMovie();
                case "2" -> showAllMovies();
                case "3" -> searchMovies();
                case "4" -> editMovie();
                case "5" -> deleteMovie();
                case "6" -> markAsWatched();
                case "7" -> findMovieSinceDays();
                case "0" -> running = false;
                default -> System.out.println("Ukendt kommando: " + choice);
            }
        }
    }

    public void addMovie() {
        System.out.print("Titel: ");
        String title = scanner.nextLine();

        System.out.print("Instruktør: ");
        String director = scanner.nextLine();

        System.out.print("Årstal: ");
        int yearCreated = Integer.parseInt(scanner.nextLine());

        System.out.print("i farver (Ja/Nej): ");
        String inColor = scanner.nextLine();
        boolean isColored;
        isColored = inColor.equalsIgnoreCase("Ja");

        System.out.print("Længde i minutter: ");
        int lengthInMinutes = Integer.parseInt(scanner.nextLine());

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        controller.addMovie(title, director, yearCreated, isColored,
                lengthInMinutes, genre);
    }

    public void searchMovies() {
        System.out.print("Søg efter titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);

        for (Movie movie : movies) {
            System.out.println(movie.printMovie());
        }

        if (movies.isEmpty()) {
            System.out.println("Ingen film matcher \"" + searchText + "\".");
        }
    }

    public Movie selectMovie() {
        System.out.print("Søg efter titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);

        if (movies.isEmpty()) {
            System.out.println("Ingen film matcher \"" + searchText + "\".");
            return null;
        }

        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).printMovie());
        }

        System.out.print("Vælg nummer: ");
        int number = Integer.parseInt(scanner.nextLine());

        return movies.get(number - 1);
    }

    public void deleteMovie() {
        Movie movie = selectMovie();

        if (movie == null) {
            return;
        }

        if (controller.deleteMovie(movie)) {
            System.out.println("\"" + movie.getTitle() + "\" er slettet");
        } else {
            System.out.println("Filmen kunne ikke slettes");
        }
    }

    public void editMovie() {
        Movie movie = selectMovie();

        if (movie == null) {
            return;
        }

        System.out.println("Lad feltet være tomt hvis det ikke skal ændres");

        System.out.print("Titel: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            title = movie.getTitle();
        }

        System.out.print("Instruktør: ");
        String director = scanner.nextLine();
        if (director.isEmpty()) {
            director = movie.getDirector();
        }

        System.out.print("Årstal: ");
        String year = scanner.nextLine();
        int yearCreated;

        if (year.isEmpty()) {
            yearCreated = movie.getYearCreated();
        } else {
            yearCreated = Integer.parseInt(year);
        }

        System.out.print("i farver (Ja/Nej): ");
        String color = scanner.nextLine();
        boolean inColor;

        if (color.isEmpty()) {
            inColor = movie.isInColor();
        } else {
            inColor = color.equalsIgnoreCase("Ja");
        }

        System.out.print("Længde i minutter: ");
        String length = scanner.nextLine();
        int lengthInMinutes;

        if (length.isEmpty()) {
            lengthInMinutes = movie.getLengthInMinutes();
        } else {
            lengthInMinutes = Integer.parseInt(length);
        }

        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        if (genre.isEmpty()) {
            genre = movie.getGenre();
        }

        boolean success = controller.editMovie(
                movie, title, director, yearCreated,
                inColor, lengthInMinutes, genre
        );

        if (success) {
            System.out.println("Filmen er blevet redigeret.");
        } else {
            System.out.println("Filmen kunne ikke redigeres.");
        }
    }

    public void showAllMovies() {
        ArrayList<Movie> movies = controller.getMovies();

        if (movies.isEmpty()) {
            System.out.println("Der er ingen film i samlingen endnu.");
        } else {
            for (Movie movie : movies) {
                System.out.println(movie.printMovie());
                System.out.println("-----------------");
            }
        }
    }
    public void findMovieSinceDays(){

    }
    public void markAsWatched(){

    }
}
