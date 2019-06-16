package exapple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;


public class exapple extends Frame {
	
	static public String ex_default = "0+0i";
	static public String ex_defaulta = "0+0i";
	static public int iteration = 30;
	static public int clr = 0;
	static public int zoomMax = 200;
	static public int itcnt = 0;
	static public double dX = -2;
	static public double dXz = 1;
	static public double dY = -1.5;
	static public double dYz = 1.5;
	static public double bigge = 600;
	static public double s = (Math.abs(dX)+dXz)/bigge;
	
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
					
					
//					System.out.println(farbe);
					
//					farbe=(int) farbe/(iteration/125);
					farbe=farbe*(255/iteration);
					farbe=255-farbe;
					
					g.setColor(new Color(0,farbe,0));
					g.fillRect(zeichne_x, zeichne_y, 1, 1);
				}
			}
//		}
	}
	
	public static void main(String[] args) {
		Frame f = new exapple();
		f.setSize((int) bigge,(int) bigge);
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
//		for (int I=0; I<=iteration; I++) {
		itcnt = 0;
		while (uez(b) && itcnt <= iteration) {
//			System.out.println(y);
			y = ia(im(b,b),X+"+"+Y+"i");
			b = y;
			itcnt++;
		}
		p = b.split("\\+");
		p2 = p[1].split("i");
		ctrl = Math.abs(Double.parseDouble(p2[0]));
		clr = (int) clr%7;
		
		return itcnt-1;
	}
	
//	static String ilog(String z1, String z2) {
//		
//		return end;
//	}
	
	static String im(String z1, String z2) {
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
	}
	
	static boolean uez(String z) {
		boolean bool = false;
		String[] az1 = z.split("\\+");
		String[] az2 = az1[1].split("i");
		double z1 = Double.parseDouble(az1[0]); //x
		double z2 = Double.parseDouble(az2[0]); //y
		
		double ctrl = Math.sqrt(z1*z1+z2*z2);
		if (Math.abs(ctrl) > 2) {
			bool = false;
		} else {
			bool=true;
		}
		
		return bool;
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
