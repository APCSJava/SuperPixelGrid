
import java.awt.Color;
import java.awt.event.KeyEvent;

/***
 * Runs the main application.
 * 
 * @author kentcollins
 * @version 2.0 adapted from Processing to stdlib.jar
 */
public class SuperPixelGrid {

	private final int SCREEN_WIDTH = 512;
	private final int SCREEN_HEIGHT = 512;

	private Colorizer c;
	private SuperPixel[][] pixels;
	private SuperPixel[][] buffer;
	private SuperPixel[][] previous;  // used for undo action
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
			StdDraw.pause(100);
		}
	}

	private void checkKeys() {
		if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
			c.commandUp(pixels);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
			c.commandLeft(pixels);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
			c.commandDown(pixels);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
			c.commandRight(pixels);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			buffer = c.commandWhite(pixels);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
			buffer = c.commandRed(pixels);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_G)) {
			buffer = c.commandGreen(pixels);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_B)) {
			buffer = c.commandBlue(pixels);
		}  else if (StdDraw.isKeyPressed(KeyEvent.VK_C)) {
			c.commandClear(pixels);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_L)) {
			previous = pixels;
			c.lifeCommand(pixels);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_Z)) {
			if (previous != null) {
				buffer = pixels;
				pixels = previous;
				previous = buffer;
			}
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
			if (buffer != null) {
				previous = pixels;
				pixels = buffer;
			}
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_T)) {
			StdOut.println(toolTips);
			toolTips = !toolTips;
			while (StdDraw.isKeyPressed(KeyEvent.VK_T)) {
				// wait for key release
			}
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
			System.exit(0);
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

		// deal with mouse presses
		if (StdDraw.isMousePressed()) {
			SuperPixel sp = pixels[mouseCoords[0]][mouseCoords[1]];
			Colorizer.modifySinglePixel(sp);
			while (StdDraw.isMousePressed()) {
				// wait for mouse release
			}
		}
	}

	private void draw() {
		StdDraw.clear(java.awt.Color.DARK_GRAY);
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				SuperPixel sp = pixels[r][c];
				StdDraw.setPenColor(sp.getColor());
				StdDraw.filledCircle(c * 16 + 8, r * 16 + 8,
						sp.getSize());
			}
		}
		if (toolTips) {
			StdDraw.setPenColor(java.awt.Color.WHITE);
			int row = 31 - mouseCoords[0]; // display upper left corner as (0, 0)
			int col = mouseCoords[1];
			StdDraw.text(StdDraw.mouseX(), StdDraw.mouseY(),
					"(" + row + "," + col + ")");
		}
		StdDraw.show();
	}

}
