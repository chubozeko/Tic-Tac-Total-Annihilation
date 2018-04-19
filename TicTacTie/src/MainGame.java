import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainGame extends JFrame implements ActionListener{

	public JPanel game;
	public JMenuBar menubar = new JMenuBar();
	public JMenu menu = new JMenu("Menu");
	public JMenuItem mi_NewGame, mi_Settings, mi_Help, mi_Exit;
	public int gameStart;
	public int boxCount = 0;
	public String winner;
	public ColorScheme cs = new ColorScheme();
	public Settings set1;
	
	XOBox box_A1, box_A2, box_A3, box_B1, box_B2, box_B3, box_C1, box_C2, box_C3;
	
	MainGame(){
		setLayout(new FlowLayout());
		setSize(400,400);
		setResizable(false);
		setTitle("TIC TAC TOTAL ANNIHILATION");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		setBackground(Color.WHITE);
		
		// MENU BAR
		setJMenuBar(menubar);
		menubar.add( menu );
		mi_NewGame = new JMenuItem("New Game");			menu.add( mi_NewGame );
		mi_Settings = new JMenuItem("Settings");		menu.add( mi_Settings );
		mi_Help = new JMenuItem("Help");				menu.add( mi_Help );
		mi_Exit = new JMenuItem("Exit");				menu.add( mi_Exit );
		
		mi_NewGame.addActionListener(this);
		mi_Settings.addActionListener(this);
		mi_Help.addActionListener(this);
		mi_Exit.addActionListener(this);
		
		game = loadGame();
		add( game );		pack();
	
		// Make the game randomly start with 'X' or 'O'
		Random s = new Random();
		gameStart = s.nextInt(2);
		
		// Get the Default Colour Scheme
		set1 = new Settings();
		set1.loadSettingsMenu();
		set1.btn_apply.addActionListener(this);
		this.cs.setColorScheme( this.set1.getSelectedScheme() );
	}
	
	JPanel loadGame() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(3, 3));
		pan.setSize(400, 400);
		
		box_A1 = new XOBox();		box_A2 = new XOBox();		box_A3 = new XOBox();
		box_B1 = new XOBox();		box_B2 = new XOBox();		box_B3 = new XOBox();
		box_C1 = new XOBox();		box_C2 = new XOBox();		box_C3 = new XOBox();
		
		pan.add( box_A1 );	pan.add( box_A2 );	pan.add( box_A3 );
		pan.add( box_B1 );	pan.add( box_B2 );	pan.add( box_B3 );
		pan.add( box_C1 );	pan.add( box_C2 );	pan.add( box_C3 );
		
		for(Component b : pan.getComponents()) {
			if(b instanceof XOBox) {
				((XOBox) b).getButton().addActionListener(this);
			}
		}
		
		return pan;
	}
	
	public void actionPerformed(ActionEvent event) {	
		Object obj = event.getSource();
		if( obj instanceof JButton) {
			if( obj != set1.btn_apply ) {
				JButton box = (JButton) event.getSource();
				if(box.isEnabled()) {
					box.setEnabled(false);
					
					if(gameStart % 2 == 0) {
						box.setText("X");
						box.setBackground( Color.decode(this.cs.getColorX()) );
					} else {
						box.setText("O");
						box.setBackground( Color.decode(this.cs.getColorO()) );
					}
					gameStart++;	boxCount++;
				}
				checkForWinner();
			}
		}
		
		if( obj instanceof JMenuItem) {
			JMenuItem item = (JMenuItem) obj;
			if (item == mi_NewGame) {
				Random s = new Random();
				gameStart = s.nextInt(2);
				
				boxCount = 0;
				winner = "";
				
				remove(game);
				game = loadGame();
				add(game);	pack();
			}
			if (item == mi_Settings) {
				set1.setVisible(true);
			}
			if (item == mi_Help) {
				
			}
			if (item == mi_Exit) {
				this.dispose();
			}
		}
		
		if( obj instanceof JButton) {
			if( obj == set1.btn_apply ) {
				this.cs = new ColorScheme();
				this.cs.setColorScheme( this.set1.getSelectedScheme() );
				set1.dispose();
				JOptionPane.showMessageDialog(null, "Color Scheme changed to " 
											+ this.cs.getRadioText()
											+ "\nSelect 'New Game' to view changes.");
			}
		}
	}
	
	public void checkForWinner() {
		if( checkLine(box_A1, box_A2, box_A3) || checkLine(box_B1, box_B2, box_B3) || 
			checkLine(box_C1, box_C2, box_C3) || checkLine(box_A1, box_B1, box_C1) || 
			checkLine(box_A2, box_B2, box_C2) || checkLine(box_A3, box_B3, box_C3) || 
			checkLine(box_A1, box_B2, box_C3) || checkLine(box_A3, box_B2, box_C1) )
			JOptionPane.showMessageDialog(null, "GAME OVER! \n\n" + winner + " WINS!" );
		else if( boxCount >= 9 )
			JOptionPane.showMessageDialog(null, "GAME OVER! \n\nIT'S A DRAW!" );
	}
	
	public boolean checkLine(XOBox a, XOBox b, XOBox c) {
		if( (a.getBoxValue() == b.getBoxValue()) && (a.getBoxValue() == c.getBoxValue()) 
				&& a.getBoxValue() != "" ) {
			winner = a.getBoxValue();
			return true; 
		} else
			return false;
	}
	
	public static void main(String[] args) { new MainGame(); }

}
