import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();
    private boolean running = true;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Feltet må ikke være tomt. Prøv igen.");
        }
    }

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
                default -> System.out.println("Vælg et nr fra listen\n" + choice + " er ikke på listen");

            }
        }
    }

    public void addMovie() {
        String title = readNonEmpty("Titel: ");
        String director = readNonEmpty("Instruktør: ");
        int yearCreated = readInt("Årstal: ");
        boolean inColor = readYesNo("i farver (Ja/Nej): ");
        int lengthInMinutes = readInt("Længde i minutter: ");
        String genre = readNonEmpty("Genre: ");

        controller.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
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

    public void searchMovies() {
        System.out.print("Søg efter titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);

        if (movies.isEmpty()) {
            System.out.println("Ingen film matcher \"" + searchText + "\".");
        } else {
            for (Movie movie : movies) {
                System.out.println(movie.printMovie());
                System.out.println("-----------------");
            }
        }
    }

    public void editMovie() {
        Movie movie = selectMovie();

        if (movie == null) {
            return;
        }

        System.out.println("Lad feltet være tomt, hvis det ikke skal ændres");

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

        int yearCreated = readOptionalInt("Årstal: ", movie.getYearCreated());
        boolean inColor = readOptionalYesNo("i farver (Ja/Nej): ", movie.isInColor());
        int lengthInMinutes = readOptionalInt("Længde i minutter: ", movie.getLengthInMinutes());

        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        if (genre.isEmpty()) {
            genre = movie.getGenre();
        }

        boolean success = controller.editMovie(movie, title, director, yearCreated,
                inColor, lengthInMinutes, genre);

        if (success) {
            System.out.println("Filmen er blevet redigeret.");
        } else {
            System.out.println("Filmen kunne ikke redigeres.");
        }
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

    public void markAsWatched() {
        Movie movie = selectMovie();

        if (movie == null) {
            return;
        }

        controller.markAsWatched(movie);
        System.out.println(movie.getTitle() + " er markeret som set");
    }

    public void findMovieSinceDays() {
        int days = readInt("Hvor mange dage siden mindst? ");
        ArrayList<Movie> movies = controller.getMoviesNotWatchedForDays(days);

        if (movies.isEmpty()) {
            System.out.println("Ingen film er urørt i " + days + " dage eller mere.");
        } else {
            System.out.println("Film, du ikke har set i " + days + " dage eller aldrig har set:");
            for (Movie movie : movies) {
                System.out.println("  " + movie.getTitle() + " – sidst set: " + lastWatchedText(movie));
            }
        }
    }

    // Finder en film via søgning og nummer. Returnerer null, hvis ingen film findes, eller brugeren fortryder.
    private Movie selectMovie() {
        System.out.print("Søg efter titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);

        if (movies.isEmpty()) {
            System.out.println("Ingen film matcher \"" + searchText + "\".");
            return null;
        }

        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getTitle()
                    + " (" + movies.get(i).getYearCreated() + ")");
        }

        while (true) {
            int number = readInt("Vælg nummer (0 for at fortryde): ");

            if (number == 0) {
                System.out.println("Fortrudt.");
                return null;
            }
            if (number >= 1 && number <= movies.size()) {
                return movies.get(number - 1);
            }
            System.out.println("Vælg et tal mellem 1 og " + movies.size() + " eller 0 for at fortryde.");
        }
    }

    // Spørger, til brugeren skriver et helt tal
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }

    // Som readInt, men tomt svar giver den nuværende værdi (bruges ved redigering)
    private int readOptionalInt(String prompt, int currentValue) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return currentValue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }

    // Spørger, til brugeren skriver ja, j, nej eller n
    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("j")) {
                return true;
            }
            if (input.equalsIgnoreCase("nej") || input.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Skriv ja eller nej. Prøv igen.");
        }
    }

    // Som readYesNo, men tomt svar giver den nuværende værdi (bruges ved redigering)
    private boolean readOptionalYesNo(String prompt, boolean currentValue) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return currentValue;
            }
            if (input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("j")) {
                return true;
            }
            if (input.equalsIgnoreCase("nej") || input.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Skriv ja eller nej. Prøv igen.");
        }
    }

    private String lastWatchedText(Movie movie) {
        if (!movie.hasBeenWatched()) {
            return "aldrig";
        }
        long days = controller.getDaysSinceWatched(movie);
        return movie.getLastWatched().format(dateFormatter) + " (for " + days + " dage siden)";
    }
}