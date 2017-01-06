
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import processing.core.PApplet;

/***
 * Runs the main application. Presents a dialog box to request the
 * name of a class to use as colorizer. If the requested class does
 * not exist or cannot be found, a default colorizer is used.
 * 
 * @author kentcollins
 */
public class SuperPixelGrid extends PApplet {

	private SuperPixel[][] pixels;
	private SuperPixel[][] buffer;
	private SuperPixel[][] previous;
	private Colorizer c;
	private boolean toolTips = false;
	int[] mouseCoords = { -1, -1 }; // row and column

	public static void main(String args[]) {
		PApplet.main(new String[] { "SuperPixelGrid" });
	}

	public void settings() {
		size(512, 512);
	}

	public void setup() {
		noStroke();
		pixels = new SuperPixel[32][32];
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				pixels[r][c] = new SuperPixel(Color.BLACK);
			}
		}
		previous = pixels;
		buffer = pixels;
		String requestedColorerClass = JOptionPane.showInputDialog("Specify a Colorizer: ");
		try {
			Class<?> cp = Class.forName(requestedColorerClass);
			c = (Colorizer) cp.getConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			requestedColorerClass = "using default colorizer";
			c = new DefaultColorer();
		}
		this.surface.setTitle("SuperPixel 1.0 -- "+requestedColorerClass);

	}

	public void update() {
		// calculate which column is under mouse
		if (mouseX >= 0 && mouseX < width) {
			mouseCoords[1] = (int) (mouseX / 16.0);
		} else
			mouseCoords[1] = -1;
		if (mouseY >= 0 && mouseY < height) {
			mouseCoords[0] = (int) (mouseY / 16.0);
		} else
			mouseCoords[0] = -1;
	}

	public void draw() {
		update();
		background(96);
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				SuperPixel sp = pixels[r][c];
				fill(sp.getColor().getRGB());
				ellipse(c * 16 + 8, r * 16 + 8, sp.getSize(), sp.getSize());
			}
		}
		if (toolTips) {
			pushStyle();
			fill(255);
			int row = mouseCoords[0];
			int col = mouseCoords[1];
			text("(" + row + "," + col + ")", mouseX, mouseY);
			popStyle();
		}
	}

	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == UP) {
				c.commandUp(pixels);
			} else if (keyCode == RIGHT) {
				c.commandRight(pixels);
			} else if (keyCode == DOWN) {
				c.commandDown(pixels);
			} else if (keyCode == LEFT) {
				c.commandLeft(pixels);
			}
		} else {
			if (key == 'r' || key == 'R') {
				buffer = c.commandRed(pixels);
			} else if (key == 'g' || key == 'G') {
				buffer = c.commandGreen(pixels);
			} else if (key == 'b' || key == 'B') {
				buffer = c.commandBlue(pixels);
			} else if (key == 'w' || key == 'W') {
				buffer = c.commandWhite(pixels);
			} else if (key == 'c' || key == 'C') {
				c.commandClear(pixels);
			} else if (key == 'l' || key == 'L') {
				previous = pixels;
				c.lifeCommand(pixels);
			} else if (key == 'z' || key == 'Z') {

				if (previous != null) {
					buffer = pixels;
					pixels = previous;
					previous = buffer;
				}
			} else if (key == ' ') {
				if (buffer != null) {
					previous = pixels;
					pixels = buffer;
				}
			} else if (key == 't' || key == 'T') {
				toolTips = !toolTips;
			}
		}
	}

	public void mousePressed() {
		SuperPixel sp = pixels[mouseCoords[0]][mouseCoords[1]];
		Colorizer.modifySuperPixel(sp);
	}

}
