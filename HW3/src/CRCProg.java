import java.io.*;
import java.util.zip.CRC32;
import java.util.*;

class CRCPRog {
private static int t;
public static void main(String args[]) throws FileNotFoundException {

    String test = "Hello Dave";

    File file = new File("src\\CRCtext.txt");
        
        
        String[] textStrings = new String[10];
        
        // Read in all text strings from CRCtext.txt
        Scanner scn = new Scanner(file);
        int i =0;
        for (i = 0; i < 10; i++)
        {
            String msg = textStrings[i] = scn.nextLine();
            //String msg = textStrings[i];

            // Create checksums and convert to hex
            long code = generateCRC(textStrings[i]);
            String hex = String.format("%4X", code);

            // Concatenate line with checksum on the end
            textStrings[i] = msg + hex;
            System.out.print(textStrings[i] + "\n");
        }

    long genCRC = generateCRC(test);

    // OLD test code
    // Format the checksum to be in hexadecimal form
    String formatted = String.format("%4X", genCRC);

    System.out.println(test + " " + formatted);

}

public static long generateCRC(String input) {
    
    byte[] bytes = input.getBytes(); // Put input string into byte array
    CRC32 myCRC = new CRC32();

    // generate the checksum of the input
    myCRC.update(bytes, 0, bytes.length); 

    return myCRC.getValue();
}

}