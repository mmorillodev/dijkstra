public class Dijkstra {
    private int[][]     adjMatrix;
    private boolean     doublyLinked;

    public Dijkstra(int qtd, boolean doublyLinked) {
        this.adjMatrix      = new int[qtd][qtd];
        this.doublyLinked   = doublyLinked;
    }

    public boolean link(int fst, int scd) {
        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd] = 1;

        if (this.doublyLinked) {
            this.adjMatrix[scd][fst] = 1;
        }

        return true;
    }

    public boolean link(int fst, int scd, int weight) {
        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd] = weight;

        if (this.doublyLinked) {
            this.adjMatrix[scd][fst] = weight;
        }

        return true;
    }

    public boolean unLink(int fst, int scd) {
        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd] = 0;

        if (doublyLinked) {
            this.adjMatrix[scd][fst] = 0;
        }
        return true;
    }

    public String toString() {
        int mLength = this.adjMatrix.length;
        StringBuilder builder = new StringBuilder(mLength <= 10 ? "\t" : "");

        if(mLength <= 10)
            for (int i = 0; i < mLength; i++)
                builder.append(i)
                        .append(i == mLength - 1 ? "\n\n" : " ");

        for (int i = 0; i < mLength; i++) {
            if(mLength <= 10)
                builder.append(i).append("\t");

            for (int j = 0; j < this.adjMatrix[i].length; j++) {
                builder.append(this.parseInt(this.adjMatrix[i][j]))
                        .append(j < this.adjMatrix[i].length ? " " : "");
            }
            if(i < mLength - 1)
                builder.append("\n");
        }

        return builder.toString();
    }

    public String completeLog() {
        StringBuilder builder = new StringBuilder("[");

        for(int i = 0; i < this.adjMatrix.length; i++) {
            for(int j = 0; j < this.adjMatrix[i].length; j++) {
                if(this.adjMatrix[i][j] == 0)
                    continue;

                if(i > j && this.adjMatrix[i][j] == this.adjMatrix[j][i])
                    continue;

                if(builder.length() > 1)
                    builder.append(", ");

                builder.append("(").append(i).append(this.adjMatrix[i][j] == this.adjMatrix[j][i] ? " <-> " : " -> ").append(j).append(") = ").append(this.adjMatrix[i][j]);
            }
        }

        return builder.append("]").toString();
    }

    private int parseInt(int node) {
        if(node >= 1)
            return 1;

        return 0;
    }
}