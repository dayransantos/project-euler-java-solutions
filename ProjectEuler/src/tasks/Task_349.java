package tasks;

import utils.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Answer :
public class Task_349 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_349());
        Logger.close();
    }

    public void solving() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Visualizer frame = new Visualizer();
                frame.setVisible(true);
            }
        });
    }

    public class Visualizer extends JFrame {
        private int sqsize = 10;

        public Visualizer() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1024, 768);

            getContentPane().add(new AntPanel());
        }

        public class AntPanel extends JPanel implements ActionListener {
            private Timer timer;
            int dx = -1;
            int dy = 0;
            int x = 30;
            int y = 30;

            boolean fill[][] = new boolean[200][200];

            public AntPanel() {
                timer = new Timer(1, this);
                timer.setRepeats(true);
                timer.start();

                drawNewParticle();
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                drawNewParticle();
                repaint();
            }

            private void drawNewParticle() {
                x += dx;
                y += dy;
                boolean f = fill[x][y];

                if (dx > 0) {
                    dx = 0;
                    dy = f ? -1 : 1;
                } else if (dx < 0) {
                    dx = 0;
                    dy = f ? 1 : -1;
                } else if (dy > 0) {
                    dx = f ? 1 : -1;
                    dy = 0;
                } else {
                    dx = f ? -1 : 1;
                    dy = 0;
                }

                fill[x][y] = !f;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (int x = 0; x*sqsize < getWidth(); x++) {
                    for (int y = 0; y*sqsize < getHeight(); y++) {
                        g.setColor(fill[x][y] ? Color.BLACK : Color.WHITE);

                        g.fillRect(x*sqsize, y*sqsize, sqsize, sqsize);
                    }
                }

                g.setColor(Color.BLUE);
                for (int x = 0; x < getWidth(); x += sqsize) {
                    g.drawLine(x, 0, x, getHeight());
                }
                for (int y = 0; y < getHeight(); y += sqsize) {
                    g.drawLine(0, y, getWidth(), y);
                }
            }
        }
    }
}
