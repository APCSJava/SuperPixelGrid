import java.awt.Color;

public interface Responder {

	SuperPixel[][] processRed(SuperPixel[][] original);

	SuperPixel[][] processGreen(SuperPixel[][] original);

	SuperPixel[][] processBlue(SuperPixel[][] original);

	SuperPixel[][] processWhite(SuperPixel[][] original);

	SuperPixel[][] processClear(SuperPixel[][] original);
	
	SuperPixel[][] processZ(SuperPixel[][] original);

	void processUp(SuperPixel[][] original);

	void processDown(SuperPixel[][] original);

	void processLeft(SuperPixel[][] original);

	void processRight(SuperPixel[][] original);

	void processSpace(SuperPixel[][] original);

	void modifySuperPixel(SuperPixel thisPixel);

	Color processFill();

}
