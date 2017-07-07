package com.asset.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.asset.model.Location;
import com.asset.model.Shop;
import com.asset.model.ShopAddress;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@Component
public class AssestService
{
	// JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "org.h2.Driver";   
	   static final String DB_URL = "jdbc:h2:~/test";  
	   
	   //  Database credentials 
	   static final String USER = "sa"; 
	   static final String PASS = ""; 
	   Gson gson = new Gson();
	   
	public Shop saveShop(Shop shop)
	{
		Connection conn = null;
		Statement stmt = null;
		Shop tempShop = new Shop();
		boolean duplicate = false;
		try
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
            String response = getLocation(shop.getAddress().getPostCode());
			
			String[] result = parseLocation(response);
			
			//Uncomment below code for first request and again comment, this for creating table.
			/*String dropTable = "DROP TABLE SHOP";
			
			stmt.executeUpdate(dropTable);
			
			String createSql =  "CREATE TABLE   SHOP " + 
		            "(id INTEGER NOT NULL AUTO_INCREMENT, " + 
		            " name VARCHAR(255), " +  
		            " number VARCHAR(255), " +  
		            " postalCode INTEGER, " +
		            " latitude FLOAT, " +  
		            " longitude FLOAT, " +
		            " PRIMARY KEY ( id ))";  
			
			stmt.executeUpdate(createSql);*/
			String sql = "select name,number,postalCode from SHOP";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String name = rs.getString("name");
				
				if(name.equals(shop.getName()))
				{
					ShopAddress address = new ShopAddress();
					address.setNumber(rs.getInt("number"));
					address.setPostCode(rs.getInt("postalCode"));
					tempShop.setName(shop.getName());
					tempShop.setAddress(address);
					duplicate =true;
					break;
				}
			}
			
			String intertSql = "INSERT INTO SHOP(name,number,postalCode,latitude,longitude) " + "VALUES ("  + "'" + shop.getName() + "'" + "," + shop.getAddress().getNumber() + "," +  shop.getAddress().getPostCode() + "," + result[0] + "," + result[1] + ")";
			stmt.executeUpdate(intertSql);
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se)
		{
			se.printStackTrace();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally { 
	         //finally block used to close resources 
	         try{ 
	            if(stmt!=null) stmt.close(); 
	         } catch(SQLException se2) { 
	         } // nothing we can do 
	         try { 
	            if(conn!=null) conn.close(); 
	         } catch(SQLException se){ 
	            se.printStackTrace(); 
	         } //end finally
		 } //end try 
		if (duplicate)
		{
			shop = tempShop;
		}
		return shop;
	}
	
	
	public Location getNearestShop(double lat, double lang)
	{
		Connection conn = null;
		Statement stmt = null;
		Location location = null;
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			double[] target = {lat,lang};
			List<double[]> latLngList = new ArrayList();
			
			String sql = "select latitude,longitude from SHOP";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				double[] latLng = {rs.getDouble("latitude"),rs.getDouble("longitude")};
				latLngList.add(latLng);
			}
			
			int index = closest(target, latLngList);
			double[] closestLatLng = latLngList.get(index); 
			
			location = new Location(closestLatLng[0],closestLatLng[1]);
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return location;
	}

	private String getLocation(Integer postalCode) throws JsonSyntaxException, JsonIOException, MalformedURLException, IOException
	{
		String url = "http://maps.googleapis.com/maps/api/geocode/json?components=postal_code:" + postalCode + "&sensor=false";
		InputStream is =new URL(url).openStream();
		String content = null;
		try {
		
		 int available = is.available();
		 byte[] bytes = new byte[available];
		 is.read(bytes);
		 content = new String(bytes);
		 
		}finally {
			is.close();
		}
		
		return content.toString();
	}
	
	private static String[] parseLocation(String response)
    {
        // Look for location using brute force.
        // There are much nicer ways to do this, e.g. with Google's JSON library: Gson
        //     https://sites.google.com/site/gson/gson-user-guide

        String[] lines = response.split("\n");

        String lat = null;
        String lng = null;

        for (int i = 0; i < lines.length; i++)
        {
            if ("\"location\" : {".equals(lines[i].trim()))
            {
                lat = getOrdinate(lines[i+1]);
                lng = getOrdinate(lines[i+2]);
                break;
            }
        }

        return new String[] {lat, lng};
    }

    private static String getOrdinate(String s)
    {
        String[] split = s.trim().split(" ");

        if (split.length < 1)
        {
            return null;
        }

        String ord = split[split.length - 1];

        if (ord.endsWith(","))
        {
            ord = ord.substring(0, ord.length() - 1);
        }

        // Check that the result is a valid double
        Double.parseDouble(ord);

        return ord;
    }
    
 // index of closest vector to target from list of candidates
    public static int closest(double[] target, List<double[]> candidates) {
        double p = Double.MIN_VALUE;
        int closest = -1;
        for (int i = 0; i < candidates.size(); i++) {
            double next = dotProduct(target, candidates.get(i));
            if (next > p) {
                p = next;
                closest = i;
            }
        }
        return closest;
    }

    // dot product of two 3vectors
    public static double dotProduct(double[] v1, double[] v2) {
        return v1[0] * v2[0] + v1[1] * v2[1] ;
    }
}
