package play;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import prefer.*;

public class AdminMain {

	public static void main(String[] args) {
		AMain am = new AMain();
		am.display();
	}


}

class AMain extends JFrame implements ActionListener{
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	private JPanel cont, bottom;
	private TopPanel tp;
	private JLabel logo;
	private RoundPanel btnPan;
	private RoundButton shop, menu;
	private ImageIcon images, chImage;
	private Pcolors pcol = new Pcolors();
	
	public AMain() {
		setBounds(100, 100, screenSize.width, screenSize.height);
		cont = new JPanel();
		setContentPane(cont);
		cont.setLayout(new BorderLayout(0, 0));
		
		//상단 정보 패널 객체
		tp = new TopPanel("메인");
		tp.getButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cont.add(tp.top, BorderLayout.NORTH);
		
		//배경 이미지
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

		//타이틀
		logo = new JLabel("Tiny Coffee");
		logo.setForeground(Color.WHITE);
		logo.setBounds((screenSize.width/2-217), 157, 434, 93);
		logo.setFont(new Font("HY헤드라인M", Font.PLAIN, 80));
		bottom.add(logo);

		//버튼 패널
		btnPan = new RoundPanel(800,350,pcol.getPosLBrown());
		btnPan.setBounds(552, 400, 800, 350);
		btnPan.setLayout(new GridLayout(1,2,20,20));
		btnPan.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
		
		shop = new RoundButton("매장 관리");
		shop.setForeground(Color.WHITE);
		shop.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		shop.addActionListener(this);
		
		menu = new RoundButton("메뉴 관리");
		menu.setForeground(Color.WHITE);
		menu.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		menu.addActionListener(this);
		
		btnPan.add(shop);btnPan.add(menu);
		
		bottom.add(btnPan);
		
	}
	
	void display() {
		setTitle("Tiny Coffee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(0, 0);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == shop) {
			ShopInfo si = new ShopInfo();
			si.display();
			dispose();
		}
		if(e.getSource() == menu) {
			menuInfo mi = new menuInfo();
			mi.display();
			dispose();
		}
		
	}
}
