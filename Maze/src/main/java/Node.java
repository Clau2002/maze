import java.awt.*;

public class Node {
    private int coordX;
    private int coordY;
    private int number;
    private int value;
    private boolean path;

    public boolean isPath() {
        return path;
    }

    public void setPath(boolean path) {
        this.path = path;
    }



    public Node(int coordX, int coordY, int number, int value)
    {
        this.coordX = coordX;
        this.coordY = coordY;
        this.number = number;
        this.value = value;
    }

    public Node() {
    }

    public boolean verifyNodes(Node node1, Node node2){
        double distance = Point.distance(node1.getCoordX(), node1.getCoordY(), node2.getCoordX(), node2.getCoordY());
        if(distance < 30)
            return true;
        return false;
    }

    public int getCoordX() {
        return coordX;
    }
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }
    public int getCoordY() {
        return coordY;
    }
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public void drawNode(Graphics g, int node_diam)
    {
        if(path){
            g.setColor(Color.GREEN);
        }
        else {
            if (value == 0)
                g.setColor(Color.BLACK);
            if (value == 1)
                g.setColor(Color.WHITE);
            if (value == 2)
                g.setColor(Color.RED);
            if (value == 3)
                g.setColor(Color.BLUE);
        }
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillRect(coordX-13, coordY-12, node_diam, node_diam);
        if(value == 0)
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.BLACK);
        g.drawRect(coordX-13, coordY-12, node_diam, node_diam);

        if(number < 10)
            g.drawString(((Integer)(number)).toString(), coordX, coordY+7);
        else
            g.drawString(((Integer)(number)).toString(), coordX-6, coordY+7);
    }

    public void drawNodeinGraph(Graphics g, int node_diam)
    {
        if(path){
            g.setColor(Color.GREEN);
        }
        else {
            if (value == 0)
                g.setColor(Color.BLACK);
            if (value == 1)
                g.setColor(Color.WHITE);
            if (value == 2)
                g.setColor(Color.RED);
            if (value == 3)
                g.setColor(Color.BLUE);
        }
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(coordX-13, coordY-12, node_diam, node_diam);
        if(value == 0)
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.BLACK);
        g.drawOval(coordX-13, coordY-12, node_diam, node_diam);

        if(number < 10)
            g.drawString(((Integer)(number)).toString(), coordX, coordY+7);
        else
            g.drawString(((Integer)(number)).toString(), coordX-6, coordY+7);
    }
}

