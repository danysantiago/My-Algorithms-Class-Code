import java.util.Random;

public class Matrix{
	private static Random rand = new Random();
	private static final int randMax = 10;
	
	public static final int Q1 = 0;
	public static final int Q2 = 1;
	public static final int Q3 = 2;
	public static final int Q4 = 3;
	
	private int[][] m;
	private int n;
	private int realN;
	
	public Matrix(int n) {
		this.n = n;
		if(powerOf2(n)){
			m = new int[n][n];
			realN = n;
		} else {
			double y = Math.floor(Math.log(n)/Math.log(2));
			realN = (int) Math.pow(2, y + 1);
			m = new int[realN][realN];
		}
	}
	
	public Matrix(int[][] m){
		this.m = m;
		this.n = this.realN =  m.length;
	}
	
	public void fillRandomly(){
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				m[i][j] = rand.nextInt(randMax);
	}
	
	public int size(){
		return n;
	}
	
	public int[][] getArray(){
		return m;
	}
	
	public int get(int r, int c){
		return m[r][c];
	}
	
	public void set(int value, int r, int c){
		m[r][c] = value;
	}
	
	public Matrix sum(Matrix m2){
		Matrix sum = new Matrix(n);
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				sum.set(this.get(i, j) + m2.get(i, j), i, j);
		
		return sum;
	}
	
	public Matrix substract(Matrix m2){
		Matrix sum = new Matrix(n);
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				sum.set(this.get(i, j) - m2.get(i, j), i, j);
		
		return sum;
	}
	
	public Matrix multiply(Matrix m2){
		Matrix mul = new Matrix(n);
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				int sum = 0;
				for(int k = 0; k < n; k++)
					sum += this.get(i, k)*m2.get(k, j);
				mul.set(sum, i, j);
			}
		}
		
		return mul;
	}
	
	public Matrix getSubMatrix(int sRow, int sCol, int n){
		int[][] C = new int[n][n];
		for(int i1 = 0, i2 = sRow; i1 < n; i1++, i2++)
            for(int j1 = 0, j2 = sCol; j1 < n; j1++, j2++)
                C[i1][j1] = m[i2][j2];
		return new Matrix(C);
	}
	
	public static boolean powerOf2(int n) {
		return (Math.log(n)/Math.log(2))%1 == 0;
	}

	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++)
				s += m[i][j] + "\t";
			s += "\n";
		}
		return s;
	}

	public void fillFromSubMatrix(Matrix m2, int sRow, int sCol) {
		for(int i1 = 0, i2=sRow; i1<m2.size(); i1++, i2++)
            for(int j1 = 0, j2=sCol; j1<m2.size(); j1++, j2++)
                m[i2][j2] = m2.get(i1,j1);
		
	}

	public int getRealN() {
		return realN = realN;
	}
			
}
