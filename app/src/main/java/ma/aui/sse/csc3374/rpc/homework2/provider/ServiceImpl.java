package ma.aui.sse.csc3374.rpc.homework2.provider;

import javax.jws.WebService;
import java.net.*;
import java.io.*;
import java.awt.AWTException; 
import java.awt.Rectangle; 
import java.awt.Toolkit; 
import java.awt.Robot; 
import java.awt.image.BufferedImage; 
import java.io.IOException; 
import java.io.File; 
import javax.imageio.ImageIO; 
import java.util.Base64;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

@WebService
public class ServiceImpl{

    public String getOsName() {
        String os = System.getProperty("os.name");
        return os;
    }

    public String reboot() throws Exception {
        try {
            Runtime.getRuntime().exec("shutdown -r -t 15");
            return "System is going to reboot";
        } catch(IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String getScreenShot() {
        try {
            Robot r = new Robot();
            Rectangle capture =new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
            BufferedImage Image = r.createScreenCapture(capture);
            String imgstr = encodeToString(Image, "png");
            return imgstr;
        } catch (AWTException e) {
            e.printStackTrace();
            return e.getMessage();
        }
      
  }
  public static String encodeToString(BufferedImage image, String type) {
    String imageString = null;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

}