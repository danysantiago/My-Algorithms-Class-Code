import java.util.Random;


public class Point implements Comparable<Point>{

	static private Random rand = new Random();

	private int x;
	private int y;

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Point(){
		x = -400 + rand.nextInt(801);
		y = -400 + rand.nextInt(801);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

	public boolean equals(Point p){
		return p.getX()==this.x && p.getY()==this.y;
	}


	/**
	 * Comparator based on a clockwise way to order points.
	 * 
	 * -----------
	 * | 1  | 2  |
	 * -----|-----   <-- Quadrants priority
	 * | 4	| 3	 |
	 * -----------
	 */
	@Override
	public int compareTo(Point p) {
		if(y > 0 && p.getY() < 0){
			return 1;
		} else if (y < 0 && p.getY() > 0){
			return -1;
		} else {
			if(x > 0 && p.getX() < 0){
				if(y > 0){
					return -1;
				} else {
					return 1;
				}
			} else if(x < 0 && p.getX() > 0){
				if(y > 0){
					return 1;
				} else {
					return -1;
				}
			} else {
				if(y >= 0 && p.getY() >= 0){
					if(x <= p.getX()){
						return 1;
					} else {
						return -1;
					}
				}else{
					if(x >= p.getX()){
						return 1;
					} else {
						return -1;
					}
				}
			}
		}
		
	}


}
