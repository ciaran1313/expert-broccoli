package pieces;

import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import game.ChessMatrix;
import game.Coordinate;

public abstract class SquareContents {
	
	//Fields
	private final Piece.Color COLOR;
	private final Coordinate PREVIOUS_POSITION, CURRENT_POSITION;
	private Coordinate nextPosition;
	
	//Abstract Methods
	abstract public boolean isPath();
	abstract public char getTypeChar();
	abstract public void reproduce(Coordinate newPosition, ChessMatrix board, ArrayList<Coordinate> filledSquareList);

	//Accessors
	public final Piece.Color getColor(){
		return this.COLOR;
	}
	
	public final Coordinate getPreviousPosition(){
		return this.PREVIOUS_POSITION;
	}
	
	public final Coordinate getCurrentPosition(){
		return this.CURRENT_POSITION;
	}
	
	public final Coordinate getNextPosition(){
		return this.nextPosition;
	}
	
	//Mutators
	protected final void ifndef_setNextPosition(Coordinate nextPosition_init){
		if (this.nextPosition == null) this.nextPosition = nextPosition_init;
	}
	
	//Methods
	public final Queue<Coordinate> calculatePath(Coordinate startPos, Coordinate endPos){
		Queue<Coordinate> result = new ArrayDeque<Coordinate>();
		result.offer(endPos);
		return result;
	}
	
	public final Path makePathSegment(Coordinate pathPosition, ChessMatrix board, ArrayList<Coordinate> filledSquareList){
		//Sets the next position to be the position of the path segment
		this.nextPosition = pathPosition;
		//Makes a path segment whose previous position is that of this
		return new Path(this, pathPosition, board, filledSquareList);
	}
	
	//Constructors
	protected SquareContents(Piece.Color color_init, Coordinate previousPosition_init, Coordinate currentPosition_init, ChessMatrix board, ArrayList<Coordinate> filledSquareList){
		this.COLOR = color_init;
		this.PREVIOUS_POSITION = previousPosition_init;
		this.CURRENT_POSITION = currentPosition_init;
		//Sets the next position as null (aka to be determined)
		this.nextPosition = null;
		//Adds itself to the board
		board.put(currentPosition_init, this);
		filledSquareList.add(this.CURRENT_POSITION);
	}
}
