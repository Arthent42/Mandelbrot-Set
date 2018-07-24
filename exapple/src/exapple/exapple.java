package exapple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;


public class exapple extends Frame {
	
	static public String ex_default = "0+0i";
	static public String ex_defaulta = "0+0i";
	static public int iteration = 100;
	static public int clr = 0;
	static public int zoomMax = 200;
	static public double dX = -2.0;
	static public double dXz = 2.0;
	static public double dY = -2.0;
	static public double dYz = 2.0;
	static public double s = (Math.abs(dX)+dXz)/600;
	
	public exapple() {
		setBackground(Color.black);
	}
	
	public void paint(Graphics g) {
//		for (int zoomX=3; zoomX<=zoomMax; zoomX++) {
			int zeichne_x = 0;
			int zeichne_y = 0;
			int farbe = 0;
			for (double q = dY; q<=dYz;q=q+s) { // y
				zeichne_y++;
				zeichne_x=0;
				for (double p = dX; p<=dXz;p=p+s) { // x
					clr = 0;
					zeichne_x++;
					farbe = compute(String.valueOf(p),String.valueOf(q));
					switch (farbe) {
						case 0: g.setColor(Color.black); break;
						case 1: g.setColor(Color.blue); break;
						case 2: g.setColor(Color.magenta); break;
						case 3: g.setColor(Color.red); break;
						case 4: g.setColor(Color.orange); break;
						case 5: g.setColor(Color.yellow); break;
						case 6: g.setColor(Color.white);
						default: g.setColor(Color.black);
					}
	//				System.out.println(farbe);
					
					g.fillRect(zeichne_x, zeichne_y, 1, 1);
				}
			}
//		}
	}
	
	public static void main(String[] args) {
		Frame f = new exapple();
		f.setSize(600,600);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	static int compute(String X, String Y) {
		String y = "0+0i";
		String b = "0+0i";
		String[] p= {"0"};
		String[] p2 = {"0"};
		
		
		double ctrl = 0;
		for (int I=0; I<=iteration; I++) {
			y = ia(im(b,b),X+"+"+Y+"i");
			b = y;
		}
		p = b.split("\\+");
		p2 = p[1].split("i");
		ctrl = Math.abs(Double.parseDouble(p2[0]));
		clr = (int) clr%7;
		
		return clr;
	}
	
	static String im(String z1, String z2) {
		try {
			String[] zahl11 = z1.split("\\+");
			String[] zahl21 = z2.split("\\+");
			String[] zahl12 = zahl11[1].split("i");
			String[] zahl22 = zahl21[1].split("i");
		
			double z11 = Double.parseDouble(zahl11[0]);
			double z21 = Double.parseDouble(zahl21[0]);
			double z12 = Double.parseDouble(zahl12[0]);
			double z22 = Double.parseDouble(zahl22[0]);
		
			double ee = z11*z21;
			double ez = z11*z22;
			double ze = z12*z21;
			double zz = z12*z22*-1;
		
			double zahl1 = ee+zz;
			double zahl2 = ez+ze;
			double result = zahl1;
			String end = String.valueOf(result)+"+"+zahl2+"i";
			ex_default = end;
			return end;
		} catch (NumberFormatException e) {
			clr++;
			return "0+0i";
		}
			
		
	}
	
	static String ia(String z1, String z2) {
		try {
			String[] zahl11 = z1.split("\\+");
			String[] zahl21 = z2.split("\\+");
			String[] zahl12 = zahl11[1].split("i");
			String[] zahl22 = zahl21[1].split("i");
		
			double z11 = Double.parseDouble(zahl11[0]);
			double z21 = Double.parseDouble(zahl21[0]);
			double z12 = Double.parseDouble(zahl12[0]);
			double z22 = Double.parseDouble(zahl22[0]);
			
			double ee = z11+z21;
			double zz = z12+z22;
			String end2 = ee+"+"+zz+"i";
			ex_defaulta = end2;
			return end2;
		} catch (NumberFormatException e) {
			clr++;
			return "0+0i";
		}
	}
}
