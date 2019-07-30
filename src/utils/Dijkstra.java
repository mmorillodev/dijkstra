package utils;

import java.util.*;

public class Dijkstra {
    private int[][]     adjMatrix;
    private boolean     doublyLinked;

    public Dijkstra(int qtd, boolean doublyLinked) {
        this.adjMatrix      = new int[qtd][qtd];
        this.doublyLinked   = doublyLinked;
    }

    public boolean link(int fst, int scd) {
        if(fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd] = 1;

        if (this.doublyLinked) {
            this.adjMatrix[scd][fst] = 1;
        }

        return true;
    }

    public boolean link(int fst, int scd, int weight) {
        if(fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd] = weight;

        if(this.doublyLinked) {
            this.adjMatrix[scd][fst] = weight;
        }

        return true;
    }

    public boolean unlink(int fst, int scd) {
        if (fst >= this.adjMatrix.length || scd >= this.adjMatrix.length || fst < 0 || scd < 0)
            return false;

        this.adjMatrix[fst][scd] = 0;

        if (doublyLinked) {
            this.adjMatrix[scd][fst] = 0;
        }
        return true;
    }

    public void unlinkAll() {
        for(int i = 0; i < this.adjMatrix.length; i++) {
            for(int j = 0; j < this.adjMatrix[i].length; j++) {
                this.adjMatrix[i][j] = 0;
            }
        }
    }

    //TODO
    public List<Integer> getShortestPath(int node1, int node2) {
        //Invalid values provided
        if(node1 >= this.adjMatrix.length ||
                node2 >= this.adjMatrix.length ||
                node1 < 0 || node2 < 0)
            return null;

        //Node provided has no links
        if(!hasConnections(node1))
            return null;

        List<Integer> path = new ArrayList<>(){{
            add(node1);
        }};

        //Node 1 is directly linked to node 2
        if(isLinked(node1, node2)) {
            path.add(node2);
            return path;
        }

        boolean[][] visitedNodes = new boolean[this.adjMatrix.length][this.adjMatrix.length];
        int current = node1;
        int latest = node1;
        int smallestQtd = 0;
        List<Integer> smallestPath = path;
        int cost = 0;

        while(getNonVisitedNode(visitedNodes, node1) > 0) {
            if(current == node2) {
                if(cost < smallestQtd){
                    smallestQtd = cost;
                    smallestPath = path;

                    path = new ArrayList<>();

                    path.add(node1);
                    current = node1;
                }
            }

            visitedNodes[latest][current] = true;
            cost ++;
            latest = current;
            current = getNonVisitedNode(visitedNodes, current);
            path.add(current);
        }

        return smallestPath;
    }

    public int getEdges() {
        int edges = 0;

        for(int i = 0; i < this.adjMatrix.length; i++) {
            for(int j = 0; j < this.adjMatrix[i].length; j++) {
                if(this.adjMatrix[i][j] > 0) edges++;
            }
        }

        return edges;
    }

    public String getMatrixAsString() {
        int mLength = this.adjMatrix.length;
        StringBuilder builder = new StringBuilder(mLength <= 10 ? "\t" : "");

        if(mLength <= 10)
            for (int i = 0; i < mLength; i++)
                builder.append(i)
                        .append(i == mLength - 1 ? "\n\n" : " ");

        for(int i = 0; i < mLength; i++) {
            if(mLength <= 10)
                builder.append(i).append("\t");

            for(int j = 0; j < this.adjMatrix[i].length; j++) {
                builder.append(this.parseInt(this.adjMatrix[i][j]))
                        .append(j < this.adjMatrix[i].length ? " " : "");
            }
            if(i < mLength - 1)
                builder.append("\n");
        }

        return builder.toString();
    }

    public String toString() {
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

    private int getNonVisitedNode(boolean[][] visitedNodes, int node) {
        for(int i = 0; i < this.adjMatrix[node].length; i++) {
            if(this.adjMatrix[node][i] > 0) {
                System.out.println("this.adjMatrix[" + node + "]["+ i +"] = " + this.adjMatrix[node][i]);
                System.out.println("visitedNodes[" + i +"] = " + visitedNodes[node][i]);
                if (visitedNodes[node][i]) {
                    continue;
                }
                return i;
            }
        }

        return -1;
    }

    private boolean isLinked(int n1, int n2) {
        return this.adjMatrix[n1][n2] > 0;
    }

    private boolean hasConnections(int node) {
        for(int i = 0; i < this.adjMatrix[node].length; i++) {
            if(this.adjMatrix[node][i] > 0)
                return true;
        }

        for(int i = 0; i < this.adjMatrix.length; i++) {
            if(this.adjMatrix[i][node] > 0)
                return true;
        }

        return false;
    }

    private int parseInt(int node) {
        if(node >= 1)
            return 1;

        return 0;
    }
}