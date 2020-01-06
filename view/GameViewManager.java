package view;

import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.CASTLE;
import model.Castles;
import model.CastlesDuke;
import model.CastlesNeutral;
import model.Displacement;
import model.GameCastleStatLabel;
import model.GameInfoLabel;

public class GameViewManager  {
	private GridPane gamePane;
	private GridPane infoPane;
	private GridPane pane;
	private Scene gameScene;
	private Stage gameStage;
	private int compteurforwin;
	private GameCastleStatLabel castlestatlabel;
	static GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
	static java.awt.Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
    private static final double WIDTH =(int)maximumWindowBounds.getWidth();
    private static final double HEIGHT =(int)maximumWindowBounds.getHeight();
	private Stage menuStage;
	private Castles myCastle;
	private Castles castleDuke;
	private Castles castleselected ;
	private boolean isenemycastleselected;
	private int[][] presence;
	private Button btnwin;
	
	public GameViewManager() {
		castleselected = null;
		myCastle = null;
		isenemycastleselected = false;
		compteurforwin = 0;
		initializeStage();
	}

	private void initializeStage() {
		pane = new GridPane();
		gamePane = new GridPane();
		infoPane=new GridPane();
		createlabelinfo();
        pane.add(gamePane, 0, 0);
        pane.add(infoPane, 1,0);
        gameScene = new Scene(pane, WIDTH, HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gamePane.setGridLinesVisible(true);
        infoPane.setGridLinesVisible(true);
	}
	
	 public boolean isCastle(int[][] presence, int x,int y) {
	    	if(x==0||x==9||y==0||y==8) {
	    		return true;
	    	}
	    	for(int i=-1;i<2;i++) {
	    		for(int j=-1;j<2;j++) {
	    			if(presence[x+i][y+j]==1) {
	    				return true;
	    			}
	    		}
	    	}
	    	return false;
	    }
	    
	private void createCastle(CASTLE choosenCastle) {
		int number=0;
		boolean doit=false;
    	presence= new int[11][11];
    	Button button;
    	Random rand = new Random(); 
		InputStream input = getClass().getResourceAsStream("/view/resources/castlechooser/castle_grey.png");
		Image image = new Image(input, 70,59, true, true);
		ImageView imageView;    
    	for(int row=0;row<10;row++) {
    		for(int col=0;col<9;col++) {
    			presence[row][col]=0;
    			button=new Button();
		    	button.setMinHeight(79);
		    	button.setMinWidth(75);
    			button.setStyle("-fx-background: transparent");
    			gamePane.add(button,row,col);
    		}
    	}
    	while(number<10) {
	    	int x = rand.nextInt(10);
	    	int y = rand.nextInt(8);
	    	if(isCastle(presence,x,y)==false) {
	    		presence[x][y]=1;
	    		imageView = new ImageView(image);
		    	button=new Button();
		    	CastlesNeutral castle = new CastlesNeutral(imageView,x,y);
		    	button.setMinHeight(79);
		    	button.setMinWidth(75);
		    	button.setStyle("-fx-background:transparent");
		    	button.setGraphic(imageView);
				gamePane.add(button,x,y);
				button.setOnAction(new EventHandler<ActionEvent>() {
    				
    				@Override
    				public void handle(ActionEvent event) {
    					if(castle.getTroop().isEmpty()) {
    						Button castledestroyed = new Button();
							castledestroyed.setMinHeight(79);
							castledestroyed.setMinWidth(75);
							castledestroyed.setStyle("-fx-background: transparent");
			    			gamePane.add(castledestroyed,castle.getX(),castle.getY());
    					}
    					else {
    					castlestatlabel.setText(castle.toString());
    					castleselected = castle;
    					isenemycastleselected = true;				
    					}
    				}
    			});
				number++;		
	    	}	
    	}
    	while(doit==false) {
	    	int x = rand.nextInt(10);
	    	int y = rand.nextInt(8);
	    	if(isCastle(presence,x,y)==false) {
	    		presence[x][y]=1;
	    		doit=true;
	    		image = new Image(choosenCastle.getUrlCastle(),70,59,true,true);
	    		imageView=new ImageView(image);
		    	button=new Button();
		    	CastlesDuke castle = new CastlesDuke(imageView, "Player 1", x,y);
		    	button.setMinHeight(79);
		    	button.setMinWidth(75);
		    	button.setStyle("-fx-background:transparent");
		    	button.setGraphic(imageView);
				gamePane.add(button,x,y);
				myCastle = castle;
				button.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						castlestatlabel.setText(castle.toString());
						myCastle = castle;
						isenemycastleselected = false;
					}
				});
	    	}
    	}
    	doit=false;
    	while(doit==false) {
        	int x = rand.nextInt(10);
        	int y = rand.nextInt(8);
        	if(isCastle(presence,x,y)==false) {
        		presence[x][y]=1;
        		doit=true;
        		image = new Image("/view/resources/castlechooser/castle_purple.png",70,59,true,true);
        		imageView=new ImageView(image);
    	    	button=new Button();
    	    	CastlesDuke castle = new CastlesDuke(imageView, "NPC", x,y);
    	    	button.setMinHeight(79);
    	    	button.setMinWidth(75);
    	    	button.setStyle("-fx-background:transparent");
    	    	button.setGraphic(imageView);
    			gamePane.add(button,x,y);
    			castleDuke = castle;
    			button.setOnAction(new EventHandler<ActionEvent>() {
    				
    				@Override
    				public void handle(ActionEvent event) {
    					if(castle.getTroop().isEmpty()) {
    						Button castledestroyed = new Button();
							castledestroyed.setMinHeight(79);
							castledestroyed.setMinWidth(75);
							castledestroyed.setStyle("-fx-background: transparent");
			    			gamePane.add(castledestroyed,castle.getX(),castle.getY());
    					}
    					else {
	    					castlestatlabel.setText(castle.toString());
	    					castleselected = castle;    					
	    					isenemycastleselected = true;
    					}
    				}
    			});
        	}
        }
	}

	public void createNewGame(Stage menuStage, CASTLE choosenCastle) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createCastle(choosenCastle);
		gameStage.show();
	}
	
	private void createbtnwin() {
		btnwin = new Button();
		btnwin.setPrefHeight(2*79+19);
		btnwin.setPrefWidth((WIDTH-10*75)/2-20);
	}
	
	private Button createbtnExit() {
		Button btnExit=new Button("EXIT");
		btnExit.setPrefHeight(2*79+19);
		btnExit.setPrefWidth((WIDTH - 10*75)/2 -20);
		btnExit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				gameStage.close();
			}
    	});
		return btnExit;
	}
	
	private Button createPikeman() {
		Button addpikeman = new Button("PIKEMAN\n ROUNDS 5\n FLORINS 100\n HP 5 & DMG 1 \n SPEED 2");
		addpikeman.setPrefHeight(3*79+39);
		addpikeman.setPrefWidth((WIDTH - 10*75)/3 -20);
		addpikeman.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (myCastle.getTreasure() > 100) {
					myCastle.addtroopPikeman();
					Random random = new Random();
					int x = random.nextInt(2);
					if (x==1) {
						if (castleDuke.getTreasure() >= 1000*(castleDuke.getLevel()+1)) {
							castleDuke.levelup();
						}
						else {
							int y = random.nextInt(3);
							switch(y) {
					            case 0:{if (castleDuke.getTreasure() > 100) { castleDuke.addtroopPikeman(); };break;}
					            case 1:{if (castleDuke.getTreasure() > 500) { castleDuke.addtroopKnight(); };break;}
					            default :{if (castleDuke.getTreasure() > 1000) { castleDuke.addtroopOnagre(); };break;}
							}
						}
					}
				}
			}
		});
		return addpikeman;	
	}
	
	private Button createKnight() {
		Button addknight = new Button("KNIGHT\n ROUNDS 20\n FLORINS 500\n HP 3 & DMG 5\n SPEED 6");
		addknight.setPrefHeight(3*79+39);
		addknight.setPrefWidth((WIDTH - 10*75)/3 -20);
		addknight.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (myCastle.getTreasure() > 500) {
					myCastle.addtroopKnight();
					Random random = new Random();
					int x = random.nextInt(2);
					if (x==1) {
						if (castleDuke.getTreasure() >= 1000*(castleDuke.getLevel()+1)) {
							castleDuke.levelup();
						}
						else {
							int y = random.nextInt(3);
							switch(y) {
								case 0:{if (castleDuke.getTreasure() > 100) { castleDuke.addtroopPikeman(); };break;}
								case 1:{if (castleDuke.getTreasure() > 500) { castleDuke.addtroopKnight(); };break;}
								default :{if (castleDuke.getTreasure() > 1000) { castleDuke.addtroopOnagre(); };break;}
					            }
						}
					}
				}
			}
		});
		return addknight;
	}
	
	private Button createOnagre()  {
		Button addonagre = new Button("ONAGRE\n ROUNDS 50\n FLORINS 1000\n HP 5 & DMG 10 \n SPEED 1");
		addonagre.setPrefHeight(3*79+39);
		addonagre.setPrefWidth((WIDTH - 10*75)/3 -20);
		addonagre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (myCastle.getTreasure() > 1000) {
						myCastle.addtroopOnagre();
						Random random = new Random();
						int x = random.nextInt(2);
						if (x==1) {
							if (castleDuke.getTreasure() >= 1000*(castleDuke.getLevel()+1)) {
								castleDuke.levelup();
							}
							else {
								int y = random.nextInt(3);
								switch(y) {
									case 0:{if (castleDuke.getTreasure() > 100) { castleDuke.addtroopPikeman(); };break;}
						            case 1:{if (castleDuke.getTreasure() > 500) { castleDuke.addtroopKnight(); };break;}
						            default :{if (castleDuke.getTreasure() > 1000) { castleDuke.addtroopOnagre(); };break;}
								}
							}
						}
					}	
			}
		});
		return addonagre;
	}
	
	private Button createbtnAttack() {
		Button btnAttack = new Button("ATTACK !");
		btnAttack.setPrefWidth(WIDTH-10*75-10);
	    btnAttack.setPrefHeight(2*79+39);
	    btnAttack.setOnAction(new EventHandler<ActionEvent>() {

				@Override
		public void handle(ActionEvent event) {
				if (castleselected !=null && isenemycastleselected == true && myCastle !=null ) {
					Displacement attack=new Displacement(gamePane,myCastle,castleselected,presence);
					myCastle.battle(castleselected);
					if( castleselected.armyisDead() == true){
						myCastle.setTreasure(myCastle.getTreasure() + castleselected.getTreasure());
						castleselected.setTreasure(0);
						compteurforwin = compteurforwin +1;
						myCastle.setTreasure(myCastle.getTreasure()+myCastle.earningsProduction());
						castleDuke.setTreasure(castleDuke.getTreasure()+castleDuke.earningsProduction());					
					}
					win();
				}
			}
		});
		return btnAttack;
	}
	
	private void win() {
		if (compteurforwin == 11) {
			btnwin.setText("YOU WON !!!!!!!");
		}
		else {
			if (myCastle.getTroop().isEmpty() && myCastle.getTreasure()<=100) {
				btnwin.setText("YOU LOST !!!!!!!");
			}
		}
	}
	
	private Button createbtnlvlup() {
		Button btnlvlup = new Button("LEVEL UP !");
		btnlvlup.setPrefWidth(WIDTH-10*75-10);
		btnlvlup.setPrefHeight(2*79+39);
		btnlvlup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (myCastle.getTreasure() >= 1000*(myCastle.getLevel()+1) ) {
					myCastle.levelup();
				}
			}
		});
		return btnlvlup;
	}
	
	private void createlabelinfo() {
		GameInfoLabel castleinfolabel = new GameInfoLabel("INFORMATION !");
		castleinfolabel.setTranslateX(0);
		castleinfolabel.setTranslateY(0);
		GameInfoLabel castletrooplabel = new GameInfoLabel("TROOPS !");
		castletrooplabel.setTranslateX(0);
		castletrooplabel.setTranslateY(0);
		castlestatlabel = new GameCastleStatLabel( " OWNER : \n LEVEL : \n TREASURE : \n EARNINGS : \n TROOPS : " );
		castlestatlabel.setTranslateX(0);
		castlestatlabel.setTranslateY(0);
		GameInfoLabel castleotherlabel = new GameInfoLabel("OTHERS !");
		castleotherlabel.setTranslateX(0);
		castleotherlabel.setTranslateY(0);
		infoPane.add(castleinfolabel,0,0);
		HBox boxinfos = new HBox(10,castlestatlabel,createbtnlvlup(),createbtnAttack());
		infoPane.add(boxinfos, 0, 1);
		infoPane.add(castletrooplabel, 0, 2);
		HBox boxtroop = new HBox(10, createPikeman(), createKnight(), createOnagre());
		boxtroop.setAlignment(Pos.CENTER);
		infoPane.add(boxtroop,0,3);
		infoPane.add(castleotherlabel,0,4);
		createbtnwin();
		HBox box = new HBox (10,btnwin, createbtnExit());
		box.setAlignment(Pos.CENTER);
		infoPane.add(box, 0, 5);	
		}
}
