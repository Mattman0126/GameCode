package rpg.game.tutorial;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class MainMenu extends BasicGameState{
	
	Rectangle one, two, three, four, five;

	public MainMenu(int startmenu){
		
	}
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Play", 500, 400);
		g.drawString("Exit", 500, 500);
		
		one = new Rectangle(50, 640, 16, 16); //16x16 pixels
		g.draw(one);
		
		g.drawString("16x16", 50, 660);
		
		two = new Rectangle(50, 580, 32, 32);
		g.draw(two);
		g.drawString("32x32", 50, 610);
		
		three = new Rectangle(50, 500, 32, 45);
		g.draw(three);
		g.drawString("32x45", 50, 545);
		
		four = new Rectangle(50, 410, 64, 64);
		g.draw(four);
		g.drawString("64x64", 50, 475);
		
		five = new Rectangle(50, 300, 48, 64);
		g.draw(five);
		g.drawString("48x64", 50, 370);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		one = new Rectangle(50, 100, 16, 16); //16x16 pixels
	
		
		System.out.println("X: " + posX + " Y:" + posY);
		//play button
		if((posX > 500 && posX < 541) && (posY > 278 && posY < 300)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}
		
		if((posX > 500 && posX < 541) && (posY > 180 && posY < 195)){
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}
	}

	
	public int getID() {
		return 0;
	}

}
