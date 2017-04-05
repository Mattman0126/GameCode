package rpg.game.tutorial;

import java.awt.Color;

import org.lwjgl.input.Mouse;
//import org.lwjgl.util.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WorldMap extends BasicGameState{
	
	Animation character, moveUp, moveDown, moveLeft, moveRight;
	Image world, npc;
	Rectangle player, door;
	//ReadableRectangle door;
	
	int[] duration = {200, 200};
	boolean escMenu = false;
	boolean npcTalk = false;
	boolean toggleMouseLocation = false;
	
	
	float characterPosX = 0;
	float characterPosY = 0;
	float shiftX = characterPosX + 450;
	float shiftY = characterPosY + 300;
	
	
	
	//String Variables
	String resume = "Resume (R)";
	String mainMenu = "Main Menu (M)";
	String quit = "Quit (Q)";
	
	
	
	public WorldMap(int worldmap) {
	}

	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		world = new Image("res/very basic worldMap.png");
		npc = new Image("/res/basic-down.png");
		player = new Rectangle(shiftX, shiftY, 10, 10);
		
		//initialize impassible objects
		door = new Rectangle(characterPosX + 356, characterPosY + 270, 10, 10);
		
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
		world.draw(characterPosX, characterPosY);
		npc.draw(characterPosX + 300, characterPosY + 270);
		
		
		
		
		character.draw(shiftX, shiftY);
		//player.setX((int)shiftX);
		//player.setY((int)shiftY);
		//player.setWidth((int)character.getWidth());
		//player.setHeight((int)character.getHeight());
		//g.draw(player);
		g.draw(door);
		
		
		
		g.drawString("Character X: " + characterPosX + " Character Y: " + characterPosY, 400, 20);
		
		if(npcTalk == true){
			g.drawString("Hello, player.", 400, 400);
		}
		
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
		
		player.setLocation(shiftX, shiftY);
		door.setLocation(characterPosX + 356, characterPosY + 270);
		
		//Building 1 Entrance
		/*
		if((characterPosX <= 104 && characterPosX >= 84) && (characterPosY >= 16 && characterPosY <= 40)){
			System.out.println("I'm in the door");
			sbg.enterState(2);
		}*/
		
		//npc interaction 
		if(input.isKeyPressed(Input.KEY_E)){
			if((characterPosX <= -85 && characterPosX >= -95) && (characterPosY >= 48 && characterPosY <= 58)){
				if(npcTalk != true){
					npcTalk = true;
					System.out.println("Talking" + npcTalk);
				}
				if(npcTalk == true){
					npcTalk = false;
				}
				
				//System.out.println("You're making the npc pretty uncomfortable rn..");
			}			
		}
		
		//second npc interaction attempt
		//if()s
		
		//quick collision detection test
		if(player.intersects(door)){
			if(character == moveUp){
				System.out.println("Entering Building.");
				sbg.enterState(2);
			}
			if(character == moveDown){
				characterPosY -= 5f;
			}
		}
		
		//up movement
		if(input.isKeyDown(Input.KEY_W)){
			if(characterPosY >= 300){
				characterPosY -= delta * .1f;
			}
			character = moveUp;
			characterPosY += delta * .1f;
			
		}
		
		//right movement
		if(input.isKeyDown(Input.KEY_D)){
			if(characterPosX <= -490){
				characterPosX += delta * .1f;
			}
			character = moveRight;
			characterPosX -= delta * .1f;
			
		}
		
		//down movement
		if(input.isKeyDown(Input.KEY_S)){
			if(characterPosY <= -390){
				characterPosY += delta * .1f;
			}
			character = moveDown;
			characterPosY -= delta * .1f;
			
		}
		
		//left movement
		if(input.isKeyDown(Input.KEY_A)){
			if(characterPosX >= 450){
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
		/*
		if(npcTalk == true){
			int x = 0;
			while(x <= 30000){
				x += delta;
			}
			npcTalk = false;
		}*/
		
		//Mouse location button 
		if(input.isKeyPressed(Input.KEY_M)){
			if(toggleMouseLocation == false){
				toggleMouseLocation = true;
			}else if(toggleMouseLocation == true){
				toggleMouseLocation = false;
			}
		}
		
		
		
		
	}

	
	public int getID() {
		return 1;
	}
	

}
