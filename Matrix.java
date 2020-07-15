/*
 *Matrix class packages the fuzzy states (vector), events(matrix) 
 *and their corresponding operations  
 *
 **/
import java.text.DecimalFormat; 
import java.util.*;
 
class Matrix{
 
    public int row;  // the number of row
    public int col;  // the number of column
    double [][]Data; // the values of the event matrix or state vector().
 
    
 
    public Matrix(int row, int col,double [][]Data) 
    {
        this.row = row;
        this.col = col;
        this.Data = Data;
    }
 
    public void setMatrix(int row , int col, double value) 
    {
        this.Data[row - 1][col - 1] = value;
    }
    
    public double getMatrix(int row, int col) 
    {
        return Data[row - 1][col - 1] ;
    }
 
    public int width() 
    {
        return row;
    }
 
    public int height() 
    {
        return col;
    }
    /*
     *define the addition of two matrix, 
     *return the result
     **/
    public Matrix add(Matrix b) 
    {
        if(this.width() != b.width() && this.height() != b.height()) 
            return null;
        double add[][] = new double[this.row][this.col];
        for(int i = 0;i<col;i++) 
            for(int j = 0;j<row;j++) 
                add[i][j] = this.Data[i][j] + b.Data[i][j];
 
        Matrix another = new Matrix(this.col,this.row,add);    
        System.out.println("after add:");
        return another;
    }
 
	  /*
     *define the transpose of the matrix, 
     *return the result
     **/
   public Matrix transpose() {
        double tran[][] = new double[this.col][this.row];
        for(int i = 0;i<this.row;i++) 
            for(int j = 0;j<this.col;j++) 
                 tran[j][i] = this.Data[i][j];
        Matrix another = new Matrix(this.col,this.row,tran);
        System.out.println("after transpose:");
        return another;
    }
    
   /*
    *return a string represent the matrix
    **/
   public String toString() {
        DecimalFormat df = new DecimalFormat("0.0");
        String result = "";
        for(int i = 0;i<this.row;i++) 
        {
            result += df.format(Data[i][0]);
            for(int j = 1;j<this.col;j++)
                result += " " + df.format(Data[i][j]);
            result +=  "\n";
        }
        return result;
    }
    
    /*
    *return a string represent the matrix.
    *toString2() is suggusted used when row = 1, that is, toString2() is for vector.
    *
    **/
    public String toString2() 
    {
        DecimalFormat df = new DecimalFormat("0.0");
        String result = "[";
        for(int i = 0;i<this.row;i++) {
            result += df.format(Data[i][0]);
            for(int j = 1;j<this.col;j++) 
                result += " " + df.format(Data[i][j]);
            result +=  "] ";
        }
        return result;
    }
   
   	  /*
     *define the multiplication of two matrix, 
     *return the result
     **/
   public Matrix multiply(Matrix b) 
   {
        double mul[][] = new double[this.row][b.col];
        double temp = 0;
        for(int i = 0;i<this.row;i++)
            for(int k = 0;k<b.col;k++) {
                for(int j = 0;j<this.col;j++)
                    temp += this.Data[i][j] * b.Data[j][k];
                mul[i][k] = temp;
                temp = 0;
            }
        Matrix result = new Matrix(this.row, b.col, mul);
        System.out.println("after multiply:");
        return result;
    }
   
   /*
    *judge whether two matrices are equal
    **/
   public static boolean isEqual(Matrix m1, Matrix m2)
   {
   		if(m1.col != m2.col || m1.row != m2.row)
   			return  false;
   		for(int i = 0; i < m1.row ; i++)
    		for(int j = 0; j < m1.col ; j++)
    			if (  Math.abs( m1.Data[i][j] - m2.Data[i][j]) > 0.00001  )
    				return  false;
    	return true;
   }
   
     /*
     *define the max-min operation of two matrix, 
     *return the result
     **/
   public static Matrix getMaxMin(Matrix m1, Matrix m2)
    {
    	double [][]data = new double[m1.row][m2.col];
    	for(int i = 0; i < m1.row ; i++)
    		for(int k = 0; k < m2.col ; k++)
    		{
    			data[i][k] = 0;
    			double tmp = 0;
    			for(int j = 0; j < m1.col ; j++)
    			{
    				if(m1.Data[i][j] < m2.Data[j][k])
    					tmp = m1.Data[i][j];
    				else
    					tmp = m2.Data[j][k];
	    			if ( tmp > data[i][k])
						data[i][k] = tmp;
    			}
    		}	
    	Matrix result = new Matrix(m1.row, m2.col, data);
    	return result;
    }  
}