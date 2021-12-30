package com.hoffnisgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.AS;
import com.hoffnisgames.world.Camera;
import com.hoffnisgames.world.Vector2i;
import com.hoffnisgames.world.World;

public class Enemy4 extends Entity {

	private double speed = 0.88;
	private int frames = 0, maxFrames = 12, index = 0, maxIndex = 1;
	
	private BufferedImage[] sprites;
	private int life = 7;
	private boolean isDamage = false;
	private int damageframe = 10;
	private int damagecur = 0;
	private int flife = 0;
	private int mflife = 15;
	
	public Enemy4(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, null);
		
		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(4*16, 9*16, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(5*16, 9*16, 16, 16);
		
		
		
		
		
		
	}
	
	public void tick() {
		
		/*if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 110) {
		if(isColiddingWithPlayer() == false) {
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
				&& !isColidding((int)(x+speed), this.getY())) {
			
			x+=speed;
			
		}
		else if((int)x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
				&& !isColidding((int)(x-speed), this.getY())) {
			
			x-=speed;
		}
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
				&& !isColidding(this.getX(), (int)(y+speed))) {
			y+=speed;
			
		}
		else if((int)y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
				&& !isColidding(this.getX(), (int)(y-speed))) {
			y-=speed;
			
		} 
		}
else {
		
			
			if(Game.rand.nextInt(100) < 10) {
				Game.player.life -= Game.rand.nextInt(3);
				Game.player.isDamage = true;
			}
		}
		}else {
			
		}*/
		if(!isColiddingWithPlayer()) {
		if(path == null || path.size() == 0) {
			Vector2i start = new Vector2i((int)(x/16),(int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16),(int)(Game.player.y/16));
			path = AS.findPath(Game.world, start, end);
		}
		}else {
			if(Game.rand.nextInt(100) < 15) {
				Game.player.life -= Game.rand.nextInt(8);
				Game.player.isDamage = true;
			}
			
		}
		if(new Random().nextInt(100) < 40)
		followPath(path);
		
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
				
			}
			
			collidingBullet();
			collidingBomb();
			
			if(life <= 0) {
				Game.player.score= Game.player.score+10;
				destroySelf();
				return;
			}
			
			if(isDamage) {
				this.damagecur++;
				if(this.damagecur == this.damageframe) {
					this.damagecur = 0;
					this.isDamage = false;
				}
			}

	}
	
	public void destroySelf() {
		Game.entities.remove(this);
		Game.ara.remove(this);
	}
	
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);	
				if(Entity.isCollinding(this, e)) {
					isDamage = true;
					life--;
					Game.player.score++;
					Game.bullets.remove(i);
					Particle.pcolor = 2;
					World.genparticle(55,(int) x, (int)y);
					return;
				}
				
			}
		}
		
	public void collidingBomb() {
		for(int i = 0; i < Game.bull.size(); i++) {
			Entity e = Game.bull.get(i);	
				if(Entity.isCollinding(this, e)) {
					isDamage = true;
					life= life-5;
					Game.player.score= Game.player.score+3;
					//Game.bull.remove(i);
					Particle.pcolor = 2;
					World.genparticle(105,(int) x, (int)y);
					return;
				}
				
			}
		}
	
	
	public boolean isColiddingWithPlayer() {
		
		Rectangle enemyCurrent = new Rectangle(this.getX()  + maskx, this.getY() + masky, mwidth, mheigth);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);
		return enemyCurrent.intersects(player);
	}
	

	
	public void render(Graphics g) {
		
		if(!isDamage)
		g.drawImage(sprites[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		else
			g.drawImage(Entity.ENEMY4_FEED, this.getX()-Camera.x, this.getY()-Camera.y, null);
		//g.setColor(Color.blue);
		//g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);/*
	}

}