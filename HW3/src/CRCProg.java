import java.io.*;
import java.util.zip.CRC32;
import java.util.*;

class CRCPRog {
public static void main(String args[]) throws FileNotFoundException {

    // Target the CRCtext file for scanner object
    File file = new File("src\\CRCtext.txt");
        
        // Create an array for all the lines of text
        String[] textStrings = new String[10];
        
        // Read in all text strings from CRCtext.txt
        Scanner scn = new Scanner(file);
        int i =0;
        for (i = 0; i < 10; i++)
        {
            // Save the text string to the array and the temp variable, msg
            String msg = textStrings[i] = scn.nextLine();
            //String msg = textStrings[i];

            // Create checksum and convert to hexadecimal
            long code = generateCRC(textStrings[i]);
            String hex = String.format("%4X", code);

            // Concatenate line with checksum on the end
            textStrings[i] = msg + hex;
            System.out.print(textStrings[i] + "\n");
        }
        scn.close();

}
    // Generate checksum based on string passed by parameter
    public static long generateCRC(String input) {
        
        byte[] bytes = input.getBytes(); // Put input string into byte array
        CRC32 myCRC = new CRC32();

        // generate the checksum of the input
        myCRC.update(bytes, 0, bytes.length); 

        return myCRC.getValue();
    }

}