import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class XOBox extends JPanel implements ActionListener{
	
	private int BOX_SIZE = 100;
	private boolean picked;
	private JButton box;
	
	XOBox(){
		setLayout(new FlowLayout());
		box = new JButton("");
		box.setPreferredSize(new Dimension(BOX_SIZE, BOX_SIZE));
		box.setBackground(Color.WHITE);
		box.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 50));
		add(box);
		setPicked(true);
	}
	
	public void setBoxValue(String val) { box.setText(val);	}
	
	public String getBoxValue() { return box.getText();	}

	public boolean isPicked() { return picked; }

	public void setPicked(boolean isPicked) { this.picked = isPicked; }
	
	public void actionPerformed(ActionEvent e) {}
	
	public void setListener(ActionListener ac) { this.box.addActionListener(ac); }
	
	public JButton getButton() { return this.box; }
	
}
