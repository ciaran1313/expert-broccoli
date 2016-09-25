package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import game.Coordinate;
import translator.Translator;

public class Square extends JTextField implements MouseListener{
	
	//Fields
	private final Coordinate location;
	private static final String[][] TABLE_OF_UNICODE_PIECE_SYMBOLS = new String[][]{
		{"♙", "♟"},
		{"♖", "♜"},
		{"♘", "♞"},
		{"♗", "♝"},
		{"♕", "♛"},
		{"♔", "♚"},
	};
	
	//Event Listeners
	@Override
	public void mouseClicked(MouseEvent e){
		
		
		/*
		String command;
		boolean doArgs;
		
		if (Console.game.getSelectedSquare() == null){
			command = "move";
			doArgs = true;
		}else{
			if (Console.game.getSelected().)
		}
		*/
		
		
		//If there is nothing selected, it selects that square, otherwise, it moves the selected piece to the square
		Console.run(((Console.game.getSelectedSquare() == null? "select":"move") + " " + Translator.coordTOstr(this.location)), true);
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e){
		
	}
	
	@Override
	public void mouseExited(MouseEvent e){
		
	}
	
	//Mutators
	@Override
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x, y, width, height);
		//Bases the font size on the smaller of either the width or the height
		super.setFont(new Font("Arial Unicode MS", 0, (width<height ? width:height)/2));
	}
	
	//Constructor
	public Square(boolean isWhite, Coordinate location_init){
		this.location = location_init;
		//Centers the symbols
		super.setHorizontalAlignment(CENTER);
		//Disables it from being used as a text area
		super.setEnabled(false);
		//Sets the background as either light or dark
		super.setBackground(this.location.equals(Console.game.getSelectedSquare())? Color.RED : (isWhite? Color.WHITE : Color.GREEN));
		try{
			//Allocates place holders
			int type_row, color_column;
			//Finds the square's type and saves the row of that type
			switch (Console.game.get(this.location).getTypeChar()){
				case 'p':type_row = 0;
					break;
				case 'R':type_row = 1;
					break;
				case 'N':type_row = 2;
					break;
				case 'B':type_row = 3;
					break;
				case 'Q':type_row = 4;
					break;
				case 'K':type_row = 5;
					break;
				default: throw new Exception();
			}
			//Finds the color and saves the column of that color
			switch (Console.game.get(this.location).getColor()){
				case WHITE:color_column = 0;
					break;
				case BLACK:color_column = 1;
					break;
				default: throw new Exception();
			}
			//Sets the symbol to be that of the row and column in the table of unicode piece symbols
			super.setText(TABLE_OF_UNICODE_PIECE_SYMBOLS [type_row] [color_column]);
			//Greys-out pieces that are paths 
			if (Console.game.get(this.location).isPath()) super.setDisabledTextColor(super.getBackground().darker());
			else if (Console.game.get(this.location).getNextPosition() != null) super.setDisabledTextColor(Color.GRAY);
			else super.setDisabledTextColor(Color.BLACK);
		}catch (NullPointerException e){} catch (Exception e){};
		super.addMouseListener(this);
	}
}