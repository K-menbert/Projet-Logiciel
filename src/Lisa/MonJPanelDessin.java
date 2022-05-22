package Lisa;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import Guoyu.Aguillage;
import Guoyu.Gare;
import Thomas.Circuit;
import Thomas.Jeu;
import Thomas.Position;
import Thomas.TrainBis;
import Thomas.Wagon;

public class MonJPanelDessin extends JPanel{
	
	private Jeu jeu;
	private String sNomImage;
	private Image image=null;
	private Hashtable<JButtonAiguillage,Aguillage> rechercheAiguilage = new Hashtable<JButtonAiguillage,Aguillage>();
	
	public MonJPanelDessin(Jeu jeu) 
	{
		this.jeu = jeu;
		
	}	
				
	private void chargerImage()
	{
		if(sNomImage!=null) {
			String sNomFile=".\\images\\"+sNomImage;
			try {                
		          image = ImageIO.read(new File(sNomFile));
		       } catch (IOException ex) {
		    	   image=null;
		       }
		}	
		this.repaint();
	}
	
	public void initButton()
	{
		double dx = this.getWidth()/(double)jeu.getTailleX();
		double dy = this.getHeight()/(double)jeu.getTailleY();
		
		for(int i=0; i< jeu.getListeAiguillage().size(); i++)
		{
			
			Aguillage aiguil = jeu.getListeAiguillage().get(i);
			JButtonAiguillage btn = new JButtonAiguillage(i);
			btn.setBackground(Color.magenta);
			btn.addActionListener(new ActionListener() {

			    @Override
			    public void actionPerformed(ActionEvent e) 
			    {
			    	Object  source=e.getSource();				    	
			    	Aguillage aig = rechercheAiguilage.get(source);				    	
			    	System.out.println(aig.getPositionAiguillage().toString());
			    }
			});
			
			btn.setBounds((int)(aiguil.getPositionAiguillage().getPosX()*dx), (int)(aiguil.getPositionAiguillage().getPosY()*dy),(int)dx,(int)dy);
			rechercheAiguilage.put(btn, aiguil);
			this.add(btn);			
		}
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		this.setBackground(Color.white);
		
		if(jeu!=null) 
		{
			double dx = this.getWidth()/(double)jeu.getTailleX();
			double dy = this.getHeight()/(double)jeu.getTailleY();						
			
			initButton();
			
			//System.out.println(dx + " " + dy + " " + jeu.getListeTrain().size());
			for(int i=0; i<jeu.getListeCircuit().size(); i++)
			{
				Color newCouleur = this.randomColor(i);
				for(int j=0; j<jeu.getListeCircuit().get(i).getposcircuit().size(); j++)
				{
					Circuit circ = jeu.getListeCircuit().get(i);
					g.setColor(newCouleur);
					g.fillRect((int)(circ.getposcircuit().get(j).getPosX()*dx), 
					(int)(circ.getposcircuit().get(j).getPosY()*dy), 
					(int)dx, (int)dy);				
				}														
			}			
			
			for(int train=0 ; train< jeu.getListeTrain().size(); train++)
			{
				TrainBis trainActuelle = jeu.getListeTrain().get(train);
				
				for(int wagon=0 ; wagon< jeu.getListeTrain().get(train).getListWagon().size(); wagon++)
				{
					Wagon wagonActuelle = trainActuelle.getListWagon().get(wagon);
					g.setColor(Color.BLUE);
					g.fillRect((int)(wagonActuelle.getPosWagon().getPosX()*dx), 
							(int)(wagonActuelle.getPosWagon().getPosY()*dy), 
							(int)dx, (int)dy);
				}
			}
			
			
	
			 //System.out.println("affiche");
		}
	}	
	
	public Color randomColor(int idCircuit)
	{
		if(idCircuit == 0)
		{
			return Color.cyan;
		}
		if(idCircuit == 1)
		{
			return Color.red;
		}
		if(idCircuit == 2)
		{
			return Color.orange;
		}
		if(idCircuit == 3)
		{
			return Color.red;
		}
		
		return Color.black;
	}
	
}


		
	

