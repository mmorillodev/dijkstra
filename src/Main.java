import java.util.Scanner;
public class Main {

    private static boolean[][] adjMatrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opt, qtdVertices, fst, scd;

        System.out.println("Quantity of vertices: ");
        qtdVertices = scanner.nextInt();
        adjMatrix = new boolean[qtdVertices][qtdVertices];

        do {
            System.out.println(getPanel());
            opt = scanner.nextInt();

            if(opt == 1) {
                System.out.println("Digite o primeiro e o segundo vértice a ser conectado: ");
                fst = scanner.nextInt();
                scd = scanner.nextInt();
                if(!connect(fst, scd)) {
                    System.out.println("Failed connecting!");
                }
            }
            else if(opt == 2) {
                System.out.println("Digite o primeiro e o segundo vértice a ser disconectado: ");

                fst = scanner.nextInt();
                scd = scanner.nextInt();

                if (!disconnect(fst, scd)) {
                    System.out.println("Failed disconnecting");
                }
            }
            else if(opt == 3) {
                System.out.println(toString(adjMatrix));
            }
            else if(opt == 0);
            else {
                System.out.println("Opção inválida");
            }
        } while(opt != 0);
    }

    private static String toString(boolean[][] matrix) {
        StringBuilder builder = new StringBuilder("\n");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                builder.append(parseInt(matrix[i][j])).append(j == matrix[i].length - 1 ? "" : " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    private static boolean connect(int fst, int scd) {
        fst -= 1;
        scd -= 1;

        if (fst >= adjMatrix.length || scd >= adjMatrix.length || fst < 0 || scd < 0)
            return false;

        adjMatrix[fst][scd] = true;
        adjMatrix[scd][fst] = true;

        return true;
    }

    private static boolean disconnect(int fst, int scd) {
        fst -= 1;
        scd -= 1;

        if (fst >= adjMatrix.length || scd >= adjMatrix.length || fst <= 0 || scd <= 0)
            return false;

        adjMatrix[fst][scd] = false;
        adjMatrix[scd][fst] = false;

        return true;
    }

    private static String getPanel() {
        return "1- Connect\n" +
        "2- Remove Connection\n" +
        "3- Print connections\n" +
        "0- Exit\n" +
        "Option: ";
    }

    private static int parseInt(boolean b) {
        if(b)
            return 1;
        return 0;
    }
}
