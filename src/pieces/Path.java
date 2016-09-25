package pieces;

import java.util.ArrayList;

import game.ChessMatrix;
import game.Coordinate;

public class Path extends SquareContents {
	
	//Fields
	private final char typeChar;
	
	//Accessors
	@Override
	public final char getTypeChar(){
		return this.typeChar;
	}
	
	@Override
	public final boolean isPath(){
		return true;
	}
	
	//Methods
	@Override
	public void reproduce(Coordinate newPosition, ChessMatrix board, ArrayList<Coordinate> filledSquareList){
		//sets the next position if it's undefined
		super.ifndef_setNextPosition(newPosition);
		//Tells it's past self to reproduce at the given square. This will run recursively until the past self is nolonger a path
		board.get(this.getPreviousPosition()).reproduce(newPosition, board, filledSquareList);
	}
	
	//Constructor
	protected Path(SquareContents parent, Coordinate currentPosition_init, ChessMatrix board, ArrayList<Coordinate> filledSquareList){
		super(parent.getColor(), parent.getCurrentPosition(), currentPosition_init, board, filledSquareList);
		this.typeChar = parent.getTypeChar();
	}
}
