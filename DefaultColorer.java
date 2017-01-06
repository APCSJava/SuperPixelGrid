import java.awt.Color;

/***
 * Provides a sample implementation of the Colorizer interface. The
 * following commands are implemented intelligently:
 * 
 * <pre>
 * 'r' or 'R' -- returns a grid where each superpixel is set to red
 * 'w' or 'W' -- returns a grid where existing superpixels may be replaced by white
 * LEFT -- immediately modifies the grid by shifting each superpixel one column left
 * </pre>
 * 
 * @author kentcollins
 *
 */
public class DefaultColorer implements Colorizer {

	@Override
	public SuperPixel[][] commandRed(SuperPixel[][] original) {
		SuperPixel[][] mod = new SuperPixel[original.length][original[0].length];
		for (int r = 0; r < mod.length; r++) {
			for (int c = 0; c < mod[r].length; c++) {
				mod[r][c] = new SuperPixel(Color.RED);
			}
		}
		return mod;
	}

	/**
	 * A broken implementation that needs to be corrected
	 */
	public SuperPixel[][] commandGreen(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * A broken implementation that needs to be corrected
	 */
	public SuperPixel[][] commandBlue(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperPixel[][] commandWhite(SuperPixel[][] original) {
		SuperPixel[][] mod = new SuperPixel[original.length][original[0].length];
		for (int r = 0; r < mod.length; r++) {
			for (int c = 0; c < mod[r].length; c++) {
				mod[r][c] = Math.random() > 0.5 ? new SuperPixel(Color.WHITE)
						: new SuperPixel(original[r][c].getColor());
			}
		}
		return mod;
	}

	/**
	 * A broken implementation that needs to be corrected
	 */
	public void commandClear(SuperPixel[][] original) {
		// TODO Auto-generated method stub
	}

	/**
	 * A broken implementation that needs to be corrected
	 */
	public void commandUp(SuperPixel[][] original) {
		// TODO Auto-generated method stub

	}

	/**
	 * A broken implementation that needs to be corrected
	 */
	public void commandDown(SuperPixel[][] original) {
		// TODO Auto-generated method stub

	}

	@Override
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
	 * A broken implementation that needs to be corrected
	 */
	public void commandRight(SuperPixel[][] original) {
		// TODO Auto-generated method stub

	}

}
