package model;

import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Displacement {
	int xStart,yStart,xFinal,yFinal;
	String doorStart,doorFinal;
	Circle circle;
	int[][] presence;
	final SequentialTransition translateAnimation;
	TranslateTransition animation;
	
	public Displacement(GridPane gridpane,Castles castle1,Castles castle2,int[][] tab) {
		xStart=castle1.getX()+1;
		xFinal=castle2.getX()+1;
		yStart=castle1.getY()+1;
		yFinal=castle2.getY()+1;
		this.presence=tab;
		doorStart=castle1.getDoor();
		doorFinal=castle2.getDoor();
		xInPixel();
		yInPixel();
		circle=new Circle(3);
		circle.setTranslateX(xStart);
		circle.setTranslateY(yStart);
		gridpane.getChildren().add(circle);	
		translateAnimation = new SequentialTransition();
		xInInt();
		yInInt();
		displacement();
	}
	
	public boolean isArrived() {
			if (xStart == xFinal && yStart == yFinal) {
				return true;
			}
			else {
				return false;
			}
	}
	
	public void exit() {
		switch(doorStart) {
			case "EAST":{goEast();break;}
			case "WEST":{goWest();break;}
			case "NORTH":{goNorth();break;}
			case "SOUTH":{goSouth();break;}
		}
	}
	
	public void entrance() {
		switch(doorFinal) {
			case "EAST":{xFinal++;break;}
			case "WEST":{xFinal--;break;}
			case "NORTH":{yFinal--;break;}
			case "SOUTH":{yFinal++;break;}
		}
	}
	
	public void Final() {
		switch(doorFinal) {
			case "EAST":{goWest();break;}
			case "WEST":{goEast();break;}
			case "NORTH":{goSouth();break;}
			case "SOUTH":{goNorth();break;}
		}
	}
	
	public void xInPixel() {
		xStart=(xStart-1)*75+75/2;
		xFinal=(xFinal-1)*75+75/2;
	}
	
	public void yInPixel() {
		yStart=(yStart-1)*79;
		yFinal=(yFinal-1)*79;
	}
	
	public void xInInt() {
		xStart=(xStart/75)+1;
		xFinal=(xFinal/75)+1;
	}
	
	public void yInInt() {
		yStart=(yStart/79)+1;
		yFinal=(yFinal/79)+1;
	}
	
	public void transition(int x,int y) {
		xInPixel();
		yInPixel();
		animation=new TranslateTransition(Duration.seconds(2), circle);
	    animation.setByX(x); 
	    animation.setByY(y); 
	    animation.setInterpolator(Interpolator.LINEAR);
	    translateAnimation.getChildren().add(animation);
	    xStart+=x;
	    yStart+=y;
	    xInInt();
	    yInInt();
	}
	
	public void goEast() {
		transition(75,0);
	}
	public void goWest() {
		transition(-75, 0);
	}
	public void goNorth() {
		transition(0, -79);
	}
	public void goSouth() {
		transition(0,79);
	}
	
	public String whereisthecastle() {
		String pos;
		if(xStart>xFinal) {
			if(yStart<yFinal) {
				pos="SW";
			}
			else {
				pos="NW";
			}
		}
		else {
			if(yStart<yFinal) {
				pos="SE";
			}
			else {
				pos="NE";
			}
		}
		return pos;
	}
	
	public void contournechateau(String go) {
		switch(go) {
			case "N":{
				if(presence[xStart-1][yStart-2]==1) {
					goWest();goNorth();goNorth();goEast();
				}
				else {
					goNorth();
				}
				break;}
			case "S":{
				if(presence[xStart-1][yStart]==1) {
					goWest();goSouth();goSouth();goEast();
				}
				else {
					goSouth();
				}
				break;}
			case "E":{
				if(presence[xStart][yStart-1]==1) {
					goNorth();goEast();goEast();goSouth();
				}
				else {
					goEast();
				}
				break;}
			case "W":{
				if(presence[xStart-2][yStart-1]==1) {
					goNorth();goWest();goWest();goSouth();
				}
				else {
					goWest();
				}
				break;}
		}
	}
	
	public void displacement() {
		entrance();
		exit();
		String pos=whereisthecastle();
			switch(pos) {
				case "NW":{
					while(xStart>xFinal) {
						contournechateau("W");
					}
					while(yStart>yFinal) {
						contournechateau("N");
					}
					break;}
				case "SW":{
					while(xStart>xFinal) {
						contournechateau("W");
					}
					while(yStart<yFinal) {
						contournechateau("S");
					}
					break;}
				case "NE":{
					while(xStart<xFinal) {
						contournechateau("E");
					}
					while(yStart>yFinal) {
						contournechateau("N");
					}
					break;}
				case "SE":{
					while(xStart<xFinal) {
						contournechateau("E");
					}
					while(yStart<yFinal) {
						contournechateau("S");
					}
					break;}
				}
		Final();
		translateAnimation.playFromStart();
	}
}
