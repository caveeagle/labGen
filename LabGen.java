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
    *   @param C block value
    */
    public void blockSet(int X,int Y, char C)
    { 
        blocks[X][Y] = C;
    } 
    

    /**
    *   SET labyrinth element
    *   @param X ����� ������ �����
    *   @param Y ����� ������ �����
    *   @param D ����������� ����� ( 1: �����, 2: ������, 3:����, 4:����� )
    */
    public void drawLine(int X,int Y, int D)
    { 
        int x0 = X;
        int y0 = Y;

        int dX = 0;
        int dY = 0;

        switch (D) {
            case 1:  dY = -1 ;
                     break;
            case 2:  dX = 1 ;
                     break;
            case 3:  dY = 1 ;
                     break;
            case 4:  dX = -1 ;
                     break;
            default: throw new IllegalArgumentException();
        }        
        
        int x1, y1, x2, y2;
        
        while(true)
        {
            x1 = x0+dX;
            y1 = y0+dY;
            
            x2 = x1+dX;
            y2 = y1+dY;
            
            char ch1 = this.blockGet(x1,y1);
            
            if( Character.isWhitespace(ch1) )
            {
              this.blockSet(x0,y0,'*');  
            }

            char ch2 = this.blockGet(x2,y2);

            if( Character.isWhitespace(ch2) && Character.isWhitespace(ch1) )
            {
                x0 = x0+dX;
                y0 = y0+dY;
            }
            else
            {
                return;
            }
        }
    } 
    
    
    private int[][] pointsInLab;
    private int pointsInLabNum;
    
    private void markOddPoints()
    {
        pointsInLab = new int[sizeX][sizeY];
        pointsInLabNum = 0;
        
        /** Clear array */
        for (int j = 0; j < sizeY; j++)
        {
            for (int i = 0; i < sizeX; i++)
            {
                pointsInLab[i][j] = 0; // clear array
            }
        }

        /** Mark Odd Points */
        for (int j = 3; j < sizeY-2; j+=2)
        {
            for (int i = 3; i < sizeX-2; i+=2)
            {
              if( j>16 && j< 25 && i>36 && i<57 ) continue;
              
              char ch = this.blockGet(i,j);
              
              if( Character.isWhitespace(ch) )
              { 
                pointsInLabNum++;
                pointsInLab[i][j] = pointsInLabNum;  
              }
            }
        }

 
    } 
    
    public void makeLabyrinth()
    {
       this.markOddPoints(); 
       
         
    }

     
     public static void main (String[] args)
     {
        LabGen lab = new LabGen(fileIn);
        
        lab.makeLabyrinth();
        
        //lab.drawLine(46,7,3);
        
        //char bc = lab.blockGet(46,21);

        //lab.blockSet(45,20,'@');
        
        
        lab.writeInFile(fileOut);
        
        System.out.println("Job done");
     }
}

