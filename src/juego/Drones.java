package Juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;

public class Drones {
	Plataformas[] drones; //540 es el espacio disponible para poner los drones
	
	Drones(){
		Plataformas[] variosDron = new Plataformas[4];
		variosDron[0] = new Plataformas(220,430,180,30); 
		variosDron[1] = new Plataformas(390,430,160,30);  
		variosDron[2] = new Plataformas(570,430,200,30);  
		variosDron[3] = new Plataformas(390,200,160,30); /// drone auxiliar
		this.drones = variosDron;
	}
	
	void imagenDrones(Entorno ent){
		Color c = new Color(0,0,255);
		ent.dibujarRectangulo(this.drones[3].getX(), this.drones[3].getY(), this.drones[3].getAncho(), this.drones[3].getAlto(), 0, c);
		ent.dibujarRectangulo(this.drones[2].getX(), this.drones[2].getY(), this.drones[2].getAncho(), this.drones[2].getAlto(), 0, c);
		ent.dibujarRectangulo(this.drones[1].getX(), this.drones[1].getY(), this.drones[1].getAncho(), this.drones[1].getAlto(), 0, c);
		ent.dibujarRectangulo(this.drones[0].getX(), this.drones[0].getY(), this.drones[0].getAncho(), this.drones[0].getAlto(), 0, c);
		Image drones = Herramientas.cargarImagen("platafDrone.jpg");
		ent.dibujarImagen(drones, this.drones[0].getX(),this.drones[0].getY() , 0, 0.51);
		ent.dibujarImagen(drones, this.drones[1].getX(), this.drones[1].getY(), 0, 0.45);
		ent.dibujarImagen(drones, this.drones[2].getX(),this.drones[2].getY(), 0, 0.54);
		ent.dibujarImagen(drones, this.drones[3].getX(), this.drones[3].getY(), 0, 0.43);
	}
	//rango en donde pueden aparacer los drones aleatoreamente 
	// y= 150 a y y=450    x=260 a x= 540
	
	void ColisionConDrones(Lemmings lem,Entorno ent){
		for(int k=0; k<this.drones.length;k++){
			if(lem.getX() >= this.drones[k].getX() - (this.drones[k].getAncho()/2) && lem.getX() <= this.drones[k].getX() + (this.drones[k].getAncho()/2))
				if(lem.colision(drones[k]))
					lem.moverDer(ent, lem.getVelocidad());
			else
				lem.moverAba(ent, lem.getVelocidad());
		}
	}
	
	void posicionAzarY(){ //pone al azar las plataformas-drones utilizando el eje Y 
		int i = 0;
		boolean entrad = true;
		while(entrad){
			Random azar = new Random();
			int num = azar.nextInt(450);
			if(num >= 150)
				drones[i].setY(num);
				if(i == 3)
					entrad = false;
				i++;
			}
		}

}
