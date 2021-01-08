package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D3 {
    private static ArrayList<String> map = new ArrayList<String>();
    
    private static void buildMap(String fileName) throws FileNotFoundException
    {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        int horizontalMap = 100;
        while (sc.hasNextLine())
        {
            
            map.add(sc.nextLine().repeat(horizontalMap));
        }
        sc.close();
    }

    /**
     * Starting at top left position of the map, it tracks the number of trees hit
     * as the sled moves to the right and down
     * @param xMove number of spots to the right
     * @param yMove number of spots down
     * @return Number of trees hit
     */
    private static int exploreMap(int xMove, int yMove)
    {
        // Tracks the sled
        int currentX = 0;
        int currentY = 0;
        // Counts number of trees
        int numTrees = 0;
        int sizeHill = map.size();

        do
        {
            // Pulls out the object at the current location.
            String object = map.get(currentY).substring(currentX, currentX+1);
            if (object.equals("#"))
            {
                numTrees++;
            }
            //Update current location
            currentX += xMove;
            currentY += yMove;
        }
        while (currentY < sizeHill);

        return numTrees;

    }

    public static void main(String[] args) throws FileNotFoundException
    {
        D3.buildMap("Day3/D3_input.txt");
        System.out.println();
        int run1 = D3.exploreMap(1, 1);
        System.out.println(run1);
        int run2 = D3.exploreMap(3, 1);
        System.out.println(run2);
        int run3 = D3.exploreMap(5, 1);
        System.out.println(run3);
        int run4 = D3.exploreMap(7, 1);
        System.out.println(run4);
        int run5 = D3.exploreMap(1, 2);
        System.out.println(run5);
    }
}
