package com.hoffnisgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.hoffnisgames.graficos.Spritesheet;
import com.hoffnisgames.main.Game;
import com.hoffnisgames.main.Sound;
import com.hoffnisgames.world.Camera;
import com.hoffnisgames.world.World;

public class Player extends Entity{

	public boolean rigth, left, up, down;
	public double speed = 0.9;
	public int rigth_dir = 0, left_dir = 1, up_dir= 2, down_dir = 3;
	public int dir = rigth_dir;
	public double life = 100, maxlife = 100;
	public int mx, my;
	public boolean jump = false;
	public int z = 0;
	public int jumpframes =50, jumpcur = 0;
	public boolean isJumping = false;
	
	private int frames = 0, maxFrames = 4, index = 0, maxIndex = 3;
	private boolean moved = false;
	private BufferedImage[] rigthPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] downPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage playerdamage;
	private boolean arma = false;
	public int ammo = 0;
	public int bomba = 0;
	public boolean isDamage = false;
	private int damageFrames = 0;
	public boolean sht = false;
	public boolean mousesht = false;
	public boolean mousesht2 = false;
	public static int score = 0;
	public static int highscore;
	
	public Player(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
		
		rigthPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		playerdamage = Game.spritesheet.getSprite(0, 16, 16, 16);
		
		for(int i = 0; i < 4; i++) {
		rigthPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 32, 16, 16);
		}
		for(int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 48, 16, 16);
			}
		for(int i = 0; i < 4; i++) {
			downPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 0, 16, 16);
			}
		for(int i = 0; i < 4; i++) {
			upPlayer[i] = Game.spritesheet.getSprite(32+(i*16), 16, 16, 16);
			}
	}
	
	public void tick() {
		
		
		
		if(jump) {
			
			if(isJumping == false) {
				jump = false;
				isJumping = true;
			}
		}
		if(isJumping== true) {
			if(jumpcur < jumpframes) {
				jumpcur++;
				z=jumpcur;
				if(jumpcur == jumpframes) {
					isJumping = false;
				}
			}
		}
			
		moved = false;
		
		if(rigth && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
			dir = rigth_dir;
			x+=speed;
			
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			dir = left_dir;
			x-=speed;
			
			}
		
		if(down && World.isFree(this.getX(),(int)(y+speed))) {
			moved = true;
			dir = down_dir;
			y+=speed;}
		
		else if(up && World.isFree(this.getX(),(int)(y-speed))) {
			moved = true;
			dir = up_dir;
		y-=speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		} else {
		
			index = 0;
			
		}
		
		this.checkCollisionLife();
		checkCollisionAmmo();
		checkCollisionGun();
		checkCollisionBomba();
		
		if(isDamage) {
			damageFrames ++;
			if(this.damageFrames == 7) {
				this.damageFrames = 0;
				isDamage = false;
			}
		}
		
		/*if(sht) {
			sht = false;
			if(arma && ammo > 0) {
				
				ammo--;
			int dx = 0;
			int px;
			int py = 5;
			if(dir == rigth_dir) {
				dx= 1;
				px = 3;
			}else {
				dx = -1;
				px= -3;
			}
			
			Shoot shoot = new Shoot(this.getX()+px,this.getY()+py,3,3,null, dx, 0);
			Game.bullets.add(shoot);
			}
		}*/
		if(mousesht) {
		mousesht = false;
		double angle = Math.atan2(my - (this.getY()+8 - Camera.y), mx - (this.getX()+8 -Camera.x));
		if(arma && ammo > 0) {
			
			ammo--;
			Sound.hurtEfferc.play();
		double dx = Math.cos(angle);
		double dy = Math.sin(angle);
		int px = 3, py = 5;
		
		/*if(dir == rigth_dir) {
			dx= 1;
			px = 3;
		}else {
			dx = -1;
			px= -3;
		}*/
		
		Shoot shoot = new Shoot(this.getX()+px,this.getY()+py,3,3,null, dx, dy);
		Game.bullets.add(shoot);
		}
	}
		
		
		if(mousesht2) {
			mousesht2 = false;
			double angle = Math.atan2(my - (this.getY()+8 - Camera.y), mx - (this.getX()+8 -Camera.x));
			if(arma && bomba > 0) {
				
				bomba--;
				Sound.fire.play();
			double dx = Math.cos(angle);
			double dy = Math.sin(angle);
			int px = 3, py = 5;
			
			/*if(dir == rigth_dir) {
				dx= 1;
				px = 3;
			}else {
				dx = -1;
				px= -3;
			}*/
			
			Shoot2 shoot2 = new Shoot2(this.getX()+px,this.getY()+py-2,16,16,null, dx, dy);
			Game.bull.add(shoot2);
			}
		}
		
		if(life <= 0) {
			Game.saveGame = true;
			if(score > highscore) {
				highscore = score;
			}
			Game.gameState = "gameover";
			
		}
			
		
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.WIDTH*16 - Game.WIDTH) ;
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.HEIGTH*16 - Game.HEIGHT) ;
			
	}
	
	
	public void checkCollisionAmmo() {
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Bullet) {
				if(Entity.isCollinding(this, atual)) {
					ammo+=15;
					score+=5;
					Game.entities.remove(atual);
				}
			}
		}
		
	}
	
public void checkCollisionBomba() {
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Bullet2) {
				if(Entity.isCollinding(this, atual)) {
					bomba+=3;
					score+=55;
					Game.entities.remove(atual);
				}
			}
		}
		
	}
	
	public void checkCollisionLife(){
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Lifepack) {
				if(Entity.isCollinding(this, atual)) {
					life+=10;
					score+=25;
					if(life > 100)
						life= 100;
					Game.entities.remove(atual);
				}
			}
		}
		
	}
	
	public void checkCollisionGun(){
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Weapon) {
				if(Entity.isCollinding(this, atual)) {
					arma = true;
					Game.entities.remove(atual);
				}
			}
		}
		
	}
	
	
	public void render(Graphics g) {
		
		if(!isDamage) {
		if(dir == rigth_dir) {
		g.drawImage(rigthPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		if(arma) {
			g.drawImage(Entity.WEAPON_EN, this.getX()+3 - Camera.x, this.getY()-1 - Camera.y, null);
		}
		}
		else if(dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if(arma) {
				g.drawImage(Entity.WEAPON_EN, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		else if(dir == down_dir) {
			g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);	
			if(arma) {
				g.drawImage(Entity.WEAPON_EN, this.getX()+6 - Camera.x, this.getY()-1 - Camera.y, null);
			}
		}
		else if(dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);	
			
		}
		}else {g.drawImage(playerdamage, this.getX()- Camera.x, this.getY() - Camera.y, null);
			
		}
			
	}

}
