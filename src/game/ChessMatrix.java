package game;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;

import pieces.*;

public class ChessMatrix extends HashMap<Coordinate, SquareContents>{
	
	//Subclasses
	public static enum MoveSuccessCode{LEGAL, ILLEGAL};
	
	//Fields
	private ArrayList<Coordinate> filledSquares;
	private int turn_number;
	private Coordinate selected;
	
	//Mutators
	public MoveSuccessCode select(Coordinate c){
		this.selected = c;
		return MoveSuccessCode.LEGAL;
	}
	
	public void deselect(){
		this.selected = null;
	}
	
	public void move(Coordinate endPos){
		//Gets the path for the move
		Queue<Coordinate> path_queue = super.get(this.getSelectedSquare()).calculatePath(this.getSelectedSquare(), endPos);
		//If it returned null (aka it was illegal), end this function and reply with a notification of the illegality of the move
		if (path_queue == null);
		//Otherwise...
		else{
			//Sets up a variable to store the previous positions for the path squares, initializing it as the start position
			Coordinate previousSegment = this.getSelectedSquare();
			//Goes through the path stack
			while(path_queue.peek() != null){
				//Gets the previous segment to make a new path segment on the next location in the path stack
				super.get(previousSegment).makePathSegment(path_queue.peek(), this, this.filledSquares);
				//Sets the "previous segment" to be the segment that was just made
				previousSegment = path_queue.poll();
				//This makes a set of path segments each with a previous position set to the location of the previous segment
			}
			//Ages all the pieces
			this.agePieces();
			//Increments the turn count
			this.turn_number++;
		}
		this.deselect();
	}
	
	private void agePieces(){
		//Sets up a temporary container to hold the resulting filled squares from this function
		ArrayList<Coordinate> new_filledSquares = new ArrayList<Coordinate>();
		//Goes through the filled squares and 
		for (Coordinate nextFilledSquare : this.filledSquares){
			//So long as the piece at the filled square has not moved yet...
			if (super.get(nextFilledSquare).getNextPosition() == null){
				//Determines the coordinate for the pieces location one square in the future (t'=t+1, x'=x, y'= y)
				final Coordinate FUTURE_POSITION = new Coordinate(nextFilledSquare.get('t')+1, nextFilledSquare.get('x'), nextFilledSquare.get('y'));
				//Copies contents to 1 square to the future
				super.get(nextFilledSquare).reproduce(FUTURE_POSITION, this, new_filledSquares);;
			}
		}
		//Copies the contents of temporary container to the permanent one now that it is empty
		this.filledSquares = new_filledSquares;
	}
	
	//Accessors
	public Coordinate getSelectedSquare(){
		return this.selected;
	}
	
	public int getLengthOfDimention(char key){
		if (key == 't') return this.turn_number;
		else return 8;
	}
	
	public Coordinate[][] getCrossSection(char visual_t, int layer, char visual_x, char visual_y){
		Coordinate result[][] = new Coordinate[getLengthOfDimention(visual_x)][getLengthOfDimention(visual_y)];
		for (int i = 0; i < result.length; i++){
			for (int j  = 0; j < result[i].length; j++){
				result[i][j] = new Coordinate(visual_t, visual_x, visual_y, layer, i, j);
			}
		}
		return result;
	}
	
	//Constructor
	public ChessMatrix(){
		this.filledSquares = new ArrayList<Coordinate>();
		this.turn_number = 1;
		this.make_royal_line(Piece.Color.WHITE, 0);
		this.make_pawn_line(Piece.Color.WHITE, 1);
		this.make_royal_line(Piece.Color.BLACK, 7);
		this.make_pawn_line(Piece.Color.BLACK, 6);
	}
	
	//Constructor Methods
	private void make_pawn_line(Piece.Color color, int y){
		for (int i_x = 0; i_x < 8; i_x++){
			new Piece(new Piece.Type.Pawn(), i_x, y, color, this, this.filledSquares);
		}
	}
	
	private void make_royal_line(Piece.Color color, int y){
		new Piece(new Piece.Type.Rook(), 	0, y, color, this, this.filledSquares);
		new Piece(new Piece.Type.Knight(), 	1, y, color, this, this.filledSquares);
		new Piece(new Piece.Type.Bishop(), 	2, y, color, this, this.filledSquares);
		new Piece(new Piece.Type.King(), 	3, y, color, this, this.filledSquares);
		new Piece(new Piece.Type.Queen(), 	4, y, color, this, this.filledSquares);
		new Piece(new Piece.Type.Bishop(), 	5, y, color, this, this.filledSquares);
		new Piece(new Piece.Type.Knight(), 	6, y, color, this, this.filledSquares);
		new Piece(new Piece.Type.Rook(), 	7, y, color, this, this.filledSquares);
	}
}
