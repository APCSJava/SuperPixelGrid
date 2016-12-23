
import java.awt.Color;

import processing.core.PApplet;
import processing.core.PVector;

public class MainApplication extends PApplet {

	private SuperPixel[][] pixels;
	private SuperPixel[][] buffer;
	private SuperPixel[][] previous;
	private MyColorer c = new MyColorer();
	private boolean toolTips = false;
	int[] mouseCoords = { -1, -1 }; // row and column

	public static void main(String args[]) {
		PApplet.main(new String[] { "MainApplication" });
		// PApplet.main(new String[] {"--present", "MainApplication"
		// });
	}

	public void settings() {
		size(512, 512);
	}

	public void setup() {
		this.surface.setTitle("SuperPixel 1.0");
		noStroke();
		pixels = new SuperPixel[32][32];
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				pixels[r][c] = new SuperPixel(Color.BLACK);
			}
		}
		previous = pixels;
		buffer = pixels;
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
			text("("+row+","+col+")", mouseX, mouseY);
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
				previous = pixels;
				pixels = buffer;
			} else if (key == 't' || key == 'T') {
				toolTips = !toolTips;
			}
		}
	}

	public void mousePressed() {
		SuperPixel sp = pixels[mouseCoords[0]][mouseCoords[1]];
		Pixel8.modifySuperPixel(sp);
	}

}
