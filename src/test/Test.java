package test;

import utils.Dijkstra;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(d.getShortestPath(0,2));
        boolean[][] visited = new boolean[6][6];
        int current = 0;
        int latest = 0;
        List<Integer> path = new ArrayList<>();
        List<Integer> shortestPath = new ArrayList<>();

        visited[latest][current] = true;
        visited[current][latest] = true;
        latest = current;
        current = d.getNonVisitedNode(visited, current);
        System.out.println(current);

        visited[latest][current] = true;
        visited[current][latest] = true;
        latest = current;
        current = d.getNonVisitedNode(visited, current);
        System.out.println(current);

        if(current == 2) {

        }
    }
}
