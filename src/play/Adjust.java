package play;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import prefer.*;

public class Adjust {

	public static void main(String[] args) {
		adjustPanel adjustment = new adjustPanel();
		adjustment.display();
	}

}

class adjustPanel extends JFrame implements ActionListener {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	private Pcolors pcol = new Pcolors();
	private TopPanel tp;
	private JPanel bottom;
	
	
	adjustPanel() {
		setLayout(new BorderLayout(0, 0));
		//상단 정보 패널 객체
		tp = new TopPanel("판매");
		tp.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(tp.top,BorderLayout.NORTH);
		
		//판매화면 패널
		bottom = new JPanel();
		bottom.setLayout(null);
		add(bottom, BorderLayout.CENTER);
		
	}
	
	void display() {
		setTitle("TinyCoffee");
		setSize(screenSize.width, screenSize.height);
		setVisible(true);
		setLocation(0, 0);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}