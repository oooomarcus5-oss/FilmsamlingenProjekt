import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();
    private boolean running = true;


    public void startProgram() {

        while (running) {
            System.out.println("1. Opret en film");
            System.out.println("2. Vis alle film");
            System.out.println("3. Søg efter film");
            System.out.println("4. Rediger en film");
            System.out.println("5. Slet en film");
            System.out.println("0. Afslut");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addMovie();
                case "2" -> showAllMovies();
                case "3" -> searchMovies();
                case "4" -> System.out.println("a");
                case "5" -> deleteMovie();
                case "0" -> running = false;
                default -> System.out.println("Ukendt kommando: " + choice);
            }

        }
    }

    public void addMovie(){
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
        controller.addMovie(title, director, yearCreated, isColored, lengthInMinutes, genre);

    }
    public void searchMovies(){
        System.out.print("Søg efter titel: ");
        String searchText = scanner.nextLine();
        ArrayList<Movie> movies = controller.searchMovies(searchText);

        for(Movie movie : movies){
            System.out.println(movie.printMovie());
        }
    }



    public void deleteMovie(){
        Movie movie = selectMovie();
        if (movie == null){
            return;
        }
        if(controller.deleteMovie(movie)){
            System.out.println("\"" + movie.getTitle() + "\" er slettet");
        } else {
            System.out.println("Filmen kunne ikke slettes");
        }
    }
    private void showAllMovies() {
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
}

