import java.awt.Graphics;
import java.awt.Rectangle;

public class Chicken implements EntityA {
	
	private int x, y, size;
	private int velX = 0, velY = 0;
	private Game g;
	private Animation animation;
	
	public Chicken(int x, int y, int size, Game g) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.g = g;
		animation = g.getChickenAnimation();
	}
	
	public void tick() {
		animation.tick();
		
		x += velX;
		y += velY;
		
		if(x <= g.edge_width)
			x = g.edge_width;
		if(x >= g.width - g.edge_width - size)
			x = g.width - g.edge_width - size;
		if(y <= 0)
			y = 0;
		if(y >= g.height - size)
			y = g.height - size;
	
		if(Physics.CollisionAtoC(this, g.ec)) {
			Sound.gameoverSound();
			g.started = false;
			g.gameover = true;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(animation.getCurrentFrame(), x, y, size, size, null);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setVelX(int x) {
		velX = x;
	}

	public void setVelY(int y) {
		velY = y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, size - 10, size - 10);
	}
}