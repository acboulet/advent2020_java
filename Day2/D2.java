package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D2 {

    private static ArrayList<ArrayList<String>> directions = new ArrayList<ArrayList<String>>();

    /**
     * Populates directions ArrayList with following items
     * Index 0: Minimum number of values allowed in password
     * Index 1: Maximum number of values allowed in password
     * Index 2: Value to be searched
     * Index 3: Password to be searched
     * @throws FileNotFoundException
     */
    private static void createList(String fileName) throws FileNotFoundException
    {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            ArrayList<String> stepDirections = new ArrayList<String>();
            String step = sc.nextLine();
            String[] items = step.split(" ", 3);
            // Split the first item to find the min and max number of values allowed in password
            String[] holderList = items[0].split("-", 2);
            for (String element : holderList)
            {
                stepDirections.add(element);
            }
            // Add search term
            stepDirections.add(items[1].substring(0,1));
            // Add password
            stepDirections.add(items[2]);
            directions.add(stepDirections);
        }
        sc.close();
    }
    
    private static int processSteps()
    {
        int noSucess = 0;
        for (ArrayList<String> step : directions)
        {
            int firstIndex = Integer.parseInt( step.get(0) ) - 1;
            int secondIndex = Integer.parseInt( step.get(1) ) - 1;
            char searchValue = step.get(2).charAt(0);
            String password = step.get(3);

            // Checking whether the searchValue matches either of the indices
            boolean firstSearch = false;
            boolean secondSearch = false;
            if (searchValue == password.charAt(firstIndex))
            {
                firstSearch = true;
            }
            if (searchValue == password.charAt(secondIndex) ) 
            {
                secondSearch = true;
            }

            // If the first search is true, but the second search is false (only 1 match) then counts as success
            // If first search is false, and second search is true then counts as success
            if (firstSearch)
            {
                if (!secondSearch)
                {
                    noSucess++;
                }
            }
            else if (secondSearch)
            {
                noSucess++;
            }
        }
        return noSucess;
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        D2.createList("Day2/Day2_input.txt");
        System.out.println(D2.processSteps());
    }

    
}
