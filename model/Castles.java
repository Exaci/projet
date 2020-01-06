package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.image.ImageView;

public class Castles {
    private String door;
    private int level,earnings,treasure,x,y;
    private List<Troop> troop;

    public Castles(ImageView im,int x,int y) {
        this.x=x;
        this.y=y;
        Random rand=new Random();
        int z=rand.nextInt(4);
        switch(z) {
	        case 0:{door="NORTH";im.setRotate(180);break;}
	        case 1:{door="EAST";im.setRotate(-90);break;}
	        case 2:{door="WEST";im.setRotate(90);break;}
	        case 3:{door="SOUTH";break;}
	        }
        troop=new ArrayList<Troop>();
    }


    @Override
    public String toString() {
        return "\n LEVEL=" + level + "\n EARNINGS=" + earnings + "\n TREASURE=" + treasure;
    }

    public int[] countTroop(){
        int []tab =new int[3];
        tab[0]=0;tab[1]=0;tab[2]=0;
        for(int i=0;i<troop.size();i++) {
            if(troop.get(i).getClass()==Knight.class) {
                tab [0]++;
            }
            if(troop.get(i).getClass()==Onagre.class) {
                tab [1]++;
            }
            if(troop.get(i).getClass()==Pikeman.class) {
                tab [2]++;
            }
        }
        return tab;
    }
    
    public int earningsProduction() {
        return level*1000;
    }
    
    public int levelCost() {
        return 1000*level;
    }
    
    public int levelTurnCost() {
        return 100+50*level;
    }

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getEarnings() {
		return earnings;
	}

	public void setEarnings(int earnings) {
		this.earnings = earnings;
	}

	public int getTreasure() {
		return treasure;
	}

	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<Troop> getTroop() {
		return troop;
	}

	public void setTroop(List<Troop> troop) {
		this.troop = troop;
	}
   
	public boolean armyisDead() {
		if (troop.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void battle(Castles enemycastle) {
		while (!this.troop.isEmpty() && !enemycastle.troop.isEmpty()) {
			this.troop.get(0).troopvstroop(enemycastle.troop.get(0));
			if(troop.get(0).lifePoint<=0) {
				getTroop().remove(troop.get(0));
				
			}
			if (enemycastle.troop.get(0).lifePoint<=0) {
				enemycastle.getTroop().remove(enemycastle.troop.get(0));
			}
		}
	}
	
	
	
	public void levelup() {
		this.level = this.level +1;
		this.earnings = earningsProduction();
		this.treasure = this.treasure - levelCost();
	}
	
	public void addtroopPikeman() {
		this.troop.add(new Pikeman());
		this.treasure = this.treasure - 100;
	}
	
	public void addtroopKnight() {
		this.troop.add(new Knight());
		this.treasure = this.treasure - 500;
	}
	
	public void addtroopOnagre() {
		this.troop.add(new Onagre());
		this.treasure = this.treasure - 1000;
	}
	
}