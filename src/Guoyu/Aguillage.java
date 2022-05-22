package Guoyu;

import java.util.Objects;

import Thomas.Position;

public class Aguillage {


	private Position positionAiguillage;
	private boolean directionHorizontale;
	private boolean directionVertical;
    
    public Aguillage() {
    }
    
    public Aguillage(Position pos) 
    { 
        this.positionAiguillage=pos;
        this.directionHorizontale=false;
        this.directionVertical=true;
    }
    
    public Position getPositionAiguillage(){
        return positionAiguillage;
    }
    
    public void setPositionAiguillage(Position newpositionAiguillage) {
        positionAiguillage = newpositionAiguillage;
    }
    
    
    public boolean getDirectionHorizontale() {
        return directionHorizontale;
    }
    
    public void setDirectionHorizontale(boolean directionHorizontale) {
        this.directionHorizontale=directionHorizontale;
    }
    
    public boolean getDirectionVertical() {
        return directionVertical;
    }
    
    public void setdirectionVertical(boolean directionVertical) {
        this.directionVertical=directionVertical;
    }

    @Override
    public String toString() {
        return "Aguillage [positionAiguillage=" + positionAiguillage + ", directionHorizontale=" + directionHorizontale
                + ", directionVertical=" + directionVertical + "]";
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(positionAiguillage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aguillage other = (Aguillage) obj;
		return Objects.equals(positionAiguillage, other.positionAiguillage);
	}
    
    

}
