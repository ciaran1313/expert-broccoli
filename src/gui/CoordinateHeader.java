package gui;

import javax.swing.JTextField;
import translator.Translator;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;


public class CoordinateHeader extends JTextField implements MouseListener{
	
	
	public static class WallSquare extends CoordinateHeader{
		
		//Fields
		private final int NUMBER;
		private final char KEY;
		
		//Event Listeners
		@Override
		public void mouseClicked(MouseEvent e){
			char crossSectionKey[] = Board.getCrossSectionKeys();
			int i = 0;
			while (true){
				if (this.KEY == crossSectionKey[i]) break;
				else if (i++>=2) return;
			}
			Console.run(String.format("view %c %d %c %c", crossSectionKey[i], NUMBER, crossSectionKey[(i+1)%3], crossSectionKey[(i+2)%3]), true);
		}
		
		//Constructor
		public WallSquare(int number_init, char key_init){
			super(number_init, key_init);
			this.NUMBER = number_init;
			this.KEY = key_init;
		}
	}
	
	
	
	//Event Listeners
	@Override
	public void mouseClicked(MouseEvent e){
		
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
	
	//Constructors
	public CoordinateHeader(int number_init, char key_init){
		super(Translator.intTOcoordSection(number_init, key_init));
		super.setDisabledTextColor(Color.WHITE);
		super.setHorizontalAlignment(CENTER);
		super.setEnabled(false);
		super.setBackground(Color.BLACK);
		super.setDisabledTextColor(Color.WHITE);
		super.addMouseListener(this);
	}
}
