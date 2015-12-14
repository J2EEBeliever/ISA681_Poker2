package cipher;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class CipherUtils
{

  

    public static String encrypt(String strToEncrypt, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
       byte[] keybytes = key.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(keybytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
            return encryptedString;
     

    }

    public static String decrypt(String strToDecrypt, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
      byte[] keybytes = key.getBytes("UTF-8");
        
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(keybytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)),"UTF-8");
            return decryptedString;
        
    
    }
    
    public static String createHMACSHA256(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException
    {
       Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
       SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
       sha256_HMAC.init(secret_key);
       return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
 
    }


    public static void main(String args[]) throws ParseException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {

        CommandLineParser parser = new PosixParser();
        Options options = new Options();
        Option help = new Option("help", "Display help");
        Option encrypt = new Option("encrypt", true, " - string to encrypt");
        Option decrypt = new Option("decrypt", true, " - string to decrypt");
        options.addOption(help);
        options.addOption(encrypt);
        options.addOption(decrypt);
       
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("encrypt"))
            {
                final String strToEncrypt = cmd.getOptionValue("encrypt");
                final String encryptedStr = CipherUtils.encrypt(strToEncrypt.trim(),"thisIsASecretKey");
                System.out.println("String to Encrypt : " + strToEncrypt);
                System.out.println("Encrypted : " + encryptedStr);
            }
            else if (cmd.hasOption("decrypt"))
            {
                final String strToDecrypt = cmd.getOptionValue("decrypt");
                final String decryptedStr = CipherUtils.decrypt(strToDecrypt.trim(),"thisIsASecretKey");
                System.out.println("String To Decrypt : " + strToDecrypt);
                System.out.println("Decrypted : " + decryptedStr);
            }
            else
            {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("[-h] [-encrypt ]", options);
            }
        
      

    }
}
