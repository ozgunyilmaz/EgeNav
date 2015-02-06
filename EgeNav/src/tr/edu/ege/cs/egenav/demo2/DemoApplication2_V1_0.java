//This is the first iteration of the demo application which includes simple GUI elements and 
//shows outputs using the console

package tr.edu.ege.cs.egenav.demo2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tr.edu.ege.cs.egenav.direction.Junction;
import tr.edu.ege.cs.egenav.direction.MapColorModel;
import tr.edu.ege.cs.egenav.direction.Path;
import tr.edu.ege.cs.egenav.direction.RasterPathFinder;
import tr.edu.ege.cs.egenav.direction.Threshold;
import tr.edu.ege.cs.egenav.direction.TurningPoint;

/**
 * @author Özgün Yılmaz
 * Created on 17.Ara.2014, 13:08:40
 */
public class DemoApplication2_V1_0 extends JFrame{
    
    FillPanel p;
    JPanel sagUst,sagAlt,sol,sag,alt;
    JComboBox cmbFile;

    public DemoApplication2_V1_0() {
        super("Path Finder");
        Container c = getContentPane();

        sagUst = new JPanel();
        sagUst.add(new JLabel("Image File:      "));

        File f = new File("images\\");
        //System.out.println(f.getAbsolutePath());
        cmbFile = new JComboBox(f.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (name.length() < 4) {
                    return false;
                }
                String suf = name.substring(name.length() - 4);
                if (suf.equalsIgnoreCase(".png") || suf.equalsIgnoreCase(".jpg") || suf.equalsIgnoreCase(".bmp") || suf.equalsIgnoreCase(".gif")) {
                    return true;
                } else {
                    return false;
                }
            }
        }));
        
        

//        if (cmbFile.getItemCount() < 1) {
//            System.out.println("Error: There are no images");
//            System.exit(0);
//        }

        p = new FillPanel("images\\" + (String) cmbFile.getSelectedItem());
        sagAlt = new JPanel();
        sagAlt.setBorder(BorderFactory.createEtchedBorder());
        sagAlt.add(p);
        
        cmbFile.addActionListener(
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    p.setImg("images\\" +(String) cmbFile.getSelectedItem());
                    p.reset();
                    pack();

                }
            });

        sagUst.add(cmbFile);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        sag=new JPanel(new BorderLayout());
        sag.add(sagUst, BorderLayout.NORTH);
        sag.add(sagAlt);
        
        c.add(sag);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {

        DemoApplication2_V1_0 app = new DemoApplication2_V1_0();

    }

    class FillPanel extends JPanel {

        BufferedImage img, img2;
        String path;
        InputStream in;
        int x, y, renk;
        int click = 0;

        public void reset() {
            click = 0;
        }

        public void setImg(String s) {
            path = s;
            try {
                img = ImageIO.read(new File(s));
                img2 = ImageIO.read(new File(s));

            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            click = 0;

            repaint();
        }

        @Override
        public void paint(Graphics g) {

            g.drawImage(img2, 0, 0, null);

        }

        public FillPanel(String s) {

            setImg(s);
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

            addMouseListener(
                    new MouseAdapter() {  // anonymous inner class

                        Point p1, p2;
                        
                        @Override
                    public void mouseMoved(java.awt.event.MouseEvent evt) {
                        System.out.println("moved");
                    }

                        @Override
                        public void mouseClicked(MouseEvent event) {


                            x = event.getX();
                            y = event.getY();


                            click++;
                            if (click == 1) {
                                p1 = new Point(x, y);

                            } else {
                                p2 = new Point(x, y);

                                setImg(path);
                                
                                renk = img.getRGB(x, y);
                                if (renk == Color.BLACK.getRGB()) {
                                    return;
                                }
                                MapColorModel mcm=new MapColorModel();
                                mcm.addBorderColor(Color.BLACK);
                                mcm.addTrackColor(Color.WHITE);
                                mcm.setBorderBased(true);
                                
                                Path path = RasterPathFinder.findPath(img, p1, p2, mcm);
                                //path.drawLinePath(img2);
                                //ArrayList<Point> p = path.getPoints();
                                //path.normalizeWithInterpolation(img2,10);   //Yolu her taraftan ortala
                                //path.normalize(img2,10);
                                
                                Threshold x=path.getThreshold(img2, mcm);
                                System.out.println("\n***Thresholds***");
                                System.out.println("Horizontal threshold: "+x.getHorizontal()+"\n"+"Vertical threshold: "+x.getVertical());
                                //path.drawLinePath(img2);    //Yolu imaj üzerinde çiz
                                //path.yaz();
                                        

                                //int tres=10;
                                Path pts2=path.toNormalizedLine(img2, mcm, x);
                                //pts2.drawLinePath(img2);
                                pts2.centerPath(img2, mcm, x);
                                pts2.drawLinePath(img2);
                                
                                ArrayList<Junction> jnc=pts2.findJunctions(img2, mcm, x);
                                System.out.println("\n***Junctions***");
                                for (int i=0;i<jnc.size();i++){
                                    System.out.println(jnc.get(i));
                                }
                                


                                //pts2.shift(2, 2); 
                                ArrayList<TurningPoint> tp=pts2.getTurningPoints();
                                System.out.println("\n***Turning points***");
                                for (int i=0;i<tp.size();i++){
                                    System.out.println(tp.get(i).getDescription());
                                }

//                                        pts2.printAngles();
//                                        pts2.printSlopes();

                                    //pts2.printAngles();
//////                                        pts2.findJunctions2(img2);

                                    //path.drawPath(img2);
                                    //path.normalize(img2,15);
                                    //path.shiftDrawPath(img2);   //İnce yolu kalınlaştır ve çiz.(Önce normalize çağrılırsa daha muntazam çizer.)

                                    //path.findJunctions(img2);

                            }

                            repaint();
                        }
                    }); // end call to addMouseListener


        }

        @Override
        public Dimension getPreferredSize() {
            if (img == null) {
                return new Dimension(500, 500);
            } else {
                return new Dimension(img.getWidth(null), img.getHeight(null));
            }
        }
    }
    
}
