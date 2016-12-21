import java.awt.Color;

public class MyColorer implements Pixel8 {

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

	@Override
	public SuperPixel[][] commandGreen(SuperPixel[][] original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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

	@Override
	public void commandClear(SuperPixel[][] original) {
		// TODO Auto-generated method stub
	}

	@Override
	public void commandUp(SuperPixel[][] original) {
		// TODO Auto-generated method stub

	}

	@Override
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

	@Override
	public void commandRight(SuperPixel[][] original) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void lifeCommand(SuperPixel[][] arg) {
		commandLeft(arg);
	}

}
