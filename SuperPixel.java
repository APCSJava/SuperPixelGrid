import java.awt.Color;

public class SuperPixel {
	
	private Color color;
	private float size;

	public SuperPixel(Color color) {
		this.color = color;
		this.size = 15;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

}
