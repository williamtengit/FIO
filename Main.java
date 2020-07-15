import java.text.DecimalFormat;
import java.util.*;
 
class Main
{
    static int col = 5; 
    static int row = 5;
    static      double [][]x1 = {{0.8, 0,     0,    0,    0}};
    static     double [][]x2 =  {{0,   1,  0,    0,    0}};   
    static     double [][]x3 =  {{0,   0,     0.6,  0,    0}};
    static     double [][]x4 =  {{0,   0,     0,    0.2, 0}};
    static     double [][]x5 =  {{0,   0,     0,    0,   0.1}};
        /*
        double [][]x1 =  {{0.2, 0,     0,    0,    0}};
        double [][]x2 =  {{0,   0.25,  0,    0,    0}};   
        double [][]x3 =  {{0,   0,     0.2,  0,    0}};
        double [][]x4 =  {{0,   0,     0,    0.2, 0}};
        double [][]x5 =  {{0,   0,     0,    0,   0.15}};
        */
    static     Matrix ax1 = new Matrix(1,5,x1);
    static     Matrix ax2 = new Matrix(1,5,x2);
    static     Matrix ax3 = new Matrix(1,5,x3);
    static     Matrix ax4 = new Matrix(1,5,x4);
    static     Matrix ax5 = new Matrix(1,5,x5);
    static     Matrix ag12345[] = {ax1,ax2,ax3,ax4,ax5};
    static     MatrixGroup gx12345 = new MatrixGroup(5,ag12345);
      //event matrix A_{\sigma_{1}^{1}}
    static double cr11[][] = { 
    					{0.4, 1,   1,    0, 0},
                        {0.6, 1,   0.8,  0, 0},
                        {1,   0.8, 0.6,  0, 0},
                        {0,    0,    0,  0, 0},
                        {0,    0,    0,  0, 0}
                      };
                      
    //event matrix A_{\sigma_{1}^{2}}
    static double cr12[][] = { 
    					{0, 0, 0,   0, 0},
                        {0, 0, 0.4, 1, 0},
                        {0, 0, 1, 0.6, 1},
                        {0, 0, 0,  0,  0},
                        {0, 0, 0,  0,  0}
                      };        
    //event matrix A_{\sigma_{2}^{1}}  
    static double cr21[][] = { 
    					{0, 0,   0,    0,    0},
                        {0, 0,   0,    0,    0},
    					{0, 0.2, 0.2,  0,    0},
                        {0, 1,   0.8,  0,    0},
                        {0, 0,   1,    0,    0}
                      };
    //event matrix A_{\sigma_{2}^{2}}                  
    static double cr22[][] = { 
                        {0, 0, 0,    0,    0   },
    					{0, 0, 0,    0,    0   },
                        {0, 0, 0.6,  0.4,  1   },
                        {0, 0, 0.8,  1,    0   },
                        {0, 0, 1,    0,    0.6 }
                       };
        
    /*   
      //event matrix A_{\sigma_{1}^{1}}
    static double cr11[][] = { 
    					{0.25, 0.33, 0.5,   0, 0},
                        {0.25, 0.33, 0.25,  0, 0},
                        {0.50, 0.33, 0.25,  0, 0},
                        {0,    0,    0,     0, 0},
                        {0,    0,    0,     0, 0}
                      };
                      
    //event matrix A_{\sigma_{1}^{2}}
    static double cr12[][] = { 
    					{0, 0, 0,    0,    0},
                        {0, 0, 0.33, 0.66, 0},
                        {0, 0, 0.66, 0.33, 1},
                        {0, 0, 0,    0,    0},
                        {0, 0, 0,    0,    0}
                      };        
    //event matrix A_{\sigma_{2}^{1}}  
    static double cr21[][] = { 
    					{0,    0,  0,    0,    0},
                        {0,    0,  0,    0,    0},
    					{0, 0.33, 0.25,  0,    0},
                        {0, 0.66, 0.25,  0,    0},
                        {0, 0,    0.50,  0,    0}
                      };
    //event matrix A_{\sigma_{2}^{2}}                  
    static double cr22[][] = { 
                        {0, 0, 0,    0,    0   },
    					{0, 0, 0,    0,    0   },
                        {0, 0, 0.33, 0.5,  0.66},
                        {0, 0, 0.33, 0.5,  0   },
                        {0, 0, 0.33, 0,    0.33}
                       };
     */                     
    static Matrix acr11 = new Matrix(col,row,cr11);
	static Matrix acr12 = new Matrix(col,row,cr12);
	static Matrix acr21 = new Matrix(col,row,cr21);
	static Matrix acr22 = new Matrix(col,row,cr22);
	//scret initial-final state-pair function
	static double s[][] = {
							{0.2,0.8,0.5,0.2,0.8},
							{0.8,0.2,0.5,0.8,1.0},
							{0.5,0.5,0.6,0.4,0.8},
							{0.2,0.8,0.4,0.4,0.8},
							{0.8,1.0,0.8,0.8,1.0}
						  };
	/*			  
	static double s[][] = {
							{0.2,0.5,0.3,0.2,0.6},
							{0.5,0.7,0.5,0.8,1.0},
							{0.3,0.5,0.6,0.2,0.7},
							{0.2,0.8,0.2,0.4,0.8},
							{0.6,1.0,0.7,0.8,1.0}
						  };
	*/			  
    //non-scret initial-final state-pair function
    static double ns[][] = new double[s.length][s[0].length];

 	//searing all the reachable states from the initial state.
 	public static Vector getAllState(Vector allState)
 	{
 		for(int i=0; i<allState.size(); i++)
        {
        	MatrixGroup tmp = (MatrixGroup)allState.elementAt(i);
     	 	MatrixGroup t1 = MatrixGroup.getMaxMin(tmp,acr11);
        	MatrixGroup t2 = MatrixGroup.getMaxMin(tmp,acr12);
        	MatrixGroup t3 = MatrixGroup.getMaxMin(tmp,acr21);
        	MatrixGroup t4 = MatrixGroup.getMaxMin(tmp,acr22);  
        	/*
        		if(i==0)
        		{
        			System.out.println (t1.toString());
        			System.out.println (t2.toString());
        			System.out.println (t3.toString());
        			System.out.println (t4.toString());
        		} 
        	*/     		
        	boolean hasE;	
        	hasE = false;
        	for(int j=0; j<allState.size(); j++)
        	{
        		MatrixGroup tmp2 = (MatrixGroup) allState.elementAt(j);
        		if( MatrixGroup.isEqual( tmp2 , t1) )
        			hasE = true;
        	}
        	if(!hasE)
        		allState.add(t1);
        			
        	hasE = false;
        	for(int j=0; j<allState.size(); j++)
        	{
        		MatrixGroup tmp2 = (MatrixGroup) allState.elementAt(j);
        		if( MatrixGroup.isEqual( tmp2 , t2) )
        			hasE = true;
        	}
        	if(!hasE)
        		allState.add(t2);
        	
        	
        	hasE = false;
        	for(int j=0; j<allState.size(); j++)
        	{
        		MatrixGroup tmp2 = (MatrixGroup) allState.elementAt(j);
        		if( MatrixGroup.isEqual( tmp2 , t3) )
        			hasE = true;
        	}
        	if(!hasE)
        		allState.add(t3);
        		
            hasE = false;
        	for(int j=0; j<allState.size(); j++)
        	{
        		MatrixGroup tmp2 = (MatrixGroup) allState.elementAt(j);
        		if( MatrixGroup.isEqual( tmp2 , t4) )
        			hasE = true;
        	}
        	if(!hasE)
        		allState.add(t4);			
        }
        return allState;	
 	}	   
   
    public static void main(String[] args) 
    {    
    	double lamda = 1;  
        Vector allState = new Vector();
        allState.add(gx12345);
        
        allState = getAllState(allState);
        System.out.println(allState.size());
	    for(int ii=0;ii<s.length;ii++)// calculate the non-scret initial-final state-pairs functions
	    {
	   	   for(int jj=0;jj<s[ii].length;jj++)
	   	   {
	   	   		ns[ii][jj] = 1 - s[ii][jj];
	   	   	//	System.out.print(ns[ii][jj]+" ");
	   	   }
	   	   //System.out.println();      
	    }
        for(int i=0; i<allState.size(); i++)
        {
        	   MatrixGroup tmp = (MatrixGroup)allState.elementAt(i);
        	   System.out.print(tmp);
        	   double [][]x = new double [tmp.group.length][tmp.group[0].Data[0].length];
        	   for(int j=0;j<tmp.group.length;j++)  //obtain all the state vectors in the group
        	   		x[j] = tmp.group[j].Data[0];
     
        	   //System.out.println(tmp.group.length);
        	   double [][]xomega = new double[1][tmp.group[0].Data[0].length]; 
        	   
        	   for(int j=0;j<tmp.size;j++)// obtain the global state vector xomega
	        	   for(int k=0; k<tmp.group[0].Data[0].length;k++)
	        	   		if(tmp.group[j].Data[0][k] > xomega[0][k])
	        	   			xomega[0][k] = tmp.group[j].Data[0][k];
	        	          	   		
        	   /*
			   for(int k=0; k<tmp.group[0].Data[0].length;k++)
			   {
			   		System.out.print(x[0][k]+" ");
			   }
			   System.out.println();
			   */	
      		   Matrix ax = new Matrix(1,5,xomega);
        	   Matrix ag[] = {ax}; 
        	   MatrixGroup gx = new MatrixGroup(1,ag);
        	   Vector allState2 = new Vector();
        	   allState2.add(gx);       
	       	   
	       	   allState2 = getAllState(allState2);//obtain the set of states from initial state xomega
	       	   
        	   
        	   
        	   //if (i==0) System.out.println(allState2.size());
        	   
        	   if(i == 0)
        	     {
        	     	System.out.println(allState2.size());
        	   	   for(int k=0;k<allState2.size();k++)
        	   	   {
        	   	   	    MatrixGroup tmp2 = (MatrixGroup)allState2.elementAt(k);
        	   	   		System.out.print(tmp2); 
        	   	   		//System.out.println();
        	   	  }         	   	
        	   }
        	    		   
    		   double maxmin_x_s = 0, maxmin_x_ns = 0;
        	   
        	   for(int ii=0;ii<x.length;ii++)
        	   {
        	   		for(int jj=0; jj<x[ii].length; jj++)
        	   		{
        	   			double tmptmp  = x[ii][jj]<s[ii][jj]?x[ii][jj]:s[ii][jj];
        	   			if(tmptmp  > maxmin_x_s  )
        	   				maxmin_x_s = tmptmp;
        	   				
        	   			double tmptmp2 = x[ii][jj]<ns[ii][jj]?x[ii][jj]:ns[ii][jj];
        	   			if(tmptmp2 > maxmin_x_ns)
        	   				maxmin_x_ns = tmptmp2;
        	   		} 			
        	   }
        	  // System.out.println("maxmin_x_s = "+maxmin_x_s+" maxmin_x_ns="+maxmin_x_ns);  
        	   double phi, psi,gamma;
        	   for(int k=0;k<allState2.size();k++)
    	   	   {
    	   	   		phi = maxmin_x_s;
    	   	   		psi = maxmin_x_ns;
    	   	   	    MatrixGroup tmp2 = (MatrixGroup)allState2.elementAt(k);
    	   	   	    double max = 0;
    	   	   	    for(int kk = 0; kk < tmp2.group[0].Data[0].length; kk++)
    	   	   	    	if(tmp2.group[0].Data[0][kk] > max)
    	   	   	    		max = tmp2.group[0].Data[0][kk];
    	   	   	    if(max < phi)
    	   	   	    	phi = max;
    	   	   	    if(max < psi)
    	   	   	    	psi = max;
    	   	   	    gamma = (phi <= psi?1:psi); 
	    	   	   if(gamma < lamda)
	    	   	   		lamda = gamma;
	    		   if(i==0)	   System.out.println("    phi = "+phi+" psi="+psi+" gamma = " + gamma+" lamda = " +lamda ); 
    	   	   }
    	     if(i==0)	System.out.println(" lamda = " + lamda);         	
    	} 
    	System.out.println("                           lamda = " + lamda);      
 }
}