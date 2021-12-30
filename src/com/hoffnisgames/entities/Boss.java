package com.hoffnisgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.hoffnisgames.entities.Enemy4;

import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.Camera;
import com.hoffnisgames.world.Vector2i;
import com.hoffnisgames.world.World;

public class Boss extends Entity {
	
	private double speed = 0.15;
	private BufferedImage[] sprites;
	private boolean isDamage = false;
	private int frames = 0, maxFrames = 12, index = 0, maxIndex = 1;
	private int life = 250;
	private int damageframe = 10;
	private int damagecur = 0; 
	private int masky = 0;
	private int maskx = 0;
	private int mwidth = 32;
	private int mheigth = 32;
	public int spaw = 0;
	public static boolean spawned = false;
	private int flife = 0;
	private int mflife = 15;

	public Boss(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, null);
		
		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(0, 16*8, 32, 32);
		sprites[1] = Game.spritesheet.getSprite(32, 16*8, 32, 32);
		
	}
	
public void tick() {
	
	spaw++;
	
	if(spaw >= 100) {
		if(Game.rand.nextInt(100) < 18) {
				spawned = true;	
		} else {
			spaw = 0;
		}
		
	}
	
	
	
		
	
	 if (Boss.spawned == true) {
		Enemy4 ar = new Enemy4(this.getX(), this.getY(), 16, 16, Entity.ENEMY4_EN);
		Game.entities.add(ar);
		Game.ara.add(ar);
		spaw = 0;
		spawned = false;
	}
	
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 110) {
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
		
			
			if(Game.rand.nextInt(100) < 50) {
				Game.player.life -= Game.rand.nextInt(50);
				Game.player.isDamage = true;
			}
		}
		
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
			destroySelf();
			Game.player.score= Game.player.score +500;
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
}

public boolean isColiddingWithPlayer() {
	
	Rectangle enemyCurrent = new Rectangle(this.getX()  + maskx, this.getY() + masky, mwidth, mheigth);
	Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);
	return enemyCurrent.intersects(player);
}

public void destroySelf() {
	Game.entities.remove(this);
	Game.boss.remove(this);
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
				World.genparticle(230,(int) x, (int)y);
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
				Particle.pcolor = 2;
				World.genparticle(400,(int) x, (int)y);
				
				//Game.bull.remove(i);
				
				return;
			}
			
		}
	}

public void render(Graphics g) {
	
	if(!isDamage)
	g.drawImage(sprites[index], this.getX()-Camera.x , this.getY()-Camera.y, null);
	else
		g.drawImage(Entity.BOSS_FEED, this.getX()-Camera.x, this.getY()-Camera.y, null);
	//g.setColor(Color.blue);
	//g.fillRect(this.getX() + this.maskx - Camera.x, this.getY() + this.masky - Camera.y, this.mwidth, this.mheigth);
}



}
