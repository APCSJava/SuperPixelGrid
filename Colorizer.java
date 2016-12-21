import java.awt.Color;

public class Colorizer implements Responder {

	@Override
	public SuperPixel[][] processRed(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperPixel[][] processGreen(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperPixel[][] processBlue(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperPixel[][] processWhite(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperPixel[][] processClear(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuperPixel[][] processZ(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processUp(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processDown(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processLeft(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processRight(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processSpace(SuperPixel[][] original) {
		for (int r = 0; r<original.length; r++) {
			for (int c = 0; c<original[r].length; c++) {
				original[r][c].setColor(Color.BLACK);
			}
		}
		
	}

	@Override
	public void modifySuperPixel(SuperPixel thisPixel) {
		thisPixel.setColor(Color.YELLOW);
	}

	@Override
	public Color processFill() {
		// TODO Auto-generated method stub
		return null;
	}

}
