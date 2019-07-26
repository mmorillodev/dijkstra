import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opt, qtdVertices, fst, scd;
        boolean clearBuffer = false;
        Dijkstra dijkstra;

        System.out.println("Number of vertices: ");
        qtdVertices = getInt(scanner);

        if(qtdVertices < 0) {
            System.err.println("Invalid number!!");
            return;
        }

        dijkstra = new Dijkstra(qtdVertices, true);

        do {
            System.out.print(getPanel());

            if(clearBuffer)
                scanner.nextLine();

            opt = getInt(scanner);

            if(opt == -1)
                clearBuffer = true;
            else
                clearBuffer = false;

            if(opt == 1) {
                System.out.println("Type two vertices to be linked: ");

                fst = getInt(scanner);
                scd = getInt(scanner);

                if(!dijkstra.link(fst, scd)) {
                    System.out.println("Failed Linking!");
                }
            }
            else if(opt == 2) {
                System.out.println("Type two vertices to be unlinked: ");

                fst = getInt(scanner);
                scd = getInt(scanner);

                if (!dijkstra.unLink(fst, scd)) {
                    System.out.println("Failed to unlink");
                }
            }
            else if(opt == 3) {
                System.out.println(dijkstra.toString());
            }
            else if(opt == 4) {
                cls();
            }
            else if(opt == 0);
            else {
                System.err.println("Invalid Option!\n");
            }
        } while(opt != 0);
    }

    //This method is used to encapsulate try catch block with nextInt method
    private static int getInt(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    private static String getPanel() {
        return  "1- Link\n" +
                "2- Unlink\n" +
                "3- Show connections\n" +
                "4- Clear Log\n" +
                "0- Exit\n\n" +
                "Option: ";
    }

    private static void cls() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}