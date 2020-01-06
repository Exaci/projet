package model;

public class Pikeman extends Troop{
    public Pikeman() {
        costProduction=100;
        timeProduction=5;
        speed=2;
        lifePoint=1;
        damages=1;
    }
    
    public String toString() {
        return "Pikeman";
    }
}