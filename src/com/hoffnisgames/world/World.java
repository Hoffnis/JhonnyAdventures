package com.hoffnisgames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.hoffnisgames.entities.Boss;
import com.hoffnisgames.entities.Boss2;
import com.hoffnisgames.entities.Bullet;
import com.hoffnisgames.entities.Bullet2;
import com.hoffnisgames.entities.Enemy;
import com.hoffnisgames.entities.Enemy2;
import com.hoffnisgames.entities.Enemy3;
import com.hoffnisgames.entities.Enemy4;
import com.hoffnisgames.entities.Enemy5;
import com.hoffnisgames.entities.Entity;
import com.hoffnisgames.entities.Lifepack;
import com.hoffnisgames.entities.Npc;
import com.hoffnisgames.entities.Particle;
import com.hoffnisgames.entities.Player;
import com.hoffnisgames.entities.Weapon;
import com.hoffnisgames.graficos.Spritesheet;
import com.hoffnisgames.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGTH;
	public static final int TILE_SIZE = 16;
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			WIDTH = map.getWidth();
			HEIGTH = map.getHeight();
			tiles = new Tile[map.getWidth()*map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++ ) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy*map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					
					if(pixelAtual ==  0xFF000000) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					//CHÃO
					}
					else if(pixelAtual == 0xFFFFFFFF) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16, yy*16,Tile.TILE_WALL);
						//PAREDE
					}
					else if(pixelAtual == 0xFF808080) {
						tiles[xx + (yy * WIDTH)] = new Wall(xx*16, yy*16,Tile.WALL_);
						//PAREDE
					}
					else if(pixelAtual == 0xFF0026FF) {
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
						
						//JOGADOR
					}
					else if(pixelAtual == 0xFFFF0000) {
						Enemy en = new Enemy(xx*16, yy*16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en);
						Game.enemies.add(en);
						//INIMIGO
					}
					else if(pixelAtual == 0xFFFF6A00) {
						Game.entities.add(new Weapon(xx*16, yy*16, 16, 16, Entity.WEAPON_EN));
						//ARMA
					}
					else if(pixelAtual == 0xFF00FFFF) {
						Game.entities.add(new Bullet(xx*16, yy*16, 16, 16, Entity.BULLET_EN));
						
						//MUNIÇÃO
					}
					else if(pixelAtual == 0xFF006989) {
						Game.entities.add(new Bullet2(xx*16, yy*16, 16, 16, Entity.BOMB_EN));
					}
					else if(pixelAtual == 0xFFFF006E) {
						Game.entities.add(new Lifepack(xx*16, yy*16, 16, 16, Entity.LIFEPACK_EN));
						//VIDA
					}
					else if (pixelAtual == 0xFF00FF21) {
						Enemy2 ra = new Enemy2(xx*16, yy*16, 16, 16, Entity.ENEMY2_EN);
						Game.entities.add(ra);
						Game.ene.add(ra);
					}
					else if (pixelAtual == 0xFFFFD800) {
						Enemy3 re = new Enemy3(xx*16, yy*16, 16, 16, Entity.ENEMY3_EN);
						Game.entities.add(re);
						Game.mies.add(re);
					}
					else if (pixelAtual == 0xFFB200FF) {
						Boss bo = new Boss(xx*16, yy*16, 32, 32, Entity.BOSS_EN);
						Game.entities.add(bo);
						Game.boss.add(bo);
					}
					else if (pixelAtual == 0xFFFF9BDC) {
						Boss2 b = new Boss2(xx*16, yy*16, 16, 16, Entity.BOSS2_EN);
						Game.entities.add(b);
						Game.bos.add(b);
					}
					else if (pixelAtual == 0xFFFF9E3D) {
						Game.entities.add(new Npc(xx*16, yy*16, 16, 16, Entity.NPC_M));
					
					
					}
				}
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	public static void genparticle(int amout, int x, int y) {
		for(int i = 0; i < amout; i++) {
			Game.entities.add(new Particle(x,y,1,1,null));
		}
		
	}
	
	public static boolean isFreeDy(int xnext,int ynext, int width, int heigth) {
		int x1 = xnext  / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+width-1)  / TILE_SIZE;
		int y2 = (ynext) / TILE_SIZE;
		
		int x3 = xnext  / TILE_SIZE;
		int y3 = (ynext+heigth-1)/ TILE_SIZE;
		
		int x4 = (xnext+width-1) / TILE_SIZE;
		int y4 = (ynext+heigth-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static boolean isFreeDyn(int xnext,int ynext, int width, int heigth) {
		int x1 = xnext  / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+width-1)  / TILE_SIZE;
		int y2 = (ynext) / TILE_SIZE;
		
		int x3 = xnext  / TILE_SIZE;
		int y3 = (ynext+heigth-1)/ TILE_SIZE;
		
		int x4 = (xnext+width-1) / TILE_SIZE;
		int y4 = (ynext+heigth-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof Wall) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof Wall) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof Wall) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof Wall));
	}

	
	public static boolean isFree(int xnext,int ynext) {
		int x1 = xnext  / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1)  / TILE_SIZE;
		int y2 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x3 = (xnext+TILE_SIZE-1)  / TILE_SIZE;
		int y3 = ynext / TILE_SIZE;
		
		int x4 = xnext / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(String level) {
		Game.entities = new ArrayList<Entity>();
		Game.enemies = new ArrayList<Enemy>();
		Game.ene = new ArrayList<Enemy2>();
		Game.mies = new ArrayList<Enemy3>();
		Game.boss = new ArrayList<Boss>();
		Game.bos = new ArrayList<Boss2>();
		Game.ara = new ArrayList<Enemy4>();
		Game.mi = new ArrayList<Enemy5>();
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.player = new Player(0, 0, 16, 16, Game.spritesheet.getSprite(32, 0, 16, 16));
		Game.entities.add(Game.player);
		Game.world = new World("/"+level);
		Game.minimapa = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);
		Game.mapixel = ((DataBufferInt)Game.minimapa.getRaster().getDataBuffer()).getData();
		return;
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		int xfinal = xstart + (Game.WIDTH >> 4) + 2;
		int yfinal = ystart + (Game.HEIGHT >> 4) + 2;
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGTH) {
					continue;
				}
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
	public static void renderMinimap() {
		for(int i = 0; i < Game.mapixel.length; i++) {
			Game.mapixel[i] = 0;
		}
		for(int xx = 0; xx < WIDTH; xx ++) {
			for(int yy = 0; yy < HEIGTH; yy++) {
				if(tiles[xx + (yy*WIDTH)] instanceof WallTile) {
					Game.mapixel[xx + (yy*WIDTH)] = 0xff0000;
				}
			}
		}
		
		int xPlayer = Game.player.getX()/16;
		int yPlayer = Game.player.getY()/16;
		
		Game.mapixel[xPlayer + (yPlayer*WIDTH)] = 0x0000ff;
	}
}
