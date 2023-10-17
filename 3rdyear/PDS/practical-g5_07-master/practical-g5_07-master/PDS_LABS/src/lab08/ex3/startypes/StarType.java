package startypes;
import java.awt.*;

public abstract class StarType {

    private int size;
    private Color color;
    protected String description;
    protected Float[] physicalProperties;
    private int x;
    private int y;

    public StarType(int size, Color color, int x, int y) {
        this.size = size;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y , size, size);
    }
    
}
