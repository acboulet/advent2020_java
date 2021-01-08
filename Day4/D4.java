package Day4;


//TODO: Checks with test2 only show 3 positives
// Need to add a way to tell me which ones are run
// hcl checker doesn't account for specific requirements regarding num/letters


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D4 {
    private static ArrayList<Map> passportDatabase = new ArrayList<Map>();

    /**
     * Searches through the txt file and combines all the data from each
     * person into a single line
     * @param fileName Name of file
     * @return ArrayList where each value is a string that provides the passport
     *          information for a given person
     * @throws FileNotFoundException
     */
    private static ArrayList<String> extractData(String fileName) throws FileNotFoundException
    {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        ArrayList<String> passportDB = new ArrayList<String>();
        // For each line in the file
        while (sc.hasNextLine())
        {   
            String firstLine = sc.nextLine();
            // Confirms first line is not empty
            if ( !(firstLine.isEmpty()) )
            {   
                // passportInfo will keep track of information for every
                String passportInfo = firstLine;
                String secondLine = "";
                if (sc.hasNextLine())
                {
                    secondLine = sc.nextLine();
                }
                // If there is still more passport information then continue
                while ( !(secondLine.isEmpty()) )
                {    
                    passportInfo += " " + secondLine;
                    // Check if there is more information
                    // If true then continue
                    // if false, then replace secondLine with an empty to end the loop
                    if (sc.hasNextLine())
                    {
                        secondLine = sc.nextLine();
                    }
                    else{secondLine = "";}
                }
                passportDB.add(passportInfo);
            }
        }
        {

        }

        return passportDB;
    }

    /**
     * Will process each line of passport data and extract the 
     * category title with the data information
     * @param passportData List containing passport information
     */
    private static void proccesData(ArrayList<String> passportData)
    {
        for (String passport : passportData)
        {
            Map<String, String> passportInfo = new HashMap<String, String>();
            String[] indivPassportData = passport.split(" ");
            for (String item : indivPassportData)
            {
                String[] itemData = item.split(":");
                passportInfo.put(itemData[0], itemData[1]);
            }
            passportDatabase.add(passportInfo);
        }

    }

    private static int checkPassports()
    {
        int positivePassports = 0;
        // String[] keyList = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};
        String[] keyList = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        for (Map<String, String> item : passportDatabase)
        {
            Boolean[] checkList = {false, false, false, false, false, false, false, false};
            Boolean[] testList = {false, false, false, false, false, false, false, false};
            for (int i = 0; i < 7; i++)
            {
                if(item.containsKey(keyList[i]))
                { checkList[i] = true;
                    boolean testResult = false;
                    D4.Tester testObject = new D4.Tester();
                    switch(i)
                    {
                        case 0: testResult = testObject.checkBYR(item.get("byr"));break;
                        case 1: testResult = testObject.checkIYR(item.get("iyr"));break;
                        case 2: testResult = testObject.checkEYR(item.get("eyr"));break;
                        case 3: testResult = testObject.checkHGT(item.get("hgt"));break;
                        case 4: testResult = testObject.checkHCL(item.get("hcl"));break;
                        case 5: testResult = testObject.checkECL(item.get("ecl"));break;
                        case 6: testResult = testObject.checkPID(item.get("pid"));break;
                    }
                    testList[i] = testResult;
                }
            }

            int keyChecker = 0;
            for (Boolean test : checkList)
            {
                if (test)
                { keyChecker++; }
            }

            int testChecker = 0;
            for (Boolean test : testList)
            {
                if (test)
                { testChecker++;}
            }

            if (testChecker == 7 && keyChecker == 7)
            { positivePassports++;}
        }
        return positivePassports;
    }

    /** Inner class made to test every passport entry */
    static public class Tester
    {
        public boolean checkBYR(String tester)
        {
            boolean result = false;
            if (tester.length() == 4)
            {
                int inTester = Integer.parseInt(tester);
                if (1920 <= inTester && inTester <= 2002)
                {
                    result = true;
                }
            }
            return result;
        }

        public boolean checkIYR(String tester)
        {
            boolean result = false;
            if (tester.length() == 4)
            {
                int inTester = Integer.parseInt(tester);
                if (2010 <= inTester && inTester <= 2020)
                {
                    result = true;
                }
            }

            return result;
        }

        public boolean checkEYR(String tester)
        {
            boolean result = false;
            if (tester.length() == 4)
            {
                int inTester = Integer.parseInt(tester);
                if (2020 <= inTester && inTester <= 2030)
                {
                    result = true;
                }
            }

            return result;
        }

        public boolean checkHGT(String tester)
        {
            boolean result = false;
            
            // Cannot be smaller than 4 digits or messes up If statements
            if (tester.length() >= 4)
            {
                // Copies the last 2 characters which make up the unit type
                String unitType = tester.substring(tester.length() - 2);
                // Copies everything except the last two characters
                int height = Integer.parseInt(tester.substring(0, tester.length() - 2));
                if (unitType.equals("cm"))
                {
                    if (150 <= height && height <= 193)
                    { result = true; }
                }
                else if (unitType.equals("in"))
                {
                    if (59 <= height && height <= 76)
                    { result = true; }
                }
            }
            return result;
        }

        public boolean checkHCL(String tester)
        {
            // The result of whether the tested string is valid
            boolean result = false;
            // The list of booleans to confirm each character in hex is valid
            Boolean[] testList = {false, false, false, false, false, false};
            // Confirms that the code starts with #
            char hashTag = tester.charAt(0);
            if (hashTag == '#')
            {
                // Confirm valid length of hexcode
                if (tester.length() == 7)
                {
                    // Check each character after the #
                    for (int i = 1; i != tester.length(); i++) 
                    {
                        char c = tester.charAt(i);
                        if (Character.isLetter(c) || Character.isDigit(c))
                        {
                            // Will change the testList to true for each value
                            testList[i-1] = true;
                        }
                    }
                    // Go through testList to confirm every value
                    int testChecker = 0;
                    for (Boolean test : testList)
                    {
                        if (test)
                        { testChecker++;}
                    }
        
                    if (testChecker == 6)
                    { result = true; }
                }
                
            }

            return result;
        }

        public boolean checkECL(String tester)
        {
            boolean result = false;
            ArrayList<String> colorList = new ArrayList<>( Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth") );
            if (colorList.contains(tester))
            { result = true; }
            return result;
        }

        public boolean checkPID(String tester)
        {
            boolean result = false;
            if (tester.length() == 9)
            { result = true; }
            return result;
        }
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        ArrayList<String> initialData = D4.extractData("Day4/D4_input.txt");
        D4.proccesData(initialData);
        System.out.println(D4.checkPassports());
        
    }
}