package model;

public class Troop {
    int costProduction,timeProduction,speed,lifePoint,damages;

    public String info() {
        return "lifePoint=" + lifePoint;
    }

    public void troopvstroop (Troop troopattack) {
        this.lifePoint = this.lifePoint - troopattack.damages;
        troopattack.lifePoint = troopattack.lifePoint - this.damages;
    }

    public int getCostProduction() {
        return costProduction;
    }

    public void setCostProduction(int costProduction) {
        this.costProduction = costProduction;
    }

    public int getTimeProduction() {
        return timeProduction;
    }

    public void setTimeProduction(int timeProduction) {
        this.timeProduction = timeProduction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public int getDamages() {
        return damages;
    }

    public void setDamages(int damages) {
        this.damages = damages;
    }
}