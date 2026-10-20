import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();
    private boolean running = true;


    public void startProgram() {
        System.out.println("1. Opret en film");
        System.out.println("2. Vis alle film");
        System.out.println("3. Søg efter film");
        System.out.println("4. Rediger en film");
        System.out.println("5. Slet en film");
        System.out.println("0. Afslut");

        while (running) {
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addMovie();
                case "2" -> System.out.println("a");
                case "3" -> searchMovies();
                case "4" -> System.out.println("a");
                case "5" -> System.out.println("a");
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
        startProgram();
    }
    public void searchMovies(){
        System.out.print("Søg efter titel: ");
        String searchText = scanner.nextLine();
        System.out.println(controller.searchMovies(searchText));
    }
}
