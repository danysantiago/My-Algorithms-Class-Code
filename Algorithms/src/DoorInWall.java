
public class DoorInWall {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int a=100000; a <= 100000; a++){
			//long sTime = System.nanoTime();
			int doorPos = a;
			int dir = 1; //(1 right, -1 left)
			boolean found = false;
			int i = 2;
			int pos = 0;
			int stepsTaken = 0;
			int n = 0;
			while(!found){
				for(int s = 1; s <= i; s++){
					if(pos == doorPos){
						found = true;
						break;
					}
					pos += dir;
					stepsTaken++;
				}
				System.out.println(pos + "\t" + stepsTaken);
				pos = 0;
				stepsTaken += Math.abs(pos);
				dir *= -1;
				i *= Math.pow(2, n++);
			}

			//System.out.println(stepsTaken);
			//System.out.println(System.nanoTime() - sTime);
		}
	}
}
