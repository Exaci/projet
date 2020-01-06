package model;

import java.util.Random;

import javafx.scene.image.ImageView;

public class CastlesNeutral extends Castles{

    public CastlesNeutral(ImageView im,int x,int y) {
        super(im,x,y);
        Random rand=new Random();
        setLevel(rand.nextInt(4)+1);
        setEarnings(earningsProduction()/10);
        setTreasure(rand.nextInt(3000)+850);
        int nb=rand.nextInt(15)+1;
        for(int i=0;i<nb;i++) {
            Troop person;
            int z=rand.nextInt(3);
            switch(z) {
	            case 0:{person=new Pikeman();break;}
	            case 1:{person=new Knight();break;}
	            default:{person=new Onagre();break;}
	            }
            getTroop().add(person);
        }
    }

    public String toString() {
        return "\n OWNER= BARON \n LEVEL=" + getLevel() 
                + "\n EARNINGS=" + getEarnings() + "\n TREASURE=" + getTreasure()+
                "\n TROOP= "+
                "\n          KNIGHT :"+countTroop()[0]+
                "\n          ONAGRE :"+countTroop()[1]+
                "\n          PIKEMAN :"+countTroop()[2];
    }
}