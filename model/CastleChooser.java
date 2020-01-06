package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CastleChooser extends VBox{
	private ImageView circleImage;
	private ImageView castleImage;
	private String circleNotChoosen = "view/resources/castlechooser/red_circle.png";
	private String circleChoosen = "view/resources/castlechooser/red_boxTick.png";
	private CASTLE castle;
	private boolean isCircleChoosen;
	
	public CastleChooser(CASTLE castle) {
		circleImage = new ImageView(circleNotChoosen);
		castleImage = new ImageView(new Image(castle.getUrlCastle(),100,100,true,true));
		this.castle = castle;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		this.getChildren().add(castleImage);		
	}
	
	public CASTLE getCastle() {
		return castle;
	}
	
	public boolean getIsCircleChoosen() {
		return isCircleChoosen;
	}
	
	public void setIsCircleCHoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
	}
	
}
