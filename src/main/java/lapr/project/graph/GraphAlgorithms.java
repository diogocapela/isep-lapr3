/*
 * A collection of graph algorithms.
 */
package lapr.project.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author DEI-ESINF
 */

public class GraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex
     *
     * @param g    Graph instance
     * @param vInf information of the Vertex that will be the source of the search
     * @return qbfs a queue with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> breadthFirstSearch(Graph<V, E> g, V vert) {
        LinkedList<V> result = null;

        if (g.validVertex(vert)) {
            result = new LinkedList<>();

            boolean[] knownVertices = new boolean[g.numVertices()];

            LinkedList<V> auxStack = new LinkedList<>();

            auxStack.addFirst(vert);

            knownVertices[g.getKey(auxStack.getFirst())] = true;

            result.add(vert);

            while (!auxStack.isEmpty()) {
                for (Edge<V, E> tmpEdge : g.outgoingEdges(auxStack.getFirst())) {
                    if (!knownVertices[g.getKey(tmpEdge.getVDest())]) {
                        knownVertices[g.getKey(tmpEdge.getVDest())] = true;
                        auxStack.addLast(tmpEdge.getVDest());
                        result.add(tmpEdge.getVDest());
                    }
                }
                auxStack.removeFirst();
            }
        }

        return result;
    }

    /**
     * Performs depth-first search starting in a Vertex
     *
     * @param g       Graph instance
     * @param vOrig   Vertex of graph g that will be the source of the search
     * @param visited set of discovered vertices
     * @param qdfs    queue with vertices of depth-first search
     */
    private static <V, E> void depthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        int currIdx = g.getKey(vOrig);
        visited[currIdx] = true;
        qdfs.addLast(vOrig);
        for (Edge<V, E> tmpEdge : g.outgoingEdges(vOrig)) {
            V vDest = tmpEdge.getVDest();
            int destIdx = g.getKey(vDest);
            if (!visited[destIdx]) {
                GraphAlgorithms.depthFirstSearch(g, vDest, visited, qdfs);
            }
        }
    }

    /**
     * @param g    Graph instance
     * @param vInf information of the Vertex that will be the source of the search
     * @return qdfs a queue with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> depthFirstSearch(Graph<V, E> g, V vert) {
        LinkedList<V> result = null;

        if (g.validVertex(vert)) {
            boolean[] visited = new boolean[g.numVertices()];
            result = new LinkedList<>();

            GraphAlgorithms.depthFirstSearch(g, vert, visited, result);
        }

        return result;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                        LinkedList<V> path, ArrayList<LinkedList<V>> paths) {
        int origIdx = g.getKey(vOrig);
        if (!visited[origIdx]) {
            if (vOrig.equals(vDest)) {
                path.addLast(vOrig);
                LinkedList<V> clone = new LinkedList<>(path);
                paths.add(clone);
                path.removeLast();
            } else {
                visited[origIdx] = true;
                path.addLast(vOrig);
                for (Edge<V, E> tmpEdge : g.outgoingEdges(vOrig)) {
                    allPaths(g, tmpEdge.getVDest(), vDest, visited, path, paths);
                }
                path.removeLast();
                visited[origIdx] = false;
            }
        }
    }

    /**
     * @param g     Graph instance
     * @param voInf information of the Vertex origin
     * @param vdInf information of the Vertex destination
     * @return paths ArrayList with all paths from voInf to vdInf
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        ArrayList<LinkedList<V>> result = new ArrayList<>();

        if (g.validVertex(vOrig) && g.validVertex(vDest)) {
            boolean[] visited = new boolean[g.numVertices()];
            LinkedList<V> path = new LinkedList<>();

            allPaths(g, vOrig, vDest, visited, path, result);
        }

        return result;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param voInf    information of the Vertex origin
     * @param vdInf    information of the Vertex destination
     * @param pathkeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {
        int destIdx = g.getKey(vDest);
        if (pathKeys[destIdx] > -1) {
            int pathIdx = destIdx;
            while (pathKeys[pathIdx] > -1) {
                path.addFirst(verts[pathIdx]);
                pathIdx = pathKeys[pathIdx];
            }
            path.addFirst(vOrig);
        } else if (vOrig.equals(vDest)) {
            path.addFirst(vOrig);
        }
    }

    // get next index for dijkstra's algorithm
    private static <V, E> int shortestPathNextIndex(boolean[] knownVertices, double[] minDist) {
        int result = -1;
        double max = Double.MAX_VALUE;

        for (int i = 0; i < minDist.length; i++) {
            if (!knownVertices[i] && minDist[i] < max) {
                max = minDist[i];
                result = i;
            }
        }

        return result;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of discovered vertices
     * @param pathkeys minimum path vertices keys
     * @param dist     minimum distances
     */
    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, V[] vertices, boolean[] visited, int[] pathKeys, double[] dist) {
        int currIdx = g.getKey(vOrig);
        if (pathKeys[currIdx] < 0) {
            dist[currIdx] = 0;
        }
        visited[currIdx] = true;
        for (Edge<V, E> tmpEdge : g.outgoingEdges(vertices[currIdx])) {
            int tmpIdx = g.getKey(tmpEdge.getVDest());
            if (dist[tmpIdx] > dist[currIdx] + tmpEdge.getWeight()) {
                pathKeys[tmpIdx] = currIdx;
                dist[tmpIdx] = dist[currIdx] + tmpEdge.getWeight();
            }
        }
        currIdx = shortestPathNextIndex(visited, dist);
        if (currIdx > -1) {
            shortestPathLength(g, vertices[currIdx], vertices, visited, pathKeys, dist);
        }
    }

    //shortest-path between vOrig and vDest
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {

        if (g.validVertex(vOrig) && g.validVertex(vDest)) {
            double result = 0;

            boolean[] knownVertices = new boolean[g.numVertices()];
            int[] precedences = new int[g.numVertices()];
            double[] minDist = new double[g.numVertices()];
            V[] vertices = g.allkeyVerts();

            for (int i = 0; i < g.numVertices(); i++) {
                precedences[i] = -1;
                minDist[i] = Double.MAX_VALUE;
            }

            int origIdx = g.getKey(vOrig);
            int destIdx = g.getKey(vDest);

            shortestPathLength(g, vOrig, vertices, knownVertices, precedences, minDist);

            getPath(g, vOrig, vDest, vertices, precedences, shortPath);

            return minDist[destIdx] < Double.MAX_VALUE ? minDist[destIdx] : 0;
        }

        return 0;
    }

    //shortest-path between voInf and all other
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {

        if (!g.validVertex(vOrig))
            return false;

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        V[] vertices = g.allkeyVerts();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, vertices, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();
            if (dist[i] < Double.MAX_VALUE)
                getPath(g, vOrig, vertices[i], vertices, pathKeys, shortPath);
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    }

    /**
     * Reverses the path
     *
     * @param path stack with path
     */
    private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty())
            pathrev.push(pathcopy.pop());

        return pathrev;
    }
}
