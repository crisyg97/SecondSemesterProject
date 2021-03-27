package Juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Board;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Board timer;
	// Variables y métodos propios de cada grupo
	// ...
	private double altoRec = 40;
	private double anchoRec = 260;
	private Plataformas[] plat;
	private Plataformas llegada;
	private Lemmings[] variosLem;
	private Drones drone;
	private Seleccionar s;
	private int caidos = 0;
	private int salvados = 0;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lemmings - Grupo Apellido1 - Apellido2 -Apellido3 - V0.01", 800, 600);
		
		this.plat = new Plataformas[3];
		this.plat[0] = new Plataformas(0,400,anchoRec,altoRec);
		this.plat[1] = new Plataformas(0,260,anchoRec,altoRec);
		this.plat[2] = new Plataformas(0,100,anchoRec,altoRec);
		this.llegada = new Plataformas(800,450,anchoRec,altoRec);
		
		this.drone = new Drones();
		variosLem = new Lemmings[10];
		for(int i = 0; i < variosLem.length;i++){
			variosLem[i] = new Lemmings(-50,360);
			variosLem[i].lemmingsAzar();
		}
		this.s = new Seleccionar(drone);
		drone.posicionAzarY(); // posiciona los drones al azar
		this.entorno.iniciar();
	}
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{

		imagenNubesYCielo(entorno); // le pone imagen al fondo y nubes
		imagenPlataformas(entorno); // le pone imagen a las plataformas
		drone.imagenDrones(entorno); //imagen de las plataformas-drones
		s.MoverDrone(entorno, drone); // mueve el drone cuando se apreta la tecla ESPACIO
		variosLem[0].tiempoDeSalida(variosLem); // inicia la salida de los lemmings
		
		for(int y=0;y<variosLem.length;y++) //recorre el arreglo de lemmings
		{ 
			variosLem[y].CaidaAltaLem(entorno, drone); //verifica la altura de la caida del lemming
			
			for(int z=0;z<plat.length;z++){ //recorre el arreglo de las plataformas de entrada
				if(variosLem[y].colision(plat[z])){
					variosLem[y].moverDer(entorno,variosLem[y].getVelocidad());
				}else{
					if(variosLem[y].getX() == plat[z].getX() + (plat[z].getAncho()/2) )
						variosLem[y].moverAba(entorno,variosLem[y].getVelocidad());
			}
				
			for(int x=0;x<drone.drones.length-1;x++){ //recorre el arreglo de las plataformas-drones
				if(variosLem[y].colision(drone.drones[x])){
					variosLem[y].moverDer(entorno,variosLem[y].getVelocidad());	
				}else{
					if(variosLem[y].getX() == drone.drones[x].getX() + (drone.drones[x].getAncho()/2) )
						variosLem[y].moverAba(entorno,variosLem[y].getVelocidad());
				}
			}
			
			if(variosLem[y].colision(drone.drones[3])){
				variosLem[y].moverDer(entorno,variosLem[y].getVelocidad());
			}else{
				if(variosLem[y].getX() >= drone.drones[3].getX() + (drone.drones[3].getAncho()/2)  && drone.drones[3].getY() - drone.drones[3].getAlto()/2 <= variosLem[y].getY() + variosLem[y].getRadio()/2)
					if(!variosLem[y].colision(drone.drones[0]) && !variosLem[y].colision(drone.drones[1] )){
						if(!variosLem[y].colision(drone.drones[2]) && !variosLem[y].colision(llegada))
							variosLem[y].moverAba(entorno,variosLem[y].getVelocidad());
					}
			}
			
			if(variosLem[y].colision(llegada)) //verifica si toca la plataforma de llegada
				variosLem[y].moverDer(entorno,variosLem[y].getVelocidad());
			variosLem[y].condicionLem(); // verifica si esta salvado o esta muerto el lemming
			salvados = contarLemingsVivos(variosLem); 
			caidos = contarLemingsCaidos(variosLem);
			entorno.escribirTexto("Lemmings muertos: "+ caidos, 650, 20);
			entorno.escribirTexto("Lemmings salvados: "+ salvados, 650, 40);
			if(caidos >= 7) //condiciones para ganar o perder el juego
				{ 
				entorno.escribirTexto("!!HAS PERDIDO��", 360, 100);
			}else{
				if(salvados >=7)
				entorno.escribirTexto("!!HAS GANADO��", 360, 100);
			}
		}
	}
		s.moverPuntero(entorno, drone);// dibuja el puntero y mueve el puntero
	}

	static int contarLemingsVivos(Lemmings[] lems){
		int vivos = 0;
		for(int k = 0;k<lems.length;k++){
			if(lems[k].isSalvado())
				vivos++;
		}
		return vivos;
	}
	static int contarLemingsCaidos(Lemmings[] lems){
		int caidos = 0;
		for(int s=0;s<lems.length;s++){
			if(lems[s].isCaido())
				caidos++;
		}
		return caidos;
	}
	
	void imagenNubesYCielo(Entorno ent){
		Image cielo = Herramientas.cargarImagen("img/cielo.png");
		ent.dibujarImagen(cielo, 400, 300, 0);
		Image nube1 = Herramientas.cargarImagen("img/nube.png");
		ent.dibujarImagen(nube1, 450, 30,0, 0.1);
		ent.dibujarImagen(nube1,500,30,0,0.1);
		ent.dibujarImagen(nube1, 250, 30, 0, 0.1);
		ent.dibujarImagen(nube1, 750, 30, 0, 0.1);
		ent.dibujarImagen(nube1, 170, 30, 0, 0.1);
		ent.dibujarImagen(nube1, 10, 30, 0, 0.1);
		}
	void imagenPlataformas(Entorno ent){
		Image plataformas = Herramientas.cargarImagen("img/plataf.jpg");
		ent.dibujarImagen(plataformas, 7, 400, 0, 0.7);
		ent.dibujarImagen(plataformas, 792, 450, 0, 0.7);
		ent.dibujarImagen(plataformas, 7, 260, 0, 0.7);
		ent.dibujarImagen(plataformas, 7, 100, 0, 0.7);
		}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
