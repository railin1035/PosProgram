package play;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DB.*;
import prefer.*;

public class SaleUI {
	public static void main(String[] args) {
		SalePanel sp = new SalePanel();
		sp.display();
	}

}


class SalePanel extends JFrame implements ActionListener {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	private TopPanel tp;
	private JPanel bottom, rightPan, leftPan, rtPan, rbPan, ltPan, lbPan;
	private JPanel mPan1, mPan2;
	private JButton[] mtBtn, mbBtn;
	private String[] mTit = {"커피","음료","아이스크림","디저트","MD"};
	private JPanel cPan1, cPan2, cPan3;
	private JLabel c1R, c1L, c2R, c2L, c3R, c3L;
	private JPanel rbCalPan, rbBtnPan, keyPan;
	private JButton[] nums;
	private JTextField tf;
	private JScrollPane scrollPan;
	private JTable cart;
	private DefaultTableModel cartModel;
	private ColorButton inBtn,delBtn,coBtn;
	private Pcolors pcol = new Pcolors();
	private String[] mName = {"아메리카노 (HOT)","아메리카노 (ICE)","카페라떼 (HOT)","카페라떼 (ICE)","",
			"카페모카 (HOT)","카페모카 (ICE)","바닐라라떼 (HOT)","바닐라라떼 (ICE)"};
	private int[] mCost = {2500,2500,3000,3000,0,3500,3500,3500,3500};
	private int[] menuId = {1,2,3,4,0,5,6,7,8};
	private String[] header = {"NO","상품명","수량","금액"};
	private String[][] cartContents;
	
	private int cartNum, totalCost=0, income=0, discount=0;
	private String fullText="";
	
	private static int cartId=0;
	private cartDTO dto = new cartDTO();
	ArrayList<detailDTO> dtolist = new ArrayList<detailDTO>();
	
	//private String[] mName = {"아메리카노 (HOT)","아메리카노 (ICE)","카페라떼 (HOT)","카페라떼 (ICE)","",
	//		"카페모카 (HOT)","카페모카 (ICE)","바닐라라떼 (HOT)","바닐라라떼 (ICE)"};
	//private int[] mCost = {2500,2500,3000,3000,0,3500,3500,3500,3500};
	
	SalePanel() {
		cartNum=0;
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
		
		
		//왼쪽 패널. 메뉴 테이블 + 가격정보
		leftPan = new JPanel();
		leftPan.setLayout(null);
		leftPan.setBounds(0, 0, 1100,963);
		bottom.add(leftPan);
		
		//메뉴 테이블 패널
		ltPan = new JPanel(new BorderLayout(0,0));
		ltPan.setBounds(0,0,1100,(leftPan.getHeight()/2+140));
		leftPan.add(ltPan);
		
		//분류 패널
		mPan1 = new JPanel(new GridLayout(1, 5));
		mPan1.setBackground(pcol.getPosLBrown());
		mPan1.setPreferredSize(new Dimension(ltPan.getWidth(), ltPan.getHeight()/5));
		
		//분류 버튼
		mtBtn = new JButton[5];
		for(int i=0; i<mtBtn.length; i++) {
			mtBtn[i] = new JButton(mTit[i]);
			mtBtn[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			mtBtn[i].setBackground(pcol.getPosLBrown());
			mtBtn[i].setForeground(Color.WHITE);
			mtBtn[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, pcol.getPosGray()));
			//mtBtn[i].addMouseListener(this);
			mPan1.add(mtBtn[i]);
		}
		ltPan.add(mPan1,BorderLayout.NORTH);
		
		
		//메뉴 리스트
		mPan2= new JPanel(new GridLayout(4, 5));
		mPan2.setBackground(Color.white);
		
		mbBtn = new JButton[20];
		for(int i=0; i<20; i++) {
			if(i == 4 || i == 9 || i == 14 || i == 19) {
				mbBtn[i] = new JButton("");
				mbBtn[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
				mbBtn[i].setBackground(Color.WHITE);
				mbBtn[i].setForeground(Color.BLACK);
				mbBtn[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.DARK_GRAY));
				mPan2.add(mbBtn[i]);
				continue;
			}
			if(i<mCost.length) {
				mbBtn[i] = new JButton("<html>"+mName[i]+"<br>"+mCost[i]+"원 </html>");
				mbBtn[i].addActionListener(this);
			}
			else {mbBtn[i] = new JButton("");}
			mbBtn[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			mbBtn[i].setBackground(Color.WHITE);
			mbBtn[i].setForeground(Color.BLACK);
			mbBtn[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.DARK_GRAY));
			mPan2.add(mbBtn[i]);
		}
		mbBtn[19].setText("<html>샷 추가<br>500원</html>");
		mbBtn[19].addActionListener(this);
		
		ltPan.add(mPan2,BorderLayout.CENTER);
		
		
		//가격 레이블 패널
		lbPan = new JPanel(new GridLayout(3, 1));
		lbPan.setBounds(0, (leftPan.getHeight()/2+140), 1100, (leftPan.getHeight()/2-140));
		lbPan.setBackground(pcol.getPosGray());
		leftPan.add(lbPan);
		
		Font titFont = new Font("맑은 고딕", Font.BOLD, 40);
		Font costFont = new Font("맑은 고딕", Font.PLAIN, 40);
		cPan1 = new JPanel(new GridLayout(1, 2));
		cPan1.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		cPan1.setBackground(pcol.getPosGray());
		c1L = new JLabel("총 금액");
		c1L.setFont(titFont);
		c1R = new JLabel("0 원");
		c1R.setFont(costFont);
		c1R.setHorizontalAlignment(SwingConstants.RIGHT);
		cPan1.add(c1L);cPan1.add(c1R);
		
		cPan2 = new JPanel(new GridLayout(1, 2));
		cPan2.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		cPan2.setBackground(pcol.getPosGray());
		c2L = new JLabel("할인 금액");
		c2L.setFont(titFont);
		c2R = new JLabel("0 원");
		c2R.setFont(costFont);
		c2R.setHorizontalAlignment(SwingConstants.RIGHT);
		cPan2.add(c2L);cPan2.add(c2R);
		
		cPan3 = new JPanel(new GridLayout(1, 2));
		cPan3.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		cPan3.setBackground(pcol.getPosGray());
		c3L = new JLabel("받을 금액");
		c3L.setFont(titFont);
		c3L.setForeground(pcol.getPosRed());
		c3R = new JLabel("0 원");
		c3R.setFont(costFont);
		c3R.setHorizontalAlignment(SwingConstants.RIGHT);
		c3R.setForeground(pcol.getPosRed());
		cPan3.add(c3L);cPan3.add(c3R);
		
		lbPan.add(cPan1);lbPan.add(cPan2);lbPan.add(cPan3);
		
		//오른쪽 패널. 선택메뉴 리스트 + 키패드
		rightPan = new JPanel(new GridLayout(2, 1));
		rightPan.setBounds(1100, 0, 820,963);
		bottom.add(rightPan);
		
		//선택메뉴 리스트
		rtPan = new JPanel(new BorderLayout(0,0));
		
		cartModel = new DefaultTableModel(cartContents, header);
		cart = new JTable(cartModel);
		cart.getTableHeader().setResizingAllowed(false);
		cart.getTableHeader().setReorderingAllowed(false);
		cart.getTableHeader().setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		cart.getTableHeader().setBackground(Color.WHITE);
		cart.setRowHeight(30);
		scrollPan = new JScrollPane(cart,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rtPan.add(scrollPan);
		
		rightPan.add(rtPan,BorderLayout.CENTER);
		
		//키패드
		rbPan = new JPanel(new BorderLayout(0,0));
		rbCalPan = new JPanel(new BorderLayout(0,0));
		tf = new JTextField("0",16);
		tf.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		tf.setForeground(pcol.getPosRed());
		tf.setHorizontalAlignment(4);
		tf.setEditable(false);
		tf.setBackground(Color.WHITE);
		tf.setPreferredSize(new Dimension(rbCalPan.getWidth(),60));
		rbCalPan.add(tf,BorderLayout.NORTH);
		
		keyPan = new JPanel(new GridLayout(4, 3, 0, 0));
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
		nums[11].addActionListener(this);
		keyPan.add(nums[11]);
		
		rbCalPan.add(keyPan,BorderLayout.CENTER);
		
		rbPan.add(rbCalPan,BorderLayout.CENTER);
		
		
		//입력,취소,결재 버튼
		rbBtnPan = new JPanel(new GridLayout(3, 1, 1, 1));
		rbBtnPan.setBackground(Color.WHITE);
		rbBtnPan.setPreferredSize(new Dimension(rightPan.getWidth()/4+40, rbPan.getHeight()));
		
		inBtn = new ColorButton("입력",pcol.getPosDBrown());
		inBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		inBtn.setForeground(Color.WHITE);
		inBtn.setBackground(pcol.getPosDBrown());
		inBtn.addActionListener(this);
		delBtn = new ColorButton("취소",pcol.getPosDBrown());
		delBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		delBtn.setForeground(Color.WHITE);
		delBtn.setBackground(pcol.getPosDBrown());
		delBtn.addActionListener(this);
		coBtn = new ColorButton("결제",pcol.getPosRed());
		coBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		coBtn.setForeground(Color.WHITE);
		coBtn.setBackground(pcol.getPosRed());
		coBtn.addActionListener(this);
		
		rbBtnPan.add(inBtn);rbBtnPan.add(delBtn);rbBtnPan.add(coBtn);
		
		rbPan.add(rbBtnPan,BorderLayout.EAST);

		rightPan.add(rbPan);
		
	}
	
	void display() {
		setTitle("TinyCoffee");
		setSize(screenSize.width, screenSize.height);
		setVisible(true);
		setLocation(0, 0);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public int tableSum() {
		int sum=0;
		for(int i=0; i< cart.getRowCount(); i++) {
			sum += Integer.parseInt(cartModel.getValueAt(i, 3).toString());
		}
		
		return sum;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==coBtn) {
			//cart DB
			cartId++;
			dto.setTotal(income);
			try {
				boolean flag = cartDAO.insertData(dto);
				
				if (flag) {
					System.out.println("카트 저장 성공");
				}
				else {
					System.out.println("카트 저장 실패");
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//detail DB
			for(int i=0; i<cart.getRowCount(); i++) {
				int count = Integer.parseInt(cartModel.getValueAt(i, 2).toString());
				int total = Integer.parseInt(cartModel.getValueAt(i, 3).toString());
				dtolist.add(new detailDTO(i,cartId, 1/*임시메뉴아이디*/,count,total));
			}
			try {
				boolean flag = detailDAO.insertData(dtolist);
				
				if (flag) {
					System.out.println("상세 저장 성공");
				}
				else {
					System.out.println("상세 저장 실패");
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			payPanel pp = new payPanel(cartId);
			pp.display();
		}
		
		for(int i=0; i<19; i++) {
			if(e.getSource()==mbBtn[i]) {
				cartNum++;
				String[] input = new String[4];
				input[0]=""+cartNum;
				input[1]=mName[i];
				input[2]="1";
				input[3]=""+mCost[i];
				cartModel.addRow(input);
				totalCost = tableSum();
				income = totalCost-discount;
				c1R.setText(totalCost+" 원");
				c3R.setText(income+" 원");
			}
		}
		if(e.getSource()==mbBtn[19]) {
			cartNum++;
			String[] input = new String[4];
			input[0]=""+cartNum;
			input[1]="샷추가";
			input[2]="1";
			input[3]="500";
			cartModel.addRow(input);
			totalCost = tableSum();
			income = totalCost-discount;
			c1R.setText(totalCost+" 원");
			c3R.setText(income+" 원");
		}
		
		if(e.getSource()==delBtn) {
			if(cart.getSelectedRow() == -1) {
				return;
			}
			else {
				cartNum--;
				cartModel.removeRow(cart.getSelectedRow());
				totalCost = tableSum();
				income = totalCost-discount;
				c1R.setText(totalCost+" 원");
				c3R.setText(income+" 원");
			}
		}
		
		if(e.getSource() == inBtn) {
			if(cart.getSelectedRow() != -1) {
				int sum = Integer.parseInt(cartModel.getValueAt(cart.getSelectedRow(), 3).toString())*Integer.parseInt(tf.getText());
				cartModel.setValueAt(tf.getText(), cart.getSelectedRow(), 2);
				cartModel.setValueAt(sum, cart.getSelectedRow(), 3);
				totalCost = tableSum();
				income = totalCost-discount;
				c1R.setText(totalCost+" 원");
				c3R.setText(income+" 원");
				tf.setText("0");
				fullText="";
			}
		}
		
		for (int i = 0; i < 11; i++) {
			if (e.getSource() == nums[i]) {
				fullText += e.getActionCommand();
				tf.setText(fullText);
			}
		}
		
		if(e.getSource()==nums[11]) {
			if(fullText.length()>0) {
				fullText = fullText.substring(0,fullText.length()-1);
				tf.setText(fullText);
			}
		}
	}

}