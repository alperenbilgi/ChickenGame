import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter {
	
	Game g;
	
	public KeyManager(Game g) {
		this.g = g;
	}
	
	public void keyPressed(KeyEvent e) {
		g.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		g.keyReleased(e);
	}
}
