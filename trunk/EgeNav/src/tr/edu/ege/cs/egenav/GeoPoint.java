package tr.edu.ege.cs.egenav;

import java.awt.Point;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 13:44:59
 */
//Koordinat sınıfı
//enlem ve boylam değereleri String'den double'a çevriliyor.

public class GeoPoint extends Location{
	
    private double lat,lon;	//enlem ve boylam

    public GeoPoint(double lat,double lon){
        this.lat=lat;
        this.lon=lon;
    }

    public GeoPoint(String p){
        //Parametre olarak enlem ve boylam'dan oluşan string alınır.
        double dec,min,sec;
        //Aşağıda enlem için derece, dakika, saniye değerleri bulunuyor.		
        int i=0;
        while (Character.isDigit(p.charAt(i))){
                i++;
        }

        dec=Integer.parseInt(p.substring(0,i));
        i++;

        int ii=i;
        while (Character.isDigit(p.charAt(i))){
                i++;
        }
        min=Integer.parseInt(p.substring(ii,i));
        i++;

        ii=i;
        while (Character.isDigit(p.charAt(i))){
                i++;
        }
        sec=Integer.parseInt(p.substring(ii,i));


        lat=dec+min/60+sec/3600;
        //enlem değeri hesaplanıyor.

        if (p.charAt(i)=='s' || p.charAt(i)=='S'){
                lat=lat*(-1);	//Eğer enlem güneyse(South-S) işareti -'dir.
        }


        //Aşağıda boylam için derece, dakika, saniye değerleri bulunuyor.	
        i=i+2;
        ii=i;
        while (Character.isDigit(p.charAt(i))){
                i++;
        }
        dec=Integer.parseInt(p.substring(ii,i));
        i++;

        ii=i;
        while (Character.isDigit(p.charAt(i))){
                i++;
        }
        min=Integer.parseInt(p.substring(ii,i));
        i++;

        ii=i;
        while (Character.isDigit(p.charAt(i))){
                i++;
        }
        sec=Integer.parseInt(p.substring(ii,i));


        lon=dec+min/60+sec/3600;

        if (p.charAt(i)=='w' || p.charAt(i)=='W'){
                lon=lon*(-1);	//Eğer boylam batıysa(West-W) işareti - olmalıdır.
        }


    }

    @Override
    public String toString(){
        return lat+","+lon;
    }

    //Aşağıda getter ve setter metodları bulunmaktadır.
    @Override
    public double getLatitude(){
        return lat;
    }

    @Override
    public double getLongitude(){
        return lon;
    }

    @Override
    public void setLatitude(double lat){
        this.lat=lat;
    }

    @Override
    public void setLongitude(double lon){
        this.lon=lon;
    }
    
    @Override
    public GeoPoint clone(){
        
        GeoPoint point=new GeoPoint(lat,lon);
        
        return point;
    }

    @Override
    public double getDistanceTo(Location location) {
        GeoPoint gp=(GeoPoint)location;
        return calculateDistance(getLatitude(),getLongitude(),gp.getLatitude(),gp.getLongitude());
    }
    
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        //Haversine formülüne göre enlemleri ve boylamları bilinen
        //2 nokta arasındaki uzaklık hesaplanıyor.
        double r = 6371; // km
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1); 

        lat1=Math.toRadians(lat1);
        lon1=Math.toRadians(lon1);
        lat2=Math.toRadians(lat2);
        lon2=Math.toRadians(lon2);


        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(lat1) * Math.cos(lat2) * 
                        Math.sin(dLon/2) * Math.sin(dLon/2); 
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
        double d = r * c;


//			System.out.println(d);
        return d;	//km. cinsinden uzaklık
    }

    
	
}