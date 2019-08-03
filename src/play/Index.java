package play;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.GregorianCalendar;

import prefer.*;

public class Index {

	public static void main(String[] args) {
		IndexMain im = new IndexMain();
		im.display();
	}

}

class IndexMain extends JFrame implements ActionListener{
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	private JPanel cont, bottom;
	private TopPanel tp;
	private JLabel logo;
	private RoundPanel btnPan;
	private RoundButton mart, sale, calc, exit;
	private ImageIcon images, chImage;
	private Pcolors pcol = new Pcolors();

	public IndexMain() {
		setBounds(100, 100, screenSize.width, screenSize.height);
		cont = new JPanel();
		setContentPane(cont);
		cont.setLayout(new BorderLayout(0, 0));

		tp = new TopPanel("메인");
		cont.add(tp.top, BorderLayout.NORTH);

		images = new ImageIcon("back.jpg");
		Image img = images.getImage();
		Image chimg = img.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
		chImage = new ImageIcon(chimg);
		
		bottom = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(chImage.getImage(),0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		cont.add(bottom, BorderLayout.CENTER);
		bottom.setLayout(null);

		logo = new JLabel("Tiny Coffee");
		logo.setForeground(new Color(255, 255, 255));
		logo.setBounds((screenSize.width/2-217), 157, 434, 93);
		logo.setFont(new Font("HY헤드라인M", Font.PLAIN, 80));
		bottom.add(logo);

		btnPan = new RoundPanel(800,500,pcol.getPosLBrown());
		btnPan.setBounds(552, 330, 800, 500);
		btnPan.setLayout(new GridLayout(2,2,20,20));
		btnPan.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		
		
		mart = new RoundButton("매장");
		mart.setForeground(Color.WHITE);
		mart.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		mart.addActionListener(this);
		sale = new RoundButton("판매");
		sale.setForeground(Color.WHITE);
		sale.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		sale.addActionListener(this);
		calc = new RoundButton("정산");
		calc.setForeground(Color.WHITE);
		calc.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		exit = new RoundButton("종료");
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		exit.addActionListener(this);
		
		btnPan.add(mart);btnPan.add(sale);btnPan.add(calc);btnPan.add(exit);

		bottom.add(btnPan);
	}
	
	void display() {
		setTitle("Tiny Coffee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(0, 0);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==mart) {
			AMain am = new AMain();
			am.display();
		}
		if(e.getSource()==sale) {
			SalePanel sp = new SalePanel();
			sp.display();
		}
		if(e.getSource() == exit) {
			System.exit(0);
		}
		
	}
	
}