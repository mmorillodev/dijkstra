import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opt, qtdVertices, fst, scd, weight;
        boolean clearBuffer = false;
        Dijkstra dijkstra;

        System.out.println("Number of vertices: ");
        qtdVertices = tryGetInt(scanner);

        //Clear buffer
        scanner.nextLine();

        //Line break
        System.out.println();

        if(qtdVertices < 0) {
            System.err.println("Invalid number!!");
            return;
        }

        System.out.println("Doubly linked? [S][N]");

        dijkstra = new Dijkstra(qtdVertices, tryGetChar(scanner) == 's');

        //Line break;
        System.out.println();

        do {
            System.out.print(getPanel());

            if(clearBuffer)
                scanner.nextLine();

            opt = tryGetInt(scanner);
            System.out.println();

            clearBuffer = opt == -1;

            if(opt == 1) {
                System.out.println("Type two vertices to be linked: ");

                fst = tryGetInt(scanner);
                scd = tryGetInt(scanner);

                if(!dijkstra.link(fst, scd)) {
                    System.out.println("Failed Linking!");
                }
            }
            else if(opt == 2) {
                System.out.println("Type two vertices to be linked: ");

                fst = tryGetInt(scanner);
                scd = tryGetInt(scanner);

                System.out.println("Type the weight of this connection: ");
                weight = tryGetInt(scanner);

                if(!dijkstra.link(fst, scd, (weight <= 0 ? 1 : weight))) {
                    System.err.println("Error linking!");
                }
            }
            else if(opt == 3) {
                System.out.println("Type two vertices to be unlinked: ");

                fst = tryGetInt(scanner);
                scd = tryGetInt(scanner);

                if (!dijkstra.unlink(fst, scd)) {
                    System.out.println("Failed to unlink");
                }
            }
            else if(opt == 4) {
                System.out.println(dijkstra.getMatrixAsString());
                System.out.println();
            }
            else if(opt == 5) {
                System.out.println(dijkstra.toString());
                System.out.println();
            }
            else if(opt == 6) {
                cls();
            }
            else if(opt == 7) {
                dijkstra.unlinkAll();
            }
            else if(opt == 8) {
                System.out.println("Type two Nodes: ");

                fst = tryGetInt(scanner);
                scd = tryGetInt(scanner);

                System.out.println();

                List<Integer> path = dijkstra.getShortestPath(fst, scd);

                if(path != null) {
                    System.out.println(path.toString() + "\n");
                }
                else {
                    System.err.println("Either no path found or invalid values provided!");
                }
            }
            else if (opt != 0) {
                System.err.println("Invalid Option!\n");
            }
        } while(opt != 0);
    }

    //This method is used to encapsulate try catch block with nextInt method
    private static int tryGetInt(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    private static char tryGetChar(Scanner scanner) {
        try {
            return scanner.nextLine().toLowerCase().charAt(0);
        } catch (InputMismatchException | StringIndexOutOfBoundsException e) {
            return 'n';
        }
    }

    private static String getPanel() {
        return  "1- Link\n" +
                "3- Unlink\n" +
                "2- Link with weight\n" +
                "4- Show raw connections\n" +
                "5- Show complete connections\n" +
                "6- Clear Log\n" +
                "7- Clear Graph\n" +
                "8- Get path\n" +
                "0- Exit\n\n" +
                "Option: ";
    }

    private static void cls() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }
}