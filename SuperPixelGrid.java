import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/***
 * Runs the main application.
 *
 * @author kentcollins
 * @version 3.0 adapted from StdLib to Java Swing
 */
public class SuperPixelGrid extends JFrame {

    private final int SCREEN_WIDTH = 512;
    private final int SCREEN_HEIGHT = 512;
    private final int CELL_SIZE = 16;

    public SuperPixelGrid() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setResizable(false);
        this.setTitle("SuperPixelGrid 3.0");

        // center the window on the viewing screen
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) screenDim.getWidth() / 2 - SCREEN_WIDTH / 2, (int) screenDim.getHeight() / 2 - SCREEN_HEIGHT / 2);

        // create a panel component and add it to the window
        this.add(new DisplayPanel());
    }

    public static void main(String args[]) {
        SuperPixelGrid spg = new SuperPixelGrid();
        spg.setVisible(true);
        System.out.println("Window should be open and listening");
    }

    private class DisplayPanel extends JPanel {
        private Colorizer c;
        private SuperPixel[][] pixels;
        private SuperPixel[][] buffer;
        private SuperPixel[][] previous; // used for undo action
        private boolean toolTips = false;
        private int[] cellCoords = {-1, -1}; // row and column from upper left
        private double[] lastMouse = {-1, -1}; // lastX, lastY

        public DisplayPanel() {
            this.setBackground(Color.DARK_GRAY);
            pixels = new SuperPixel[SCREEN_WIDTH / CELL_SIZE][SCREEN_HEIGHT / CELL_SIZE];
            for (int r = 0; r < pixels.length; r++) {
                for (int c = 0; c < pixels[r].length; c++) {
                    pixels[r][c] = new SuperPixel(Color.BLACK);
                }
            }
            previous = pixels;
            buffer = pixels;
            c = new Colorizer();

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        c.commandUp(pixels);
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        c.commandLeft(pixels);
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        c.commandDown(pixels);
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        c.commandRight(pixels);
                    } else if (e.getKeyCode() == KeyEvent.VK_W) {
                        buffer = c.commandWhite(pixels);
                    } else if (e.getKeyCode() == KeyEvent.VK_R) {
                        buffer = c.commandRed(pixels);
                    } else if (e.getKeyCode() == KeyEvent.VK_G) {
                        buffer = c.commandGreen(pixels);
                    } else if (e.getKeyCode() == KeyEvent.VK_B) {
                        buffer = c.commandBlue(pixels);
                    } else if (e.getKeyCode() == KeyEvent.VK_C) {
                        c.commandClear(pixels);
                    } else if (e.getKeyCode() == KeyEvent.VK_L) {
                        previous = pixels;
                        buffer = c.lifeCommand(pixels);
                        pixels = buffer;
                    } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                        if (previous != null) {
                            buffer = pixels;
                            pixels = previous;
                            previous = buffer;
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        if (buffer != null) {
                            previous = pixels;
                            pixels = buffer;
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_T) {
                        toolTips = !toolTips;
                    } else if (e.getKeyCode() == KeyEvent.VK_Q) {
                        System.exit(0);
                    }
                    repaint();
                }
            });
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    lastMouse = new double[]{e.getX(), e.getY()};
                    int mouseRow = e.getY() / CELL_SIZE;
                    int mouseCol = e.getX() / CELL_SIZE;
                    cellCoords = new int[]{mouseRow, mouseCol};
                    repaint();
                }
            });
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    SuperPixel sp = pixels[e.getY() / CELL_SIZE][e.getX() / CELL_SIZE];
                    Colorizer.modifySinglePixel(sp);
                }
            });
            this.setFocusable(true);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            for (int r = 0; r < pixels.length; r++) {
                for (int c = 0; c < pixels[r].length; c++) {
                    SuperPixel sp = pixels[r][c];
                    g2d.setPaint(sp.getColor());
                    g2d.fillOval(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
            if (toolTips) {
                g2d.setPaint(java.awt.Color.WHITE);
                int row = cellCoords[0]; // display upper left corner as (0, 0)
                int col = cellCoords[1];
                showMessage("[" + row + "] [" + col + "]", g2d);
            }

        }

        public void showMessage(String s, Graphics2D g2d) {
            Font font = new Font("SansSerif", Font.PLAIN, 16);
            Rectangle2D textBox = font.getStringBounds(s, g2d.getFontRenderContext());
            g2d.setFont(font);
            g2d.setColor(Color.WHITE);
            g2d.drawString(s, (int) (lastMouse[0] - textBox.getWidth() / 2), (int) lastMouse[1]);
        }

    }
}
