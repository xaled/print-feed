package com.printfeed.main;




import java.util.ArrayList;
import java.util.List;

import com.printfeed.model.*;
import java.sql.*;


public class PrintfeedDatabaseHandler  {

	private static PrintfeedDatabaseHandler instance;
    // I- All private and static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "printfeed";
    private static final String DATABASE_PATH = "printfeed.db";

    // Database table title
    private static final String TABLE_FEEDITEM = "feeditem";
    private static final String TABLE_FEEDSOURCE = "feedsource";

    private static final String KEY_ID = "id";
    // FeedItem Table Columns titles
    private static final String KEY_TITLE = "title";
    private static final String KEY_LINK = "link";
    private static final String KEY_DESC = "desc";
    private static final String KEY_SRCID = "srcid";
    private static final String KEY_SEEN = "seen";
    private static final String KEY_DATE = "date";

    // FeedSource Table Columns titles
    private static final String KEY_NAME = "name";
    private static final String KEY_URL = "url";
    private static final String KEY_TAGS = "tags";


    private Connection connection = null;

    // II- Constructors
    public PrintfeedDatabaseHandler() {
        instance = this;
        connection = null;
        try {
          Class.forName("org.sqlite.JDBC");
          connection = DriverManager.getConnection("jdbc:sqlite:"+DATABASE_PATH);
          System.out.println("Opened database successfully");
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }

    }

    public static PrintfeedDatabaseHandler getInstance(){
    	return instance;
    }

    // III- Creating & updating Tables
    public void onCreate() {

		try {
			Statement stmt = connection.createStatement();
	        String CREATE_FEEDITEM_TABLE = "CREATE TABLE " + TABLE_FEEDITEM + "("+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_LINK + " TEXT," + KEY_DESC + " TEXT," + KEY_SRCID + " INTEGER," + KEY_SEEN + " INTEGER," + KEY_DATE + " INTEGER " + ")";
            stmt.executeUpdate(CREATE_FEEDITEM_TABLE);

	        String CREATE_FEEDSOURCE_TABLE = "CREATE TABLE " + TABLE_FEEDSOURCE + "("+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_URL + " TEXT," + KEY_TAGS + " TEXT " + ")";
            stmt.executeUpdate(CREATE_FEEDSOURCE_TABLE);

	        stmt.close();
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
    }

    // Upgrading database

    public void onUpgrade() {
    	try {
	        // Drop older table if existed
	    	Statement stmt = connection.createStatement();
	    	stmt.executeUpdate("DROP TABLE IF EXISTS " + TABLE_FEEDITEM);
	    	stmt.executeUpdate("DROP TABLE IF EXISTS " + TABLE_FEEDSOURCE);
	        // Create tables again
	        onCreate();
	        stmt.close();
    	} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
    }

    /**
     * IV- All CRUD(Create, Read, Update, Delete) Operations
     */

    // IV.1 Creating
    // Adding new FeedItem
    public void addFeedItem(FeedItem _feeditem) {
        try {
            String sql = "INSERT INTO "+TABLE_FEEDITEM +" (title, link, desc, srcid, seen, date) "+
                         "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++,_feeditem.getTitle());
            stmt.setString(i++,_feeditem.getLink());
            stmt.setString(i++,_feeditem.getDesc());
            stmt.setLong(i++,_feeditem.getSrcId());
            stmt.setBoolean(i++,_feeditem.getSeen());
            stmt.setLong(i++,_feeditem.getDate());
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
    }
    // Adding new FeedSource
    public void addFeedSource(FeedSource _feedsource) {
        try {
            String sql = "INSERT INTO "+TABLE_FEEDSOURCE +" (name, url, tags) "+
                         "VALUES (?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++,_feedsource.getName());
            stmt.setString(i++,_feedsource.getUrl());
            stmt.setString(i++,_feedsource.getTags());
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
    }

    // IV.2 Reading
    // IV.2.1 Getting Single
    // Getting single FeedItem
    public FeedItem getFeedItem(long id) {
        try{

            String sql = "SELECT * FROM TABLE_FEEDITEM WHERE id=? LIMIT 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if( rs.next() ) {
                FeedItem feeditem_ = new FeedItem();
                feeditem_.setId(rs.getLong("id"));
                feeditem_.setTitle(rs.getString("title"));
                feeditem_.setLink(rs.getString("link"));
                feeditem_.setDesc(rs.getString("desc"));
                feeditem_.setSrcId(rs.getLong("srcid"));
                feeditem_.setSeen(rs.getBoolean("seen"));
                feeditem_.setDate(rs.getLong("date"));
                return feeditem_;
            }
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return null;
    }
    // Getting single FeedSource
    public FeedSource getFeedSource(long id) {
        try{

            String sql = "SELECT * FROM TABLE_FEEDSOURCE WHERE id=? LIMIT 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if( rs.next() ) {
                FeedSource feedsource_ = new FeedSource();
                feedsource_.setId(rs.getLong("id"));
                feedsource_.setName(rs.getString("name"));
                feedsource_.setUrl(rs.getString("url"));
                feedsource_.setTags(rs.getString("tags"));
                return feedsource_;
            }
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return null;
    }



    // IV.2.2 Getting All
    // Getting All FeedItem
    public List<FeedItem> getAllFeedItems() {
        ArrayList<FeedItem> list = new ArrayList<FeedItem>();
        try{

            String sql = "SELECT * FROM TABLE_FEEDITEM";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while( rs.next() ) {
                FeedItem feeditem_ = new FeedItem();
                feeditem_.setId(rs.getLong("id"));
                feeditem_.setTitle(rs.getString("title"));
                feeditem_.setLink(rs.getString("link"));
                feeditem_.setDesc(rs.getString("desc"));
                feeditem_.setSrcId(rs.getLong("srcid"));
                feeditem_.setSeen(rs.getBoolean("seen"));
                feeditem_.setDate(rs.getLong("date"));
                list.add(feeditem_);
            }
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
		return list;
    }
    // Getting All FeedSource
    public List<FeedSource> getAllFeedSources() {
        ArrayList<FeedSource> list = new ArrayList<FeedSource>();
        try{

            String sql = "SELECT * FROM TABLE_FEEDSOURCE";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while( rs.next() ) {
                FeedSource feedsource_ = new FeedSource();
                feedsource_.setId(rs.getLong("id"));
                feedsource_.setName(rs.getString("name"));
                feedsource_.setUrl(rs.getString("url"));
                feedsource_.setTags(rs.getString("tags"));
                list.add(feedsource_);
            }
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
		return list;
    }

    //IV.2.3 TODO: special gets

    //IV.2.4 Getting counts
    // Getting FeedItems count
    public int getAllFeedItemsCount() {
        try{

            String sql = "SELECT COUNT(*) as countt FROM TABLE_FEEDITEM";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if( rs.next() ) {
                return rs.getInt("countt");
            }
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return -1;
    }
    // Getting FeedSources count
    public int getAllFeedSourcesCount() {
        try{

            String sql = "SELECT COUNT(*) as countt FROM TABLE_FEEDSOURCE";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if( rs.next() ) {
                return rs.getInt("countt");
            }
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return -1;
    }

    //IV.3 Updating
    //IV.3.1 Updating single
    // Updating single FeedItem
    public void updateFeedItem(FeedItem _feeditem) {
        try {
            String sql = "UPDATE "+TABLE_FEEDITEM +" SET title = ?, title = ?, link = ?, desc = ?, srcid = ?, seen = ?, date = ? "+
                         "WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++,_feeditem.getTitle());
            stmt.setString(i++,_feeditem.getLink());
            stmt.setString(i++,_feeditem.getDesc());
            stmt.setLong(i++,_feeditem.getSrcId());
            stmt.setBoolean(i++,_feeditem.getSeen());
            stmt.setLong(i++,_feeditem.getDate());
            stmt.setLong(i++,_feeditem.getId());
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
    }
    // Updating single FeedSource
    public void updateFeedSource(FeedSource _feedsource) {
        try {
            String sql = "UPDATE "+TABLE_FEEDSOURCE +" SET name = ?, name = ?, url = ?, tags = ? "+
                         "WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++,_feedsource.getName());
            stmt.setString(i++,_feedsource.getUrl());
            stmt.setString(i++,_feedsource.getTags());
            stmt.setLong(i++,_feedsource.getId());
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
    }

    //IV.3.2 TODO: Updating special


    //IV.4 Deleting
    // Deleting single FeedItem
    public void deleteFeedItem(FeedItem _feeditem) {
        try {
            String sql = "DELETE FROM "+TABLE_FEEDITEM + " WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1,_feeditem.getId());
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
    }
    // Deleting single FeedSource
    public void deleteFeedSource(FeedSource _feedsource) {
        try {
            String sql = "DELETE FROM "+TABLE_FEEDSOURCE + " WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1,_feedsource.getId());
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
    }




}