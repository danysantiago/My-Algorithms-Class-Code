import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class SmartForce {

	private static Random rand = new Random();

	/**Smart Force algorithm:
	 * 1.) Randomly choose 3 points from the point list and create a triangle
	 * 2.) Test for points inside triangle and remove them from list because they will not be extreme points
	 * 	   of the convex-hull
	 * 3.) Proceed with brute-force algorithm on remaining points on the list
	 */
	public static void execute(ArrayList<Point> pList, ArrayList<Point> solList) {

		//Create a copy of the point list, this is done because point are going to be removed
		ArrayList<Point> pList2 = (ArrayList<Point>) pList.clone();

		if(pList2.size() > 2){
			//Choose 3 points from the triangle but try them to be as far away as possible
			Point a;
			Point b;
			Point c;
			do {
				int n;
				n = 500;
				do {
					a = pList.get(rand.nextInt(pList.size()));
					n-=100;
				} while (Math.abs(a.getX()) < n && Math.abs(a.getY()) < n && n > 0);
				n = 500;
				do {
					b = pList.get(rand.nextInt(pList.size()));
					n-=100;
				} while (Math.abs(a.getX()) < n && Math.abs(a.getY()) < n && n > 0);
				n = 500;
				do {
					c = pList.get(rand.nextInt(pList.size()));
					n-=100;
				} while (Math.abs(a.getX()) < n && Math.abs(a.getY()) < n && n > 0);
			} while(a.equals(b) || a.equals(c) || b.equals(c));

			Main.triLines.add(new Line(a,b));
			Main.triLines.add(new Line(a,c));
			Main.triLines.add(new Line(b,c));

			//Remove points who are inside the triangle formed by the 3 points randomly selected
			for(int i = 0; i < pList2.size(); i++){
				Point p = pList2.get(i);
				if(!p.equals(a) && !p.equals(b) && !p.equals(c)){
					if(isPointInTriangle(p, a, b, c)){
						pList2.remove(i);
					}
				}
			}
		}

		//Proceed to perform brute force algorithm on remaining points
		for(int i = 0; i < pList2.size() - 1; i++){
			for(int j = i+1; j < pList2.size(); j++){
				boolean isExtremeLine = true;
				int initialSign = 0;
				Point p1 = pList2.get(i);
				Point p2 = pList2.get(j);
				int coefA = p2.getY() - p1.getY();
				int coefB = p1.getX() - p2.getX();
				int coefC = p1.getX()*p2.getY() - p1.getY()*p2.getX();
				for(int k = 0; k < pList2.size(); k++){
					Point p3 = pList2.get(k);
					int pRegion = coefA*p3.getX() + coefB*p3.getY() - coefC;
					if(initialSign != 0){
						if(pRegion > 0 && initialSign < 0){
							isExtremeLine = false;
							break;
						} else if (pRegion < 0 && initialSign > 0){
							isExtremeLine = false;
							break;
						} else {

						}
					} else { 
						if(pRegion > 0){
							initialSign = 1;
						} else if (pRegion < 0){
							initialSign = -1;
						} else {

						}
					}
				}
				if(isExtremeLine){
					addToSol(solList, p1);
					addToSol(solList, p2);
					//Sol line for drawing
					Main.solLines.add(new Line(p1,p2));
				}
			}
		}

		//Sort solution list
		Collections.sort(solList);

		//Print solution list
		for (int i = solList.size()-1; i >= 0; i--){
			System.out.print(solList.get(i).toString() + " ");
		}
	}

	//Method to add point to solution list if point is not already a solution
	private static void addToSol(ArrayList<Point> solList, Point pToAdd) {
		for (Point p : solList)
			if(p.equals(pToAdd))
				return;
				solList.add(pToAdd);
	}

	//Method to check weather a point is inside triangle.
	/**
	 * 1. Calculate triangle area
	 * 2. Calculate sub triangles area formed by the point P and the vertices of the triangle
	 * 3. Compare the original area to the sum of the sub-triangle areas
	 */
	private static boolean isPointInTriangle(Point p, Point a, Point b, Point c){
		//Calculate triangle area
		double area = areaOfTriangle(a,b,c);

		//Calculate sub triangles areas
		double subArea1 = areaOfTriangle(p,b,c);
		double subArea2 = areaOfTriangle(a,p,c);
		double subArea3 = areaOfTriangle(a,b,p);
		double area2 = subArea1 + subArea2 + subArea3;

		//Error tolerated
		double epsilon = 0.000001;

		return Math.abs(area - area2) < epsilon;
	}

	//Method to calculate Triangle Area
	//Heron's Formula (Kinda)
	private static double areaOfTriangle(Point a, Point b, Point c){
		double arg1 = a.getX()*(b.getY()-c.getY());
		double arg2 = b.getX()*(c.getY()-a.getY());
		double arg3 = c.getX()*(a.getY()-b.getY());

		return Math.abs(arg1 + arg2 + arg3)/2;
	}

}
