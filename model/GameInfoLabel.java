package model;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class GameInfoLabel extends Label{
	static GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
	static java.awt.Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
    private static final double WIDTH =(int)maximumWindowBounds.getWidth();
	public final static String FONT_PATH = "src/model/resources/Kenney Pixel.ttf";
    public final static String BACKGROUND_IMAGE = "view/resources/red_button10.png";
    
    public GameInfoLabel(String text) {
        setPrefWidth(WIDTH-10*75);
        setPrefHeight(39);
        setText(text);
        setWrapText(true);
        setLabelFont();
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE,WIDTH-10*75,39,false,true), BackgroundRepeat.NO_REPEAT,
        		BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER);
    }	
    
    private void setLabelFont() {
    	try {
    		setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
    	} 
    	catch (FileNotFoundException e) {
    		setFont(Font.font("Verdana",23));
    	}
    }
}