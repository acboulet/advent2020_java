package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D1 {

    /** Stores all cost values provided for challenge */
    private static ArrayList<Integer> values = new ArrayList<Integer>();

    /**
     * Creates the list that stores all the costs for analysis
     * @throws FileNotFoundException No idea why it keeps throwing this
     */
    private static void createList() throws FileNotFoundException
    {
        File file = new File("Day1/Day1_input.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            Integer number = Integer.parseInt(sc.nextLine());
            values.add(number);
        }
        sc.close();
    }

    /**
     * Searches through list of all values, add tries adding to 2020
     * Will then store values that equal 2020 in a list
     * @return List contaning two values whose sum is 2020
     */
    private static ArrayList<Integer> parseList()
    {
        ArrayList<Integer> correctValues = new ArrayList<Integer>();
        for (int i = 0; i < values.size(); i++)
        {
            Integer num1 = values.get(i);
            // Grabs second number after num1 start
            for (int j = i+1; j < values.size(); j++)
            {
                Integer num2  = values.get(j);
                for (int k = j+1; k < values.size(); k++)
                {
                Integer num3 = values.get(k);
                Integer sum = num1 + num2 + num3;
                if (sum == 2020)
                {
                    correctValues.add(num1);
                    correctValues.add(num2);
                    correctValues.add(num3);
                    System.out.println("Solved");
                }
                }
                

            }

        }
    return correctValues;
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        D1.createList();
        ArrayList<Integer> results = D1.parseList();
        System.out.println(results);
    }
}