package graphics;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Dzmitry
 */
public class PaintComponent extends JComponent {
    ArrayList<Point> p1 = new ArrayList<Point>();
    ArrayList<Point> p2 = new ArrayList<Point>();
    public PaintComponent() {
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        p1.add(new Point(x1, 400-y1));
        p2.add(new Point(x2, 400-y2));
    }

    protected void paintComponent(Graphics g) {
//        g.setColor(Color.red);

        g.drawOval(0, 0, 200, 400);


        for (int i = Math.max(0, p1.size()-2); i < p1.size(); ++i) {
            Point P1 = p1.get(i);
            Point P2 = p2.get(i);

            g.drawLine(P1.x, P1.y, P2.x, P2.y);
        }
    }
}
