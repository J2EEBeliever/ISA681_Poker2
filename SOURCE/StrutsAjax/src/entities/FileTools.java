package entities;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 *
 * @author Brian Woltemate
 */

public final class FileTools {
//Set Properties from request file
       final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MySQLConnection.class);
public static Properties readProperties(String FileName, Properties Defaults) throws IOException
       
{
    Properties appProps = new Properties();
    try
    {
    InputStream in;
    
    in = Thread.currentThread().getContextClassLoader().getResourceAsStream("/"+FileName);

   
    
         appProps.load(in);
    }
    catch (java.io.FileNotFoundException e)
    {
        log.error(e);
        appProps = Defaults;
    }
 //   File test = getSettingsDirectory();
 //   JOptionPane.showMessageDialog(null, test);
   return appProps;

}





}



