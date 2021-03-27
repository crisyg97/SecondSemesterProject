package Juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Seleccionar {
	private Lemmings puntero;
	private int indice = 0;
	
	Seleccionar(Drones drones){ // crea el circulo que va ser el puntero  
		double x = drones.drones[0].getX();
		double y = drones.drones[0].getY() + 40;
		puntero = new Lemmings(x,y);
	}
	
	void Selec(Drones drones, int x, Entorno ent){ // se le asigna puntero a uno de los drones
		puntero.setPosX(drones.drones[x].getX());
		puntero.setPosY(drones.drones[x].getY() + 40);
		puntero.setRadio(30);
		Image flecha = Herramientas.cargarImagen("img/flecha_roja.png"); //imagen de la flecha
		ent.dibujarImagen(flecha, puntero.getX(), puntero.getY(), 0, 0.3);	
	}
	Lemmings moverPuntero(Entorno e, Drones drones){ //para mover el puntero
		if(e.sePresiono(e.TECLA_DERECHA))
			indice = indice + 1;
			if(indice == 4)
				indice = 0;
			Selec(drones,indice,e);
		if(e.sePresiono(e.TECLA_IZQUIERDA))
			indice = indice -1;
			if(indice == -1)
				indice = 3;
			Selec(drones,indice,e);
		return this.puntero;
	}
	void MoverDrone(Entorno e,Drones drones){ // selecciona el drone para moverlo
		if(e.estaPresionada(e.TECLA_ESPACIO))
			drones.drones[indice].moverse(e);
	}
}
