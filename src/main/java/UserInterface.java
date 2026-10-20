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
                case "1" -> System.out.println("a");
                case "2" -> System.out.println("a");
                case "3" -> System.out.println("a");
                case "4" -> System.out.println("a");
                case "5" -> System.out.println("a");
                case "0" -> running = false;
                default -> System.out.println("Ukendt kommando: " + choice);
            }

        }
    }
}
