package Bodian;

import java.util.LinkedList;

import Thomas.Circuit;

public class Train {
	private int idtrain;
	private int taille;
	private int retard;
	private int idpos;
	public Train(int idtrain, int taille, int retard, int idpos) {
		this.idtrain = idtrain;
		this.taille = taille;
		this.retard = retard;
		this.idpos = idpos;
	}
	public Train() {
		this.idtrain = 0;
		this.taille = 0;
		this.retard = 0;
		this.idpos = 0;
			}
	public int getIdtrain() {
		return idtrain;
	}
	public void setIdtrain(int idtrain) {
		this.idtrain = idtrain;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public int getRetard() {
		return taille;
	}
	public void setRetard(int retard) {
		this.retard = retard;
	}
	public int getIdpos() {
		return idpos;
	}
	public void setIdpos(int idpos) {
		this.idpos = idpos;
	}
	 
	public void deplacement(Circuit circ) {
		idpos=idpos+1;
		if(idpos>=circ.getposcircuit().size()) {
			idpos=0;
			
			
			}
					
			
			
		}
	
	
	public String toString() {
		return "ID train:" + idtrain +", Taille de train: "+taille + "Retard de train: "+retard;
	}
}
