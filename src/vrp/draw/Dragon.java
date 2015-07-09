/**
 * @author  Venkat Reddy
 * @date    05-05-2000
 */
package vrp.draw;

import java.awt.*;
import java.awt.event.*;

public class Dragon extends Frame {
	private DragonCanvas c;
	private int gen = 17;
	private boolean bColors = true;
	public static void main(String[] args) {
		Dragon d = new Dragon(args);
	}
	public Dragon (String args[]) {

		if (args.length >01) {
			gen = Integer.parseInt(args[0]);
			if (gen > 20) gen = 20;
			if (gen <2) gen = 2;
		}
		if (args.length>1) bColors = Boolean.valueOf(args[1]).booleanValue();
		c = new DragonCanvas();
		add(c);
        addWindowListener(new WHandler());
        setSize(800, 600);
        setBackground(Color.white);
        setVisible(true);
	}
	public int pow (int b, int e) {
		int n = 1;
		for (int i=0; i<e; i++) { n*=b; }
		return n;
	}
	public int log2 (int n) {
		int i=0;
		while (n>1) {n/=2; i++;}
		return i;
	}
	public void trace(String str) {
		System.out.println(str);
	}
	class DragonCanvas extends Canvas {
		Color[] clr = { new Color(0, 0, 0),
						new Color(128, 0, 0),
						new Color(0, 128, 0),
						new Color(0, 0, 128),
						new Color(128, 128, 0),
						new Color(0, 128, 128),
						new Color(128, 0, 128)
						};
		public void paint(Graphics g)	{
			int xMax = getSize().width;
			int yMax = 	getSize().height;
			int n, c, i, r = (int)(double)(xMax*.23), s=yMax/3, x, y, d=2;
			g.setColor(Color.white);
			for (int j=1; j<gen; j++) {
				if (bColors) g.setColor(clr[j%clr.length]);
				for (n = 1 + pow(2, (j-1)); n <= pow(2, j); n++) {
					c = n;
					i = 0;
					do {
						c = pow(2, 1+log2(c-1)) - c + 1;
						i++;
					} while (c != 1);

					if(i%2 == 0) {x = i/2%2 == 0 ? 1 : -1 ; y = 0;}
					else         {y = i/2%2 == 0 ? 1 : -1 ; x = 0;}

					g.drawLine(r, s, r+d*x, s-d*y);
					r += d*x;
					s -= d*y;
				}
			}
		}
	}
	class WHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) { System.exit(0);}
	}
}
