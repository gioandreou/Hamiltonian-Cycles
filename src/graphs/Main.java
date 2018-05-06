package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This program implements the Hamiltonian Cycle algorithm.
 * 
 * This is the main class of the program where all the operations are made.
 * 
 * @author Giorgos Andreou AEM: 2334 andreoug@csd.auth.gr
 * 
 *
 */
public class Main {
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
	
		
		/**
		 * Reading the text file which contains the graph
		 */
		System.out.println("The graph must be in this form to in order to be read: ");
		System.out.println("----------------------------");
		System.out.println("row col");
		System.out.println("val11 val12 val13 ...");
		System.out.println("val21 val22 val23 ...");
		System.out.println("val31 val32 val33 ...");
		System.out.println("...");
		System.out.println("");
		System.out.println("----------------------------");
		System.out.println("Enter the file name with extension, with is located in the same folder as program: ");
		Scanner input = new Scanner(System.in);
		File file = new File(input.nextLine());
        input = new Scanner(file); 
		
        
        /**
         * Reading from file the first numbers which indicate the size of the graph,
         * in order to create a proper 2-d array containing values. 
         */
        int row = input.nextInt();
		int col = input.nextInt();
		String[][] graph = new String[row][col];
		char[][] gr = new char[row][col];
		
		/**
         * parsing the values of the graph into vectro's attributes
         * and simultaneously creating the M array which takes only at every non ZERO attribute of M1 the last
         * letter 
         */
		for(int r=0; r<row; r++) {
		   for(int c=0; c<col; c++) {
			   String string = input.next();
		      graph[r][c] = string;
		      gr[r][c] = string.charAt(string.length() - 1);

		  }
		}
		/*
		 * scanner is closed after reading all numbers
		 */
		input.close();
		
		
		/**
		 * Printing back the graph, clearly for better viewing and process-understanding purposes
		 */
		System.out.println("=====");
		System.out.println("The original Graph:");
		for(int r=0; r<row; r++) {
			   for(int c=0; c<col; c++) {
			      System.out.print(graph[r][c]+" ");

			  }
			   System.out.println();
			}	
		/**
		 * Printing also the M array
		 */
		System.out.println("=====");
		System.out.println("The M matrix:");
		System.out.println("=====");
		for(int r=0; r<row; r++) {
			   for(int c=0; c<col; c++) {
			      System.out.print(gr[r][c]+" ");

			  }
			   System.out.println();
			}
		/**
		 * Calling the hamilton method size-2 times to find the cycles.
		 * row or col either way is the same
		 */
		for(int i=0; i<row-2; i++){
		graph=hamilton(graph,gr);
		System.out.println("============");
		System.out.println("Step:"+(i+1));
		System.out.println("============");
		for(int r=0; r<row; r++) {
			   for(int c=0; c<col; c++) {
			      System.out.print(graph[r][c]+" ");

			  }
			   System.out.println();
			}	
		}
		
		
	}
	
	/**
	 * This is the method to compute the multiplication of the arrays.
	 * A 2-D String array and a 2-d char Array, M1 and M arrays are parsed as parameteres
	 * @param graph, gr
	 * @return	the final Matrix made by Hadamard product
	 */
	public static String[][] hamilton(String[][] A,char[][] B){
		/**
		 * parsing the sizes 
		 */
		int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;
		
        /**
         * Initialization of the new array -matrix
         * and putting 0s at all positions
         */
        String [][]C = new String[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = "0";
            }
        }
        /*
         * Matrix multiplication algorithm 
         * Configurated to add at C matrix, M1 attribute which has a String and the char of the M matrix
         * Just like multiplication IF there is a 0 the result of the string is 0 
         * and according to Hamilton method, the symbol of M must not be contained in the counter String of 
         * the Mj-1
         * 
         */
        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    if(!A[i][k].equals("0")){
                    	if(B[k][j]!='0'){
                    		if(A[i][k].indexOf(B[k][j])==-1){
                    		
                    			
                    			/*
                    			 * just commented out, @debug purposes
                    			 */
                    			//System.out.println("C["+i+"]["+j+"]= A["+i+"]["+k+"]+B["+k+"]["+j+"]");
                    			//System.out.println("C["+i+"]["+j+"]="+A[i][k]+"+"+B[k][j]);
                    			//System.out.println();
                    			
                    			C[i][j] = A[i][k]+B[k][j];
                    			
                    			if(C[i][j].contains("0")){
                    				C[i][j]="0";
                    			}
                    		}
                    		}
                    	}
                    }
        
            }
            }
		return C;
	}

}
