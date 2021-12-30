package com.hoffnisgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.Camera;
import com.hoffnisgames.world.World;

public class Shoot2 extends Entity {
	
	private double dy;
	private double dx;
	private double spd = 2.55;
	private int life = 60;
	private int curlife = 0;
	private int frames = 0, maxFrames = 8, index = 0, maxIndex = 1;
	private BufferedImage[] sh;

	public Shoot2(int x, int y, int width, int heigth, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, heigth, sprite);
		
		this.dx= dx;
		this.dy= dy;
		sh = new BufferedImage[2];
		sh[0] = Game.spritesheet.getSprite(0, 32, 16, 16);
		sh[1] = Game.spritesheet.getSprite(16, 32, 16, 16);
	
	}
	
	public void tick() {
		if(World.isFreeDyn((int)(x+(dx*spd)), (int)(y+(dy*spd)), 16, 16)) {
		x+=dx*spd;
		y+=dy*spd;
		}else {
			
			Game.bull.remove(this);
			Particle.pcolor = 1;
			World.genparticle(100,(int) x, (int)y);
			return;
		}
		
		if(!World.isFreeDy((int)(x+(dx*spd)), (int)(y+(dy*spd)), 16, 16)) {
			Particle.pcolor = 1;
			World.genparticle(40,(int) x, (int)y);
			return;
		}
		
			
		curlife++;
		
		if(curlife == life) {
			Game.bull.remove(this);
			return;
		}
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
			
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sh[index], this.getX()-Camera.x , this.getY()-Camera.y, null);
		
	}
}
