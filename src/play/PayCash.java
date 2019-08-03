package play;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import DB.*;
import prefer.*;

public class PayCash {

	public static void main(String[] args) {
		CashPanel cash = new CashPanel();
		cash.display();
	}

}

class CashPanel extends JFrame implements ActionListener{
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	private TopPanel tp;
	private JPanel bottom, payPan;
	private JPanel topPan, rightPan, leftPan;
	private JLabel cashLa;
	private JPanel ltPan, ltLinePan, lbPan;
	private JLabel payLa;
	private JTextField payTf;
	private JPanel costPan1, costPan2, costPan3, costPan4;
	private JLabel c1La1, c1La2, c2La1, c2La2, c3La1, c3La2, c4La1, c4La2;
	private JPanel rtPan, rbPan, keyPan;
	private JButton[] nums;
	private JTextField tf;
	private ColorButton okBtn, backBtn;
	//private static int cartid;
	private Pcolors pcol = new Pcolors();
	
	int totalCost = 0;
	int amount = 0;
	int discount = 0;
	int income = 0;
	
	private String fullText="";
	
	
	
	CashPanel(){}
	
	CashPanel(int cartId) {
		setLayout(new BorderLayout(0, 0));
		//cartid = cartId;
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
		cashLa = new JLabel("현금결제");
		cashLa.setForeground(Color.WHITE);
		cashLa.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		
		topPan.add(cashLa);
		payPan.add(topPan);
		
		//왼쪽 패널. 결제 금액, 거스름돈 등
		leftPan = new JPanel();
		leftPan.setLayout(null);
		leftPan.setBackground(Color.WHITE);
		leftPan.setBounds(30, 90, (payPan.getWidth()/3)*2-50,payPan.getHeight()-120);
		payPan.add(leftPan);
		
		Font titFont = new Font("맑은 고딕", Font.BOLD, 30);
		Font costFont = new Font("맑은 고딕", Font.PLAIN, 30);
		
		//받은 금액 패널
		ltLinePan = new JPanel();
		ltLinePan.setLayout(null);
		ltLinePan.setBorder(new LineBorder(Color.LIGHT_GRAY));
		ltLinePan.setBounds(0,0,leftPan.getWidth(), 86);
		leftPan.add(ltLinePan);
		
		ltPan = new JPanel();
		ltPan.setBorder(new EmptyBorder(7, 0, 0, 0));
		ltPan.setBounds(1,1,leftPan.getWidth()-2, 84);
		ltPan.setBackground(Color.WHITE);
		ltLinePan.add(ltPan);

		payLa = new JLabel("현금결제금액  ");
		payLa.setFont(new Font("맑은 고딕", Font.BOLD, 34));
		
		payTf = new JTextField(income+" 원");
		payTf.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		payTf.setEditable(false);
		payTf.setBackground(Color.WHITE);
		payTf.setForeground(Color.BLACK);
		payTf.setHorizontalAlignment(JTextField.RIGHT);
		payTf.setBorder(new LineBorder(Color.GRAY));
		payTf.setPreferredSize(new Dimension((ltPan.getWidth()/3)*2-25,60));
		
		ltPan.add(payLa);ltPan.add(payTf);
		
		//총금액, 거스름돈 등 금액 정보 패널
		lbPan = new JPanel(new GridLayout(4, 1));
		lbPan.setBackground(pcol.getPosGray());
		lbPan.setBounds(0,leftPan.getHeight()-230,leftPan.getWidth(),230);
		lbPan.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		leftPan.add(lbPan);
		
		costPan1 = new JPanel(new GridLayout(1, 2));
		costPan1.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		c1La1 = new JLabel("수량 / 총금액");
		c1La1.setFont(titFont);
		c1La1.setForeground(Color.GRAY);
		c1La2 = new JLabel();
		c1La2.setFont(costFont);
		c1La2.setForeground(Color.GRAY);
		c1La2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		try {
			detailDTO dto = new detailDTO();
			boolean flag = detailDAO.loadData(cartId, dto);
			c1La2.setText( dto.getAmount()+"개 / "+dto.getCost()+"원");
			
			if (flag) {
				System.out.println("불러오기 성공");
			}
			else {
				System.out.println("불러오기 실패");
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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
		
		costPan4 = new JPanel(new GridLayout(1, 2));
		costPan4.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		c4La1 = new JLabel("거스름돈");
		c4La1.setFont(titFont);
		c4La1.setForeground(Color.GRAY);
		c4La2 = new JLabel((totalCost-income)+"원");
		c4La2.setFont(costFont);
		c4La2.setForeground(Color.GRAY);
		c4La2.setHorizontalAlignment(SwingConstants.RIGHT);
		costPan4.add(c4La1);costPan4.add(c4La2);
		
		lbPan.add(costPan1);lbPan.add(costPan2);lbPan.add(costPan3);lbPan.add(costPan4);
		
		//오른쪽 패널. 키패드
		rightPan = new JPanel(new BorderLayout(0,20));
		rightPan.setBackground(Color.WHITE);
		rightPan.setBounds((payPan.getWidth()/3)*2, 90, (payPan.getWidth()/3)-30,payPan.getHeight()-120);
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
		okBtn = new ColorButton("결제완료",pcol.getPosDBrown());
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
}