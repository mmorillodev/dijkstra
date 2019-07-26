import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opt, qtdVertices, fst, scd;
        Dijkstra dijkstra;

        System.out.println("Number of vertices: ");
        qtdVertices = scanner.nextInt();

        dijkstra = new Dijkstra(qtdVertices, true);

        do {
            System.out.println(getPanel());
            opt = scanner.nextInt();

            if(opt == 1) {
                System.out.println("Type two vertices to be linked: ");

                fst = scanner.nextInt();
                scd = scanner.nextInt();

                if(!dijkstra.link(fst, scd)) {
                    System.out.println("Failed Linking!");
                }
            }
            else if(opt == 2) {
                System.out.println("Type two vertices to be unlinked: ");

                fst = scanner.nextInt();
                scd = scanner.nextInt();

                if (!dijkstra.unLink(fst, scd)) {
                    System.out.println("Failed to unlink");
                }
            }
            else if(opt == 3) {
                System.out.println(dijkstra.toString());
            }
            else if(opt == 0);
            else {
                System.out.println("Invalid Option");
            }
        } while(opt != 0);
    }

    private static String getPanel() {
        return  "1- Link\n" +
                "2- Unlink\n" +
                "3- Show connections\n" +
                "0- Exit\n" +
                "Option: ";
    }
}