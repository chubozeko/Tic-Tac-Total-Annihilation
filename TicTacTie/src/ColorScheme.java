import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ColorScheme extends JPanel{

	JRadioButton 	rb = new JRadioButton();
	private JPanel 	box_colorX = new JPanel();
	private JPanel 	box_colorO = new JPanel();
	private String 	colorX;
	private String 	colorO;
	Font font;
	
	ColorScheme(){}
	
	ColorScheme( String colorName, String colorX, String colorO ){
		this.setLayout( new FlowLayout() );
		
		font = new Font("Tahoma", Font.PLAIN, 18);
		rb.setText(colorName);
		rb.setFont(font);
		
		box_colorX.setSize(100, 100);
		box_colorO.setSize(100, 100);
		
		this.colorX = colorX;
		this.colorO = colorO;
		
		box_colorX.setBackground( Color.decode( this.colorX ) );
		box_colorO.setBackground( Color.decode( this.colorO ) );
		
		add( rb );
		add( box_colorX );
		add( box_colorO );
	}
	
	public void setRadioText(String rText) { this.rb.setText(rText); }
	
	public String getRadioText() { return this.rb.getText(); }
	
	public void setColorX(String colorX) { this.colorX = colorX; }
	
	public String getColorX() { return this.colorX;	}
	
	public void setColorO(String colorO) { this.colorO = colorO; }
	
	public String getColorO() {	return this.colorO;	}
	
	public void setColorScheme(ColorScheme colorScheme) {
		this.colorX = colorScheme.getColorX();
		this.colorO = colorScheme.getColorO();
		this.rb.setText( colorScheme.getRadioText() );
	}
	
}
