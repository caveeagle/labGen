 /**
 *   Class for labyrinth generating
 *   @author Efremov Victor
 *   @version 1.0
 */

public class LabGen
{
     /** Input file name, for initial condition */
     static String fileIn = "lab_in.txt";
     
     /** Output file name */
     static String fileOut = "lab_out.txt";
     
     /** labyrinth size X */
     static int sizeX = 92 ;
     
     /** labyrinth size Y */
     static int sizeY = 42;
     
     /** labyrinth blocks */
     private char[][] blocks;
     
    /**
    *   Construct a labyrinth
    *   @param inputFilename
    */
    public LabGen(String inputFilename)
    {
        In in = new In(inputFilename);
        blocks = new char[sizeX][sizeY];
        
        for (int j = 0; j < sizeY; j++)
        {
            String s = in.readLine();
            char[] chars = s.toCharArray();
            
            for (int i = 0; i < sizeX; i++)
            {
                char bc = chars[i];
                blocks[i][j] = bc;
            }
        }
        
    } 

    /**
    *   Write labyrinth into file
    *   @param outputFilename
    */
    public void writeInFile(String outputFilename)
    {
        Out out = new Out(outputFilename);
        for (int j = 0; j < sizeY; j++)
        {
            for (int i = 0; i < sizeX; i++)
            {
                out.print(blocks[i][j]);
            }
            out.print('\n');
        }
    } 

     
     public static void main (String[] args)
     {
        LabGen labyrinth = new LabGen(fileIn);
        
        labyrinth.writeInFile(fileOut);
        
        System.out.println("Job done");
     }
}

