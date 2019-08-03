package play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DB.detailDAO;
import DB.detailDTO;
import prefer.*;

public class PayCredit {

	public static void main(String[] args) {
		CreditPanel credit = new CreditPanel();
		credit.display();

	}

}

class CreditPanel extends JFrame implements ActionListener{
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	private TopPanel tp;
	private JPanel bottom, payPan;
	private JPanel topPan, rightPan, leftPan;
	private JLabel cashLa;
	private JPanel ltLinePan, ltPan1, ltPan2, ltPan3, ltPan4, lbPan;
	private JLabel payLa1, payLa2, payLa3, payLa4;
	private JTextField payTf1, payTf2, payTf3, payTf4;
	private JPanel costPan1, costPan2, costPan3;
	private JLabel c1La1, c1La2, c2La1, c2La2, c3La1, c3La2;
	private JPanel rtPan, rbPan, keyPan;
	private JButton[] nums;
	private JTextField tf;
	private ColorButton okBtn, backBtn;
	
	private Pcolors pcol = new Pcolors();
	
	int totalCost=0;
	int amount=0;
	int discount = 0;
	int income = 0;
	
	private String fullText="";
	
	CreditPanel() {}
	CreditPanel(int cartId) {
		setLayout(new BorderLayout(0, 0));
		
		try {
			detailDTO dto = new detailDTO();
			boolean flag = detailDAO.loadData(cartId, dto);
			amount = dto.getAmount();
			totalCost = dto.getCost();
			calIncome();
			if (flag) {
				System.out.println("불러오기 성공");
			}
			else {
				System.out.println("불러오기 실패");
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		//상단 정보 패널 객체
		tp = new TopPanel("결제");
		tp.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(tp.top,BorderLayout.NORTH);
		
		//뒷배경
		bottom = new JPanel();
		bottom.setLayout(null);
		bottom.setBackground(pcol.getPosLBrown());
		add(bottom, BorderLayout.CENTER);
		
		//결제용 뒷 패널
		payPan = new JPanel();
		payPan.setBounds((screenSize.width/2)-600, 140, 1200, 700);
		payPan.setBackground(Color.WHITE);
		payPan.setLayout(null);
		bottom.add(payPan);
		
		//제목 패널
		topPan = new JPanel();
		topPan.setBackground(Color.BLACK);
		topPan.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 6));
		topPan.setBounds(0,0,payPan.getWidth(), 60);
		cashLa = new JLabel("카드결제");
		cashLa.setForeground(Color.WHITE);
		cashLa.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		
		topPan.add(cashLa);
		payPan.add(topPan);
		
		//왼쪽 패널. 결제 금액, 카드정보 등
		leftPan = new JPanel();
		leftPan.setLayout(null);
		leftPan.setBackground(Color.WHITE);
		leftPan.setBounds(30, 90, (payPan.getWidth()/3)*2-50,payPan.getHeight()-130);
		payPan.add(leftPan);
		
		Font titFont = new Font("맑은 고딕", Font.BOLD, 30);
		Font costFont = new Font("맑은 고딕", Font.PLAIN, 30);
		
		//카드 결제 정보 패널
		ltLinePan = new JPanel(new GridLayout(4, 1));
		ltLinePan.setLayout(null);
		ltLinePan.setBorder(new LineBorder(Color.LIGHT_GRAY));
		ltLinePan.setBounds(0,0,leftPan.getWidth(), 350);
		ltLinePan.setBackground(Color.WHITE);
		leftPan.add(ltLinePan);
		
		ltPan1 = new JPanel();
		ltPan1.setBorder(new EmptyBorder(14, 0, 0, 0));
		ltPan1.setBounds(1,1,leftPan.getWidth()-2, 86);
		ltPan1.setBackground(Color.WHITE);
		ltLinePan.add(ltPan1);

		payLa1 = new JLabel("결제금액  ");
		payLa1.setFont(new Font("맑은 고딕", Font.BOLD, 34));
		
		payTf1 = new JTextField(income+" 원");
		payTf1.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		payTf1.setEditable(false);
		payTf1.setBackground(Color.WHITE);
		payTf1.setForeground(Color.BLACK);
		payTf1.setHorizontalAlignment(JTextField.RIGHT);
		payTf1.setBorder(new LineBorder(Color.GRAY));
		payTf1.setPreferredSize(new Dimension((ltPan1.getWidth()/3)*2-25,60));
		
		ltPan1.add(payLa1);ltPan1.add(payTf1);
		
		ltPan2 = new JPanel();
		ltPan2.setBorder(new EmptyBorder(14, 0, 0, 0));
		ltPan2.setBounds(1,83,leftPan.getWidth()-2, 86);
		ltPan2.setBackground(Color.WHITE);
		ltLinePan.add(ltPan2);

		payLa2 = new JLabel("할부기간  ");
		payLa2.setFont(new Font("맑은 고딕", Font.BOLD, 34));
		
		payTf2 = new JTextField(0+" 개월");
		payTf2.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		payTf2.setEditable(false);
		payTf2.setBackground(Color.WHITE);
		payTf2.setForeground(Color.BLACK);
		payTf2.setHorizontalAlignment(JTextField.RIGHT);
		payTf2.setBorder(new LineBorder(Color.GRAY));
		payTf2.setPreferredSize(new Dimension((ltPan2.getWidth()/3)*2-25,60));
		
		ltPan2.add(payLa2);ltPan2.add(payTf2);
		
		ltPan3 = new JPanel();
		ltPan3.setBorder(new EmptyBorder(14, 0, 0, 0));
		ltPan3.setBounds(1,(82*2)+1,leftPan.getWidth()-2, 86);
		ltPan3.setBackground(Color.WHITE);
		ltLinePan.add(ltPan3);

		payLa3 = new JLabel("카드번호  ");
		payLa3.setFont(new Font("맑은 고딕", Font.BOLD, 34));
		
		payTf3 = new JTextField("123435677730788891");
		payTf3.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		payTf3.setEditable(false);
		payTf3.setBackground(Color.WHITE);
		payTf3.setForeground(Color.BLACK);
		payTf3.setHorizontalAlignment(JTextField.RIGHT);
		payTf3.setBorder(new LineBorder(Color.GRAY));
		payTf3.setPreferredSize(new Dimension((ltPan3.getWidth()/3)*2-25,60));
		
		ltPan3.add(payLa3);ltPan3.add(payTf3);
		
		ltPan4 = new JPanel();
		ltPan4.setBorder(new EmptyBorder(14, 0, 0, 0));
		ltPan4.setBounds(1,(82*3)+1,leftPan.getWidth()-2, 86);
		ltPan4.setBackground(Color.WHITE);
		ltLinePan.add(ltPan4);

		payLa4 = new JLabel("승인금액  ");
		payLa4.setFont(new Font("맑은 고딕", Font.BOLD, 34));
		
		payTf4 = new JTextField(income+" 원");
		payTf4.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		payTf4.setEditable(false);
		payTf4.setBackground(Color.WHITE);
		payTf4.setForeground(Color.BLACK);
		payTf4.setHorizontalAlignment(JTextField.RIGHT);
		payTf4.setBorder(new LineBorder(Color.GRAY));
		payTf4.setPreferredSize(new Dimension((ltPan4.getWidth()/3)*2-25,60));
		
		ltPan4.add(payLa4);ltPan4.add(payTf4);
		
		// 금액 정보 패널
		lbPan = new JPanel(new GridLayout(3, 1));
		lbPan.setBackground(pcol.getPosGray());
		lbPan.setBounds(0,leftPan.getHeight()-200,leftPan.getWidth(),200);
		lbPan.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		leftPan.add(lbPan);
		
		costPan1 = new JPanel(new GridLayout(1, 2));
		costPan1.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		c1La1 = new JLabel("수량 / 총금액");
		c1La1.setFont(titFont);
		c1La1.setForeground(Color.GRAY);
		c1La2 = new JLabel(amount+"개 / "+totalCost+"원");
		c1La2.setFont(costFont);
		c1La2.setForeground(Color.GRAY);
		c1La2.setHorizontalAlignment(SwingConstants.RIGHT);
		costPan1.add(c1La1);costPan1.add(c1La2);
		
		costPan2 = new JPanel(new GridLayout(1, 2));
		costPan2.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		c2La1 = new JLabel("할인금액");
		c2La1.setFont(titFont);
		c2La1.setForeground(Color.GRAY);
		c2La2 = new JLabel(discount+"원");
		c2La2.setFont(costFont);
		c2La2.setForeground(Color.GRAY);
		c2La2.setHorizontalAlignment(SwingConstants.RIGHT);
		costPan2.add(c2La1);costPan2.add(c2La2);
		
		costPan3 = new JPanel(new GridLayout(1, 2));
		costPan3.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		c3La1 = new JLabel("받을금액");
		c3La1.setFont(titFont);
		c3La1.setForeground(Color.GRAY);
		c3La2 = new JLabel((totalCost-discount)+"원");
		c3La2.setFont(costFont);
		c3La2.setForeground(Color.GRAY);
		c3La2.setHorizontalAlignment(SwingConstants.RIGHT);
		costPan3.add(c3La1);costPan3.add(c3La2);

		lbPan.add(costPan1);lbPan.add(costPan2);lbPan.add(costPan3);
		
		//오른쪽 패널. 키패드
		rightPan = new JPanel(new BorderLayout(0,20));
		rightPan.setBackground(Color.WHITE);
		rightPan.setBounds((payPan.getWidth()/3)*2, 90, (payPan.getWidth()/3)-30,payPan.getHeight()-130);
		payPan.add(rightPan);
		
		//키패드
		rtPan = new JPanel(new BorderLayout(0,6));
		rtPan.setBackground(Color.WHITE);
		
		tf = new JTextField("0",16);
		tf.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		tf.setForeground(pcol.getPosRed());
		tf.setHorizontalAlignment(4);
		tf.setEditable(false);
		tf.setBackground(Color.WHITE);
		tf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		tf.setPreferredSize(new Dimension(rtPan.getWidth(),60));
		
		rtPan.add(tf,BorderLayout.NORTH);
		
		keyPan = new JPanel(new GridLayout(4, 3, 0, 0));
		keyPan.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY));
		nums = new JButton[12];
		for (int i = 0; i < 9; i++) {
			nums[i] = new JButton("" + (i + 1));
			nums[i].setFont(new Font("맑은 고딕", Font.PLAIN, 30));
			nums[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.GRAY));
			nums[i].setBackground(Color.WHITE);
			nums[i].addActionListener(this);
			keyPan.add(nums[i]);
		}
		nums[9] = new JButton("0");
		nums[9].setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		nums[9].setBackground(Color.WHITE);
		nums[9].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
		nums[9].addActionListener(this);
		keyPan.add(nums[9]);
		nums[10] = new JButton("00");
		nums[10].setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		nums[10].setBackground(Color.WHITE);
		nums[10].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
		nums[10].addActionListener(this);
		keyPan.add(nums[10]);
		nums[11] = new JButton("Del");
		nums[11].setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		nums[11].setBackground(Color.WHITE);
		nums[11].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY));
		keyPan.add(nums[11]);
		
		rtPan.add(keyPan,BorderLayout.CENTER);
		rightPan.add(rtPan,BorderLayout.CENTER);
		
		//결재완료, 취소 버튼
		rbPan = new JPanel(new GridLayout(1, 2, 10, 10));
		rbPan.setBackground(Color.WHITE);
		okBtn = new ColorButton("승인",pcol.getPosDBrown());
		okBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		okBtn.setForeground(Color.WHITE);
		okBtn.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		okBtn.setBackground(pcol.getPosDBrown());
		backBtn = new ColorButton("취소",pcol.getPosGray());
		backBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		backBtn.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		backBtn.setForeground(Color.WHITE);
		backBtn.setBackground(pcol.getPosGray());
		backBtn.addActionListener(this);
		
		rbPan.add(okBtn); rbPan.add(backBtn);
		
		rightPan.add(rbPan,BorderLayout.SOUTH);
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
		if(e.getSource() == backBtn) {
			dispose();
		}
		for (int i = 0; i < 11; i++) {
			if (e.getSource() == nums[i]) {
				fullText += e.getActionCommand();
				tf.setText(fullText);
			}
		}
		
	}
	private void calIncome() {
		income = totalCost - discount;
	}
}