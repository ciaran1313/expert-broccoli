package gui;

import java.util.ArrayList;
import game.ChessMatrix;
import game.Coordinate;
import translator.Translator;

public class Console {
	
	//Fields
	public static ChessMatrix game;
	private static GUI gui;
	public static CUI cui;
	
	//Methods
	public static void log(String str){
		cui.appendLog(str);
	}
	
	//Private Methods
	private static void view(char visual_t, int layer, char visual_x, char visual_y){
		Board.setCrossSection(visual_t, layer, visual_x, visual_y);
	}
	
	private static void select(Coordinate location){
		game.select(location);
	}
	
	private static void deselect(){
		game.deselect();
	}
	
	private static void move(Coordinate location){
		game.move(location);
		Board.incrementCrossSection();
	}
	
	private static void move(Coordinate startPos, Coordinate endPos){
		select(startPos);
		move(endPos);
	}
	
	//Methods
	public static void run(String input, boolean doEcho){
		ArrayList<String> args = Translator.strTOargs(input);
		try{
			if (args.get(0).equals("view")) view(args.get(1).charAt(0), Integer.parseInt(args.get(2)), args.get(3).charAt(0), args.get(4).charAt(0));
			else if (args.get(0).equals("select")) select(Translator.strTOcoord(args.get(1)));
			else if (args.get(0).equals("deselect")) deselect();
			else if (args.get(0).equals("move")){
				switch (args.size()){
					case 2: move(Translator.strTOcoord(args.get(1)));
						break;
					case 3: move(Translator.strTOcoord(args.get(1)), Translator.strTOcoord(args.get(2)));
						break;
					default: throw new Exception();
				}
			}
		}catch(Exception e){
			cui.appendLog("ERROR");
		}
		gui.repaintBoard(game);
		if (doEcho) cui.appendLog(input);
	}
	
	//Constructor
	public Console(GUI gui_init){
		game = new ChessMatrix();
		cui = new CUI();
		gui = gui_init;
		
	}
}
