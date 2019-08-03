package play;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import DB.*;
import prefer.*;

public class StoreInfo {

	public static void main(String[] args) {
		ShopInfo si = new ShopInfo();
		si.display();
	}

}

class ShopInfo extends JFrame implements ActionListener {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	private TopPanel tp;
	private JPanel bottom, LPan, RPan;
	private JLabel logo;
	private RoundPanel roundPan;
	private Pcolors pcol = new Pcolors();
	private ImageIcon images, chImage;
	private JPanel infoLa, infoTf;
	private JLabel stName, shName, shNum, shAdmin, shAddr, shCall, shEtc;
	private JTextField[] infoArr;
	private JButton updateBtn, backBtn, okBtn;
	private String marketId = "m01";

	// String[] tfArr = {"Tiny Coffee", "인천폴리텍", "123456789000","함이슬", "인천시 부평구
	// 무네미로", "032-000-0000","테스트"};

	storeDTO dto = new storeDTO();

	public ShopInfo() {
		setLayout(new BorderLayout(0, 0));
		// 상단 정보 패널 객체
		tp = new TopPanel("매장");
		tp.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(tp.top, BorderLayout.NORTH);

		images = new ImageIcon("back.jpg");
		Image img = images.getImage();
		Image chimg = img.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
		chImage = new ImageIcon(chimg);

		// 하단 메인 패널
		bottom = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(chImage.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		bottom.setLayout(null);
		add(bottom, BorderLayout.CENTER);

		// 타이틀
		logo = new JLabel("Tiny Coffee");
		logo.setForeground(Color.WHITE);
		logo.setBounds((screenSize.width / 2 - 217), 30, 434, 93);
		logo.setFont(new Font("HY헤드라인M", Font.PLAIN, 80));
		bottom.add(logo);

		// 내용 패널
		roundPan = new RoundPanel(1200, 730, Color.WHITE);
		roundPan.setBounds((screenSize.width / 2 - 600), 153, 1200, 730);
		roundPan.setLayout(new BorderLayout(0, 0));
		roundPan.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// 정보 패널
		LPan = new JPanel(new BorderLayout(0, 0));
		roundPan.add(LPan, BorderLayout.CENTER);

		infoLa = new JPanel(new GridLayout(7, 1));
		infoLa.setPreferredSize(new Dimension(roundPan.getWidth() / 6, LPan.getHeight()));
		infoLa.setBackground(Color.WHITE);
		infoTf = new JPanel(new GridLayout(7, 1));
		infoTf.setBackground(Color.WHITE);
		infoTf.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

		Font laFont = new Font("맑은 고딕", Font.BOLD, 25);

		// 정보 리스트
		stName = new JLabel("상호명");
		stName.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		stName.setFont(laFont);
		shName = new JLabel("지점명");
		shName.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		shName.setFont(laFont);
		shNum = new JLabel("사업자번호");
		shNum.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		shNum.setFont(laFont);
		shAdmin = new JLabel("지점장");
		shAdmin.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		shAdmin.setFont(laFont);
		shAddr = new JLabel("주소");
		shAddr.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		shAddr.setFont(laFont);
		shCall = new JLabel("연락처");
		shCall.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		shCall.setFont(laFont);
		shEtc = new JLabel("비고");
		shEtc.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		shEtc.setFont(laFont);

		infoLa.add(stName);
		infoLa.add(shName);
		infoLa.add(shNum);
		infoLa.add(shAdmin);
		infoLa.add(shAddr);
		infoLa.add(shCall);
		infoLa.add(shEtc);

		// 정보 텍스트 필드
		infoArr = new JTextField[7];
		for (int i = 0; i < 7; i++) {
			// infoArr[i] = new JTextField(tfArr[i],50);
			infoArr[i] = new JTextField("", 50);
			infoArr[i].setFont(new Font("맑은 고딕", Font.PLAIN, 25));
			infoArr[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
			infoArr[i].setEnabled(false);
			infoArr[i].setDisabledTextColor(Color.GRAY);
			infoTf.add(infoArr[i]);
		}

		try {
			boolean flag = storeDAO.loadData(dto);
			if (flag == false) {
				System.out.println("불러오기 실패");
			}
			else {
				System.out.println("불러오기 성공");
			}
			//marketId = dto.getmId();
			infoArr[0].setText(dto.getcName());
			infoArr[1].setText(dto.getmName());
			infoArr[2].setText(dto.getmNum());
			infoArr[3].setText(dto.getmMaster());
			infoArr[4].setText(dto.getmAddr());
			infoArr[5].setText(dto.getmCall());
			infoArr[6].setText(dto.getmEtc());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		LPan.add(infoLa, BorderLayout.WEST);
		LPan.add(infoTf, BorderLayout.CENTER);

		// 버튼 패널
		RPan = new JPanel(null);
		RPan.setPreferredSize(new Dimension(roundPan.getWidth() / 4 + 30, roundPan.getHeight()));
		RPan.setBackground(Color.WHITE);

		updateBtn = new JButton("수정");
		updateBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setBackground(pcol.getPosDBrown());
		updateBtn.setBounds(20, roundPan.getHeight() - 250, roundPan.getWidth() / 4, 100);
		updateBtn.addActionListener(this);

		okBtn = new JButton("완료");
		okBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		okBtn.setForeground(Color.WHITE);
		okBtn.setBackground(pcol.getPosDBrown());
		okBtn.setBounds(20, roundPan.getHeight() - 250, roundPan.getWidth() / 4, 100);
		okBtn.setVisible(false);
		okBtn.addActionListener(this);

		backBtn = new JButton("취소");
		backBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		backBtn.setForeground(Color.WHITE);
		backBtn.setBackground(pcol.getPosGray());
		backBtn.setBounds(20, roundPan.getHeight() - 140, roundPan.getWidth() / 4, 100);
		backBtn.addActionListener(this);

		RPan.add(updateBtn);
		RPan.add(okBtn);
		RPan.add(backBtn);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateBtn) {
			okBtn.setVisible(true);
			updateBtn.setVisible(false);
			for (int i = 0; i < 7; i++) {
				// tfArr[i] = infoArr[i].getText();
				infoArr[i].enable(true);
				infoArr[i].setForeground(Color.BLACK);
			}
			infoArr[0].requestFocus();
		}
		if (e.getSource() == backBtn) {
			updateBtn.setVisible(true);
			okBtn.setVisible(false);

			try {
				boolean flag = storeDAO.loadData(dto);
				if (flag == false) {
					System.out.println("불러오기 실패");
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			infoArr[0].setText(dto.getcName());
			infoArr[1].setText(dto.getmName());
			infoArr[2].setText(dto.getmNum());
			infoArr[3].setText(dto.getmMaster());
			infoArr[4].setText(dto.getmAddr());
			infoArr[5].setText(dto.getmCall());
			infoArr[6].setText(dto.getmEtc());
			
			for (int i = 0; i < 7; i++) {
				// infoArr[i].setText(tfArr[i]);
				infoArr[i].enable(false);
				infoArr[i].setForeground(Color.GRAY);
			}
		}
		if (e.getSource() == okBtn) {
			updateBtn.setVisible(true);
			okBtn.setVisible(false);

			dto.setmId(marketId);
			dto.setcName(infoArr[0].getText());
			dto.setmName(infoArr[1].getText());
			dto.setmNum(infoArr[2].getText());
			dto.setmMaster(infoArr[3].getText());
			dto.setmAddr(infoArr[4].getText());
			dto.setmCall(infoArr[5].getText());
			dto.setmEtc(infoArr[6].getText());
			
			try {
				boolean flag = storeDAO.updateData(dto);
				if (flag == false) {
					System.out.println("저장하기 실패");
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			for (int i = 0; i < 7; i++) {
				// tfArr[i] = infoArr[i].getText();
				infoArr[i].enable(false);
				infoArr[i].setForeground(Color.GRAY);
			}
		}

	}
}