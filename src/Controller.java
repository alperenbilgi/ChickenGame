import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

	public LinkedList<EntityA> ea = new LinkedList<EntityA>();
	public LinkedList<EntityB> eb = new LinkedList<EntityB>();
	public LinkedList<EntityC> ec = new LinkedList<EntityC>();
	public EntityA enta;
	public EntityB entb;
	public EntityC entc;
	private Game game;
	private Random r = new Random();
			
	public Controller(Game g) {
		game = g;
	}
	
	public void createCat(int count) {
		for(int i = 0; i < count; i++)
			addEntity(new Cat(r.nextInt(game.width - game.edge_width - 90) + game.edge_width + 20, 800, game.speed + r.nextInt(2) + 1, 70, game));
	}
	
	public void createLeftCoin(int count) {
		for(int i = 0; i < count; i++)
			addEntity(new Coin(game.edge_width - 10, r.nextInt(1500) + 750, r.nextInt(30) + 40, "Left", game, this));
	}
	
	public void createRightCoin(int count) {
		int temp;
		for(int i = 0; i < count; i++) {
			temp = r.nextInt(30) + 40;
			addEntity(new Coin(game.width - game.edge_width - temp + 10, r.nextInt(1500) + 750, temp, "Right", game, this));
		}
	}
	
	public void tick() {
		for(int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			
			enta.tick();
		}
		
		for(int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			
			entb.tick();
		}
		
		for(int i = 0; i < ec.size(); i++) {
			entc = ec.get(i);
			
			entc.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			
			enta.render(g);
		}
		
		for(int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			
			entb.render(g);
		}
		
		for(int i = 0; i < ec.size(); i++) {
			entc = ec.get(i);
			
			entc.render(g);
		}
	}
	
	public void addEntity(EntityA ent) {
		ea.add(ent);
	}
	
	public void removeEntity(EntityA ent) {
		ea.remove(ent);
	}
	
	public void addEntity(EntityB ent) {
		eb.add(ent);
	}
	
	public void removeEntity(EntityB ent) {
		eb.remove(ent);
	}
	
	public void addEntity(EntityC ent) {
		ec.add(ent);
	}
	
	public void removeEntity(EntityC ent) {
		ec.remove(ent);
	}
	
	public LinkedList<EntityA> getEntityA() {
		return ea;
	}
	
	public LinkedList<EntityB> getEntityB() {
		return eb;
	}
	
	public LinkedList<EntityC> getEntityC() {
		return ec;
	}
}