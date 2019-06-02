package calificador;

import com.github.sarxos.webcam.Webcam;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Calificador {

    static int flag = 0;
    static int flag2 = 0;
    static int cuentax = 0;
    static int espaciosx = 0;
    static int espaciosy = 0;
    static int totalx = 0;
    static int totaly = 0;

    public static void main(String[] args) throws IOException {
        //Webcam wc =  Webcam.getDefault();
      //wc.open();
    //ImageIO.write(wc.getImage(), "PNG", new File("Primera.PNG"));
        int x = 0;
        try {
            File file1 = new File("Primera.PNG");
            BufferedImage image12 = ImageIO.read(file1);
            for (int y = 0; y < image12.getHeight(); y++) {

                for (x = 0; x < image12.getWidth(); x++) {
                    int c = image12.getRGB(x, y);
                    if (c < -10241749) {
                        if (flag == 0) {
                            flag = 1;

                        }

                    }

                    if (flag == 1) {
                        if (totalx == 0) {
                            totalx = cuentax + 2;
                        }
                        flag = 2;
                    } else {
                        cuentax++;
                    }

                }

                if (totalx == 0) {
                    totaly++;
                }
                cuentax = 0;

            }
            //totalx += 4;
            for (int y = totaly + 2; y < image12.getHeight(); y++) {
                for (x = totalx; x < image12.getWidth(); x++) {

                    int c = image12.getRGB(x, y);
                    if (c < -10241749) {

                        if (flag2 == 1) {
                            flag2 = 2;
                        }

                    } else {
                        if (flag2 == 0 || flag2 == 1) {
                            flag2 = 1;
                            espaciosx++;
                        }

                    }

                }

            }

            for (int y = totaly + 2; y < image12.getHeight(); y++) {
                for (x = totalx; x < totalx + 1; x++) {

                    int c = image12.getRGB(x, y);
                    if (c < -10241749) {
                       flag2 = 1;
                       if(flag2==2){
                           flag2=3;
                       }
                    } else {
                        if(flag2==1){
                          flag2 = 2;  
                          
                        }
                        

                    }

                }
                
                if(flag2==2){
                    espaciosy++;
                }
                
            }

            for (int y = totaly + 2; y < espaciosy-totaly; y++) {
                for (x = totalx; x < espaciosx+totalx; x++) {

                    int c = image12.getRGB(x, y);
                    if (c < -10241749) {

                        System.out.print("0");

                    } else {

                        System.out.print(" ");

                    }

                }

                System.out.println("");
            }

            System.out.println(espaciosx / 10);
        } catch (IOException ex) {
            Logger.getLogger(Calificador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
