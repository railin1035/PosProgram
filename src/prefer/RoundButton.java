package prefer;

import java.awt.*;
import javax.swing.JButton;

public class RoundButton extends JButton {
	private Pcolors pcol = new Pcolors();
	
	public RoundButton(String text) {
		super(text);
		setBorderPainted(false);
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
	    int height = getHeight();
	    
	    //안티 엘리어싱 쓰려고 그래픽2D로 바꾸고 가장자리 부드럽게 바꿈
	    Graphics2D graphics = (Graphics2D) g;
	    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
	    //다른 곳에서 설정한 색상 불러온 것.
	    Color btnCol = pcol.getPosDBrown();
	    
	    //마우스 올리거나 눌렀을때 색 바꿈
	    if(getModel().isRollover() || getModel().isPressed()) {
	    	graphics.setColor(btnCol.brighter());
	    }else {
	    	graphics.setColor(btnCol);
	    }

	    graphics.fillRoundRect(0, 0, width, height, 30, 30);
	 
	    //버튼 텍스트 다시 그림
	    FontMetrics fontMetrics = graphics.getFontMetrics();
	    Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
	 
	    int textX = (width - stringBounds.width) / 2;
	    int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
	 
	    graphics.setColor(getForeground());
	    graphics.setFont(getFont());
	    graphics.drawString(getText(), textX, textY);
	    graphics.dispose();
	 
	    super.paintComponent(g);
	}
}