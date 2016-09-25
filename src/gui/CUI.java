package gui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.KeyStroke;

public class CUI extends JPanel implements CaretListener, KeyListener{
	
	//Fields
	private JTextArea log;
	private int inputBegin;
	
	//Event Listeners
	@Override
	public void caretUpdate(CaretEvent event){
		this.log.setEditable(event.getDot() >= inputBegin);
	}
	
	@Override
	public void keyTyped(KeyEvent event){
		switch (event.getKeyChar()){
			case '\n':
				//Local command "clear"
				if (this.log.getText().substring(inputBegin, this.log.getText().length()).equals("clear")){
					this.log.setText("");
					this.log.setEditable(true);
					this.inputBegin = 0;
				}
				//Local command "help"
				else if ((this.log.getText().substring(inputBegin, this.log.getText().length()).equals("help"))){
					this.log.append("\nYOUVE BEEN HELPED!\n");
				}
				else{
					Console.run(this.log.getText().substring(this.inputBegin, this.log.getText().length()), false);
					this.log.append("\n");
				}
				this.inputBegin = this.log.getText().length();
				break;
			//Disables backspace from deleting \n characters
			case '\b':
				try{
					if (!log.getText().endsWith("\n")) log.getDocument().remove(log.getDocument().getLength()-1, 1);
				}catch(BadLocationException e){}
				break;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		
	}
	
	//Mutators
	public void appendLog(String str){
		//puts the string on the last line of the console. If the last line has text in it, it makes a new line and puts it there
		this.log.append((this.log.getText().length() != 0 && this.log.getText().charAt(this.log.getText().length()-1) != '\n'? "\n":"") + str + "\n");
	}
	
	//Constructors
	public CUI(){
		this.log = new JTextArea();
		this.log.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
		this.log.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
		this.log.addCaretListener(this);
		this.log.addKeyListener(this);
		this.inputBegin = 0;
		super.setLayout(new GridLayout(1, 1));
		super.add(new JScrollPane(log));

	}

}
