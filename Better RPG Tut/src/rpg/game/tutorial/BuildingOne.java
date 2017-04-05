package rpg.game.tutorial;

import java.awt.Color;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class BuildingOne extends BasicGameState{
	
	Animation character, moveUp, moveDown, moveLeft, moveRight;
	Image buildingOne;
	Rectangle player;
	Line doorway;
	
	int[] duration = {200, 200};
	boolean escMenu = false;
	boolean toggleMouseLocation = false;
	
	
	float characterPosX = 0;
	float characterPosY = 0;
	float shiftX;
	float shiftY;
	
	//String Variables
	String resume = "Resume (R)";
	String mainMenu = "Main Menu (M)";
	String quit = "Quit (Q)";
	
	
	
	public BuildingOne(int worldmap) {
	}

	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		buildingOne = new Image("res/building_one.png");
		
		player = new Rectangle(shiftX, shiftY, 10, 10);
		doorway = new Line(258, -42, 320, -42);
		
		shiftX = gc.getWidth()/2;
		shiftY = gc.getHeight()/2;
		
		Image[] walkUp = {new Image("res/basic-up.png"), new Image("res/basic-up-move.png")};
		Image[] walkRight = {new Image("res/basic-Right.png"), new Image("res/basic-right-move.png")};
		Image[] walkDown = {new Image("res/basic-down.png"), new Image("res/basic-down-move.png")};
		Image[] walkLeft = {new Image("res/basic-left.png"), new Image("res/basic-left-move.png")};
		
		moveUp = new Animation(walkUp, duration, true);
		moveRight = new Animation(walkRight, duration, true);
		moveDown = new Animation(walkDown, duration, true);
		moveLeft = new Animation(walkLeft, duration, true);
		
		character = moveDown;
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		buildingOne.draw(characterPosX + 294, characterPosY - 37);
		
		
		character.draw(shiftX, shiftY);
		g.draw(doorway);
		
		g.drawString("Character X: " + characterPosX + "Character Y: " + characterPosY, 400, 20);
		
		if(escMenu == true){
			g.drawString(resume, 400, 330);
			g.drawString(mainMenu, 400, 280);
			g.drawString(quit, 400, 230);
			if (escMenu == false){
				g.clear();
			}
		}
		
		if(toggleMouseLocation == true){
			int mouseX = Mouse.getX();
			int mouseY = 700 - Mouse.getY();
			
			g.drawString("Mouse X: " + mouseX + ", " + "Mouse Y: " + mouseY, 40, 30);
		}
		
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		doorway.setLocation(characterPosX + 218, characterPosY + 402);
		player.setLocation(shiftX, shiftY);
		//Building 1 Exit
		/*
		if((characterPosX >= 258 && characterPosX <= 320) && (characterPosY <= -42)){
			sbg.enterState(1);
		}
		*/
		
		if(player.intersects(doorway)){
			if(character == moveDown){
				System.out.println("Leaving room.");
				
				sbg.enterState(1);
				
			}
			if(character == moveUp){
				characterPosY += 5f;
			}
			
		}
			
		
		//up movement
		if(input.isKeyDown(Input.KEY_W)){
			if(characterPosY >= 345){
				characterPosY -= delta * .1f;
			}
			character = moveUp;
			characterPosY += delta * .1f;
			
		}
		
		//right movement
		if(input.isKeyDown(Input.KEY_D)){
			if(characterPosX <= -180){
				characterPosX += delta * .1f;
			}
			character = moveRight;
			characterPosX -= delta * .1f;
			
		}
		
		//down movement
		if(input.isKeyDown(Input.KEY_S)){
			if(characterPosY <= 0){
				if(characterPosX >= 321 || characterPosX <= 257){
					characterPosY += delta * .1f;
				}
				
			}
			character = moveDown;
			characterPosY -= delta * .1f;
			
		}
		
		//left movement
		if(input.isKeyDown(Input.KEY_A)){
			if(characterPosX >= 496){
				characterPosX -= delta * .1f;
			}
			character = moveLeft;
			characterPosX += delta * .1f;
			
		}
		
		
		//escape menu
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			escMenu = true;
		}
		if(escMenu == true){
			if(input.isKeyDown(Input.KEY_R)){
				escMenu = false;
				
			}
			if(input.isKeyDown(Input.KEY_M)){
				escMenu = false;
				sbg.enterState(0);
			}
			if(input.isKeyDown(Input.KEY_Q)){
				escMenu = false;
				System.exit(0);
			}
		}
		
		//Mouse location button 
		if(input.isKeyPressed(Input.KEY_M)){
			System.out.println(characterPosX);
			if(toggleMouseLocation == false){
				toggleMouseLocation = true;
			}else if(toggleMouseLocation == true){
				toggleMouseLocation = false;
			}
		}
		
	}

	
	public int getID() {
		return 2;
	}

}
