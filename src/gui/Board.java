package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import game.Coordinate;


public class Board extends JPanel{
		
	//Fields
	private static int layer = 0;
	private static char crossSectionKey[] = new char[]{'t', 'x', 'y'};
	
	//Mutators
	public static void setCrossSection(char visual_t, int newLayer, char visual_x, char visual_y){
		layer = newLayer;
		crossSectionKey = new char[]{visual_t, visual_x, visual_y};
	}
	
	public static void incrementCrossSection(){
		//increments the layer if the key for it is t
		if (crossSectionKey[0] == 't') layer++;
	}
	
	//Accessors
	public static char[] getCrossSectionKeys(){
		return crossSectionKey;
	}
	
	//Constructor
	public Board(){
		//Generates cross section
		Coordinate[][] crossSection = Console.game.getCrossSection(crossSectionKey[0], layer, crossSectionKey[1], crossSectionKey[2]);
		//Sets the layout to a grid layout with room for the cross section, plus a header row and column for the coordinate header squares
		super.setLayout(new GridLayout(crossSection[0].length+1, crossSection.length+1));
		//Adds a coordinate header square in the corner to  display the layer
		super.add(new CoordinateHeader(layer, crossSectionKey[0]));
		//Adds the horizontal header
		for (int i = 0; i < crossSection.length; i++) super.add(new CoordinateHeader.WallSquare(i, crossSectionKey[1]));
		//Goes line by line
		for (int i_y = 0; i_y < crossSection[0].length; i_y++){
			//Adds the vertical header square for that row
			super.add(new CoordinateHeader.WallSquare(i_y, crossSectionKey[2]));
			//Adds the squares
			for (int i_x = 0; i_x < crossSection.length; i_x++){
				super.add(new Square(i_x%2==i_y%2, crossSection[i_x][i_y]));
			}
		}

	}
}
