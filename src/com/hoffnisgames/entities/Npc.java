package com.hoffnisgames.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.hoffnisgames.main.Game;

public class Npc extends Entity {
	
	public String[] frases = new String[6];
	public String[] frases1 = new String[4];
	public String[] frases2 = new String[4];
	public String[] frases3 = new String[5];
	public String[] frases4 = new String[9];
	public String[] frases5 = new String[11];

	public boolean show = false;
	public boolean show1 = false;
	public boolean show2 = false;
	public boolean show3 = false;
	public boolean show4 = false;
	public boolean show5 = false;
	public boolean sho = false;
	public boolean sho1 = false;
	public boolean sho2 = false;
	public boolean sho3 = false;
	public boolean sho4 = false;
	public boolean sho5 = false;
	public int tim;
	public int maxtim;
	public int curx = 0;
	public int curx1 = 0;
	public int curx2 = 0;
	public int curx3 = 0;
	public int curx4 = 0;
	public int curx5 = 0;
	public int fraseindex = 0;
	public int fra = 0;
	public int fra1 = 0;
	public int fra2 = 0;
	public int fra3 = 0;
	public int fra4 = 0;
	public int fra5 = 0;
	public int fraseindex1 = 0;
	public int fraseindex2 = 0;
	public int fraseindex3 = 0;
	public int fraseindex4 = 0;
	public int fraseindex5 = 0;


	public Npc(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
		// TODO Auto-generated constructor stub
		
		frases[0] = "Olá, estou aqui pra te guiar";
		frases[1] = "preste atenção! não estou com paciência!";
		frases[2] = "SEMPRE colete as a arma e  munição";
		frases[3] = "Acima esta a Arma e abaixo a munição";
		frases[4] = "Use as setas para se mover ou W A S D";
		frases[5] = "Use as setas para se mover ou W A S D";
		
		frases1[0] = "CUIDADOOOO! Inimigo a frente!";
		frases1[1] = "Tendo munição, use clique ESQUERDO e.... ";
		frases1[2] = "AAAAATIREEEEEEE!";
		frases1[3] = "Tendo munição, clique direito atira!";
		
		frases2[0] = "MUITO BEM! Pensei que era mais Otário";
		frases2[1] = "Mas né! Vi que se feriu...";
		frases2[2] = "Em frente há pirulas para se curar";
		frases2[3] = "a frente há pirulas para se curar";
		
		frases3[0] = "Use as munições com sabedoria";
		frases3[1] = "Hã? como eu apareço tão rápido?";
		frases3[2] = "...................................................................";
		frases3[3] = "NÃO É DA SUA CONTAAAA!";
		frases3[4] = "NÃO É DA SUA CONTAAAA!";
		
		frases4[0] = "Ao final da cada fase, você irá...";
		frases4[1] = "Recuperar a vida e perderá a sua arma";
		frases4[2] = "Então seja sabio, cada fase é unica!";
		frases4[3] = "QUEEEEEEE?....";
		frases4[4] = "...Não tem sentido....";
		frases4[5] = "seguir ordens de uma estranha!?";
		frases4[6] = "CÊ TA QUERENDO UNS TAPA NE GAROTO!?";
		frases4[7] = "AGORA VÁ EM FRENTE SEU BOSTA!!!";
		frases4[8] = "AGORA VÁ EM FRENTE SEU BOSTA!!!";
		
		
		frases5[0] = "Esta arma especial aqui em baixo...";
		frases5[1] = "...é capaz de ultrapassar paredes";
		frases5[2] = "Use o clique DIREITO para usa-la";
		frases5[3] = "Ela é mais forte e rara, Use Sabiamente";
		frases5[4] = "Você passará de fase...";
		frases5[5] = "...sempre que derrotar tudo";
		frases5[6] = "Ao atirar irá começar seu desafio de fato";
		frases5[7] = "TE VEJO MAIS TARDE NOOB!";
		frases5[8] = "Ops, quer dizer, té mais Jhonny";
		frases5[9] = "BOA SORTE, VOCE VAI PRECISAR KKKKK";
		frases5[10] = "BOA SORTE, VOCE VAI PRECISAR KKKKK";
		
	}
	
	public void tick() {
		

	
		if(show && !sho) {
			if(curx < frases[fraseindex].length()) {
				curx++;
			}else {
fra ++;
				if(fraseindex < frases.length && fra == 100) {
					fraseindex++;
					curx = 0;
					fra = 0;
				}
			}
		}else {
			fraseindex = 0;
			curx = 0;
			fra = 0;
		}
		
		
		
		
		if(show1 && !sho1) {
			if(curx1 < frases1[fraseindex1].length()) {
				curx1++;
			}else {
fra1 ++;
				if(fraseindex1 < frases1.length && fra1 == 100) {
					fraseindex1++;
					curx1 = 0;
					fra1 = 0;
				}
			}
		}else {
			fraseindex1 = 0;
			curx1 = 0;
			fra1 = 0;
		}
		
		
		
		
		
		if(show2 && !sho2) {
			if(curx2 < frases2[fraseindex2].length()) {
				curx2++;
			}else {
fra2 ++;
				if(fraseindex2 < frases2.length && fra2 == 100) {
					fraseindex2++;
					curx2 = 0;
					fra2 = 0;
				}
			}
		}else {
			fraseindex2 = 0;
			curx2 = 0;
			fra2 = 0;
		}
		
		
		
		
		
		if(show3 && !sho3) {
			if(curx3 < frases3[fraseindex3].length()) {
				curx3++;
			}else {
fra3 ++;
				if(fraseindex3 < frases3.length && fra3 == 100) {
					fraseindex3++;
					curx3 = 0;
					fra3 = 0;
				}
			}
		}else {
			fraseindex3 = 0;
			curx3 = 0;
			fra3 = 0;
		}
		
		
		
		
		if(show4 && !sho4) {
			if(curx4 < frases4[fraseindex4].length()) {
				curx4++;
			}else {
fra4 ++;
				if(fraseindex4 < frases4.length && fra4 == 100) {
					fraseindex4++;
					curx4 = 0;
					fra4 = 0;
				}
			}
		}else {
			fraseindex4 = 0;
			curx4 = 0;
			fra4 = 0;
		}
		
		
		
		
		if(show5 && !sho5) {
			if(curx5 < frases5[fraseindex5].length()) {
				curx5++;
			}else {
fra5 ++;
				if(fraseindex5 < frases5.length && fra5 == 100) {
					fraseindex5++;
					curx5 = 0;
					fra5 = 0;
				}
			}
		}else {
			fraseindex5 = 0;
			curx5 = 0;
			fra5 = 0;
		}
		
		
	}
	
	public void render(Graphics g) {
		super.render(g);
		if(Game.player.getX() >= 16*2 && Game.player.getY() <= 16*5 && Game.player.getX() <= 16*3 +5 && Game.player.getY() >= 16*4 && Game.CUR_LEVEL == 0) {
			
			show = true;
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.WHITE);
			g.drawString(frases[fraseindex].substring(0, curx), (int)x -25, (int)y);
			if(fraseindex > 4) {
				fraseindex = 4;
				sho = true;
			}
			
		
			
		}else if(Game.player.getX() >= 16*16 && Game.player.getY() <= 16*7 && Game.player.getX() <= 16*17 +5 && Game.player.getY() >= 16*6 && Game.CUR_LEVEL == 0) {
			
			
			
			show1 = true;
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.WHITE);
			g.drawString(frases1[fraseindex1].substring(0, curx1), (int)x -25, (int)y);
			if(fraseindex1 > 2 ) {
				fraseindex1 = 2;
				sho1 = true;
			}
		}
		else if(Game.player.getX() >= 27*16 && Game.player.getY() <= 16*2 && Game.player.getX() <= 16*29 +5 && Game.player.getY() >= 16*2 && Game.CUR_LEVEL == 0) {
			
			
			show2 = true;
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.WHITE);
			g.drawString(frases2[fraseindex2].substring(0, curx2), (int)x -25, (int)y);
			if(fraseindex2 > 2 ) {
				fraseindex2 = 2;
				sho2 = true;
			}
			
			
		}
		else if(Game.player.getX() >= 16*42 && Game.player.getY() <= 16*7 && Game.player.getX() <= 16*43 +5 && Game.player.getY() >= 16*6 && Game.CUR_LEVEL == 0) {
			
			
			show3 = true;
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.WHITE);
			g.drawString(frases3[fraseindex3].substring(0, curx3), (int)x -25, (int)y);
			if(fraseindex3 > 3 ) {
				fraseindex3 = 3;
				sho3 = true;
			}
			
			
			
		}
		else if(Game.player.getX() >= 16*58 && Game.player.getY() <= 16*3 && Game.player.getX() <= 16*60 +5 && Game.player.getY() >= 16*2 && Game.CUR_LEVEL == 0) {
			
			
			show4 = true;
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.WHITE);
			g.drawString(frases4[fraseindex4].substring(0, curx4), (int)x -25, (int)y);
			if(fraseindex4 > 7 ) {
				fraseindex4 = 7;
				sho4 = true;
			}
			
			
			
		}
		else if(Game.player.getX() >= 72*16 && Game.player.getY() <= 16*7 && Game.player.getX() <= 73*17 +5 && Game.player.getY() >= 16*5 && Game.CUR_LEVEL == 0) {
			
			
			
			show5 = true;
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.WHITE);
			g.drawString(frases5[fraseindex5].substring(0, curx5), (int)x -25, (int)y);
			if(fraseindex5 > 9 ) {
				fraseindex5 = 9;
				sho5 = true;
			}
			
			
			
			
		}else {
			show = false;
			curx = 0;
			sho = false;
			show1 = false;
			curx1 = 0;
			sho1 = false;
			show2 = false;
			curx2 = 0;
			sho2 = false;
			show3 = false;
			curx3 = 0;
			sho3 = false;
			show4 = false;
			curx4 = 0;
			sho4 = false;
			show5 = false;
			curx5 = 0;
			sho5 = false;
		}
	}

}
