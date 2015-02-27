
class LabGen
{
     static String fileIn = "lab_in.txt";
     
     static String fileOut = "lab_out.txt";
     
     static int sizeX = 92 ;

     static int sizeY = 42 ;
     
     
     
     public static void main (String[] args)
     {
        
        
        
        In in = new In(fileIn);
        char[][] blocks = new char[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++)
            for (int j = 0; j < sizeY; j++)
                blocks[i][j] = in.readChar();
        
        
        
        
        
        System.out.println("Привет, Хабр!");
     }
}

