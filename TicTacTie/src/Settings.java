import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Settings extends JFrame{

	final static int DEFAULT_THEME_INDEX = 0;
	JButton btn_apply;
	String schemeString = "";
	ArrayList<String> colorList = new ArrayList<String>();
	ArrayList<ColorScheme> colorSchemesList = new ArrayList<ColorScheme>();
	JPanel pan_schemes = new JPanel();
	
	Settings(){}
	
	public void loadSettingsMenu() {
		setLayout( new BorderLayout() );
		setSize(480,480);
		setTitle("Settings");
		setUndecorated(true);
		setLocationRelativeTo(null);
		//setResizable(false);
		
		loadColourSchemes("c:\\java_temp\\color_schemes.txt");
		//loadColourSchemes("color_schemes.txt");
		pan_schemes = addSchemes();

		add( addHeaderPanel(), BorderLayout.NORTH );
		add( pan_schemes, BorderLayout.CENTER );
		add( addFooterButtons(), BorderLayout.SOUTH );
		
		//setVisible(true);
	}
	
	public void loadColourSchemes(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			int byte1;
			
			do {
				byte1 = fis.read();
				if( byte1 != -1 && byte1 != 13 && byte1 != 10) {
					schemeString += (char) byte1;
				}
				if ( (char)byte1 == '\n' || (char)byte1 == '\0' ) {
					colorList.add(schemeString);
					schemeString="";
				}
			} while (byte1 != -1);
			colorList.add(schemeString);
			fis.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "" + e);
		}
	}

	JPanel addHeaderPanel() {
		JPanel pan = new JPanel();
		pan.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		pan.setSize(480,50);
		
		JLabel lbl_header = new JLabel("Choose Colour Scheme:");
		lbl_header.setFont( new Font("Tahoma", Font.BOLD, 24) );
		
		pan.add( lbl_header );
		return pan;
	}
	
	JPanel addSchemes() {
		JPanel pan = new JPanel();
		pan.setLayout( new GridLayout( colorList.size() , 1) );
		pan.setSize(300,320);

		ButtonGroup bg = new ButtonGroup();
		
		for(int i=0; i<colorList.size(); i++) {
			String[] clr = colorList.get( i ).split("=");
			String[] colours = clr[1].split(",");
			
			ColorScheme cs1 = new ColorScheme( clr[0], colours[0], colours[1] );
			cs1.setLayout( new FlowLayout( FlowLayout.LEFT ) );
			bg.add( cs1.rb );
			
			// Set Default Colour Scheme to 1st one
			if(i == DEFAULT_THEME_INDEX)
				cs1.rb.setSelected(true);
			
			colorSchemesList.add( cs1 );
			pan.add( cs1 );
		}
		return pan;
	}
	
	JPanel addFooterButtons() {
		JPanel pan = new JPanel();
		pan.setLayout( new FlowLayout() );
		pan.setSize(480,50);
		//pan.setBackground(Color.DARK_GRAY);
		
		btn_apply = new JButton("Apply & Exit");
		btn_apply.setFont( new Font("Tahoma", Font.BOLD, 24) );
		
		pan.add( btn_apply );
		return pan;
	}
	
	ColorScheme getSelectedScheme() {
		ColorScheme x = new ColorScheme();
		for(Component r : pan_schemes.getComponents()) {
			if(r instanceof ColorScheme) {
				if ( ((ColorScheme) r).rb.isSelected() ) {
					x.setColorScheme( (ColorScheme) r );
				}	
			}
		}
		return x;
	}
	
//	public static void main(String[] args) {
//		Settings set1 = new Settings();
//		set1.loadSettingsMenu(true);
//	}

}
