/*
 *MatrixGroup class packages the states of the compositions of fuzzy DESs.
 *Thus, a matrixgroup object contains a group of matrices(actually vector). 
 *
 **/

import java.text.DecimalFormat;
import java.util.*;

class MatrixGroup
{
	int size; // the size of the group 
	Matrix group[]; // the group of matrices(actually, vector or states)
	
	public MatrixGroup(int size)
	{
		this.size = size;
	}
	
	public MatrixGroup(int size, Matrix g[])
	{
		this.size = size;
		group = new Matrix[size];
		for(int i=0;i<size;i++)
			group[i] = g[i];
	}
   /*
    *judge whether two groups of vectors are equal
    **/
   public static boolean isEqual(MatrixGroup mg1, MatrixGroup mg2)
   {
   		if(mg1.size != mg2.size)
   			return false;
    	for(int i=0;i<mg1.size;i++)
    		if(!Matrix.isEqual(mg1.group[i],mg2.group[i]))
    			return false;
    	return true;
   }
   
   
   /*
    *generate a string to reprsent the group of vectors.
    *
    **/
   public String toString()
   {
   		StringBuffer result = new StringBuffer("");
   		for(int i=0;i<size;i++)
   			result.append(group[i].toString2());
   		result.append("\n");
   		return result.toString();
   }
   
     /*
    *define a group of states (g) evolute with the same event(acr).
    *
    *
    **/
   public static MatrixGroup getMaxMin(MatrixGroup g, Matrix acr)
   {
   	 	MatrixGroup result = new MatrixGroup(g.size);
   	 	result.group = new Matrix[g.size];
   	 	for(int i=0;i<g.size;i++)
   	 	{
   	 		result.group[i] = Matrix.getMaxMin(g.group[i],acr);
   	 	}
   	 	return result;
   }
	
}