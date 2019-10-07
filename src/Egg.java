import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Egg implements EntityA {
	
	private int x, y, size;
	private String direction;
	private BufferedImage image;
	private Game g;
	private Controller c;
	
	public Egg(int x, int y, int size, String direction, Game g, Controller c) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.direction = direction;
		this.g = g;
		this.c = c;
		image = g.getEggPic();
	}
	
	public void tick() {
		y += 1;
		
		if(direction == "Left")
			x -= 5;
		else if(direction == "Right")
			x += 5;
	
		if(x <= -size || x >= g.width + size)
			c.removeEntity(this);
		
		if(Physics.CollisionAtoC(this, g.ec))
			c.removeEntity(this);
	}
	
	public void render(Graphics g) {
		g.drawImage(image, x, y, size, size, null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, size - 10, size - 10);
	}
}