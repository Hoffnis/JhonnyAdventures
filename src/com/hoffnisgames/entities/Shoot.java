package com.hoffnisgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.Camera;
import com.hoffnisgames.world.World;

public class Shoot extends Entity {
	
	private double dy;
	private double dx;
	private double spd = 4;
	private int life = 60;
	private int curlife = 0;
	

	public Shoot(int x, int y, int width, int heigth, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, heigth, sprite);
		
		this.dx= dx;
		this.dy= dy;
	
	}
	
	public void tick() {
		if(World.isFreeDy((int)(x+(dx*spd)), (int)(y+(dy*spd)), 3, 3)) {
		x+=dx*spd;
		y+=dy*spd;
		}else {
			
			Particle.pcolor = 1;
			Game.bullets.remove(this);
			World.genparticle(100,(int) x, (int)y);
			return;
		}
		if(!World.isFreeDyn((int)(x+(dx*spd)), (int)(y+(dy*spd)), 3, 3)) {
			Particle.pcolor = 1;
			Game.bullets.remove(this);
			World.genparticle(100,(int) x, (int)y);
			return;
		}
			
		curlife++;
		if(curlife == life) {
			Game.bullets.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, 3, 3);
		
	}
}
