import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Coin implements EntityB {

	private int x, y, size;
	private String side;
	private Game g;
	private Controller c;
	private Animation animation;
	private Random r = new Random();
	
	public Coin(int x, int y, int size, String side, Game g, Controller c) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.side = side;
		this.g = g;
		this.c = c;
		animation = g.getCoinAnimation();
	}
	
	public void tick() {
		animation.tick();
			
		y -= 2;
		
		if(y < -size) {
			y += r.nextInt(300) + 750 + size;
			size = r.nextInt(30) + 40;
			if(side == "Left")
				x = g.edge_width - 10;
			else if(side == "Right")
				x = g.width - g.edge_width - 40;
		}
		
		for(int i = 0; i < g.ea.size(); i++) {
			EntityA TempEnt = g.ea.get(i);
			
			if(Physics.CollisionBtoA(this, g.ea)) {
				Sound.hitSound();
				if(this.side == "Left")
					g.LeftCoin_hit += 1;
				else if(this.side == "Right")
					g.RightCoin_hit += 1;
				g.point += size - 40;
				g.real_point += size - 40;
				c.removeEntity(this);
				c.removeEntity(TempEnt);
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(animation.getCurrentFrame(), x, y, size, size, null);
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