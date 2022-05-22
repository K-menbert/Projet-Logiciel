package Thomas;

public class MinuteurRetard {
	/*
	* Utilisation :
	* Chronometre monchrono = new Chronometre();
	* monchrono.start();
	*
	* Lancer le chrono. Temps en millisecondes
	* monchrono.demarrer(1000);

	* monchrono.estfini();
	* renvoie vrai si le temps est écoulé.
	*/

	private int tempsRestant;
	private int decoule=100;

	public void run() 
	{
		while(true) 
		{

			if (tempsRestant>0) 
			{
				tempsRestant=tempsRestant-decoule;
				if (tempsRestant<0) tempsRestant=0;
			}

			try 
			{
				Thread.sleep(decoule);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void demarrer(int tps) 
	{
		tempsRestant=tps;
	}

	public int getTempsRestant() 
	{
		return tempsRestant;
	}
	
	public boolean estfini() 
	{
		if (tempsRestant>0) return false;
		return true;
	}
}



