package tasks;

import utils.OtherUtils;
import utils.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;

//Answer :
public class Task_317 implements ITask {
    private double top;

    public static void main(String[] args) {
        // to cleare see the shape is a parabola :)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Visualizer frame = new Visualizer();
                frame.setVisible(true);
            }
        });

        Logger.init("default.log");
        Tester.test(new Task_317());
        Logger.close();
    }

    public static final double g = 9.81;
    public static final double v = 20;
    public static final double h = 100;

    private double hmax;
    private double w100;
    private double k;
    private double wmax;

    public void solving() {
//      so it's a parabola, with top in (0, hmax) and points in (+-w100, 100)

//        double vx = v/sqrt(2);
//        double vy = v/sqrt(2);
//        h == 0 == vy*t - g*t^2/2 == vy - gt/2=>
//        double t = 2*vy / g;
//        double w100 = vx*t == 2*vx*vy/g == 2*vx^2/g == 2*v^2/2/g == v^2/g;
        w100 = v*v/g;
        top = v*v/g;
        hmax = h + top;
        // y = k *x^2;
        k = top / (w100*w100);
        // parabola equation:  y = hmax - k*x^2
        wmax = sqrt(hmax/k);
        // integral: sy = h*x - x^3/3;

        double v = PI*PI*(sy(wmax) - sy(-wmax));
        System.out.println(OtherUtils.formatDouble(v, 4));
    }

    private double sy(double x) {
        return h*x - x*x*x/3.0;
    }

    public static class Visualizer extends JFrame {
        public Visualizer() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);

            getContentPane().add(new FireworksPanel());
        }

        public class FireworksPanel extends JPanel implements ActionListener {
            private BufferedImage img;
            private Graphics2D g2;

            double final dt = 0.2;
            private Timer timer;

            public FireworksPanel() {
                timer = new Timer(1, this);
                timer.setRepeats(true);
                timer.start();
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                drawNewParticle((random() - 0.5) * (2 * Math.PI));
                repaint();
            }

            private void drawNewParticle(double v) {
                double t = 0;
                int ox = 0;
                int oy = 0;
                double vx = 0;
                double vy = 0;
                boolean justStarted;
                justStarted = true;
                t = -dt;
                double a = (random() - 0.5) * (2*Math.PI);
                vx = v*cos(a);
                vy = v*sin(a);

                t += dt;
                int x = (int) round(Visualizer.this.getWidth() / 2 + vx * t);
                int y = (int) round(h + vy*t - g*t*t/2.0);

                if (!justStarted) {
                    if (g2 != null) {
                        g2.drawLine(ox, getHeight()/2 - oy, x, getHeight()/2 - y);
                    }
                } else {
                    justStarted = false;
                }
                ox = x;
                oy = y;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (g2 == null) {
                    img = getGraphicsConfiguration().createCompatibleImage(getWidth(), getHeight());
                    g2 = img.createGraphics();
                    g2.setColor(Color.WHITE);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    g2.setColor(Color.BLACK);
                }
                g.drawImage(img, 0, 0, null);
                g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
            }
        }
    }
}
