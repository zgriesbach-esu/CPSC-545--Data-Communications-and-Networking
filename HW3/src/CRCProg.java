import java.io.*;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;

class CRCPRog {
private static int t;
public static void main(String args[]) {

    String test = "Hello Dave";

    long genCRC = generateCRC(test);

    // Format the checksum to be in hexadecimal form
    String formatted = String.format("%4X", genCRC);
    
    System.out.println(test + " " + formatted);

}

public static long generateCRC(String input) {
    
    byte[] bytes = input.getBytes(); // Put input string into byte array
    CRC32 myCRC = new CRC32();
    myCRC.update(bytes, 0, bytes.length); // generate the checksum of the input

    return myCRC.getValue();
}

}