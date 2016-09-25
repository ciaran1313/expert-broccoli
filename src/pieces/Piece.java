package pieces;

import java.util.ArrayList;
import game.ChessMatrix;
import game.Coordinate;

public class Piece extends SquareContents{
	
	//Subclasses
	public static enum Color{WHITE, BLACK};
	public static interface Type{
		public char getChar();
		public final static class King implements Type{
			@Override
			public final char getChar(){
				return 'K';
			}
		}
		public final static class Queen implements Type{
			@Override
			public final char getChar(){
				return 'Q';
			}
		}
		public final static class Bishop implements Type{
			@Override
			public final char getChar(){
				return 'B';
			}
		}
		public final static class Knight implements Type{
			@Override
			public final char getChar(){
				return 'N';
			}
		}
		public final static class Rook implements Type{
			@Override
			public final char getChar(){
				return 'R';
			}
		}
		public final static class Pawn implements Type{
			@Override
			public final char getChar(){
				return 'p';
			}
		}
	}
	
	//Fields
	protected final Piece.Type TYPE;
	
	//Accessors
	@Override
	public final char getTypeChar(){
		return this.TYPE.getChar();
	}
	
	@Override
	public final boolean isPath(){
		return false;
	}
	
	//Methods
	@Override
	public final void reproduce(Coordinate newPosition, ChessMatrix board, ArrayList<Coordinate> filledSquareList){
		//sets the next position if it's undefined
		super.ifndef_setNextPosition(newPosition);
		//puts a copy of itself on the given square
		new Piece(this, newPosition, board, filledSquareList);
	}
	
	//Constructors
	public Piece(Piece.Type type_init, int x, int y, Piece.Color color_init, ChessMatrix board, ArrayList<Coordinate> filledSquareList){
		super(color_init, null, new Coordinate(0, x, y), board, filledSquareList);
		this.TYPE = type_init;
	}
	
	private Piece(Piece parent, Coordinate position, ChessMatrix board, ArrayList<Coordinate> filledSquareList){
		super(parent.getColor(), parent.getCurrentPosition(), position, board, filledSquareList);
		this.TYPE = parent.TYPE;
	}
}
