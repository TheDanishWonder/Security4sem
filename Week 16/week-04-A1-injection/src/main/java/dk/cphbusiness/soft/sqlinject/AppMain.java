package dk.cphbusiness.soft.sqlinject;

import static dk.cphbusiness.soft.sqlinject.PlaceHolders.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppMain {

  public static void main( String[] args ) throws SQLException, ClassNotFoundException {
    try {
      String id = "2 or 1=1"; 
      String idNoShit = "2";
      String name = "Jens' or ''='";
      injectSimpleStatement(id, name);
      injectPreparedStatement(idNoShit, name);
      injectPlaceHolderStatement(idNoShit, name);
      placeHolderSortStatement("name");
      placeHolderSortStatement("id");
      placeHolderSortStatement("id; DROP TABLE junk; --");
      }
    catch (Exception e) { e.printStackTrace(); }
    }

  private static void sortExample(String sortKey) {
    String sql = "select * from junk order by "+field("name", "name", "id")+";";
    
    }

  private static void inExample(String... options) {
    String sql = "select * from junk where name in "+stringList(options)+";";
    }

  private static void injectSimpleStatement( String id, String name ) throws SQLException, ClassNotFoundException {
    System.out.println( "Simple inject" );
    try ( Connection con = getConnection();
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(
              "SELECT * FROM junk WHERE id = "+id+" and name = '"+name+"'"
              )
        ) {
      while (rs.next()) 
          System.out.println(
              "--> "+rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("role")
              );
      }
    }

  private static void placeHolderSortStatement(String field) throws SQLException, ClassNotFoundException {
    System.out.println( "Placeholder inject" );
    try ( Connection con = getConnection();
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(
              "select * from junk order by "+field(field, "name", "id")+";"
              )
        ) {
      while (rs.next()) 
          System.out.println(
              "--> "+rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("role")
              );
      }
    }

  private static void injectPlaceHolderStatement( String id, String name ) throws SQLException, ClassNotFoundException {
    System.out.println( "Placeholder inject" );
    try ( Connection con = getConnection();
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(
              "SELECT * FROM junk WHERE id = "+integer(id)+" and name = "+string(name)
              )
        ) {
      while (rs.next()) 
          System.out.println(
              "--> "+rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("role")
              );
      }
    }

  private static void injectPreparedStatement( String id, String name ) throws SQLException, ClassNotFoundException {
    System.out.println( "Prepared statement" );
    try ( Connection con = getConnection();
          PreparedStatement stmt = con.prepareStatement(
              "SELECT * FROM junk WHERE id = ? and name = ?"
              ) 
        ) {
      stmt.setInt( 1, Integer.parseInt( id ) );
      stmt.setString( 2, name );
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) 
          System.out.println(
              "--> "+rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("role")
              );
      }
    }

  private static Connection getConnection() throws SQLException, ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    URL url = AppMain.class.getClassLoader().getResource("tmpinject.db");
    return DriverManager.getConnection( "jdbc:sqlite:" + url.getPath() );
    }

  }
