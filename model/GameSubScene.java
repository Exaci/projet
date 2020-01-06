package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.scene.image.Image;


public class GameSubScene extends SubScene {
    private final static String BACKGROUND_IMAGE="model/resources/grey_panel.png";
    private boolean isHidden;

    public GameSubScene() {
        super(new GridPane(),600,400);
        prefWidth(100);
        prefHeight(400);
        BackgroundImage image= new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
        GridPane root=(GridPane) this.getRoot();
        root.setBackground(new Background(image));
        isHidden = true;
        setTranslateX(10*75);
        setTranslateY(0);
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if(isHidden) {
            transition.setToX(500);
            isHidden = false;
        } 
        else {
            transition.setToX(1900);
            isHidden = true;
        }
        transition.play();
    }
    
    public GridPane getPane() {
    	return (GridPane) this.getRoot();
    }
}