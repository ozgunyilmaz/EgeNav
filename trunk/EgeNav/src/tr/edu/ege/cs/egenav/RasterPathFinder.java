package tr.edu.ege.cs.egenav;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 05.May.2014, 11:48:48
 */
public class RasterPathFinder {
    
    public static Path findPath(BufferedImage img, Point init, Point dest, MapColorModel mcm){
        
        int x = dest.x;
        int y = dest.y;
        
        int renk = img.getRGB(x, y);
        if (mcm.isBorder(renk)) {
            return null;
        }
        
//System.out.println(renk);
        ArrayList<Path> paths = new ArrayList<Path>();
        paths.add(new Path(init));
        int x2, y2;

        while (paths.size() > 0) {

            Path temp = paths.get(0);
            x2 = temp.getLastX();
            y2 = temp.getLastY();
            img.setRGB(x2, y2, mcm.getABorderColor().getRGB());

            if ((x2 == x) && (y2 == y)) {
            //Eğer bulunursa gidiş yolu p vektöründedir.

                //ArrayList<Point> p = temp.getPoints();
                //temp.normalize(img2);
                //temp.drawPath(img2, Color.RED);

                return temp;

            }else{

                if (x2 > 0 && img.getRGB(x2 - 1, y2) == renk) {
                    img.setRGB(x2 - 1, y2, mcm.getABorderColor().getRGB());
                    Path p = temp.clone();
                    p.add(new Point(x2 - 1, y2));
                    paths.add(p);
                }
                if (y2 > 0 && img.getRGB(x2, y2 - 1) == renk) {
                    img.setRGB(x2, y2 - 1, mcm.getABorderColor().getRGB());
                    Path p = temp.clone();
                    p.add(new Point(x2, y2 - 1));
                    paths.add(p);
                }

                if (x2 < img.getWidth() - 1 && img.getRGB(x2 + 1, y2) == renk) {
                    img.setRGB(x2 + 1, y2, mcm.getABorderColor().getRGB());
                    Path p = temp.clone();
                    p.add(new Point(x2 + 1, y2));
                    paths.add(p);
                }

                if (y2 < img.getHeight() - 1 && img.getRGB(x2, y2 + 1) == renk) {
                    img.setRGB(x2, y2 + 1, mcm.getABorderColor().getRGB());
                    Path p = temp.clone();
                    p.add(new Point(x2, y2 + 1));
                    paths.add(p);
                }

            }


            paths.remove(0);
        }

        return null;
    }
    
    public static BufferedImage deepCopy(BufferedImage bi) {
         ColorModel cm = bi.getColorModel();
         boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
         WritableRaster raster = bi.copyData(null);
         return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
}
