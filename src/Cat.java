import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Cat implements EntityC {

	private int x, y, speed, size;
	private Game g;
	private BufferedImage image_left, image_right;
	private Random r = new Random();
	
	public Cat(int x, int y, int speed, int size, Game g) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
		this.g = g;
		image_right = g.getCatPic_R();
		image_left = g.getCatPic_L();
	}
	
	public void tick() {
		y -= speed;
		
		if(y < -size) {
			x = r.nextInt(g.width - g.edge_width - 90) + g.edge_width + 20;
			y = 750;
			speed = g.speed + r.nextInt(2) + 1;
		}
	}
	
	public void render(Graphics g) {
		if(x >= (this.g.width) / 2)
			g.drawImage(image_right, x, y, size, size, null);
		else
			g.drawImage(image_left, x, y, size, size, null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, size - 30, size - 30);
	}
}