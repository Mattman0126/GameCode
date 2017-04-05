package rpg.game.tutorial;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class TutorialGame extends StateBasedGame{

	public static final String gameName = "RPG Sandbox Game";
	public static final int startMenu = 0;
	public static final int worldMap = 1;
	public static final int buildingOne = 2;
	
	
	
	public TutorialGame(String gameName) {
		super(gameName);
		this.addState(new MainMenu(startMenu));
		this.addState(new WorldMap(worldMap));
		this.addState(new BuildingOne(buildingOne));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(startMenu).init(gc, this);
		this.getState(worldMap).init(gc, this);
		this.getState(buildingOne).init(gc, this);
		//start program in the start menu
		this.enterState(worldMap);
		
	}

	public static void main(String[] args) {
		AppGameContainer agc;
		try{
			agc = new AppGameContainer(new TutorialGame(gameName));
			agc.setDisplayMode(1000, 700, false);
			agc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}

	}

}
