
import java.awt.Color;

import processing.core.PApplet;

public class MainApplication extends PApplet {

	private SuperPixel[][] pixels;
	private SuperPixel[][] buffer;
	private SuperPixel[][] previous;
	private MyColorer c = new MyColorer();

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

	public void draw() {
		background(64);

		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				SuperPixel sp = pixels[r][c];
				fill(sp.getColor().getRGB());
				ellipse(c * 16 + 8, r * 16 + 8, sp.getSize(), sp.getSize());
			}
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
			}
		}
	}

	public void mousePressed() {
		int row = (int) (mouseY / 16.0);
		int col = (int) (mouseX / 16.0);
		SuperPixel sp = pixels[row][col];
		Pixel8.modifySuperPixel(sp);

	}

}
