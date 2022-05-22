package Thomas;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import Guoyu.Aguillage;
import Guoyu.Gare;
import Lisa.JFrameJeu;

public class Jeu extends Thread
{
		
	private LinkedList<Circuit> listeCircuit;
	private LinkedList<TrainBis> listeTrain;
	private LinkedList<Aguillage> listeAiguillage;
	private LinkedList<Gare> listeGare;
	private boolean sensHorizontal;
	private SNCF sncf;
	private Hashtable<Position,Integer> google;
	private JFrameJeu mjf;
	private int NombreCircuitTotal;
	
	//Constructeur lkj
	
	public Jeu(SNCF sncfParam , JFrameJeu mjf) 
	{
		sncf = sncfParam;
		
		listeCircuit = sncf.getListeCircuit();
		listeTrain = sncf.getListeTrain();
		listeAiguillage = sncf.getListeAiguillage();
		listeGare = sncf.getListeGare();
		google = sncf.getGoogle();
		
		NombreCircuitTotal = listeCircuit.size();
		
		this.mjf=mjf;
		
	}
	
	public void run() 
	{	
		while (true) 
		{	
			try
			{
				Thread.sleep(1000);
				//deplacement();
				mjf.revalidate();
				mjf.repaint();
				
			} catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//Déplacement
	
	public void deplacement ()
	{	
			//Parcours de la liste des trains 
			for(int train=0; train < listeTrain.size();train++)
			{
				TrainBis trainActuelle = listeTrain.get(train);

				if(trainActuelle.getRetard() == 0)
				{
					//Parcours de la liste des wagons
					for(int wagon=0; wagon < trainActuelle.getListWagon().size(); wagon++)
					{
						Wagon wagonActuelle = trainActuelle.getListWagon().get(wagon);
						
						//Si wagon n'est pas sur un aiguillage
						if(wagonActuelle.getIdCircuitCorrespondant() != -1)
						{
							Circuit circuitCorrespondant = listeCircuit.get(wagonActuelle.getIdCircuitCorrespondant());
							
							int indexPosition = circuitCorrespondant.getposcircuit().indexOf(wagonActuelle.getPosWagon());
							
							//Si suite dans la liste
							if(circuitCorrespondant.getposcircuit().get(indexPosition+1) != null)
							{
								Position positionApres = circuitCorrespondant.getposcircuit().get(indexPosition+1);
								Position positionActuelle = circuitCorrespondant.getposcircuit().get(indexPosition);
								
								if(google.get(positionApres) == circuitCorrespondant.getidcircuit())
								{
									//Déplacement Wagon sur le circuit
									wagonActuelle.setPosWagon(positionApres);
									wagonActuelle.setPosWagonAvant(positionActuelle);
								}
								else
								{
									//Aiguillage
									provenance(positionActuelle,positionApres);
									Aguillage aiguillageCorrespondant = new Aguillage();
									
									//AiguillageCorrespondant
									for(int aiguillage=0; aiguillage < listeAiguillage.size();aiguillage++)
									{
										if(listeAiguillage.get(aiguillage).getPositionAiguillage().equals(positionApres))
										{
											aiguillageCorrespondant = listeAiguillage.get(aiguillage);
													
										}
									}
																	
									//Orientation
									if(sensHorizontal && aiguillageCorrespondant.getDirectionHorizontale() == true) 
									{
										wagonActuelle.setPosWagon(positionApres);
										wagonActuelle.setPosWagonAvant(positionActuelle);
										wagonActuelle.setIdCircuitCorrespondant(-1);
									}
									else if(!sensHorizontal && aiguillageCorrespondant.getDirectionVertical() == true)
									{ 
										wagonActuelle.setPosWagon(positionApres);
										wagonActuelle.setPosWagonAvant(positionActuelle);
										wagonActuelle.setIdCircuitCorrespondant(-1);
									}
									else { System.out.println("GAME OVER"); }
									
								}
							}
												
						}	
						//FIN//Si wagon n'est pas sur un aiguillage
						else
						{
							Aguillage aiguillageCorrespondant = new Aguillage();
							Position positionWagon = wagonActuelle.getPosWagon();
							Position positionWagonAvant = wagonActuelle.getPosWagonAvant();
							int circuitCorrespondant = -1;
							
							int posX = positionWagon.getPosX();
							int posY = positionWagon.getPosY();
							
							int posAvantX = positionWagonAvant.getPosX();
							int posAvantY = positionWagonAvant.getPosY();		
							
							//AiguillageCorrespondant
							for(int aiguillage=0; aiguillage < listeAiguillage.size();aiguillage++)
							{
								if(listeAiguillage.get(aiguillage).getPositionAiguillage().equals(positionWagon))
								{
									aiguillageCorrespondant = listeAiguillage.get(aiguillage);
											
								}
							}
							
							Position recherchePos = null;
							
							if(aiguillageCorrespondant.getDirectionHorizontale() == true)
							{									
								//Droite
								if(posX-posAvantX > 0)
								{
									recherchePos = new Position(posX+1,posY);
									wagonActuelle.setPosWagon(recherchePos);
									wagonActuelle.setPosWagonAvant(positionWagon);
									wagonActuelle.setIdCircuitCorrespondant(google.get(recherchePos));
								}
								//Gauche
								else
								{
									recherchePos = new Position(posX-1,posY);
									wagonActuelle.setPosWagon(recherchePos);
									wagonActuelle.setPosWagonAvant(positionWagon);
									wagonActuelle.setIdCircuitCorrespondant(google.get(recherchePos));
								}
							}
							else
							{
								//Bas
								if(posY-posAvantY > 0)
								{
									recherchePos = new Position(posX,posY+1);
									wagonActuelle.setPosWagon(recherchePos);
									wagonActuelle.setPosWagonAvant(positionWagon);
									wagonActuelle.setIdCircuitCorrespondant(google.get(recherchePos));
								}
								//Haut
								else
								{
									recherchePos = new Position(posX,posY-1);
									wagonActuelle.setPosWagon(recherchePos);
									wagonActuelle.setPosWagonAvant(positionWagon);
									wagonActuelle.setIdCircuitCorrespondant(google.get(recherchePos));
								}
							}
						}
						//Fin du jeu si gagner
						for(int n=0; n < listeGare.size();n++)
						{
							if(wagonActuelle.getIdCircuitCorrespondant() == listeGare.get(n).getIdGare() && wagonActuelle.getPosWagon() == listeGare.get(n).getPosGare())
							{						
								trainActuelle.getListWagon().remove(wagonActuelle);
							}
						}		
					}
					//Fin de la liste des wagons
					
					
						
						
				}	
				//Retard
				else
				{
					trainActuelle.setRetard(trainActuelle.getRetard()-1);
				}
				
			}
			//Fin de la liste des trains
	}
	
	public void provenance(Position positionActuelle, Position positionApres)
	{
		if(positionApres.getPosX() != positionActuelle.getPosX())
		{
			sensHorizontal = true; 
		}
		if(positionApres.getPosY() != positionActuelle.getPosY())
		{
			sensHorizontal = false;
		}
	}	
	
	
	public LinkedList<Circuit> getListeCircuit() {
		return listeCircuit;
	}
	public LinkedList<TrainBis> getListeTrain() {
		return listeTrain;
	}

	public LinkedList<Aguillage> getListeAiguillage() {
		return listeAiguillage;
	}

	public LinkedList<Gare> getListeGare() {
		return listeGare;
	}

	public int getTailleX()
	{
		return sncf.getTailleX();
	}
	public int getTailleY()
	{
		return sncf.getTailleY();
	}

}
