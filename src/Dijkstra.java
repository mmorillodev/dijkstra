public class Dijkstra {
    private Node[][]    adjMatrix;
    private boolean     doublyLinked;

    public Dijkstra(int qtd, boolean doublyLinked) {
        this.adjMatrix      = new Node[qtd][qtd];
        this.doublyLinked   = doublyLinked;
    }

    public boolean link(int fst, int scd) {
        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd].linked = true;

        if(this.doublyLinked)
            this.adjMatrix[scd][fst].linked = true;

        return true;
    }

    public boolean link(int fst, int scd, int weight) {
        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd].linked = true;
        this.adjMatrix[fst][scd].weight = weight;

        if(this.doublyLinked) {
            this.adjMatrix[scd][fst].linked = true;
            this.adjMatrix[scd][fst].weight = weight;
        }

        return true;
    }

    public boolean unLink(int fst, int scd) {
        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst <= 0 || scd <= 0)
            return false;

        this.adjMatrix[fst][scd].linked = false;
        this.adjMatrix[fst][scd].weight = 0;

        if(doublyLinked) {
            this.adjMatrix[scd][fst].linked = false;
            this.adjMatrix[scd][fst].weight = 0;
        }

        return true;
    }

    /*public String toString() {
        StringBuilder builder = new StringBuilder();

        int j;
        for (Node[] line : adjMatrix) {
            j = 0;
            for (Node node : line) {
                builder.append(this.parseInt(node))
                        .append(j < line.length ? " " : "");
                j++;
            }
            builder.append("\n");
        }

        return builder.toString();
    }*/

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
            builder.append("\n");
        }

        return builder.toString();
    }

    private int parseInt(Node node) {
        if(node == null)
            return 0;

        if(node.linked)
            return 1;

        return 0;
    }

    private class Node {
        boolean linked;
        int weight;
    }
}
