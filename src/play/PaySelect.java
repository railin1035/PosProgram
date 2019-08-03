package play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import prefer.*;

public class PaySelect {

	public static void main(String[] args) {
		payPanel pp = new payPanel();
		pp.display();
	}

}

class payPanel extends JFrame implements ActionListener{
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	private TopPanel tp;
	private JPanel bottom;
	private RoundPanel btnPan;
	private RoundButton cashBtn, creditBtn, pointBtn;
	private Pcolors pcol = new Pcolors();
	private int cartId;
	public payPanel() {}
	public payPanel(int cartId) {
		setLayout(new BorderLayout(0, 0));
		this.cartId = cartId;
		//상단 정보 패널 객체
		tp = new TopPanel("결제");
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
		bottom.setBackground(pcol.getPosLBrown());
		add(bottom, BorderLayout.CENTER);
		//버튼 패널
		btnPan = new RoundPanel(1400,700,Color.white);
		btnPan.setBounds((screenSize.width/2)-700, 140, 1400, 700);
		btnPan.setLayout(new GridLayout(1,3,20,20));
		btnPan.setBorder(BorderFactory.createEmptyBorder(70, 90, 70, 90));
		
		pointBtn = new RoundButton("포인트 결제");
		pointBtn.setHorizontalAlignment(SwingConstants.CENTER);
		pointBtn.setForeground(Color.WHITE);
		pointBtn.setFont(new Font("맑은 고딕", Font.BOLD, 60));
		pointBtn.addActionListener(this);
		
		cashBtn = new RoundButton("현금 결제");
		cashBtn.setForeground(Color.WHITE);
		cashBtn.setFont(new Font("맑은 고딕", Font.BOLD, 60));
		cashBtn.addActionListener(this);

		creditBtn = new RoundButton("카드 결제");
		creditBtn.setForeground(Color.WHITE);
		creditBtn.setFont(new Font("맑은 고딕", Font.BOLD, 60));
		creditBtn.addActionListener(this);

		btnPan.add(pointBtn);btnPan.add(cashBtn);btnPan.add(creditBtn);
		
		bottom.add(btnPan);
		
	}
	
	void display() {
		setTitle("TinyCoffee");
		setSize(screenSize.width, screenSize.height);
		setVisible(true);
		setLocation(0, 0);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cashBtn) {
			CashPanel cash = new CashPanel(cartId);
			cash.display();
		}
		if(e.getSource() == creditBtn) {
			CreditPanel credit = new CreditPanel(cartId);
			credit.display();
		}
		if(e.getSource() == pointBtn) {
			PointPanel point = new PointPanel(cartId);
			point.display();
		}
		
	}
}