import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {

	static public int amountOfPoints;

	static public ArrayList<Point> pList = new ArrayList<Point>();
	static public ArrayList<Point> solList = new ArrayList<Point>();

	static public ArrayList<Line> solLines = new ArrayList<Line>();
	static public ArrayList<Line> triLines = new ArrayList<Line>();

	static public Random rand = new Random();

	public static void main(String[] args) {
		if(args.length == 0){
			System.out.println("No Algorithm specified try useing: '-brutef' or '-smartf' as parameters.");
			System.exit(-1);
		}

		System.out.println("Enter input:");
		Scanner in = new Scanner(System.in);
		try {
			amountOfPoints = in.nextInt();
			if(args.length == 2 && args[1].equals("-r")){
				for(int i = 0; i < amountOfPoints; i++)
					//Use if using input stream for amount of random points
					pList.add(new Point());
			} else {
				for(int i = 0; i < amountOfPoints; i++)
					//Use if using input stream to give points coordinates and amount of points
					pList.add(new Point(in.nextInt(), in.nextInt()));
			}

		} catch (InputMismatchException e){
			System.err.println("Invalid input.");
			System.exit(-1);
		}


		long startTime;
		long endTime;

		startTime = System.nanoTime();

		if(args[0].equals("-brutef")){
			System.out.println("Brute-force solution:");
			BruteForce.execute(pList, solList);

			//Show solution visually!
			showMeVisually();
		} else if (args[0].equals("-smartf")){
			System.out.println("Smart-force solution:");
			SmartForce.execute(pList, solList);

			//Show solution visually!
			showMeVisually();
		} else {
			System.err.println("Invalid parameter.");
		}

		endTime = System.nanoTime() - startTime;
		System.out.println("\nElapsed time: " + endTime*Math.pow(10, 9) + " ms");
	}

	//Method to show solution visually!
	private static void showMeVisually() {
		JFrame jf = new JFrame();
		jf.add(new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				int x_max = 1000;
				int y_max = 1000;
				int displ = 500;

				//Draw BLACK Background
				g.setColor(Color.BLACK);
				for(int i = 0; i <= y_max; i++){
					g.drawLine(0, i, x_max, i);
				}
				//Draw GRAY Cartesian Axis
				g.setColor(Color.DARK_GRAY);
				g.drawLine(0, displ, x_max, displ);
				g.drawLine(displ, 0, displ, y_max);

				//Draw WHITE Dots
				g.setColor(Color.WHITE);
				for (Point p : pList){
					g.drawLine(p.getX() + displ, displ - p.getY(), p.getX() + displ, displ - p.getY());
				}

				//Draw Convex-Hull Lines
				for (int i = 0; i < solLines.size(); i++){
					Point p1 = solLines.get(i).getP1();
					Point p2 = solLines.get(i).getP2();
					g.drawLine(p1.getX() + displ, displ - p1.getY(), p2.getX() + displ, displ - p2.getY());
				}

				//Draw Triangle Lines
				g.setColor(Color.YELLOW);
				for (int i = 0; i < triLines.size(); i++){
					Point p1 = triLines.get(i).getP1();
					Point p2 = triLines.get(i).getP2();
					g.drawLine(p1.getX() + displ, displ - p1.getY(), p2.getX() + displ, displ - p2.getY());
				}
			}
		});
		jf.setSize(1000,1000);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}


