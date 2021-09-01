package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class RecursiveTree extends JFrame implements ActionListener{
	
	JPanel sidemeny = new JPanel();
	
	int dybde = 8;
	
	public RecursiveTree() {
		super("Modifiserbart rekursivt tre");
        setResizable(false);
		setBounds(100, 100, 800, 600);
		//setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
	}
	
	public void tegnTre(Graphics g, int xForste, int yForste, double vinkel, int dybde) {
		if(dybde < 0) return;
		
		int xAndre = xForste + (int) (Math.cos(Math.toRadians(vinkel)) * dybde * 10.0);
		int yAndre = yForste + (int) (Math.sin(Math.toRadians(vinkel)) * dybde * 10.0);
		
		g.drawLine(xForste, yForste, xAndre, yAndre);
		tegnTre(g, xAndre, yAndre, vinkel - 35, dybde - 1);
		tegnTre(g, xAndre, yAndre, vinkel + 35, dybde - 1);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK); //fargen på de grafiske elementene (linjene)
        tegnTre(g, 400, 500, -90, dybde);
	}


	public static void main(String[] args) {
		RecursiveTree testTre = new RecursiveTree();
		testTre.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
