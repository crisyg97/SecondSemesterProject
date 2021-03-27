package Juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Lemmings {
	private double posX;
	private double posY;
	private double radio;
	private boolean caido;
	private boolean salvado;
	private double velocidad;
	double dist = 0; //sirve para contar la distancia de caida del lemming
	
	Lemmings(double x,double y){// inicializa el lemming
		this.posX = x;
		this.posY = y;
		this.radio = 40;
		this.caido = false;
		this.salvado = false;
		this.velocidad = 0;
	}
	
	void dibujarLemming(Entorno ent){//dibuja el circulo que sera el lemming
		Color c = new Color(0,255,0);
		ent.dibujarCirculo(this.posX, this.posY, this.radio,c);
	}
	
	void ImagenLemming(Entorno ent){ //  le pone imagen al lemming
		Image lem = Herramientas.cargarImagen("img/lemming_caminando.png");
		ent.dibujarImagen(lem, this.posX, this.posY, 0, 1);
	}
	
	void lemmingsAzar(){ //  elige al azar la plataforma de entrada por la cual va aparece el lemming
		double plat0Y = 60;
		double plat1Y = 220;
		double plat2Y = 360;
		Random gen = new Random();
		int azar = gen.nextInt(3);
		if(azar == 0){
			this.posY = plat0Y;}
		if(azar == 1){
			this.posY = plat1Y;}
		if(azar == 2){
			this.posY = plat2Y;}
	}

	public boolean colision(Plataformas plat){ //colision con las plataformas
		double mitadAncho = plat.getAncho()/2;
		double mitadRadio = this.radio/2;
		double mitadAlto = plat.getAlto()/2;
		if(this.posX >= (plat.getX() - mitadAncho) && this.posX < (plat.getX() + mitadAncho) && (this.posY + mitadRadio) == (plat.getY() - mitadAlto) && (this.posY + mitadRadio) == (plat.getY() - mitadAlto))
			return true;
		else
			return false;
	}
	
	void tiempoDeSalida(Lemmings[] varioslemmings){ //tiempo en el que salen los lemmings
		int n = varioslemmings.length;
		Lemmings primero = varioslemmings[0];
		primero.setVelocidad(1);
		for(int i=1; i<n;i++){
			if(primero.getX() >= 130){
				primero = varioslemmings[i];
				primero.setVelocidad(1);
			}
		}
	}
	
	void CaidaAltaLem(Entorno ent, Drones drone){
		boolean entrad = true;
		if(entrad)
			if(this.getX() >= 130 && this.getX() <= 540){
				if(this.getX() >= 130 && this.getX() <= drone.drones[0].getX() + drone.drones[0].getAncho()/2 && !this.colision(drone.drones[0]))
					dist += 0.5; //suma a la varible dist 0.5 si se encuentra cayendo el lemming
				
			}
		if(dist > 15) // cuando sea 15 significa que es muy alta la caida
			this.moverAba(ent, 1);
			entrad = false;
			this.setCaido(true);
	}
	
	void condicionLem(){ 
		if(this.posX >= 750 && !this.isSalvado())
			this.setSalvado(true);
			this.setCaido(false);
		if(this.posY > 490 && !this.isCaido() && this.posX < 800)
				this.setCaido(true);
	}
	void moverDer(Entorno e,double vel){ // mueve a la derecha y dibuja el lemming
		this.posX += vel;
		Image lem = Herramientas.cargarImagen("img/lemming_caminando.png");
		e.dibujarImagen(lem, this.posX, this.posY, 0, 1);
	}
	
	void moverAba(Entorno e,double vel){ // mueve hacia abajo y dibuja el lemming
		this.posY += vel;
		Image lem = Herramientas.cargarImagen("lemming_cayendo1.png");
		e.dibujarImagen(lem, this.posX, this.posY, 0, 1);
	}
	
	public double getVelocidad() {
		return velocidad;}
	public void setVelocidad(double d) {
		this.velocidad = d;}
	void moverIzq(){
		this.posX -=1;}
	public boolean isSalvado() {
		return salvado;}
	public void setSalvado(boolean salvado) {
		this.salvado = salvado;}
	public boolean isCaido() {
		return caido;}
	public void setCaido(boolean caido) {
		this.caido = caido;}
	public double getRadio() {
		return radio;}
	public void setRadio(double radio) {
		this.radio = radio;}
	public void setPosX(double posX) {
		this.posX = posX;}
	public void setPosY(double posY) {
		this.posY = posY;}
	public double getX(){
		return posX;}
	public double getY(){
		return posY;}
}
