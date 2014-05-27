package tr.edu.ege.cs.egenav.test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Özgün Yılmaz
 * Created on 27.May.2014, 12:43:19
 */
public class DBTest {
    
    public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
        File f=new File("C:\\Users\\samsung\\Documents\\test.db");
        if (f.exists()){
            f.delete();
        }
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\samsung\\Documents\\test.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "CREATE TABLE Maps " +
                       "(Mapurl TEXT PRIMARY KEY NOT NULL," +
                       " ImageFileName  TEXT    NOT NULL, " + 
                       " DownloadDate   INTEGER NOT NULL, " + 
                       " UsageCount     INTEGER NOT NULL)"; 
      stmt.executeUpdate(sql);
      
      
      sql = "INSERT INTO Maps (Mapurl,ImageFileName,DownloadDate,UsageCount) " +
                   "VALUES ('www.kgk.com', 'deneme.png', 11, 1);"; 
      stmt.executeUpdate(sql);
      
      sql = "INSERT INTO Maps (Mapurl,ImageFileName,DownloadDate,UsageCount) " +
                   "VALUES ('www.abc.com', 'jfjf.png', 12, 1);"; 
      stmt.executeUpdate(sql);
      
      sql = "INSERT INTO Maps (Mapurl,ImageFileName,DownloadDate,UsageCount) " +
                   "VALUES ('www.zzzz.com', 'zzzz.png', 13, 1);"; 
      stmt.executeUpdate(sql);
      
      sql = "INSERT INTO Maps (Mapurl,ImageFileName,DownloadDate,UsageCount) " +
                   "VALUES ('www.aaaa.com', 'aaaaa.png', 10, 4);"; 
      stmt.executeUpdate(sql);
      
      sql = "UPDATE Maps set Mapurl = 'www.xxx.com',ImageFileName='xxxx.PNG' where Mapurl='www.zzzz.com';";
      stmt.executeUpdate(sql);

      ////**************************************************
      ResultSet rs = stmt.executeQuery( "SELECT * FROM Maps;" );
      while ( rs.next() ) {
         
         String  url = rs.getString("Mapurl");
         
         String  fname = rs.getString("ImageFileName");
         long date=rs.getLong("DownloadDate");
         int count = rs.getInt("UsageCount");
         System.out.println( "Mapurl = " + url );
         System.out.println( "ImageFileName = " + fname );
         System.out.println( "DownloadDate = " + date );
         System.out.println( "UsageCount = " + count );
         
         System.out.println();
      }
      //*********************************************************
      //System.out.println(rs.getString(1));

      stmt.close();
      //c.commit();
      c.close();
      
      
      
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
    
}
