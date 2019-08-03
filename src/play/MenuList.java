package play;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import prefer.*;

public class MenuList {

	public static void main(String[] args) {
		menuInfo mi = new menuInfo();
		mi.display();
	}

}

class menuInfo extends JFrame {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	private TopPanel tp;
	private JPanel bottom, LPan, RPan;
	private JLabel logo;
	private RoundPanel roundPan;
	private Pcolors pcol = new Pcolors();
	private ImageIcon images, chImage;
	private JPanel mPan1, mPan2;
	private ColorButton[] mtBtn;
	private JButton[] mbBtn;
	private String[] mTit = {"커피","음료","아이스크림","디저트","MD"};
	private ColorButton newBtn, upBtn, delBtn;
	
	public menuInfo() {
		setLayout(new BorderLayout(0, 0));
		//상단 정보 패널 객체
		tp = new TopPanel("매장");
		tp.getButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(tp.top,BorderLayout.NORTH);
		
		images = new ImageIcon("back.jpg");
		Image img = images.getImage();
		Image chimg = img.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
		chImage = new ImageIcon(chimg);
		
		//하단 메인 패널
		bottom = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(chImage.getImage(),0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		bottom.setLayout(null);
		add(bottom, BorderLayout.CENTER);
		
		//타이틀
		logo = new JLabel("Tiny Coffee");
		logo.setForeground(Color.WHITE);
		logo.setBounds((screenSize.width/2-217), 30, 434, 93);
		logo.setFont(new Font("HY헤드라인M", Font.PLAIN, 80));
		bottom.add(logo);
		
		//내용 패널
		roundPan = new RoundPanel(1200,730,Color.WHITE);
		roundPan.setBounds((screenSize.width/2-600), 153, 1200, 730);
		roundPan.setLayout(new BorderLayout(0,0));
		roundPan.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		LPan = new JPanel(new BorderLayout(0,0));
		LPan.setBounds(0, 0, (roundPan.getWidth()/5)*4, roundPan.getHeight());
		LPan.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
		roundPan.add(LPan, BorderLayout.CENTER);
		
		mPan1 = new JPanel(new GridLayout(1, 5, 1, 0));
		mPan1.setPreferredSize(new Dimension(LPan.getWidth(), LPan.getHeight()/5));
		mPan1.setBackground(Color.WHITE);
		mPan1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.DARK_GRAY));
		LPan.add(mPan1,BorderLayout.NORTH);
		
		//분류 버튼
		mtBtn = new ColorButton[5];
		for(int i=0; i<mtBtn.length; i++) {
			mtBtn[i] = new ColorButton(mTit[i],pcol.getPosLBrown());
			mtBtn[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			mtBtn[i].setBackground(pcol.getPosLBrown());
			mtBtn[i].setForeground(Color.WHITE);
			//mtBtn[i].addMouseListener(this);
			mPan1.add(mtBtn[i]);
		}
		//mtBtn[1].setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.DARK_GRAY));
		//mtBtn[3].setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.DARK_GRAY));
		
		
		
		//메뉴 리스트
		mPan2= new JPanel(new GridLayout(4, 5));
		mPan2.setBackground(Color.white);
		mPan2.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.DARK_GRAY));
		
		String[] mName = {"<html>아메리카노 (HOT)<br>2500원</html>","<html>아메리카노 (ICE)<br>2500원</html>",
				"<html>카페라떼 (HOT)<br>3000원</html>","<html>카페라떼 (ICE)<br>3000원</html>","",
				"<html>카페모카 (HOT)<br>3500원</html>","<html>카페모카 (ICE)<br>3500원</html>",
				"<html>바닐라라떼 (HOT)<br>3500원</html>","<html>바닐라라떼 (ICE)<br>3500원</html>",""};
		mbBtn = new JButton[20];
		for(int i=0; i<10; i++) {
			mbBtn[i] = new JButton(mName[i]);
			mbBtn[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			mbBtn[i].setBackground(Color.WHITE);
			mbBtn[i].updateUI();
			mbBtn[i].setForeground(Color.BLACK);
			mbBtn[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.DARK_GRAY));
			//mbBtn[i].addMouseListener(this);
			mPan2.add(mbBtn[i]);
		}
		for(int i=10; i<20; i++) {
			mbBtn[i] = new JButton("");
			mbBtn[i].setBackground(Color.WHITE);
			mbBtn[i].setForeground(Color.BLACK);
			mbBtn[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.DARK_GRAY));
			//mbBtn[i].addMouseListener(this);
			mPan2.add(mbBtn[i]);
		}
		mbBtn[19].setText("<html>샷 추가<br>500원</html>");
		mbBtn[19].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		LPan.add(mPan2,BorderLayout.CENTER);
		
		
		//제어 버튼 패널
		RPan = new JPanel(null);
		RPan.setPreferredSize(new Dimension(roundPan.getWidth()/5, roundPan.getHeight()));
		RPan.setBackground(Color.WHITE);
		
		newBtn = new ColorButton("메뉴추가", pcol.getPosDBrown());
		newBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		newBtn.setForeground(Color.WHITE);
		newBtn.setBackground(pcol.getPosDBrown());
		newBtn.setBounds(20,0,roundPan.getWidth()/5-20,100);
		//newBtn.addActionListener(this);
		
		upBtn = new ColorButton("메뉴수정", pcol.getPosDBrown());
		upBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		upBtn.setForeground(Color.WHITE);
		upBtn.setBackground(pcol.getPosDBrown());
		upBtn.setBounds(20,110,roundPan.getWidth()/5-20,100);
		//upBtn.addActionListener(this);
		
		delBtn = new ColorButton("메뉴삭제", pcol.getPosGray());
		delBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		delBtn.setForeground(Color.WHITE);
		delBtn.setBackground(pcol.getPosGray());
		delBtn.setBounds(20,220,roundPan.getWidth()/5-20,100);
		//delBtn.addActionListener(this);
		
		RPan.add(newBtn);RPan.add(upBtn);RPan.add(delBtn);
		
		roundPan.add(RPan, BorderLayout.EAST);
		
		bottom.add(roundPan);
	}
	
	void display() {
		setTitle("TinyCoffee");
		setSize(screenSize.width, screenSize.height);
		setVisible(true);
		setLocation(0, 0);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}