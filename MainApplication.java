
import java.awt.Color;

import processing.core.PApplet;

public class MainApplication extends PApplet {
	
	private SuperPixel[][] pixels;
	private Colorizer c = new Colorizer();

	public static void main(String args[]) {
		PApplet.main(new String[] { "MainApplication" });
		// PApplet.main(new String[] {"--present", "MainApplication" });
	}

	public void settings() {
		size(512, 512);
	}

	public void setup() {
		noStroke();
		pixels = new SuperPixel[32][32];
		for (int r = 0; r<pixels.length; r++) {
			for (int c = 0; c<pixels[r].length; c++) {
				pixels[r][c] = new SuperPixel(Color.BLACK);
			}
		}
	}

	public void draw() {
		background(64);
		
		for (int r = 0; r<pixels.length; r++) {
			for (int c = 0; c<pixels[r].length; c++) {
				SuperPixel sp = pixels[r][c];
				fill(sp.getColor().getRGB());
				ellipse(r*16+8, c*16+8, sp.getSize(), sp.getSize());
			}
		}
	}

	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == UP) {
				c.processUp(pixels);
			} else if (keyCode == RIGHT) {
				c.processRight(pixels);
			} else if (keyCode == DOWN) {
				c.processDown(pixels);
			} else if (keyCode == LEFT) {
				c.processLeft(pixels);
			}
		} else {
			if (key == 'r' || key == 'R') {
				pixels = c.processRed(pixels);
			} else if (key == 'g' || key == 'G') {
				pixels = c.processGreen(pixels);
			} else if (key == 'b' || key == 'B') {
				pixels = c.processBlue(pixels);
			} else if (key == 'c' || key == 'C') {
				pixels = c.processClear(pixels);
			} else if (key == 'w' || key == 'W') {
				pixels = c.processWhite(pixels);
			} else if (key == ' ') {
				c.processSpace(pixels);
				
			}
		}
	}

	public void mousePressed() {
		int row = (int) (mouseX/16.0);
		int col = (int) (mouseY/16.0);
		SuperPixel sp = pixels[row][col];
		c.modifySuperPixel(sp);

	}

}
