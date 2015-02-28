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


    /**
    *   GET labyrinth element
    *   @param X
    *   @param Y
    */
    public char blockGet(int X,int Y)
    { 
        return blocks[X][Y];
    } 

    /**
    *   SET labyrinth element
    *   @param X
    *   @param Y
    *   @param C: block value
    */
    public void blockSet(int X,int Y, char C)
    { 
        blocks[X][Y] = C;
    } 


     
     public static void main (String[] args)
     {
        LabGen lab = new LabGen(fileIn);
        
        //char bc = lab.blockGet(46,21);

        //lab.blockSet(45,20,'@');
        
        
        lab.writeInFile(fileOut);
        
        System.out.println("Job done");
     }
}

