package model;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class GameCastleStatLabel extends Label{
	static GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
	static java.awt.Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
    private static final double WIDTH =(int)maximumWindowBounds.getWidth();
	public final static String FONT_PATH = "src/model/resources/Kenney Pixel.ttf";
    public final static String BACKGROUND_IMAGE = "view/resources/red_button10.png";
    
    public GameCastleStatLabel(String text) {
        setPrefWidth(WIDTH-10*75);
        setPrefHeight(2*79+39);
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER_LEFT);
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