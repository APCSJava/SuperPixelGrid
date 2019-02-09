
import java.awt.Color;

/***
 * Runs the main application. Presents a dialog box to request the name of a
 * class to use as colorizer. If the requested class does not exist or cannot be
 * found, a default colorizer is used.
 * 
 * @author kentcollins
 */
public class SuperPixelGrid {

	private final int SCREEN_WIDTH = 512;
	private final int SCREEN_HEIGHT = 512;

	private SuperPixel[][] pixels;
	private SuperPixel[][] buffer;
	private SuperPixel[][] previous;
	private Colorizer c;
	private boolean toolTips = false;
	int[] mouseCoords = { -1, -1 }; // row and column

	public static void main(String args[]) {
		SuperPixelGrid spg = new SuperPixelGrid();
		spg.run();
	}

	public SuperPixelGrid() {
		pixels = new SuperPixel[32][32];
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				pixels[r][c] = new SuperPixel(Color.BLACK);
			}
		}
		previous = pixels;
		buffer = pixels;
		c = new Colorizer();
	}

	public void run() {
		StdDraw.setXscale(0, SCREEN_WIDTH);
		StdDraw.setYscale(0, SCREEN_HEIGHT);
		StdDraw.enableDoubleBuffering();
		while (true) {
			checkMouse();
			checkKeys();
			draw();
		}
	}

	private void checkKeys() {
		while (StdDraw.hasNextKeyTyped()) {
			char k = StdDraw.nextKeyTyped();
			keyPressed(k);
			StdOut.println(k);
		}
	}

	private void checkMouse() {
		double mouseX = StdDraw.mouseX();
		double mouseY = StdDraw.mouseY();

		// calculate which column is under mouse
		if (mouseX >= 0 && mouseX < SCREEN_WIDTH) {
			mouseCoords[1] = (int) (mouseX / 16.0);
		} else
			mouseCoords[1] = -1;
		if (mouseY >= 0 && mouseY < SCREEN_HEIGHT) {
			mouseCoords[0] = (int) (mouseY / 16.0);
		} else
			mouseCoords[0] = -1;
	}

	private void draw() {
		StdDraw.clear(java.awt.Color.DARK_GRAY);
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				SuperPixel sp = pixels[r][c];
				StdDraw.setPenColor(sp.getColor());
				StdDraw.filledCircle(c * 16 + 8, r * 16 + 8, sp.getSize());
			}
		}
		if (toolTips) {
			StdDraw.setPenColor(java.awt.Color.WHITE);
			int row = mouseCoords[0];
			int col = mouseCoords[1];
			StdDraw.text(256, 256, "(" + row + "," + col + ")");
		}
		StdDraw.show();
	}

	private void keyPressed(char key) {
		switch (key) {
		case 'w': case 'W':
			c.commandUp(pixels);
			break;
		case 'a': case 'A':
			c.commandLeft(pixels);
			break;
		case 's': case 'S':
			c.commandDown(pixels);
			break;
		case 'd': case 'D':
			c.commandRight(pixels);
			break;
		case 'r':
		case 'R':
			buffer = c.commandRed(pixels);
			break;
		case 'g':
		case 'G':
			buffer = c.commandGreen(pixels);
			break;
		case 'b':
		case 'B':
			buffer = c.commandBlue(pixels);
			break;
		case 'x':
		case 'X':
			buffer = c.commandWhite(pixels);
			break;
		case 'c':
		case 'C':
			c.commandClear(pixels);
			break;
		case 'l':
		case 'L':
			previous = pixels;
			c.lifeCommand(pixels);
			break;
		case 'z':
		case 'Z':
			if (previous != null) {
				buffer = pixels;
				pixels = previous;
				previous = buffer;
			}
			break;
		case ' ':
			if (buffer != null) {
				previous = pixels;
				pixels = buffer;
			}
			break;
		case 't':
		case 'T':
			StdOut.println(toolTips);
			toolTips = !toolTips;
			break;
		}
	}

	private void mousePressed() {
		SuperPixel sp = pixels[mouseCoords[0]][mouseCoords[1]];
		Colorizer.modifySuperPixel(sp);
	}

}
