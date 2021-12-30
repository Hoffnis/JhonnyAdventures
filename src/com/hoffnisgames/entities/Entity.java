package com.hoffnisgames.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.Camera;
import com.hoffnisgames.world.Node;
import com.hoffnisgames.world.Vector2i;

public class Entity {
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(7*16, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(6*16, 0, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(6*16, 16, 16, 16);
	public static BufferedImage BOMB_EN = Game.spritesheet.getSprite(8*16, 0, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(7*16, 16, 16, 16);
	public static BufferedImage ENEMY_FEED = Game.spritesheet.getSprite(144, 16, 16, 16);
	public static BufferedImage ENEMY2_EN = Game.spritesheet.getSprite(6*16, 32, 16, 16);
	public static BufferedImage ENEMY2_FEED = Game.spritesheet.getSprite(16*9, 3*16, 16, 16);
	public static BufferedImage ENEMY3_EN = Game.spritesheet.getSprite(6*16, 3*16, 16, 16);
	public static BufferedImage ENEMY3_FEED = Game.spritesheet.getSprite(16*8, 3*16, 16, 16);
	public static BufferedImage BOSS_EN = Game.spritesheet.getSprite(0, 8*16, 32, 32);
	public static BufferedImage BOSS_FEED = Game.spritesheet.getSprite(0, 6*16, 32, 32);
	public static BufferedImage BOSS2_EN = Game.spritesheet.getSprite(8*16, 9*16, 16, 16);
	public static BufferedImage BOSS2_FEED = Game.spritesheet.getSprite(9*16, 8*16, 16, 16);
	public static BufferedImage ENEMY4_EN = Game.spritesheet.getSprite(4*16, 9*16, 16, 16);
	public static BufferedImage ENEMY4_FEED = Game.spritesheet.getSprite(4*16, 8*16, 16, 16);
	public static BufferedImage ENEMY5_EN = Game.spritesheet.getSprite(6*16, 9*16, 16, 16);
	public static BufferedImage ENEMY5_FEED = Game.spritesheet.getSprite(6*16, 8*16, 16, 16);
	public static BufferedImage NPC_M = Game.spritesheet.getSprite(0, 48, 16, 16);
	protected double x;
	protected double y;
	protected int width;
	protected int heigth;
	public int depth;
	
	protected List<Node> path;
	
	private BufferedImage sprite;
	
	public int maskx, masky, mwidth, mheigth;
	
	public Entity(int x, int y, int width, int heigth, BufferedImage sprite){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheigth = heigth;
	}
	
public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
		
		@Override	
		public int compare(Entity n0, Entity n1) {
			if(n1.depth < n0.depth) 
				return + 1;
			if(n1.depth >n0.depth)
			return -1;
			return 0;
		}
	};
	
	public void setX(int newX) {
		this.x = newX;
		
	}
	
	
	public void setY(int newY) {
		
		this.y = newY;
		
	}
	
	public int getX(){
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
		
	}
	
	public int getWidth() {
		return this.width;
		
	}
	
	public int getHeigth() {
		
		return this.heigth;
	}
	
	public void tick() {
		
		
	}
	
	public double calculateDistance(int x1, int y1, int x2, int y2) {
		
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	public boolean isColidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext  + maskx, ynext + masky, mwidth, mheigth);
		for(int i = 0 ; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this) {
			continue;}
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky,mwidth,mheigth);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}		
		}
		
		
		return false;
	}
	
	public void followPath(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() -1 ).tile;
				
				if(x< target.x * 16) {
					x++;
				}else if(x > target.x *16) {
					x--;
				}
				
				if(y < target.y * 16) {
					y++;
				}
				else if(y> target.y *16) {
					y--;
				}
				
				if(x == target.x* 16 && y == target.y * 16) {
					path.remove(path.size()-1);
				}
			}
		}
	}
	
	public static boolean isCollinding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mwidth, e1.mheigth);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mwidth, e2.mheigth);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		
	}


}
