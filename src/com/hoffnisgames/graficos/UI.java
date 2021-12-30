package com.hoffnisgames.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.hoffnisgames.entities.Player;
import com.hoffnisgames.main.Game;

public class UI {

	
	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect(5, 3, 50, 7);
		
		g.setColor(Color.green);
		g.fillRect(5, 3, (int)((Game.player.life/Game.player.maxlife)*50), 7);
		
		
	}
}
