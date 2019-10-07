import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Game implements Runnable {

	private Display display;
	private int background_y = 0, cat_count = 3, level2_x = 700, level3_x = 700, level4_x = 700, level5_x = 700, level_check = 300;
	public int width, height, edge_width = 50, speed = 2, real_point = 0, point = 0, LeftCoin_count = 3, LeftCoin_hit = 0, RightCoin_count = 3, RightCoin_hit = 0;
	private boolean running = false, shooting = false, restart = false;
	public boolean started = false, gameover = false;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private KeyManager keyManager;
	private BufferedImage background, left_background, right_background, egg, left_cat, right_cat, end_cat;
	private BufferedImage[] chicken, coin;
	private Animation chicken_animation, coin_animation;
	private Chicken Chicken;
	private Controller c;
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	public LinkedList<EntityC> ec;
	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		keyManager = new KeyManager(this);
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
	}
	
	private void init() {
		background = ImageLoader.loadImage("/images/background.png");
		left_background = ImageLoader.loadImage("/images/background_left.png");
		right_background = ImageLoader.loadImage("/images/background_right.png");
		
		chicken = new BufferedImage[4];
		chicken_animation = new Animation(200, chicken);
		chicken[0] = ImageLoader.loadImage("/images/chicken1.png");
		chicken[1] = ImageLoader.loadImage("/images/chicken2.png");
		chicken[2] = ImageLoader.loadImage("/images/chicken3.png");
		chicken[3] = ImageLoader.loadImage("/images/chicken2.png");
		
		egg = ImageLoader.loadImage("/images/egg.png");
		
		right_cat = ImageLoader.loadImage("/images/cat_right.png");
		left_cat = ImageLoader.loadImage("/images/cat_left.png");
		end_cat = ImageLoader.loadImage("/images/cat_end.png");
		
		coin = new BufferedImage[9];
		coin_animation = new Animation(150, coin);
		coin[0] = ImageLoader.loadImage("/images/coin1.png");
		coin[1] = ImageLoader.loadImage("/images/coin2.png");
		coin[2] = ImageLoader.loadImage("/images/coin3.png");
		coin[3] = ImageLoader.loadImage("/images/coin4.png");
		coin[4] = ImageLoader.loadImage("/images/coin5.png");
		coin[5] = ImageLoader.loadImage("/images/coin6.png");
		coin[6] = ImageLoader.loadImage("/images/coin7.png");
		coin[7] = ImageLoader.loadImage("/images/coin8.png");
		coin[8] = ImageLoader.loadImage("/images/coin9.png");
		
		Chicken = new Chicken(315, 55, 70, this);
		c = new Controller(this);
		c.createCat(cat_count);
		c.createLeftCoin(LeftCoin_count);
		c.createRightCoin(RightCoin_count);
		ea = c.getEntityA();
		eb = c.getEntityB();
		ec = c.getEntityC();
	}
	
	private void tick() {
		if(!started && !gameover) {
			chicken_animation.tick();
		}
		
		if(started && !gameover) {
			Chicken.tick();
			c.tick();
			
			background_y -= 2;
			if(background_y == -height)
				background_y = 0;
		
			if(LeftCoin_hit >= 1) {
				LeftCoin_hit = 0;
				c.createLeftCoin(1);
			}
			
			if(RightCoin_hit >= 1) {
				RightCoin_hit = 0;
				c.createRightCoin(1);
			}
		
			if(point >= level_check && real_point < 2000) {
				Sound.levelupSound();
				point -= level_check;
				level_check += 100;
				speed++;
				edge_width += 30;
				c.createCat(2);
				c.createLeftCoin(1);
				c.createRightCoin(1);
			}
			
			if(real_point >= 300 && level2_x > -400)
				level2_x -= 5;
			
			if(real_point >= 700 && level3_x > -400)
				level3_x -= 5;
			
			if(real_point >= 1200 && level4_x > -400)
				level4_x -= 5;
			
			if(real_point >= 1800 && level5_x > -400)
				level5_x -= 5;
		}
		
		if(gameover && restart) {
			real_point = 0;			point = 0;	
			Chicken.setX(315);		Chicken.setY(55);
			level2_x = 700;			level3_x = 700;
			level_check = 300;
			edge_width = 50;
			speed = 2;
			restart = false;
			started = true;
			gameover = false;	
			init();
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		if(!started && !gameover) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Tahoma", 3, 35));
			g.drawString("Press SPACE to start", 170, 330);
			g.drawImage(chicken_animation.getCurrentFrame(), 20, 20, 50, 50, null);
			g.drawImage(chicken_animation.getCurrentFrame(), 630, 20, 50, 50, null);
			g.drawImage(chicken_animation.getCurrentFrame(), 20, 680, 50, 50, null);
			g.drawImage(chicken_animation.getCurrentFrame(), 630, 680, 50, 50, null);
		}
		
		if(started && !gameover) {
			g.drawImage(background, 0, background_y, width, height, null);
			g.drawImage(left_background, 0, background_y, edge_width, height, null);
			g.drawImage(right_background, width - edge_width, background_y, edge_width, height, null);
			if(background_y >= -height) {
				g.drawImage(background, 0, background_y + height, width, height, null);
				g.drawImage(left_background, 0, background_y + height, edge_width, height, null);
				g.drawImage(right_background, width - edge_width, background_y + height, edge_width, height, null);
			}
			
			Chicken.render(g);
			c.render(g);
				
			g.setColor(Color.yellow);
			g.setFont(new Font("Tahoma", 3, 35));
			g.drawString("Point: " + real_point, 285, 50);	
			
			if(real_point >= 300) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Tahoma", 3, 100));
				g.drawString("Level 2", level2_x, 350);
			}
			
			if(real_point >= 700)
				g.drawString("Level 3", level3_x, 350);
			
			if(real_point >= 1200)
				g.drawString("Level 4", level4_x, 350);

			if(real_point >= 1800)
				g.drawString("Level 5", level5_x, 350);
		}
		
		if(!started && gameover) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Tahoma", 3, 30));
			g.drawString("Game Over", 263, 350);
			g.setFont(new Font("Tahoma", 3, 20));
			g.drawString("Point: " + real_point, 305, 400);
			g.drawImage(end_cat, 250, 75, 200, 200, null);
			g.setColor(Color.RED);
			g.drawString("Press R to restart..", 260, 720);
		}	
		
		bs.show();
		g.dispose();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT)
			Chicken.setVelX(-3);
		else if(key == KeyEvent.VK_RIGHT)
			Chicken.setVelX(3);
		else if(key == KeyEvent.VK_UP)
			Chicken.setVelY(-5);
		else if(key == KeyEvent.VK_DOWN)
			Chicken.setVelY(3);
		else if(key == KeyEvent.VK_A && !shooting) {
			shooting = true;
			c.addEntity(new Egg(Chicken.getX() + 25, Chicken.getY() + 30, 20, "Left", this, c));
		} else if(key == KeyEvent.VK_D && !shooting) {
			shooting = true;
			c.addEntity(new Egg(Chicken.getX() + 25, Chicken.getY() + 30, 20, "Right", this, c));
		} else if(key == KeyEvent.VK_SPACE && !started && !gameover) {
			started = true;
			gameover = false;
		} else if(key == KeyEvent.VK_R && gameover)
			restart = true;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT)
			Chicken.setVelX(0);
		else if(key == KeyEvent.VK_RIGHT)
			Chicken.setVelX(0);
		else if(key == KeyEvent.VK_UP)
			Chicken.setVelY(0);
		else if(key == KeyEvent.VK_DOWN)
			Chicken.setVelY(0);
		else if(key == KeyEvent.VK_A)
			shooting = false;
		else if(key == KeyEvent.VK_D)
			shooting = false;
	}

	public BufferedImage getEggPic() {
		return egg;
	}
	
	public BufferedImage getCatPic_R() {
		return right_cat;
	}
	
	public BufferedImage getCatPic_L() {
		return left_cat;
	}

	public Animation getChickenAnimation() {
		return chicken_animation;
	}
	
	public Animation getCoinAnimation() {
		return coin_animation;
	}

	public void run() {
		init();
	
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		long ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false; 
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}