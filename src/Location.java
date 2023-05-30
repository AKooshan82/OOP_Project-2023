import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Location {
    public void run() throws FileNotFoundException {
        File givenGraph = new File(""); //give the path of the graph file
        Scanner scanner = new Scanner(givenGraph);
        Graph g = new Graph(scanner);
        g.dijkstra(g.vertices.get(0),g.vertices.get(g.vertices.size()-1));
    }
}
// A weighted directed edge
class Edge{
    public Vertex to; //the destination of the directed edge
    public int weight;

    public Edge(Vertex to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
class Vertex{
    public ArrayList<Edge> edges;
    public int id; // The index of the vertex in the input.
    public long distance; // The distance from the dijekstra search start
    public Vertex parent; // The previous vertex on the path
    public int heapIndex; // We need to remember the location of the vertex in the heap

    public Vertex(int id) {
        this.id = id;
        edges = new ArrayList<Edge>();
    }
}
//A heap of vertices ordered by the distance.
class Heap{
    //The heap's data in array form.
    private ArrayList<Vertex> heapData;

    // Initilize an empty heap.
    public Heap() {
        heapData = new ArrayList<Vertex>();
        heapData.add(null); //Elements of the heap start at heapData[1]
    }

    // Initilize a heap from n vertices in O(n)
    public Heap(ArrayList<Vertex> vertices) {
        heapData = new ArrayList<Vertex>();
        heapData.add(null);
        for(int i = 0; i < vertices.size(); i++) {
            heapData.add(vertices.get(i));
            vertices.get(i).heapIndex = i+1;
        }
        //heapify down elements starting from the lower layers of the heap
        for(int i = heapData.size()-1; i > 0; i--) {
            heapifyDown(heapData.get(i));
        }
    }

    //Returns the number of elements in the heap
    public int size() {
        return heapData.size() - 1;
    }

    public Vertex getMin() {
        return heapData.get(1);
    }

    //Swap the positions of u and v in the heap
    private void swap(Vertex u, Vertex v) {
        heapData.set(u.heapIndex, v);
        heapData.set(v.heapIndex, u);
        int uIndex = u.heapIndex;
        u.heapIndex = v.heapIndex;
        v.heapIndex = uIndex;
    }

    // get the element above v in the heap, or null if it doesn't exist
    private Vertex up(Vertex v) {
        return heapData.get(v.heapIndex/2);
    }

    // get the left child of v in the heap, or null if it doesn't exist
    private Vertex left(Vertex v) {
        if(v.heapIndex * 2 < heapData.size()) {
            return heapData.get(v.heapIndex *2);
        }
        return null;
    }

    // get the right child of v in the heap, or null if it doesn't exist
    private Vertex right(Vertex v) {
        if(v.heapIndex * 2 + 1 < heapData.size()) {
            return heapData.get(v.heapIndex *2 + 1);
        }
        return null;
    }

    //swap v down through the heap until it has no smaller elements below it
    private void heapifyDown(Vertex v) {
        //while there is an element below v with a smaller distance
        while((left(v) != null && left(v).distance < v.distance)
                || (right(v) != null && right(v).distance < v.distance)) {
            //If v should be swapped with the left child
            if(right(v) == null || right(v).distance > left(v).distance) {
                swap(v,left(v));
            }else {
                swap(v,right(v));
            }
        }
    }

    //swap v up through the heap until it has no greater elements above it
    private void heapifyUp(Vertex v) {
        //while the parent of v in the heap has a grater distance
        while(up(v) != null && up(v).distance > v.distance) {
            swap(v,up(v));
        }
    }

    //Remove the minimum item from the heap
    public void popMin() {
        swap(heapData.get(1), heapData.get(heapData.size()-1));
        heapData.get(heapData.size()-1).heapIndex = -1; //Update this to mark that the vertex is not in the heap(it is now a 'purple vertex')
        heapData.remove(heapData.size()-1);
        if(heapData.size() > 1) {
            heapifyDown(heapData.get(1));
        }
    }

    public void addElement(Vertex v) {
        v.heapIndex = heapData.size();
        heapData.add(v);
        heapifyUp(v);
    }

    public void decreaseDistance(Vertex v, long newDistance) {
        v.distance = newDistance;
        heapifyUp(v);
    }
}
class Graph{
    public ArrayList<Vertex> vertices;

    public Graph(Scanner scanner) {
        vertices = new ArrayList<Vertex>();
        int vertex_count = scanner.nextInt();
        int edge_count = scanner.nextInt();
        for(int i = 0; i < vertex_count; i++) {
            vertices.add(new Vertex(i));
        }
        for(int i = 0; i < edge_count; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weight = scanner.nextInt();
            Edge edge = new Edge(vertices.get(to), weight);
            vertices.get(from).edges.add(edge);
            Edge reverseEdge = new Edge(vertices.get(from),weight);
            vertices.get(to).edges.add(reverseEdge);
        }
    }

    //Print the shortest path from source to destination using dijkstra
    public void dijkstra(Vertex source, Vertex destination) {
        //Initilize the vertices
        for(Vertex v : vertices) {
            v.distance = 1000000000000000L; //A large value (infinity) that is small enough that distance*2 does not overflow long
            v.parent = null; // The path is not discovered yet
        }
        source.distance = 0; //The distance from source to source is 0.
        Heap heap = new Heap(vertices); //Initilize a new heap with all of the vertices
        //While there are still green vertices
        while(heap.size() > 0) {
            Vertex current = heap.getMin();
            heap.popMin();
            for(Edge e : current.edges) {
                //If we discovered a shorter path to e.to
                if(current.distance + e.weight < e.to.distance) {
                    heap.decreaseDistance(e.to, current.distance+e.weight);
                    e.to.parent = current; //update the best path to e.to
                }
            }
        }
        //Extract the shortest path
        //If no path exists to the destination
        if(destination.parent == null) {
            System.out.println(-1);
            return;
        }
        Stack<Vertex> path = new Stack<Vertex>();
        path.add(destination);
        while(path.peek().parent != null) {
            path.add(path.peek().parent);
        }
        StringBuilder output = new StringBuilder(); //quicker than printing each id separately
        while(path.size() > 0) {
            output.append((path.pop().id+1)+" ");
        }
        System.out.println(output.toString());
    }
}