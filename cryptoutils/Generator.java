package cryptoutils;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class Generator {
    static KeyPairGenerator kpg;
    static
    {
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void writeToFile(String filename,String format,Key key)
    {
        try(FileOutputStream fos = new FileOutputStream(filename+format))
        {
            fos.write(key.getEncoded());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void writeToText(String filename,String format,Key key)
    {
        Base64.Encoder bEncode = Base64.getEncoder();
        try(FileWriter fw = new FileWriter(filename+format))
        {
            String base64String = bEncode.encodeToString(key.getEncoded());
            System.out.println("Writing key: "+base64String);
            if(format==".key")
                fw.write("----Private----Key Starting------------\n ");
            if(format==".pub")
                fw.write("----Public----Key Starting------------\n ");
            fw.write(base64String);
            fw.write("\n--------Key Ending--------------");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void generate()
    {
        kpg.initialize(512);
        KeyPair kp = kpg.generateKeyPair();
        Key pubkey = kp.getPublic(),pvtkey = kp.getPrivate();
        //For write to file in Binary format - Not Human Readable
        writeToFile("secret",".pub",pubkey);
        writeToFile("secret",".key",pvtkey);
        //For writing to file in Text format - Human Readable
        System.out.print("Public key is: ");
        writeToText("secret_text",".pub",pubkey);
        System.out.print("Private key is: ");
        writeToText("secret_text",".key",pvtkey);
    }


}
