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

public class Boss2 extends Entity {
	
	private double speed = 0.4;
	private BufferedImage[] sprites;
	private boolean isDamage = false;
	private int frames = 0, maxFrames = 22, index = 0, maxIndex = 1;
	private int life = 110;
	private int damageframe = 10;
	private int damagecur = 0; 
	private int masky = 0;
	private int maskx = 0;
	private int mwidth = 16;
	private int mheigth = 16;
	public int spawn = 0;
	public static boolean spawnede = false;
	private int flife = 0;
	private int mflife = 15;

	public Boss2(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, null);
		
		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(8*16, 16*9, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(9*16, 16*9, 16, 16);
		
	}
	
public void tick() {
	
	spawn++;
	
	if(spawn >= 100) {
		if(Game.rand.nextInt(100) < 38) {
				spawnede = true;	
		} else {
			spawn = 0;
		}
		
	}
	
	
	
		
	
	 if (Boss2.spawnede == true) {
		Enemy5 ae = new Enemy5(this.getX(), this.getY(), 16, 16, Entity.ENEMY5_EN);
		Game.entities.add(ae);
		Game.mi.add(ae);
		spawn = 0;
		spawnede = false;
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
		
			
			if(Game.rand.nextInt(100) < 40) {
				Game.player.life -= Game.rand.nextInt(25);
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
			Game.player.score = Game.player.score +350;
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
	Game.bos.remove(this);
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
				World.genparticle(205,(int) x, (int)y);
				return;
			}
			
		}
	}

public void collidingBomb() {
	for(int i = 0; i < Game.bull.size(); i++) {
		Entity e = Game.bull.get(i);	
			if(Entity.isCollinding(this, e)) {
				isDamage = true;
				Game.player.score = Game.player.score +3;
				life= life-5;
				//Game.bull.remove(i);
				Particle.pcolor = 2;
				World.genparticle(310,(int) x, (int)y);
				return;
			}
			
		}
	}

public void render(Graphics g) {
	
	if(!isDamage)
	g.drawImage(sprites[index], this.getX()-Camera.x , this.getY()-Camera.y, null);
	else
		g.drawImage(Entity.BOSS2_FEED, this.getX()-Camera.x, this.getY()-Camera.y, null);
	//g.setColor(Color.blue);
	//g.fillRect(this.getX() + this.maskx - Camera.x, this.getY() + this.masky - Camera.y, this.mwidth, this.mheigth);
}



}
