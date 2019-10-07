import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityC {

	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();	
	public int getX();
	public int getY();

}