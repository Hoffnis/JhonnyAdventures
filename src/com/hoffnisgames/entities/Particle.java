package com.hoffnisgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.hoffnisgames.main.Game;
import com.hoffnisgames.world.Camera;



public class Particle extends Entity {
	
	public int lifeT = 22;
	public int curT = 0;
	
	public double spd = 0.25;
	public double dx = 0, dy =0;
	public static int pcolor = 1;
	
	public Particle(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
		// TODO Auto-generated constructor stub
	
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();		
	}
	
	public void tick() {
		x+= dx*spd;
		y+= dy*spd;
		
		curT++;
		
		if(curT >= 6)
			spd = 0.2;
		else if(curT >= 13)
			spd = 0.1;
		else if(curT >= 19)
			spd = 0.01;
		else
			spd = 0.4;
		
		if(lifeT <= curT) {
			Game.entities.remove(this);
		}
	}
	
	public void render(Graphics g) {
		if(pcolor == 1) {
		g.setColor(Color.gray);}
		else if(pcolor == 2) {
			g.setColor(Color.RED);
		}
		g.fillRect(this.getX() -  Camera.x, this.getY() - Camera.y, width, heigth);
	}

}
