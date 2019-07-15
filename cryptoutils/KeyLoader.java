package cryptoutils;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyLoader {
    public static  void loadAsBinary(String keyFile,String format)
    {
        Base64.Encoder bEncoder = Base64.getEncoder();
        try {

            System.out.println("Initializing Loader ... ");
            Path path = Paths.get(keyFile+format);
            byte[] bytes = Files.readAllBytes(path);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            if(format.equals(".key"))
            {
                System.out.print("The private key found in file " + keyFile + " is ");
                PKCS8EncodedKeySpec pvtKs = new PKCS8EncodedKeySpec(bytes);
                PrivateKey pvt = kf.generatePrivate(pvtKs);
                System.out.println(bEncoder.encodeToString(pvt.getEncoded()));
            }

            else if(format.equals(".pub"))
            {
                System.out.print("The public key found in file " + keyFile + " is ");
                X509EncodedKeySpec pubKs = new X509EncodedKeySpec(bytes);
                PublicKey pub = kf.generatePublic(pubKs);
                System.out.println(bEncoder.encodeToString(pub.getEncoded()));
            }
            else {
                System.out.println("Unrecognized Format");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void loadAsText(String keyFile,String format)
    {



    }


}
