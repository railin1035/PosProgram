package prefer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;

import javax.swing.*;


public class TopPanel extends JPanel{
	private JLabel store, count, admin, grade, date;
	private ColorButton title;
	public JPanel top = new JPanel();
	
	public TopPanel(String tit) {
		top.setBackground(Color.BLACK);
		top.setLayout(new FlowLayout(FlowLayout.LEFT, 170, 5));
		title = new ColorButton(tit+"    ", Color.BLACK);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		store = new JLabel("인천폴리텍");
		store.setForeground(Color.WHITE);
		store.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		count = new JLabel("0000");
		count.setForeground(Color.WHITE);
		count.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		admin = new JLabel("함이슬");
		admin.setForeground(Color.WHITE);
		admin.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		grade = new JLabel("지점장");
		grade.setForeground(Color.WHITE);
		grade.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		GregorianCalendar gc = new GregorianCalendar();
		date = new JLabel("영업일자 : " + gc.get(gc.YEAR) + "년 " + (gc.get(gc.MONTH)+1) + "월 " + gc.get(gc.DATE) + "일");
		date.setForeground(Color.WHITE);
		date.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
	
		top.add(title);top.add(store);top.add(count);top.add(admin);top.add(grade);top.add(date);
	}
	public JButton getButton() {
		return title;
	}
	
}
