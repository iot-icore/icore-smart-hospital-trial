package iCore.CVOTemplates;

public class Matrix {
    private int nrows;
    private int ncols;
    private double[][] data;

    public Matrix(double[][] dat) {
        this.data = dat;
        this.nrows = dat.length;
        this.ncols = dat[0].length;
    }

    public Matrix(int nrow, int ncol) {
        this.nrows = nrow;
        this.ncols = ncol;
        data = new double[nrow][ncol];
    }
    
    public Matrix(double[] dat, int nrow, int ncol) {
        this.nrows = nrow;
        this.ncols = ncol;
        data = new double[nrow][ncol];
        for(int i=0,k=0; i<nrow; i++) {
        	for(int j=0; j<ncol; j++) {
        		data[i][j] = dat[k++];
        	}
        }
    }
    
    public int getNrows() {
    	return nrows;
    }
    
    public int getNcols() {
    	return ncols;
    }
    
    public void setValueAt(int i, int j, double value) {
    	data[i][j] = value;
    }
    
    public double getValueAt(int i, int j) {
    	return data[i][j];
    }
    
    public double[] getRowVector(int row) {
    	double vec[] = new double[ncols];
    	for(int j=0; j<ncols; j++) {
    		vec[j] = data[row][j];
    	}
    	return vec;
    }
    
    public double[] getColumnVector(int col) {
    	double vec[] = new double[nrows];
    	for(int i=0; i<nrows; i++) {
    		vec[i] = data[i][col];
    	}
    	return vec;
    }
    
    public boolean isSquare() {
    	if(nrows == ncols) return true;
    	else return false;
    }
    
    public int size() {
    	return nrows;
    }
    
    public void roundValues() {
    	for (int i=0; i<nrows; i++) {
	        for (int j=0; j<ncols; j++) {
	        	data[i][j] = (double)Math.round(data[i][j]*10000)/10000;
	        }
	    }
    }
    
    public Matrix addConstant(double value) {
    	Matrix mat = new Matrix(nrows, ncols);
	    for (int i=0; i<nrows; i++) {
	        for (int j=0; j<ncols; j++) {
	            data[i][j] = data[i][j]+value;
	        }
	    }
	    return mat;
    }
    
    public Matrix multiplyByConstant(double value) {
    	Matrix mat = new Matrix(nrows, ncols);
	    for (int i=0; i<nrows; i++) {
	        for (int j=0; j<ncols; j++) {
	            mat.setValueAt(i, j, data[i][j]*value);
	        }
	    }
	    return mat;
    }
}