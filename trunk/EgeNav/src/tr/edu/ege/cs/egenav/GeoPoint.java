package tr.edu.ege.cs.egenav;

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
    public double getLatitude(){
        return lat;
    }

    public double getLongitude(){
        return lon;
    }

    public void setLatitude(double lat){
        this.lat=lat;
    }

    public void setLongitude(double lon){
        this.lon=lon;
    }
    
    @Override
    public GeoPoint clone(){
        
        GeoPoint point=new GeoPoint(lat,lon);
        
        return point;
    }
	
}