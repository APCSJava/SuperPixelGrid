
/***
 * The CommandHandler class colorizes the superpixels in a grid in response to
 * mouse and keyboard interactions.
 *
 */

import javax.swing.*;
import java.awt.Color;

public class CommandHandler {

    /**
     * Produces a new array colored as the original but with a 50% probability that
     * any given pixel will be changed to white.
     * <p>
     * Postcondition -- all superpixels in the parameter original remain unchanged
     *
     * @param original a superpixel array that should not be altered
     * @return a new array possessing the properties described
     */
    public SuperPixel[][] commandWhite(SuperPixel[][] original) {
        SuperPixel[][] temp = new SuperPixel[original.length][original[0].length];
        for (int r = 0; r < temp.length; r++) {
            for (int c = 0; c < temp[r].length; c++) {
                if (Math.random() > 0.5) {
                    temp[r][c] = new SuperPixel(Color.WHITE);
                } else {
                    temp[r][c] = new SuperPixel(original[r][c].getColor());
                }
            }
        }
        return temp;
    }

    /**
     * Produces an array of the same dimensions as the original but with all
     * superpixels colored red (java.awt.Color.RED).
     * <p>
     * Postcondition -- all superpixels in the original remain unchanged
     *
     * @param original a superpixel array that should not be altered
     * @return a new array possessing the properties described
     */
    public SuperPixel[][] commandRed(SuperPixel[][] original) {
        // TODO replace the code below with your own implementation
		String message = "CommandHandler method commandRed not implemented.";
        JOptionPane.showMessageDialog(null, message);
        return null;
    }

    /**
     * Produce an array of the same dimensions as the original having every other
     * column colored green.
     * <p>
     * Postcondition -- all superpixels in the original remain unchanged
     *
     * @param original a superpixel array that should not be altered
     * @return a new array possessing the properties described
     */
    public SuperPixel[][] commandGreen(SuperPixel[][] original) {
		// TODO replace the code below with your own implementation
		String message = "CommandHandler method commandGreen not implemented.";
		JOptionPane.showMessageDialog(null, message);
		return null;
	}

    /**
     * Produce an array of the same dimensions as the original having every third
     * row colored blue. The returned array will be held in a buffer until loaded by
     * pressing the SPACE bar.
     * <p>
     * Postcondition -- all superpixels in the original remain unchanged
     *
     * @param original a superpixel array that should not be altered
     * @return a new array possessing the properties described
     */
    public SuperPixel[][] commandBlue(SuperPixel[][] original) {
		// TODO replace the code below with your own implementation
		String message = "CommandHandler method commandBlue not implemented.";
		JOptionPane.showMessageDialog(null, message);
		return null;
	}

    /**
     * Modifies the original array by setting the color of all elements to black
     * (java.awt.Color.BLACK).
     *
     * @param original a superpixel array to mutate
     */
    public void commandClear(SuperPixel[][] original) {
		// TODO replace the code below with your own implementation
		String message = "CommandHandler method commandClear not implemented.";
		JOptionPane.showMessageDialog(null, message);
	}

    /**
     * Shifts all super pixels up one row. As the bottom row moves up, it is
     * replaced with a row of all black pixels (i.e. edges do not wrap when scrolling
     * up).
     *
     * @param original a superpixel array to mutate
     */
    public void commandUp(SuperPixel[][] original) {
		// TODO replace the code below with your own implementation
		String message = "CommandHandler method commandUp not implemented.";
		JOptionPane.showMessageDialog(null, message);
	}

    /**
     * Shifts all super pixels down one row. As the bottom row moves down, its
     * pixels reappear at the top (i.e. edges wrap when scrolling down).
     *
     * @param original a superpixel array to mutate
     */
    public void commandDown(SuperPixel[][] original) {
		// TODO replace the code below with your own implementation
		String message = "CommandHandler method commandDown not implemented.";
		JOptionPane.showMessageDialog(null, message);
	}

    /**
     * Shifts all super pixels left one column. As the leftmost pixels scroll off
     * the screen they are replaced with black super pixels in the rightmost column
     * (i.e. edges do not wrap when scrolling left)
     *
     * @param original a superpixel array to mutate
     */
    public void commandLeft(SuperPixel[][] original) {
        for (int r = 0; r < original.length; r++) {
            for (int c = 0; c < original[0].length; c++) {
                if (c == original[0].length - 1) {
                    original[r][c] = new SuperPixel(Color.BLACK);
                } else {
                    original[r][c] = original[r][c + 1];
                }
            }
        }
    }

    /**
     * Shifts all super pixels right one column. As the rightmost pixels scroll off
     * the screen they reappear at the left edge (i.e. edges wrap when scrolling
     * right)
     *
     * @param original a superpixel array to mutate
     */
    public void commandRight(SuperPixel[][] original) {
		// TODO replace the code below with your own implementation
		String message = "CommandHandler method commandRight not implemented.";
		JOptionPane.showMessageDialog(null, message);
	}

    /**
     * Sets the color property of the parameter to white.
     *
     * @param superPixel a single superpixel to mutate
     */
    public static void clickSuperPixel(SuperPixel superPixel) {
		// TODO replace the code below with your own implementation
		String message = "CommandHandler method clickSuperPixel not implemented.";
		JOptionPane.showMessageDialog(null, message);
	}

    /**
     * Modifies the given array according to John Conway's rules for the Game of
     * Life. Examine each cell in the current generation and determine its state in
     * the subsequent generation based on the state of surrounding cells.
     *
     * @param original a superpixel array to mutate
     */
    public SuperPixel[][] lifeCommand(SuperPixel[][] original) {
		// TODO replace the code below with your own implementation
		String message = "CommandHandler method lifeCommand not implemented.";
		JOptionPane.showMessageDialog(null, message);
		return null;
	}

}
