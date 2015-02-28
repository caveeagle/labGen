 /**
 *   Class for labyrinth generating
 *   @author Efremov Victor
 *   @version 1.0
 */

public class LabGen
{
     /** labyrinth size X */
     static int sizeX = 92 ;
     
     /** labyrinth size Y */
     static int sizeY = 42;
     
     /** labyrinth blocks */
     private char[][] blocks;
     
     private char wallBlock = '#';
     private char emptyBlock = ' ';

    /* Central room coord */
    private int mainRoomXmin = 36;
    private int mainRoomXmax = 56;
    private int mainRoomYmin = 16;
    private int mainRoomYmax = 25;
    
     
    /* ========================================= */
    
    /**
    *   Construct a labyrinth from file
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
    *   Construct a labyrinth without files
    */
    public LabGen()
    {
        blocks = new char[sizeX][sizeY];

        for (int j = 0; j < sizeY; j++)
        {
            for (int i = 0; i < sizeX; i++)
            {
               blocks[i][j] = emptyBlock;
            }
        }
        
        for(int j = 0; j < sizeY; j++)
        {
                blocks[0][j] = wallBlock;
                blocks[sizeX-1][j] = wallBlock;
        }
        for(int i = 0; i < sizeX; i++)
        {
                blocks[i][0] = wallBlock;
                blocks[i][sizeY-1] = wallBlock;
        }
        
        for(int i = mainRoomXmin+1; i <= mainRoomXmax-1; i++)
        {
            blocks[i][mainRoomYmin] = wallBlock;
            blocks[i][mainRoomYmax] = wallBlock;
        }
        for(int j = mainRoomYmin+1; j <= mainRoomYmax-1; j++)
        {
            blocks[mainRoomXmin][j] = wallBlock;
            blocks[mainRoomXmax][j] = wallBlock;
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
    *   @param X точка начала линии
    *   @param Y точка начала линии
    *   @param D направление линии ( 1: вверх, 2: вправо, 3:вниз, 4:влево )
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
              this.blockSet(x0,y0,wallBlock);  
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
    
    
    private int[] pointsInLabX = new int[sizeX*sizeY];
    private int[] pointsInLabY = new int[sizeX*sizeY];
    private int pointsInLabNum = 0;
    
    private void markOddPoints()
    {
        /** Find Odd Points */
        for (int j = 2; j < sizeY-2; j+=2)
        {
            for (int i = 2; i < sizeX-2; i+=2)
            {
              if( j>=mainRoomYmin-1 && j<=mainRoomYmax+1 && i>=mainRoomXmin-1 && i<=mainRoomXmax+1 ) continue;
              
              char ch = this.blockGet(i,j);
              
              if( Character.isWhitespace(ch) )
              { 
                pointsInLabX[pointsInLabNum] = i;
                pointsInLabY[pointsInLabNum] = j;
                pointsInLabNum++;
              }
            }
        }
        
        /** Randomize Odd Points */
        for (int i = 0; i < pointsInLabNum; i++)
        {
            int j = StdRandom.uniform(i+1);
            
            int tX = pointsInLabX[i];
            int tY = pointsInLabY[i];
            pointsInLabX[i] = pointsInLabX[j];
            pointsInLabY[i] = pointsInLabY[j];
            pointsInLabX[j] = tX;
            pointsInLabY[j] = tY;
        }
        
    } 
    
    private void correctWalls()
    {
        // Strengthen walls
        for(int j = 0; j < sizeY; j++)
        {
                blocks[1][j] = wallBlock;
                blocks[sizeX-2][j] = wallBlock;
        }
        for(int i = 0; i < sizeX; i++)
        {
                blocks[i][1] = wallBlock;
                blocks[i][sizeY-2] = wallBlock;
        }       
        
        // Correct main Room walls 
        for(int i = mainRoomXmin+1; i <= mainRoomXmax-1; i++)
        {
            blocks[i][mainRoomYmin] = emptyBlock;
            blocks[i][mainRoomYmax] = emptyBlock;
        }
        for(int j = mainRoomYmin+1; j <= mainRoomYmax-1; j++)
        {
            blocks[mainRoomXmin][j] = emptyBlock;
            blocks[mainRoomXmax][j] = emptyBlock;
        }
        for(int i = mainRoomXmin; i <= mainRoomXmax; i++)
        {
            blocks[i][mainRoomYmin] = wallBlock;
            blocks[i][mainRoomYmax] = wallBlock;
        }
        for(int j = mainRoomYmin; j <= mainRoomYmax; j++)
        {
            blocks[mainRoomXmin][j] = wallBlock;
            blocks[mainRoomXmax][j] = wallBlock;
        }
    }

    public void makeLabyrinth()
    {
       this.markOddPoints(); 
       
       while(pointsInLabNum>0)
       {
          int D = StdRandom.uniform(1,5); 
          int x = pointsInLabX[pointsInLabNum-1];
          int y = pointsInLabY[pointsInLabNum-1];
          pointsInLabNum--;
          
          this.drawLine(x,y,D);
       }
       
        correctWalls();
        
        blocks[46][16] = emptyBlock; // Door from Main Room
       
        for (int i = 76; i < 86; i++) // Make hidden room
        {
            for (int j = 33; j < 37; j++)
            {
               blocks[i][j] = emptyBlock;
            }
        }
         
    }

     
     public static void main (String[] args)
     {
        LabGen lab = new LabGen();
        
        lab.makeLabyrinth();
        
        lab.writeInFile("lab_out.txt");
        
        System.out.println("Job done");
     }
}

