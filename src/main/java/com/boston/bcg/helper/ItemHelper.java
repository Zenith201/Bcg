package com.boston.bcg.helper;

import com.boston.bcg.dao.Item;
import com.boston.bcg.dao.SearchItem;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ItemHelper {

   private static ItemHelper instance;
   static final String GET_ALL_PREVIEW="(select * from  getOfficeItemsPreview()) Union (select * from  getSchoolItemsPreview()) union (select * from getItItemsPreview()) union (select * from getPrintingItemsPreview())";

    public static ItemHelper getInstance() {
        if (instance==null)
        instance= new ItemHelper();

        return instance;
    }

  public   String getCategorySample(String category){
            HashMap<String,Object> response=new HashMap<>();
            ArrayList<Item>items=new ArrayList<>();
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                final Connection connection = DriverManager.getConnection("jdbc:sqlserver://boston-solutions.database.windows.net:1433;database=bcg;encrypt=true;trustServerCertificate=true;", "zane", "takougoum2001NANA");
                final PreparedStatement statement = connection.prepareStatement("select Top (10) * from bcg.items where category like '"+category+"'");
                ResultSet resultSet=statement.executeQuery();
                while (resultSet.next())
                    items.add(new Item(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("unit_price")
                    ,resultSet.getString("gross_price"),resultSet.getString("description"),resultSet.getString("category")));
                connection.close();
                statement.close();
            }
            catch (Exception e) {
                response.put("time_stamp", new Date().toLocaleString());
                response.put("status","error");
                response.put("cause",e.getMessage());
                return new Gson().toJson(response);
            }

            response.put("time_stamp",new Date().toLocaleString());
            response.put("status","success");
            response.put("data",items);
            response.put("cause",null);

            return new Gson().toJson(response);
    }

  public  String getSetOfItems(String category){
        HashMap<String,Object> response=new HashMap<>();
        ArrayList<Item>items=new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            final Connection connection = DriverManager.getConnection("jdbc:sqlserver://boston-solutions.database.windows.net:1433;database=bcg;encrypt=true;trustServerCertificate=true;", "zane", "takougoum2001NANA");
            final PreparedStatement statement = connection.prepareStatement("select Top (35) * from bcg.items where category='"+category+"'");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next())
                items.add(new Item(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("unit_price")
                        ,resultSet.getString("gross_price"),resultSet.getString("description"),resultSet.getString("category")));
            connection.close();
            statement.close();
        }
        catch (Exception e) {
            response.put("time_stamp", new Date().toLocaleString());
            response.put("status","error");
            response.put("cause",e.getMessage());
            return new Gson().toJson(response);
        }

        response.put("time_stamp",new Date().toLocaleString());
        response.put("status","success");
        response.put("data",items);
        response.put("cause",null);

        return new Gson().toJson(response);
    }

  public  String searchItems(String query,int set){
        HashMap<String,Object> response=new HashMap<>();
        ArrayList<SearchItem>items=new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            final Connection connection = DriverManager.getConnection("jdbc:sqlserver://boston-solutions.database.windows.net:1433;database=bcg;encrypt=true;trustServerCertificate=true;", "zane", "takougoum2001NANA");

             PreparedStatement statement = connection.prepareStatement("SELECT\n" +
                     "    Name,\n" +
                     "    Id\n" +
                     "FROM\n" +
                     "    bcg.items \n" +
                     "\twhere Name like '%"+query+"%'\n" +
                     "ORDER BY\n" +
                     "    id \n" +
                     "\n" +
                     "OFFSET "+set*25+" ROWS \n" +
                     "FETCH NEXT 25 ROWS ONLY;");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next())
                items.add(new SearchItem(resultSet.getString("id"),resultSet.getString("name")));
            connection.close();
            statement.close();
        }
        catch (Exception e) {
            response.put("time_stamp", new Date().toLocaleString());
            response.put("status","error");
            response.put("cause",e.getMessage());
            return new Gson().toJson(response);
        }

        response.put("time_stamp",new Date().toLocaleString());
        response.put("status","success");
        response.put("data",items);
        response.put("cause",null);

        return new Gson().toJson(response);
    }


    public  String getItemById(String id){
        HashMap<String,Object> response=new HashMap<>();
        Item item = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            final Connection connection = DriverManager.getConnection("jdbc:sqlserver://boston-solutions.database.windows.net:1433;database=bcg;encrypt=true;trustServerCertificate=true;", "zane", "takougoum2001NANA");

            PreparedStatement statement = connection.prepareStatement("select * from bcg.items where id="+id);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next())
                item=new Item(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("unit_price")
                        ,resultSet.getString("gross_price"),resultSet.getString("description"),resultSet.getString("category"));
            connection.close();
            statement.close();
        }
        catch (Exception e) {
            response.put("time_stamp", new Date().toLocaleString());
            response.put("status","error");
            response.put("cause",e.getMessage());
            return new Gson().toJson(response);
        }

        response.put("time_stamp",new Date().toLocaleString());
        response.put("status","success");
        response.put("data",item);
        response.put("cause",null);

        return new Gson().toJson(response);
    }

    public  String getItemsFeed(){
        HashMap<String,Object> response=new HashMap<>();
        ArrayList<Item>items=new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            final Connection connection = DriverManager.getConnection("jdbc:sqlserver://boston-solutions.database.windows.net:1433;database=bcg;encrypt=true;trustServerCertificate=true;", "zane", "takougoum2001NANA");

            PreparedStatement statement = connection.prepareStatement(GET_ALL_PREVIEW);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next())
                items.add(new Item(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("unit_price")
                        ,resultSet.getString("gross_price"),resultSet.getString("description"),resultSet.getString("category")));
            connection.close();
            statement.close();
        }
        catch (Exception e) {
            response.put("time_stamp", new Date().toLocaleString());
            response.put("status","error");
            response.put("cause",e.getMessage());
            return new Gson().toJson(response);
        }

        response.put("time_stamp",new Date().toLocaleString());
        response.put("status","success");
        response.put("data",items);
        response.put("cause",null);

        return new Gson().toJson(response);
    }



}
