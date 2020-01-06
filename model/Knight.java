package model;

public class Knight extends Troop{

    public Knight() {
        costProduction=500;
        timeProduction=20;
        speed=6;
        lifePoint=3;
        damages=5;
    }
    
    public String toString() {
        return "Knight";
    }
}