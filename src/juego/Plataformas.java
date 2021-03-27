package Juego;

import java.awt.Color;

import entorno.Entorno;

public class Plataformas {
	private double ancho;
	private double alto;
	private double x;
	private double y;

	Plataformas(double x2, double y2, double anch, double alt){
		this.alto = alt;
		this.ancho = anch;
		this.x = x2;
		this.y = y2;
	}
	void dibujarPlataforma(Entorno e, double x, double y, double anch, double alt){
		Color c = new Color(166,94,046);
		e.dibujarRectangulo(x, y, anch, alt, 0, c);
	}
	
	void moverse(Entorno ent){
		if(ent.estaPresionada(ent.TECLA_ABAJO))
			this.y += 3;
		else 
			if(ent.estaPresionada(ent.TECLA_ARRIBA))
				this.y -= 3;
	}
	
	
	public double getAncho() {
		return ancho;}
	public double getAlto() {
		return alto;}
	public double getX() {
		return x;}
	public double getY() {
		return y;}
	public void setX(double x) {
		this.x = x;}
	public void setY(double y) {
		this.y = y;}
}
