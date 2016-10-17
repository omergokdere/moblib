package com.moblib.connection;

import com.moblib.utilities.ListViewItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by omer.gokdere on 28.11.2015.
 */
public class mysqlConnection {

    Connection conn;

    public void Connect() throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection("jdbc:mysql://ServerIP:ServerPortNumber/schemaNameuser=ID&password=password");
    }

    public void Disconnect() throws Exception
    {
        conn.close();
    }

    public List<ListViewItem> FindByAuthor(String author) throws Exception
    {
        Connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT bookID,author,title,description,category,publisher,language,otherAuthor,isbn FROM vw_books WHERE author LIKE '%" + author + "%' ORDER BY title");
        List<ListViewItem> list = new ArrayList<ListViewItem>();

        ListViewItem listViewItem;

        while (rs.next())
        {
            listViewItem = new ListViewItem();
            listViewItem.setBookID(rs.getInt("bookID"));
            listViewItem.setColumn1(rs.getString("author"));
            listViewItem.setColumn2(rs.getString("title"));
            listViewItem.setAuthor(rs.getString("author"));
            listViewItem.setTitle(rs.getString("title"));
            listViewItem.setDescription(rs.getString("description"));
            listViewItem.setCategory(rs.getString("category"));
            listViewItem.setPublisher(rs.getString("publisher"));
            listViewItem.setLanguage(rs.getString("language"));
            listViewItem.setOtherAuthor(rs.getString("otherAuthor"));
            listViewItem.setIsbn(rs.getString("isbn"));
            list.add(listViewItem);
        }
        rs.close();
        stmt.close();
        Disconnect();
        return list;
    }

    public List<ListViewItem> FindByCategory(String category) throws Exception
    {
        Connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT bookID,author,title,description,category,publisher,language,otherAuthor,isbn FROM vw_books WHERE category LIKE '%" + category + "%' ORDER BY title");
        List<ListViewItem> list = new ArrayList<ListViewItem>();

        ListViewItem listViewItem;

        while (rs.next())
        {
            listViewItem = new ListViewItem();
            listViewItem.setBookID(rs.getInt("bookID"));
            listViewItem.setColumn1(rs.getString("category"));
            listViewItem.setColumn2(rs.getString("title"));
            listViewItem.setAuthor(rs.getString("author"));
            listViewItem.setTitle(rs.getString("title"));
            listViewItem.setDescription(rs.getString("description"));
            listViewItem.setCategory(rs.getString("category"));
            listViewItem.setPublisher(rs.getString("publisher"));
            listViewItem.setLanguage(rs.getString("language"));
            listViewItem.setOtherAuthor(rs.getString("otherAuthor"));
            listViewItem.setIsbn(rs.getString("isbn"));
            list.add(listViewItem);
        }
        rs.close();
        stmt.close();
        Disconnect();
        return list;
    }

    public List<ListViewItem> FindByPublisher(String publisher) throws Exception
    {
        Connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT bookID,author,title,description,category,publisher,language,otherAuthor,isbn FROM vw_books WHERE publisher LIKE '%" + publisher + "%' ORDER BY title");
        List<ListViewItem> list = new ArrayList<ListViewItem>();

        ListViewItem listViewItem;

        while (rs.next())
        {
            listViewItem = new ListViewItem();
            listViewItem.setBookID(rs.getInt("bookID"));
            listViewItem.setColumn1(rs.getString("publisher"));
            listViewItem.setColumn2(rs.getString("title"));
            listViewItem.setAuthor(rs.getString("author"));
            listViewItem.setTitle(rs.getString("title"));
            listViewItem.setDescription(rs.getString("description"));
            listViewItem.setCategory(rs.getString("category"));
            listViewItem.setPublisher(rs.getString("publisher"));
            listViewItem.setLanguage(rs.getString("language"));
            listViewItem.setOtherAuthor(rs.getString("otherAuthor"));
            listViewItem.setIsbn(rs.getString("isbn"));
            list.add(listViewItem);
        }
        rs.close();
        stmt.close();
        Disconnect();
        return list;
    }

    public List<ListViewItem> FindByTitle(String title) throws Exception
    {
        Connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT bookID,author,title,description,category,publisher,language,otherAuthor,isbn FROM vw_books WHERE title LIKE '%" + title + "%' ORDER BY title");
        List<ListViewItem> list = new ArrayList<ListViewItem>();

        ListViewItem listViewItem;

        while (rs.next())
        {
            listViewItem = new ListViewItem();
            listViewItem.setBookID(rs.getInt("bookID"));
            listViewItem.setColumn1(rs.getString("author"));
            listViewItem.setColumn2(rs.getString("title"));
            listViewItem.setAuthor(rs.getString("author"));
            listViewItem.setTitle(rs.getString("title"));
            listViewItem.setDescription(rs.getString("description"));
            listViewItem.setCategory(rs.getString("category"));
            listViewItem.setPublisher(rs.getString("publisher"));
            listViewItem.setLanguage(rs.getString("language"));
            listViewItem.setOtherAuthor(rs.getString("otherAuthor"));
            listViewItem.setIsbn(rs.getString("isbn"));
            list.add(listViewItem);
        }
        rs.close();
        stmt.close();
        Disconnect();
        return list;
    }


    public List<ListViewItem> getBookList(int searchBy, String searchText) throws Exception
    {
        List<ListViewItem> list = null;
        switch (searchBy)
        {
            case 1:
                list = FindByAuthor(searchText);
                break;
            case 2:
                list = FindByCategory(searchText);
                break;
            case 3:
                list = FindByPublisher(searchText);
                break;
            case 4:
                list = FindByTitle(searchText);
                break;
            default :
                list = null;
                break;
        }
        return list;
    }
}
