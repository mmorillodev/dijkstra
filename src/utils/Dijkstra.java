package utils;

import java.util.*;

public class Dijkstra {
    private int[][] connection;
    private boolean     doublyLinked;

    public Dijkstra(int qtd, boolean doublyLinked) {
        this.connection = new int[qtd][qtd];
        this.doublyLinked   = doublyLinked;
    }

    public boolean link(int fst, int scd) {
        if(fst >= this.connection.length || scd >= this.connection.length || fst < 0 || scd < 0)
            return false;

        this.connection[fst][scd] = 1;

        if (this.doublyLinked) {
            this.connection[scd][fst] = 1;
        }

        return true;
    }

    public boolean link(int fst, int scd, int weight) {
        if(fst >= this.connection.length || scd >= this.connection.length || fst < 0 || scd < 0)
            return false;

        this.connection[fst][scd] = weight;

        if(this.doublyLinked) {
            this.connection[scd][fst] = weight;
        }

        return true;
    }

    public boolean unlink(int fst, int scd) {
        if (fst >= this.connection.length || scd >= this.connection.length || fst < 0 || scd < 0)
            return false;

        this.connection[fst][scd] = 0;

        if (doublyLinked) {
            this.connection[scd][fst] = 0;
        }
        return true;
    }

    public void unlinkAll() {
        for(int i = 0; i < this.connection.length; i++) {
            for(int j = 0; j < this.connection[i].length; j++) {
                this.connection[i][j] = 0;
            }
        }
    }

    //TODO
    public List<Integer> getShortestPath(int node1, int node2) {
        //Invalid values provided
        if(node1 >= this.connection.length ||
                node2 >= this.connection.length ||
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

        boolean[][] visitedNodes = new boolean[this.connection.length][this.connection.length];
        int current = node1;
        int latest = node1;
        int smallestQtd = 0;
        List<Integer> smallestPath = path;
        int cost = 0;

        while(getNonVisitedNode(visitedNodes, node1) > 0) {
            if(current == node2) {
                path.add(current);
                if(cost < smallestQtd){
                    smallestQtd = cost;
                    smallestPath = path;

                    path = new ArrayList<>(){{
                        add(node1);
                    }};
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

        for(int i = 0; i < this.connection.length; i++) {
            for(int j = 0; j < this.connection[i].length; j++) {
                if(this.connection[i][j] > 0) edges++;
            }
        }

        return edges;
    }

    public String getMatrixAsString() {
        int mLength = this.connection.length;
        StringBuilder builder = new StringBuilder(mLength <= 10 ? "\t" : "");

        if(mLength <= 10)
            for (int i = 0; i < mLength; i++)
                builder.append(i)
                        .append(i == mLength - 1 ? "\n\n" : " ");

        for(int i = 0; i < mLength; i++) {
            if(mLength <= 10)
                builder.append(i).append("\t");

            for(int j = 0; j < this.connection[i].length; j++) {
                builder.append(this.parseInt(this.connection[i][j]))
                        .append(j < this.connection[i].length ? " " : "");
            }
            if(i < mLength - 1)
                builder.append("\n");
        }

        return builder.toString();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for(int i = 0; i < this.connection.length; i++) {
            for(int j = 0; j < this.connection[i].length; j++) {
                if(this.connection[i][j] == 0)
                    continue;

                if(i > j && this.connection[i][j] == this.connection[j][i])
                    continue;

                if(builder.length() > 1)
                    builder.append(", ");

                builder.append("(").append(i).append(this.connection[i][j] == this.connection[j][i] ? " <-> " : " -> ").append(j).append(") = ").append(this.connection[i][j]);
            }
        }

        return builder.append("]").toString();
    }

    public int getNonVisitedNode(boolean[][] visitedNodes, int node) {
        for(int i = 0; i < this.connection[node].length; i++) {
            if(this.connection[node][i] > 0) {
//                System.out.println("this.connection[" + node + "]["+ i +"] = " + this.connection[node][i]);
//                System.out.println("visitedNodes[" + i +"] = " + visitedNodes[node][i]);
                if (visitedNodes[node][i]) {
                    continue;
                }
                return i;
            }
        }

        return -1;
    }

    private boolean isLinked(int n1, int n2) {
        return this.connection[n1][n2] > 0;
    }

    public boolean hasConnections(int node) {
        for(int i = 0; i < this.connection[node].length; i++) {
            if(this.connection[node][i] > 0)
                return true;
        }

        for(int i = 0; i < this.connection.length; i++) {
            if(this.connection[i][node] > 0)
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