import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.ArrayList;

public class Graf extends JPanel {
    int lines = 5;
    int columns = 6;
    Integer[][] matrix;
    ArrayList<Node> mazeNodes;
    Vector<Node> graphNodes;
    HashSet<Edge> edges;

    public Graf() {
        matrix = new Integer[lines][columns];
        mazeNodes = new ArrayList<>();
        graphNodes = new Vector<>();
        edges = new HashSet<>();
    }

    public void readMatrixFromFile() {
        try {
            Scanner input = new Scanner(new File("matrixLabirint.txt"));
            while (input.hasNextLine()) {
                for (int i = 0; i < lines; i++) {
                    for (int j = 0; j < columns; j++) {
                        try {
                            matrix[i][j] = input.nextInt();
                        } catch (java.util.NoSuchElementException e) {}
                    }
                }
            }
        } catch (Exception e) {}

        int counter = 1;
        int coordX_in_maze = 30;
        int coordY_in_maze = 30;
        int coordX_in_graph = 70;
        int coordY_in_graph = 70;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                //build maze
                Node mazeNode = new Node(j * coordX_in_maze + 100, i * coordY_in_maze + 100, counter, matrix[i][j]);
                mazeNodes.add(mazeNode);
                //build graph
                Node graphNode = new Node(j * coordX_in_graph + 400, i * coordX_in_graph + 400, counter, matrix[i][j]);
                graphNodes.add(graphNode);
                counter++;
                repaint();
            }
        }
    }

    public void addEdge(Node start, Node end){
        Edge edge = new Edge(start,end);
        edges.add(edge);
        repaint();
    }

    public ArrayList<Integer> FindIndexes(Node node){
        int nodeIndex = node.getNumber() - 1;
        int nodeIndexCopy = nodeIndex;
        Integer i = 0, j = 0;

        while(nodeIndexCopy >= columns){
            i++;
            nodeIndexCopy = nodeIndexCopy - columns;
        }
        while(nodeIndexCopy > 0){
            j++;
            nodeIndexCopy = nodeIndexCopy - 1;
        }

        ArrayList<Integer>indexes = new ArrayList<>();
        indexes.add(i);
        indexes.add(j);

        return indexes;
    }

    public Integer indexesInt(int x, int y) {
        return x * columns + y;
    }

    public ArrayList<Integer> FindNeighbours(Node nod){
        int i = FindIndexes(nod).get(0);
        int j = FindIndexes(nod).get(1);
        ArrayList<Integer> neighbours = new ArrayList<>();

        if(i > 0 && matrix[i-1][j] > 0){
            neighbours.add(indexesInt(i-1, j));
        }
        if(i < lines -1 && matrix[i+1][j] > 0){
            neighbours.add(indexesInt(i+1, j));
        }
        if(j > 0 && matrix[i][j-1] > 0){
            neighbours.add(indexesInt(i, j-1));
        }
        if(j < columns -1 && matrix[i][j+1] > 0){
            neighbours.add(indexesInt(i, j+1));
        }

        return neighbours;
    }

    void Bfs(int start, int end){
        int numberOfNodes = lines * columns;
        Queue<Integer> queue = new LinkedList<>();
        Vector<Boolean> visited = new Vector<>();
        Vector<Integer> predecessors = new Vector<>();
        queue.add(start);

        for(int i = 0 ; i < numberOfNodes; i++){
            visited.add(false);
        }
        visited.setElementAt(true,start);

        for(int i = 0 ; i < numberOfNodes; i++){
            predecessors.add(null);
        }

        while(!queue.isEmpty()){
            int tmpNode = queue.remove();
            ArrayList<Integer> neighbours = FindNeighbours(graphNodes.get(tmpNode));
            for(Integer neighbour : neighbours){
                if(!visited.elementAt(neighbour)){
                    queue.add(neighbour);
                    visited.setElementAt(true,neighbour);
                    predecessors.setElementAt(tmpNode,neighbour);
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        for(Integer i = end; i!=null ; i = predecessors.elementAt(i)){
            path.add(i);
        }
        Collections.reverse(path);

        if(path.get(0) == start){
            for(int i = 0 ; i < path.size()-1; i++){
                addEdge(graphNodes.get(path.get(i)), graphNodes.get(path.get(i+1)));
            }

            for(Integer node : path){
                Node graphNode = graphNodes.get(node);
                Node mazeNode = mazeNodes.get(node);
                if(graphNode.getValue()!= 2 && graphNode.getValue()!= 3){
                    graphNode.setPath(true);
                    mazeNode.setPath(true);
                    mazeNodes.set(node,mazeNode);
                    graphNodes.setElementAt(graphNode, node);
                }
            }
        }
    }

    void bfsForMore(){
        Node end = null;
        for(Node nod : graphNodes){
            if(nod.getValue()==3){
                end = nod;
            }
        }
        for(Node nod : graphNodes){
            if(nod.getValue()==2){
                Bfs(nod.getNumber()-1,end.getNumber()-1);
            }
        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        for(Node node : mazeNodes){
            node.drawNode(graphics, 30);
        }
        for(Node node : graphNodes) {
            if(node.getValue()>0)
                node.drawNodeinGraph(graphics, 30);
        }
        for(Edge edge : edges){
            edge.drawArc(graphics);
        }
    }
}
