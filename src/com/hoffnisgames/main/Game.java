package com.hoffnisgames.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.hoffnisgames.entities.Boss;
import com.hoffnisgames.entities.Boss2;
import com.hoffnisgames.entities.Enemy;
import com.hoffnisgames.entities.Enemy2;
import com.hoffnisgames.entities.Enemy3;
import com.hoffnisgames.entities.Enemy4;
import com.hoffnisgames.entities.Enemy5;
import com.hoffnisgames.entities.Entity;
import com.hoffnisgames.entities.Npc;
import com.hoffnisgames.entities.Player;
import com.hoffnisgames.entities.Shoot;
import com.hoffnisgames.entities.Shoot2;
import com.hoffnisgames.graficos.Spritesheet;
import com.hoffnisgames.graficos.UI;
import com.hoffnisgames.world.World;


public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 200;
	public static final int HEIGHT = 150;
	public static final int SCALE = 4;
	private int x = 0;
	public static int CUR_LEVEL = 0;
	private int MAX_LEVEL = 40;
	private boolean showGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;
	
	private BufferedImage image;
	
	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static List<Enemy2> ene;
	public static List<Enemy3> mies;
	public static List<Enemy4> ara;
	public static List<Boss> boss;
	public static List<Shoot> bullets;
	public static List<Shoot2> bull;
	public static List<Boss2> bos;
	public static List<Enemy5> mi;
	public static Spritesheet spritesheet;
	public static Player player;
	public static World world;
	public static Random rand;
	public UI ui;
	public static String gameState = "Menu";
	public Menu menu;
	public static boolean saveGame = false;
	public int[] pixels;
	public BufferedImage lightmap;
	public int[] lightpixel;
	public static BufferedImage minimapa;
	public static int[] mapixel;
	
	public static int tran = 1;
	public static int jogar = 2;
	public static int cena = tran;
	public int timecena = 0;
	public int maxtimecena = 60*3;
	

	
public Game() {
	
	Sound.musicBackground.loop();
	rand = new Random();
	addKeyListener(this);
	addMouseListener(this);
	this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	initFrame();
	
	ui = new UI();
	image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	try {
		lightmap =ImageIO.read(getClass().getResource("/lightmap.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	lightpixel = new int[lightmap.getWidth() * lightmap.getHeight()];
	lightmap.getRGB(0, 0, lightmap.getWidth(), lightmap.getHeight(), lightpixel, 0, lightmap.getWidth());
	entities = new ArrayList<Entity>();
	enemies = new ArrayList<Enemy>();
	ene = new ArrayList<Enemy2>();
	mies = new ArrayList<Enemy3>();
	ara = new ArrayList<Enemy4>();
	boss = new ArrayList<Boss>();
	bos = new ArrayList<Boss2>();
	mi = new ArrayList<Enemy5>();
	spritesheet = new Spritesheet("/spritesheet.png");
	bullets = new ArrayList<Shoot>();
	bull = new ArrayList<Shoot2>();
	menu = new Menu();
	player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
	entities.add(player);
	world = new World("/level0.png");
	
	minimapa = new BufferedImage(World.WIDTH, World.HEIGTH, BufferedImage.TYPE_INT_RGB);
	
	mapixel = ((DataBufferInt)minimapa.getRaster().getDataBuffer()).getData();
}

public void initFrame() {
	
	frame = new JFrame("Hoffnis");
	frame.add(this);
	frame.setResizable(false);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
}

public synchronized void start() {
	thread = new Thread(this);
	isRunning = true;
	thread.start();
}

public synchronized void stop() {
	isRunning = false;
	try {
		thread.join();
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
	
	
}
	
public static void main(String args[]) {
	Game game = new Game();
	game.start();
	
	
}

public void tick() {
	

	if(gameState == "normal") {
		
	if(this.saveGame) {
			this.saveGame = false;
			String[] opt1 = {"level"};
			int[] opt2 = {CUR_LEVEL};
			Menu.saveGame(opt1, opt2, 10);
			System.out.println("Jogo salvo com sucesso");
		}
		this.restartGame = false;
		if(Game.cena == Game.jogar) {
	for(int i = 0; i < entities.size(); i++) {
		Entity e = entities.get(i);
		e.tick();
	}
	
	for(int i = 0; i < bullets.size(); i++) {
		bullets.get(i).tick();
	}
	
	for(int i = 0; i < bull.size(); i++) {
		bull.get(i).tick();
	}
		}else if (Game.cena == Game.tran ){
			Game.bull.remove(this);
			
			timecena++;
			if(timecena >= maxtimecena) {
				Game.cena = Game.jogar;
				timecena = 0;
			}
		}
	
	if(enemies.size() == 0 && ene.size() == 0 && mies.size() == 0 && boss.size() == 0 && bos.size() == 0 && bull.size() == 0 && bullets.size() ==0) {
		Game.bull.remove(this);
		CUR_LEVEL++;
		
		Game.cena = Game.tran;
		if(CUR_LEVEL > MAX_LEVEL) {
			CUR_LEVEL = 1;
		}
		
		
		String newWorld = "level"+CUR_LEVEL+".png";
		Game.bull.remove(this);
		World.restartGame(newWorld);
	}
	}else if(gameState == "gameover") {
		
		
		this.framesGameOver++;
		if(framesGameOver == 25) {
			this.framesGameOver = 0;
			if(showGameOver) 
				this.showGameOver = false;
				else 
				this.showGameOver = true;
			
		}
		if(restartGame) {
			
			this.restartGame = false;
			
			this.gameState = "normal";
			CUR_LEVEL = 1;
			Game.player.score = 0;
			String newWorld = "level"+CUR_LEVEL+".png";
			World.restartGame(newWorld);
		}
	}else if (gameState == "Menu") {
		
		menu.tick();
	}
	
	//System.out.println(bos.size());
}

public void applylight() {
	for(int xx = 0; xx < Game.WIDTH; xx++) {
		for(int yy = 0; yy <Game.HEIGHT; yy++) {
			if(lightpixel[xx+(yy * Game.WIDTH)] == 0xffffffff) {
				pixels[xx+(yy* Game.WIDTH)] = 0;
			}
		}
	}
}

public void render() {
	BufferStrategy bs = this.getBufferStrategy();
	if(bs == null) {
		this.createBufferStrategy(3);
		return;
	}
	
	Graphics g = image.getGraphics();
	g.setColor(new Color(0,0,10));
	g.fillRect(0, 0, WIDTH, HEIGHT);
	
	//Graphics2D g2= (Graphics2D) g;
	
	world.render(g);
	Collections.sort(entities, Entity.nodeSorter);
	for(int i = 0; i < entities.size(); i++) {
		Entity e = entities.get(i);
		e.render(g);
	}
	
	for(int i = 0; i < bullets.size(); i++) {
		bullets.get(i).render(g);
	}
	for(int i = 0; i < bull.size(); i++) {
		bull.get(i).render(g);
	}
	applylight();
	ui.render(g);
	g.dispose();
	g = bs.getDrawGraphics();
	g.drawImage(image,0,0,WIDTH*SCALE, HEIGHT*SCALE, null);
	g.setFont(new Font("Arial", Font.BOLD, 27));
	g.setColor(Color.WHITE);
	g.drawString("Munição: " + player.ammo, 20, 65);
	g.setFont(new Font("Arial", Font.BOLD, 27));
	g.setColor(Color.WHITE);
	g.drawString("Especial: " + player.bomba, 19, 95);
	g.setColor(Color.WHITE);
	g.setFont(new Font("arial",Font.BOLD, 32 ));
	g.drawString((int)player.life+ "/"+ (int)player.maxlife, 65, 37);
	g.setFont(new Font("Arial", Font.BOLD, 35));
	g.setColor(Color.WHITE);
	g.drawString("Score: " + player.score, 570, 42);
	if(gameState == "gameover") {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0, 100));
		g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		g.setFont(new Font("Arial", Font.BOLD, 52));
		g.setColor(Color.WHITE);
		g.drawString(" GAME OVER ", (WIDTH*SCALE) / 2 -170, (HEIGHT*SCALE) / 2 + 25);
		g.setFont(new Font("Arial", Font.BOLD, 28));
		if(showGameOver) {
		g.drawString("Pressione <Enter> para tentar novamente", (WIDTH*SCALE) / 2 -265, (HEIGHT*SCALE) / 2 + 85);
		g.setFont(new Font("Arial", Font.BOLD, 35));
		g.setColor(Color.WHITE);
		g.drawString("High Score: " + player.highscore, 400, 42);
		}
			
		}
	else if(gameState == "Menu") {
		menu.render(g);
		
	}
	
	if(Game.cena == Game.tran) {
		if(gameState != "Menu" && gameState != "gameover") {
			
			g.setFont(new Font("Arial", Font.BOLD, 72));
			g.setColor(Color.WHITE);
			g.drawString("Prepare-se...", (Game.WIDTH*Game.SCALE) / 2-180, (Game.HEIGHT*Game.SCALE) / 2 +50);
		
		}
	}
	
	World.renderMinimap();
	g.drawImage(minimapa, 645, 445, World.WIDTH*5, World.HEIGTH*5, null);
	bs.show();
}
	public void run() {
		long LastTime = System.nanoTime();
		double amountOfTicks= 60.0;
		double ns = 1000000000 / amountOfTicks;
		int frames = 0;
		double timer = System.currentTimeMillis();
		double delta= 0;
		requestFocus();
	
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - LastTime) / ns;
			LastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer+=1000;
			}
		}
				
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			player.jump = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT  || e.getKeyCode() == KeyEvent.VK_D) {
			player.rigth = true;
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			
			player.up = true;
			if(gameState == "Menu") {
				menu.up = true;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
			if(gameState == "Menu") {
				menu.down = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_X) {
			player.sht = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER && gameState == "gameover") {
			this.restartGame = true;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER && gameState == "Menu") {
			menu.enter = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState = "Menu";
			menu.pause = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(gameState == "normal") {
			this.saveGame = true;}
		}
		
	}

	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT  || e.getKeyCode() == KeyEvent.VK_D) {
			player.rigth = false;
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			
			player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
	}


	public void keyTyped(KeyEvent e) {
	
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton() == MouseEvent.BUTTON3) {
		
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1) {
		player.mousesht = true;
		player.mx = (e.getX() /4);
		player.my = (e.getY() /4);
		}
		 if(e.getButton() == MouseEvent.BUTTON3) {
				player.mousesht2 = true;
				player.mx = (e.getX() /4);
				player.my = (e.getY() /4);
		 }
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
