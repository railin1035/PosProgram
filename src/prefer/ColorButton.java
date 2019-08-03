package prefer;

import java.awt.*;
import javax.swing.JButton;

public class ColorButton extends JButton { 
	private Color col;
	
	public ColorButton(String text, Color c) {
		super(text);
		col = c;
		setBorderPainted(false);
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
	    int height = getHeight();
	    
	    Graphics2D g1 = (Graphics2D) g;
	    g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
	    if(getModel().isPressed()) {
	    	g1.setColor(col.darker());
	    }else {
	    	g1.setColor(col);
	    }

	    g1.fillRect(0, 0, width, height);
	 
	    FontMetrics fontMetrics = g1.getFontMetrics();
	    Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), g1).getBounds();
	 
	    int textX = (width - stringBounds.width) / 2;
	    int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
	 
	    g1.setColor(getForeground());
	    g1.setFont(getFont());
	    g1.drawString(getText(), textX, textY);
	    g1.dispose();
	 
	    super.paintComponent(g1);
	}
}
