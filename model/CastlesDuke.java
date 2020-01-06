package model;

import java.util.Random;

import javafx.scene.image.ImageView;

public class CastlesDuke extends Castles{
    String owner;

    public CastlesDuke(ImageView im,String owner,int x,int y) {
        super(im,x,y);
        this.owner=owner;
        setLevel(1);
        setEarnings(earningsProduction());
        setTreasure(0);
        Random rand=new Random();
        for(int i=0;i<10;i++) {
            Troop person;
            int z=rand.nextInt(3);
            switch(z) {
            case 1:{person=new Pikeman();break;}
            case 2:{person=new Knight();break;}
            default:{person=new Onagre();break;}
            }
            getTroop().add(person);
        }

    }

    @Override
    public String toString() {
        return "\n OWNER=" + owner + "\n LEVEL=" + getLevel() 
                + "\n EARNINGS=" + getEarnings() + "\n TREASURE=" + getTreasure()+
                "\n TROOP= "+
                "\n          KNIGHT :"+countTroop()[0]+
                "\n          ONAGRE :"+countTroop()[1]+
                "\n          PIKEMAN :"+countTroop()[2];
    }
}
