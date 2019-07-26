public class Dijkstra {
    private boolean[][] adjMatrix;
    private boolean     doublyLinked;

    public Dijkstra(int qtd, boolean doublyLinked) {
        this.adjMatrix      = new boolean[qtd][qtd];
        this.doublyLinked   = doublyLinked;
    }

    public boolean link(int fst, int scd) {
        fst -= 1;
        scd -= 1;

        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd] = true;

        if(this.doublyLinked)
            this.adjMatrix[scd][fst] = true;

        return true;
    }

    public boolean unLink(int fst, int scd) {
        fst -= 1;
        scd -= 1;

        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst <= 0 || scd <= 0)
            return false;

        this.adjMatrix[fst][scd] = false;

        if(doublyLinked)
            this.adjMatrix[scd][fst] = false;

        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("\n  ");

        for (int i = 1; i <= this.adjMatrix.length; i++)
            builder.append(i)
                    .append(i == this.adjMatrix.length ? "\n" : " ");

        for (int i = 0; i < this.adjMatrix.length; i++) {
            builder.append(i + 1).append(" ");

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
