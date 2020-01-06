package view;


import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.CASTLE;
import model.CastleButton;
import model.CastleChooser;
import model.CastleSubScene;
import model.InfoLabel;

public class ViewManager {
	static GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
	static java.awt.Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
    private static final double WIDTH =(int)maximumWindowBounds.getWidth();
    private static final double HEIGHT =(int)maximumWindowBounds.getHeight();
    private GridPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    
    private final static int MENU_BUTTONS_START_X=(int) WIDTH/20;
    private final static int MENU_BUTTONS_START_Y=(int) HEIGHT/15;
    
    List<CastleButton> menuButton;
    List<CastleChooser> castleList;
    private CASTLE choosenCastle;
    
    private CastleSubScene castleChooserScene;
    
    private CastleSubScene scenetoHide;

    public ViewManager() {
    	menuButton= new ArrayList<>();
        mainPane = new GridPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createCastle();
        createBackground();

    }
    
    
    private void showSubScene(CastleSubScene subScene) {
    	if (scenetoHide != null) {
    		scenetoHide.moveSubScene();
    	}
    	
    	subScene.moveSubScene();
    	scenetoHide = subScene;
    }
    
    private void createSubScenes() {
    	castleChooserScene = new CastleSubScene();
    	mainPane.getChildren().add(castleChooserScene);
    	
    	
    	createCastleChooserSubScene();
    }
    
    
    
    private void createCastleChooserSubScene() {
		castleChooserScene = new CastleSubScene();
		mainPane.getChildren().add(castleChooserScene);
		
		InfoLabel castlechooserLabel = new InfoLabel("CHOOSE YOUR CASTLE !");
		castlechooserLabel.setTranslateX(145);
		castlechooserLabel.setTranslateY(10);
		
		castleChooserScene.getPane().getChildren().add(castlechooserLabel);
		castleChooserScene.getPane().getChildren().add(createCastleToChoose());
		castleChooserScene.getPane().getChildren().add(createButtonToStart());
		
	}

    private HBox createCastleToChoose() {
    	HBox box = new HBox();
    	box.setSpacing(20);
    	castleList = new ArrayList<>();
    	for(CASTLE castle : CASTLE.values()) {
    		CastleChooser CastleToPick = new CastleChooser(castle);
    		castleList.add(CastleToPick);
    		box.getChildren().add(CastleToPick);
    		CastleToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for(CastleChooser castle : castleList) {
						castle.setIsCircleCHoosen(false);
					}
					
					CastleToPick.setIsCircleCHoosen(true);
					choosenCastle = CastleToPick.getCastle();
				}
    			
    		});
    	}
    	box.setTranslateX(70);
    	box.setTranslateY(150);
    	return box;
    }
    
    private CastleButton createButtonToStart() {
    	CastleButton startButton = new CastleButton("START");
    	startButton.setTranslateX(350);
    	startButton.setTranslateY(275);
    	startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (choosenCastle !=null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage	, choosenCastle);
				}
				
			}
    		
		});
    	return startButton;
    }
    
	public Stage getMainStage() {
        return mainStage;

    }
    
    private void addMenuButton(CastleButton button) {
    	button.setTranslateX(MENU_BUTTONS_START_X);
    	button.setTranslateY(MENU_BUTTONS_START_Y + menuButton.size()*HEIGHT/6);
    	menuButton.add(button);
    	mainPane.getChildren().add(button);
    }
    
    private void createCastle() {
    	createStartButton();
    	createExitButton();
    }
    private void createStartButton() {
    	CastleButton startButton=new CastleButton("PLAY");
    	addMenuButton(startButton);
    	
    	startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showSubScene(castleChooserScene);
				
			}
		});
    }
    
    
    private void createExitButton() {
    	CastleButton exitButton=new CastleButton("EXIT");
    	addMenuButton(exitButton);
    	
    	exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
				
			}
    		
    	});
    }
    
    private void createBackground() {
    	Image backgroundImage = new Image("view/resources/logo_jeux.png",WIDTH,HEIGHT,false,true);
    	BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
    	mainPane.setBackground(new Background(background));
    
    }
    
}