import java.util.ArrayList;
import java.util.Collections;


public class BruteForce {

	/**Brute Force algorithm:
	 * 1.) Create all possible convex-hull lines between all the combinations between 2 points in the point list
	 *     using the equations for a line between 2 points: A*x + B*y = C
	 * 2.) Compare all the other points in the list to check if they lie in the same side of the line created.
	 * 3.) If step 2 successfully completes add those two points to solution.
	 */
	public static void execute(ArrayList<Point> pList, ArrayList<Point> solList) {

		for(int i = 0; i < pList.size() - 1; i++){
			for(int j = i+1; j < pList.size(); j++){
				boolean isExtremeLine = true;
				int initialSign = 0;
				Point p1 = pList.get(i);
				Point p2 = pList.get(j);
				int coefA = p2.getY() - p1.getY();
				int coefB = p1.getX() - p2.getX();
				int coefC = p1.getX()*p2.getY() - p1.getY()*p2.getX();
				for(int k = 0; k < pList.size(); k++){
					Point p3 = pList.get(k);
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
}
