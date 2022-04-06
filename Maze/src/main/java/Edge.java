import java.awt.Color;
import java.awt.Graphics;

public class Edge extends Node
{
    private Node start;
    private Node end;

    public Edge(Node start, Node end)
    {
        this.start = start;
        this.end = end;
    }

    public void drawArc(Graphics g)
    {
        if (start != null)
        {
            g.setColor(Color.RED);
            g.drawLine(start.getCoordX(), start.getCoordY(), end.getCoordX(), end.getCoordY());
        }
    }
}

