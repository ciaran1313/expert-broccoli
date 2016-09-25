package gui;

import javax.swing.JApplet;
import javax.swing.JComponent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import game.ChessMatrix;

public class GUI extends JApplet implements ComponentListener{
	
	//Fields
	private JComponent board, controlPanel;
	
	
	//Event Listeners
	@Override
	public void componentResized(ComponentEvent e){
		try{
			board.setBounds(0, 0, super.getWidth(), (int)(0.8*super.getHeight()));
			controlPanel.setBounds(0, (int)(0.8*super.getHeight()), super.getWidth(), (int)(0.2*super.getHeight()));
		}catch (NullPointerException npe){}
	}
	
	@Override
	public void componentMoved(ComponentEvent e){
		
	}
	
	@Override
	public void componentShown(ComponentEvent e){
		
	}
	
	@Override
	public void componentHidden(ComponentEvent e){
		
	}
	
	//Mutators
	public void repaintBoard(ChessMatrix game){
		try{
			super.remove(this.board);
		}catch (NullPointerException e){}
		this.board = new Board();
		super.add(this.board);
		this.componentResized(null);
		super.paintComponents(this.getGraphics());	
	}
	
	//Constructors
	@Override
	public void init(){
		new Console(this);
		super.setLayout(null);
		super.addComponentListener(this);
		controlPanel = Console.cui;
		super.add(controlPanel);
		this.repaintBoard(Console.game);	
	}
}
