
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
	private final int CELL_SIZE = 16;

	private Colorizer c;
	private SuperPixel[][] pixels;
	private SuperPixel[][] buffer;
	private SuperPixel[][] previous;  // used for undo action
	private boolean toolTips = false;
	int[] cellCoords = { -1, -1 }; // row and column from upper left

	public static void main(String args[]) {
		SuperPixelGrid spg = new SuperPixelGrid();
		spg.run();
	}

	public SuperPixelGrid() {
		pixels = new SuperPixel[SCREEN_WIDTH/CELL_SIZE][SCREEN_HEIGHT/CELL_SIZE];
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
				StdDraw.pause(100);
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
		double mouseY = SCREEN_HEIGHT-StdDraw.mouseY();
		int mouseRow = (int) (mouseY/CELL_SIZE);
		int mouseCol = (int) (mouseX/CELL_SIZE);
		cellCoords = new int[] {mouseRow, mouseCol};
		// deal with mouse presses
		if (StdDraw.isMousePressed()) {
			SuperPixel sp = pixels[mouseRow][mouseCol];
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
				StdDraw.filledCircle(c * 16 + 8, SCREEN_HEIGHT- (r * 16) - 8,
						sp.getSize());
			}
		}
		if (toolTips) {
			StdDraw.setPenColor(java.awt.Color.WHITE);
			int row = cellCoords[0]; // display upper left corner as (0, 0)
			int col = cellCoords[1];
			StdDraw.text(StdDraw.mouseX(), StdDraw.mouseY(),
					"(" + row + "," + col + ")");
		}
		StdDraw.show();
	}

}
