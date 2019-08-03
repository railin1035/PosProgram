package prefer;

import java.awt.*;
import javax.swing.JLabel;

public class RoundPanel extends JLabel {
	int w;
	int h;
	private Pcolors pcol = new Pcolors();
	Color col;
	
	public RoundPanel(int width, int height, Color c) {
		w = width;
		h = height;
		col = c;
		setOpaque(false);
		setPreferredSize(new Dimension(w, h));
		setMaximumSize(new Dimension(w, h));
		setMinimumSize(new Dimension(w, h));
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphic = (Graphics2D) g.create();
		graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphic.setColor(col);
		graphic.fillRoundRect(0, 0, w, h, 30, 30);
		graphic.setComposite(AlphaComposite.Src);	
	}
}