package translator;

import java.util.ArrayList;
import game.Coordinate;

public final class Translator {
	
	//String <-> Argument List
	public static ArrayList<String> strTOargs(String input){
		ArrayList<String> output = new ArrayList<String>();
		String next_arg = new String();
		for (int i = 0; i < input.length(); i++){
			if (input.charAt(i) == ' '){
				output.add(next_arg);
				next_arg = new String();
			}else{
				next_arg += input.charAt(i);
			}
		}
		output.add(next_arg);
		return output;
	}
	
	//Roman Numeral <-> Number
	private static final char[][] TABLE_OF_ROMAN_NUMERAL_DIGIT_CHARACTERS = new char[][]{
		{'i', 'v'},
		{'x', 'l'},
		{'c', 'd'},
		{'m'}
	};
	
	public static int romanNumeralTOint(char input) throws TranslationException{
		switch (input){
			case 'm': return 1000;
			case 'd': return 500;
			case 'c': return 100;
			case 'l': return 50;
			case 'x': return 10;
			case 'v': return 5;
			case 'i': return 1;
			default: throw new TranslationException();
		}
	}
	
	public static int romanNumeralTOint(String input) throws TranslationException{
		if (input.equals("n")) return 0;
		else{
			int output = 0, currentValue, previousValue = 0;
			//Goes through the inputed string backwards
			for (int i = input.length()-1; i >= 0; i--){
				currentValue = romanNumeralTOint(input.charAt(i));//Potential TranslationException
				output += (currentValue * (currentValue >= previousValue? 1:-1));
				previousValue = currentValue;
			}
			return output;
		}
	}
	
	public static String intTOroman(String input){
		if (input.equals("0")) return "n";
		else{
			String output = new String();
			for (int index = 0, i = input.length()-1; index < input.length(); index++, i--){
				if (input.charAt(index) == '9'){
					output += TABLE_OF_ROMAN_NUMERAL_DIGIT_CHARACTERS[i  ][0];
					output += TABLE_OF_ROMAN_NUMERAL_DIGIT_CHARACTERS[i+1][0];
				}else if (input.charAt(index) >= '5'){
					output += TABLE_OF_ROMAN_NUMERAL_DIGIT_CHARACTERS[i][1];
					for (int j = '5'; j < input.charAt(index); j++) output += TABLE_OF_ROMAN_NUMERAL_DIGIT_CHARACTERS[i][0];
				}else if (input.charAt(index) == '4'){
					output += TABLE_OF_ROMAN_NUMERAL_DIGIT_CHARACTERS[i][0];
					output += TABLE_OF_ROMAN_NUMERAL_DIGIT_CHARACTERS[i][1];
				}else if (input.charAt(index) >= '0'){
					for (int j = '0'; j < input.charAt(index); j++) output += TABLE_OF_ROMAN_NUMERAL_DIGIT_CHARACTERS[i][0];
				}
			}
			return output;
		}
	}
	
	public static String intTOroman(Integer input){
		return intTOroman(input.toString());
	}
	
	//String <-> Coordinate
	public static Coordinate strTOcoord(String input) throws TranslationException{
		return new Coordinate(
				romanNumeralTOint(input.substring(2, input.length())),
				input.charAt(0)-'a',
				input.charAt(1)-'1'
		);
	}
	
	public static String coordTOstr(Coordinate input){
		String output = new String();
		output += (char)(input.get('x') + 'a');
		output += (char)(input.get('y') + '1');
		output += intTOroman(input.get('t'));
		return output;
	}
	
	public static String intTOcoordSection(int input, char type){
		if (type == 't') return intTOroman(input);
		else return new String() + (char)(input + (type == 'x'? 'a' : '1'));
	}
}
