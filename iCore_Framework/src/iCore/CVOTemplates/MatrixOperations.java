package iCore.CVOTemplates;
/**
 * @author chayan, TUDelft
 * This class defines the matrix operations that are 
 * required for virtual sensing.
 */

public class MatrixOperations {

	public int changeSign(int loc) {
    	if(loc%2 == 0) return 1;
    	else return -1;
    }
	
	public Matrix addMatrices(Matrix matrix1, Matrix matrix2) throws MatrixOperationsException {
		int nrows=matrix1.getNrows(), ncols=matrix1.getNcols();
		if(nrows!=matrix2.getNrows() || ncols!=matrix2.getNcols())
			throw new MatrixOperationsException("matrices dimentions need to be matched.");
		
		Matrix mat = new Matrix(matrix1.getNrows(), matrix1.getNcols());
	    for (int i=0; i<nrows; i++) {
	        for (int j=0; j<ncols; j++) {
	            mat.setValueAt(i, j, matrix1.getValueAt(i,j) + matrix2.getValueAt(i,j));
	        }
	    }
	    return mat;
	}
    
	public Matrix multiplyMatrices(Matrix matrix1, Matrix matrix2) throws MatrixOperationsException {
		if(matrix1.getNcols() != matrix2.getNrows())
			throw new MatrixOperationsException("coloumn of first matrix need to be same as row of second matrix.");
		
		int nrows=matrix1.getNrows(), ncols=matrix2.getNcols();
		int nrowscols = matrix2.getNrows();
		Matrix mat = new Matrix(nrows, ncols);
	    for (int i=0; i<nrows; i++) {
	        for (int j=0; j<ncols; j++) {
	        	double sum = 0;
	        	for(int k=0; k<nrowscols; k++) {
	        		sum += matrix1.getValueAt(i,k)*matrix2.getValueAt(k,j);
	        	}
	            mat.setValueAt(i, j, sum);
	        }
	    }
	    return mat;
	}
    
    public Matrix transposeMatrix(Matrix matrix) {
        Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
        for (int i=0;i<matrix.getNrows();i++) {
            for (int j=0;j<matrix.getNcols();j++) {
                transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
            }
        }
        return transposedMatrix;
    }
    
	public Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_col) {
        Matrix mat = new Matrix(matrix.getNrows()-1, matrix.getNcols()-1);
        int r = -1;
        for (int i=0;i<matrix.getNrows();i++) {
            if (i==excluding_row)
                continue;
                r++;
                int c = -1;
            for (int j=0;j<matrix.getNcols();j++) {
                if (j==excluding_col)
                    continue;
                mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
            }
        }
        return mat;
    }
    
    public double determinant(Matrix matrix) throws MatrixOperationsException {
        if (!matrix.isSquare())
            throw new MatrixOperationsException("matrix need to be square.");
        if (matrix.size() == 1) {
        	return matrix.getValueAt(0, 0);
        }
        if (matrix.size()==2) {
            return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) - ( matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
        }
        double sum = 0.0;
        for (int i=0; i<matrix.getNcols(); i++) {
            sum += changeSign(i) * matrix.getValueAt(0, i) * determinant(createSubMatrix(matrix, 0, i));
        }
        return sum;
    }
    
    public Matrix cofactorMatrix(Matrix matrix)  throws MatrixOperationsException {
	    Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());
	    for (int i=0; i<matrix.getNrows(); i++) {
	        for (int j=0; j<matrix.getNcols(); j++) {
	            mat.setValueAt(i, j, changeSign(i) * changeSign(j) * determinant(createSubMatrix(matrix, i, j)));
	        }
	    }
	    return mat;
	}
    
    /**
	 * This function finds inverse of a square matrix.
	 * @param mat the matrix that need to be inverted
	 * @param d the dimension of the matrix, i.e. mat is a d-by-d matrix
	 * @return the inverted d-by-d matrix 
	 */
    public Matrix inverseMatrix(Matrix matrix) throws MatrixOperationsException {
        double det = determinant(matrix);
    	if(det == 0) {
    		throw new MatrixOperationsException("The matrix is singular");
    	}
        
    	Matrix mat = transposeMatrix(cofactorMatrix(matrix));
    	return mat.multiplyByConstant(1.0/det);
    }
    
    
    public Matrix QRDecomposition(Matrix matrix) throws MatrixOperationsException {
    	int nrows = matrix.getNrows();
    	int ncols = matrix.getNcols();
    	Matrix Q = new Matrix(nrows, ncols);
    	
    	for(int col=0; col<ncols; col++) {
    		double a[] = matrix.getColumnVector(col);
    		double u[] = matrix.getColumnVector(col);
    		for(int k=col-1; k>=0; k--) {
    			double e[] = Q.getColumnVector(k);
    			double dProduct = scalarMultiplication(a,e);
    	    	for(int i=0; i<a.length; i++) {
    	    		u[i] -= dProduct*e[i];        // u[col] -= <a[col]*e[k]>e[k]
    	    	}
    		}
    		double unorm = getNormal(u, 2);
    		for(int row=0; row<nrows; row++) {
    			Q.setValueAt(row, col, u[row]/unorm);
    		}
    	}
    	return Q;
    }
	
    
	public Matrix pseudoInverse(Matrix matrix) throws MatrixOperationsException {
		
		Matrix Q = QRDecomposition(matrix);
		Matrix R = multiplyMatrices(transposeMatrix(Q), matrix);
		
		Matrix mat = multiplyMatrices(inverseMatrix(R), transposeMatrix(Q));		
		return mat;
	}
	
	public double[] backSubstitution(Matrix U, double b[]) throws MatrixOperationsException {
		int nrows = U.getNrows();
		if(nrows != b.length)
			throw new MatrixOperationsException("Number of equations does not match with number of constants");
		
		double x[] = new double[nrows];
		for(int row=nrows-1; row>=0; row--) {
			x[row] = b[row];
			for(int i=row+1; i<nrows; i++) {
				x[row] -= x[i]*U.getValueAt(row,i);
			}
			x[row] = x[row]/U.getValueAt(row,row);
		}
		return x;
	}
	
	public double[] minNormOverDetSystem(Matrix A, double b[]) throws MatrixOperationsException {
		if(A.getNrows() != b.length)
			throw new MatrixOperationsException("Number of equations does not match with number of constants");
		
		if(A.getNrows() <= A.getNcols())
			throw new MatrixOperationsException("Training Data Size is too small.");
		
		Matrix Q = QRDecomposition(A);
		Matrix R = multiplyMatrices(transposeMatrix(Q), A);
		/*for(int i=0; i<Q.getNrows(); i++) {
			for(int j=0; j<Q.getNcols(); j++) {
				double value = (double)Math.round(Q.getValueAt(i,j)*10000)/10000;
				System.out.print(value + "\t");		
			}
			System.out.print("\n");
		}
		for(int i=0; i<R.getNrows(); i++) {
			for(int j=0; j<R.getNcols(); j++) {
				double value = (double)Math.round(R.getValueAt(i,j)*10000)/10000;
				System.out.print(value + "\t");		
			}
			System.out.print("\n");
		}*/
		
		Matrix B = new Matrix(b, b.length, 1);
		Matrix newB = multiplyMatrices(transposeMatrix(Q), B);
		double x[] = backSubstitution(R, newB.getColumnVector(0));
		return x;
	}
	
	
	private double scalarMultiplication(double a[], double e[]) {
    	double result=0;
    	for(int i=0; i<a.length; i++) {
    		result += a[i]*e[i];
    	}
    	return result;
    }
    
    private double getNormal(double u[], int order) {
    	double norm, sum = 0;
    	for(int i=0; i<u.length; i++) {
    		sum += Math.pow(u[i], order);
    	}
    	norm = Math.pow(sum, (double)1/order);
    	return norm;
    }
    
	
	/*public class  MatrixOperationsException extends Exception {

		
		private static final long serialVersionUID = 1L;

		MatrixOperationsException(String str) {
   		 	System.err.println(str);
   	 	}
	}*/
}