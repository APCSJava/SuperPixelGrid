import java.awt.Color;

/***
 * Provides practice for implementing an interface. The intended
 * behavior of each method is specified in Javadoc comments.
 * 
 * Intentionally, some methods are expected to use params to modify
 * data while others should avoid doing so -- this facilitates the
 * use of the "undo" command and provides an opportunity to reflect
 * on the use of references, etc.
 * 
 * @author kentcollins
 *
 */
public interface Colorizer {

	/**
	 * Produces an array having the same dimensions as the original
	 * but with all superpixels colored red (java.awt.Color.RED).
	 * 
	 * The returned array will be held in a buffer until loaded by
	 * pressing the SPACE bar. The original array should not be
	 * modified -- i.e. the picture on the screen should not change
	 * until the SPACE key is pressed.
	 * 
	 * @param original
	 *            a superpixel array that should not be altered
	 * @return a new array with the properties described
	 */
	SuperPixel[][] commandRed(SuperPixel[][] original);

	/**
	 * Produce an array of the same dimensions as the original having
	 * every other column colored green.
	 * 
	 * The returned array will be held in a buffer until loaded by
	 * pressing the SPACE bar. The original array should not be
	 * modified -- i.e. the picture on the screen should not change
	 * until the SPACE key is pressed.
	 * 
	 * @param original
	 *            a superpixel array that should not be altered
	 * @return a new array with the properties described
	 */
	SuperPixel[][] commandGreen(SuperPixel[][] original);

	/**
	 * Produce an array of the same dimensions as the original having
	 * every third row colored blue. The returned array will be held
	 * in a buffer until loaded by pressing the SPACE bar.
	 * 
	 * The original array should not be modified -- i.e. the picture
	 * on the screen should not change until the SPACE key is
	 * pressed.
	 * 
	 * @param original
	 *            a superpixel array that should not be altered
	 * @return a new array with the properties described
	 */
	SuperPixel[][] commandBlue(SuperPixel[][] original);

	/**
	 * Produce an array of the same dimensions as the original but
	 * each superpixel has a 50% probability of turning white.
	 * 
	 * The returned array will be held in a buffer until loaded by
	 * pressing the SPACE bar. The original array should not be
	 * modified -- i.e. the picture on the screen should not change
	 * until the SPACE key is pressed.
	 * 
	 * @param original
	 *            a superpixel array that should not be altered
	 * @return a new array with the properties described
	 */
	SuperPixel[][] commandWhite(SuperPixel[][] original);

	/**
	 * Modifies the original array by setting the color of all
	 * elements to black (java.awt.Color.BLACK).
	 * 
	 * @param original
	 *            a superpixel array to mutate
	 */
	void commandClear(SuperPixel[][] original);

	/**
	 * Shifts all super pixels up one row. As the bottom row moves
	 * up, it is replaced with a row of all black pixels (ie. edges
	 * do not wrap when scrolling up).
	 * 
	 * @param original
	 *            a superpixel array to mutate
	 */
	void commandUp(SuperPixel[][] original);

	/**
	 * Shifts all super pixels down one row. As the bottom row moves
	 * down, its pixels reappear at the top (ie. edges wrap when
	 * scrolling down).
	 * 
	 * @param original
	 *            a superpixel array to mutate
	 */
	void commandDown(SuperPixel[][] original);

	/**
	 * Shifts all super pixels left one column. As the leftmost
	 * pixels scroll off the screen they are replaced with black
	 * super pixels in the rightmost column (ie. edges do not wrap
	 * when scrolling left)
	 * 
	 * @param original
	 *            a superpixel array to mutate
	 */
	void commandLeft(SuperPixel[][] original);

	/**
	 * Shifts all super pixels right one column. As the rightmost
	 * pixels scroll off the screen they reappear at the left edge
	 * (ie. edges wrap when scrolling right)
	 * 
	 * @param original
	 *            a superpixel array to mutate
	 */
	void commandRight(SuperPixel[][] original);

	/**
	 * As of Java8, interfaces are able to specify static methods.
	 * While the topic is beyond the scope of the exam, here is an
	 * example.
	 * 
	 * @param thisPixel
	 *            a single superpixel to mutate
	 */
	static void modifySuperPixel(SuperPixel thisPixel) {
		thisPixel.setColor(Color.WHITE);
	}

	/**
	 * As of Java8, interfaces are able to specify default methods.
	 * While the topic is beyond the scope of the exam, here is an
	 * example that simply calls the commandWhite() method.
	 * 
	 * @param original a superpixel array to mutate
	 */
	default void lifeCommand(SuperPixel[][] original) {
		original = commandWhite(original);
	}

}
