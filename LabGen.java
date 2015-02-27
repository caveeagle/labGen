 /**
 *   Class for labyrinth generating
 *   @author Efremov Victor
 *   @version 1.0
 */

class LabGen
{
     /** Input file name, for initial condition */
     static String fileIn = "lab_in.txt";
     
     /** Output file name */
     static String fileOut = "lab_out.txt";
     
     /** labyrinth size X */
     static int sizeX = 92 ;
     
     /** labyrinth size Y */
     static int sizeY = 42 ;
     
     
     
     public static void main (String[] args)
     {
        /*  Read input file */
        In in = new In(fileIn);
        char[][] blocks = new char[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++)
            for (int j = 0; j < sizeY; j++)
                blocks[i][j] = in.readChar();
        
        /*  Write output file */
        Out out = new Out(fileOut);
        for (int i = 0; i < sizeX; i++)
            for (int j = 0; j < sizeY; j++)
                out.print(blocks[i][j]);
        
        System.out.println("Job done");
     }
}

