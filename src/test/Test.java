package test;

import utils.Dijkstra;

public class Test {
    public static void main(String[] args) {
        Dijkstra d = new Dijkstra(6, true){{
            link(0,1);
            link(1,2);
            link(2,5);
            link(5,4);
            link(4,3);
            link(3,0);
            link(0,4);
            link(4,2);
        }};
        boolean[][] visited = new boolean[6][6];
        visited[0][1] = true;
        visited[0][3] = true;
        visited[0][4] = true;

        visited[1][0] = true;

        System.out.println(d.getNonVisitedNode(visited, 1));
        System.out.println(d.hasConnections(5));

        System.out.println(d.getShortestPath(1, 4));
    }
}
