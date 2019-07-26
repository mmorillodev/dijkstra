public class Dijkstra {
    private boolean[][] adjMatrix;
    private boolean     doublyLinked;

    public Dijkstra(int qtd, boolean doublyLinked) {
        this.adjMatrix      = new boolean[qtd][qtd];
        this.doublyLinked   = doublyLinked;
    }

    public boolean link(int fst, int scd) {
        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd] = true;

        if(this.doublyLinked)
            this.adjMatrix[scd][fst] = true;

        return true;
    }

    public boolean unLink(int fst, int scd) {
        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst <= 0 || scd <= 0)
            return false;

        this.adjMatrix[fst][scd] = false;

        if(doublyLinked)
            this.adjMatrix[scd][fst] = false;

        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("\t");

        for (int i = 0; i < this.adjMatrix.length; i++)
            builder.append(i)
                    .append(i == this.adjMatrix.length - 1 ? "\n\n" : " ");

        for (int i = 0; i < this.adjMatrix.length; i++) {
            builder.append(i).append("\t");

            for (int j = 0; j < this.adjMatrix[i].length; j++) {
                builder.append(this.parseInt(this.adjMatrix[i][j]))
                        .append(j == this.adjMatrix[i].length - 1 ? "" : " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    private int parseInt(boolean b) {
        if(b)
            return 1;
        return 0;
    }
}
