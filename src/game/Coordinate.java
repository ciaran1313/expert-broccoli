package game;

import java.util.HashMap;

public class Coordinate extends HashMap<Character, Integer>{
	
	//Fields
	public static final char[] ENUMERATED = {'t','x','y'};
	
	//Constructors
	public Coordinate(int t, int x, int y){
		this('t', 'x', 'y', t, x, y);
	}
	
	public Coordinate(char d1, char d2, char d3, int v1, int v2, int v3){
		//Makes a hash map with capacity three
		super(3);
		//Puts the numbers in
		super.put(d1, v1);
		super.put(d2, v2);
		super.put(d3, v3);
	}
}
