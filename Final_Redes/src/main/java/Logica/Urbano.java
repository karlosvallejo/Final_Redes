package Logica;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import processing.core.PVector;

public class Urbano implements Runnable{
	AudioPlayer song;
	short centecimas=0;
	BeatDetect beat;
	Logica mundo;
	Minim minim;
	float eRadius;
	PVector posicion;
	PVector velocidad;
	PVector aceleracion;
	//tempo provicional
	short segundosTempo=10;
	ClassLoader classLoader;
	boolean puedeTocar=false;
	public Urbano(Logica mundo) {
		this.mundo=mundo;
		minim= new Minim(this.mundo.app);
		
		classLoader = getClass().getClassLoader();
		
		song = minim.loadFile(classLoader.getResource("sounds/Urbano/urbanoSOL.mp3").getPath(), 2048);
		

		
		posicion= new PVector(670,300);
		velocidad= new PVector(0,0);
		aceleracion= new PVector(0,0);
		
		
		beat= new BeatDetect();
		new Thread(this).start();
		
	}
	
	public void cargarSonido(String filename){
		song = minim.loadFile(classLoader.getResource("sounds/Urbano/" + filename).getPath(), 2048);
	}
	
	public boolean sonarNota(){
//	System.out.println(centecimas);
	//	System.out.println(song.isPlaying());
		if(!song.isPlaying() && puedeTocar){
			song.cue(0); 
			song.play();
			
		}
		
		
		if(song.isPlaying() && centecimas==segundosTempo){
			puedeTocar=false;
			song.pause(); 
			song.rewind(); 
			song.pause(); 
			centecimas=0;
		
			return true;
		}
		return false;
	}
	
	
	
	public void iniciarPlay(){
		song.play();
	}
	
	public void pintar(){
		beat.detect(song.mix);
		  float a = PApplet.map(eRadius, 20, 80, 60, 255);
		  mundo.app.fill(60, 255, 0, a);
		  if ( beat.isOnset() ) eRadius = 80;
		  mundo.app.ellipse(1000, 600, eRadius, eRadius);
		  eRadius *= 0.95;
		  if ( eRadius < 20 ) eRadius = 20;
	}

	public void run() {
		try{
			while(true){
				if(puedeTocar){
				centecimas++;
				}
				
				Thread.sleep(100);
			}
			
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	


}
