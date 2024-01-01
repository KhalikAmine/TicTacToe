package TicTacToe;

import java.awt.Graphics;
import java.awt.Rectangle;

public class TicTacToeCell extends Rectangle{
	private String value;
	private boolean isPlaceHolder;
	
	public TicTacToeCell(int x, int y, int height, int width){
		super(x,y,height,width);
		//this.value = "E";
		setContentToEmpty();
		isPlaceHolder = false;
	}
	public void setContentToX(){
		this.value = "x";
		isPlaceHolder = false;
	}
	public void setContentToO(){
		this.value = "o";
		isPlaceHolder = false;
	}
	public void setContentToEmpty(){
		this.value = "E";
		isPlaceHolder = false;
	}
	public void setPlaceholder() {
		this.value = " ";
		isPlaceHolder = true;
	}
	public boolean isContentX(){
		return "x".equals(this.value);
	}
	public boolean isContentO(){
		return "o".equals(this.value);
	}
	public boolean isContentEmpty(){
		return "E".equals(this.value) && !isPlaceHolder;
	}
	public boolean isPlaceholder() {
		return isPlaceHolder;
	}
	public void printContentToConsole(){
		System.out.println(this.value);
	}
	public void paintComponent(Graphics g) {
		g.drawRect(x, y, height, width);
		if (isContentX()) {
            g.drawString("x", x + 20, y + 20);
        } else if (isContentO()) {
            g.drawString("o", x + 20, y + 20);
        }
	}
	public boolean isInsideCell(int x, int y) {
		return(x >= this.x && x < this.x + this.width && y >= this.y && y < this.y + this.height);
	}
}
