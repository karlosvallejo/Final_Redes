package Logica;


import processing.core.PApplet;

public class MainApp extends PApplet {
Logica log;
	static public void main(String[] args) {
		  PApplet.main(MainApp.class.getName());
		}
	
	public void settings(){
		//size(1200,700,P2D);
		size(1200,700);
	}
	
	public void setup(){
		
		log= new Logica(this);
		ellipseMode(RADIUS);
		rectMode(CENTER);
		textAlign(CENTER);
		
	
		
	}
	
	public void draw(){
		background(255);
		log.pintar();
	}
	
	
	

}
