package com.plainrss.dbhandler;


import java.util.ArrayList;
import java.util.List;

import com.printfeed.model.FeedItem;
import com.printfeed.model.FeedSource;

import java.sql.*;


public class RSSDatabaseHandler  {
	
	private static RSSDatabaseHandler instance;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "RSSmonster_DB";
    private static final String DATABASE_PATH = "printfeed.db";
 
    // RSSItems table title
    private static final String TABLE_RSSITEMS = "items";
    private static final String TABLE_SOURCES = "sources";
 
    // RSSItems Table Columns titles
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_LINK = "link";
    private static final String KEY_DESC = "desc";
    private static final String KEY_SRCID = "sourceID";
    private static final String KEY_SEEN = "seen";
    private static final String KEY_DATE = "date";
    
    // Sources Table Columns titles
    private static final String KEY_SRC_ID = "id";
    private static final String KEY_SRC_NAME = "name";
    private static final String KEY_SRC_URL = "sourceURL";
    private static final String KEY_SRC_TAGS = "tags";
 
    public RSSDatabaseHandler() {
        instance = this;
        Connection c = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    
    public static RSSDatabaseHandler getInstance(){
    	return instance;
    }

    // Creating Tables
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_RSSITEMS_TABLE = "CREATE TABLE " + TABLE_RSSITEMS + "("
//                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
//                + KEY_LINK + " TEXT NOT NULL UNIQUE, " + KEY_DESC + " TEXT, " 
//                + KEY_SRCID + " INTEGER, " + KEY_SEEN + " INTEGER, " + KEY_DATE + " TEXT " + ")";
//        String CREATE_SOURCES_TABLE = "CREATE TABLE " + TABLE_SOURCES + "("
//                + KEY_SRC_ID + " INTEGER PRIMARY KEY," + KEY_SRC_NAME + " TEXT,"
//                + KEY_SRC_URL + " TEXT NOT NULL UNIQUE, " + KEY_SRC_TAGS + " TEXT " + ")";
//        db.execSQL(CREATE_RSSITEMS_TABLE);
//        db.execSQL(CREATE_SOURCES_TABLE);
//    }
 
    // Upgrading database
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RSSITEMS);
// 
//        // Create tables again
//        onCreate(db);
//    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new FeedItem
    public void addFeedItem(FeedItem feedItem) {

    }
    
    // Adding new FeedSource
    public void addFeedSource(FeedSource feedsource){

    	
    }
 
    // Getting single FeedItem
    public FeedItem getFeedItem(int id) {
        return null;
    }
     
    // Getting All FeedItems
    public List<FeedItem> getAllFeedItems() {
        List<FeedItem> FeedItemsList = new ArrayList<FeedItem>();

        return FeedItemsList;
    }
    
    public List<FeedItem> getAllFeedItemsBySourceID(int srcId, Boolean seen){
        List<FeedItem> FeedItemsList = new ArrayList<FeedItem>();

        return FeedItemsList;
    }
    

 
    // Updating single rssItem
    public int updateFeedItem(FeedItem feedItem) {
    	return -1;
    }
    public int MarkFeedItemAsSeen(FeedItem feedItem) {
    	return -1;
    }
 
    // Deleting single rssItem
    public void deleteFeedItem(FeedItem feedItem) {

    }
 
 
    // Getting items Count
    public int getFeedItemsCount() {

        return 0;
    }
    
    public int getFeedItemsCount(int srcId, Boolean seen){
    	return 0;
    	
    }

    // Getting single source
    FeedSource getFeedSource(int id) {

        return null;
    }
     
    // Getting All Sources
    public List<FeedSource> getAllFeedSources() {
        List<FeedSource> sourceList = new ArrayList<FeedSource>();

        return sourceList;
    }
 
    // Updating single source
    public int updateFeedSource(FeedSource source) {
       return -1;
    }
 
    // Deleting single source
    public void deleteFeedSource(FeedSource source) {

    }
 
 
    // Getting sources Count
    public int getFeedSourceCount() {
        return 0;
    }
}

/*
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
 * 
 
 
public class RssDBHandler extends SQLiteOpenHelper {
	
	private static RssDBHandler instance;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "RSSmonster_DB";
 
    // RSSItems table title
    private static final String TABLE_RSSITEMS = "items";
    private static final String TABLE_SOURCES = "sources";
 
    // RSSItems Table Columns titles
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_LINK = "link";
    private static final String KEY_DESC = "desc";
    private static final String KEY_SRCID = "sourceID";
    private static final String KEY_SEEN = "seen";
    private static final String KEY_DATE = "date";
    
    // Sources Table Columns titles
    private static final String KEY_SRC_ID = "id";
    private static final String KEY_SRC_NAME = "name";
    private static final String KEY_SRC_URL = "sourceURL";
    private static final String KEY_SRC_TAGS = "tags";
 
    public RssDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        instance = this;
    }
    
    public static RssDBHandler getInstance(){
    	return instance;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RSSITEMS_TABLE = "CREATE TABLE " + TABLE_RSSITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_LINK + " TEXT NOT NULL UNIQUE, " + KEY_DESC + " TEXT, " 
                + KEY_SRCID + " INTEGER, " + KEY_SEEN + " INTEGER, " + KEY_DATE + " TEXT " + ")";
        String CREATE_SOURCES_TABLE = "CREATE TABLE " + TABLE_SOURCES + "("
                + KEY_SRC_ID + " INTEGER PRIMARY KEY," + KEY_SRC_NAME + " TEXT,"
                + KEY_SRC_URL + " TEXT NOT NULL UNIQUE, " + KEY_SRC_TAGS + " TEXT " + ")";
        db.execSQL(CREATE_RSSITEMS_TABLE);
        db.execSQL(CREATE_SOURCES_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RSSITEMS);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new rssItem
//    public void addRSSItem(RSSItem rssItem) {
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        ContentValues values = new ContentValues();
//        values.put(KEY_TITLE, rssItem.getTitle()); // RSSItem Name
//        values.put(KEY_LINK, rssItem.getLink()); // RSSItem URL
//        values.put(KEY_DESC, rssItem.getDesc()); // RSSItem desc
//        values.put(KEY_SRCID, rssItem.getSourceID()); // RSSItem sourceID
////        values.put(KEY_SEEN, rssItem.getSeen());
//        values.put(KEY_SEEN, 0);
//        values.put(KEY_DATE, rssItem.getDate());
//        // Inserting Row
//        db.insert(TABLE_RSSITEMS, null, values);
//        db.close(); // Closing database connection
//    }
//    
//    // Adding new source
//    public void addSource(Source source){
//    	SQLiteDatabase db = this.getWritableDatabase();
//    	
//    	ContentValues values = new ContentValues();
//    	values.put(KEY_SRC_NAME, source.getName()); // Source Name
//        values.put(KEY_SRC_URL, source.getSourceURL()); // Source URL
//        values.put(KEY_SRC_TAGS, source.getTags()); // Source tags
//        
//        // Inserting Row
//        db.insert(TABLE_SOURCES, null, values);
//        db.close(); // Closing database connection
//    	
//    }
// 
//    // Getting esingle rssItem
//    public RSSItem getRSSItem(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
// 
//        Cursor cursor = db.query(TABLE_RSSITEMS, new String[] { KEY_ID,
//                KEY_TITLE, KEY_LINK, KEY_DESC, KEY_SRCID, KEY_SEEN, KEY_DATE }, KEY_ID + "=?",
////        Cursor cursor = db.query(TABLE_RSSITEMS, new String[] { KEY_ID,
////                KEY_TITLE, KEY_LINK }, KEY_ID + "=?",
//
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
////        @SuppressWarnings("unused")
////		String dbug = cursor.getString(3);
//        RSSItem rssItem = new RSSItem(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2), cursor.getString(3)
//                , Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), cursor.getString(6));
//        // return rssItem
//        return rssItem;
//    }
//     
//    // Getting All RSSItems
//    public List<RSSItem> getAllRSSItems() {
//        List<RSSItem> rssItemList = new ArrayList<RSSItem>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_RSSITEMS;
// 
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
// 
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                RSSItem rssItem = new RSSItem();
//                rssItem.setID(Integer.parseInt(cursor.getString(0)));
//                rssItem.setTitle(cursor.getString(1));
//                rssItem.setLink(cursor.getString(2));
////                Log.d("DESCR","test!!!!!!" + cursor.getString(2));
//                rssItem.setDesc(cursor.getString(3));
//                rssItem.setSourceID(Integer.parseInt(cursor.getString(4)));
//                rssItem.setSeen(Integer.parseInt(cursor.getString(5)));
//                rssItem.setDate(cursor.getString(6));
//                // Adding rssItem to list
//                rssItemList.add(rssItem);
//            } while (cursor.moveToNext());
//        }
// 
//        // return rssItem list
//        return rssItemList;
//    }
//    public List<RSSItem> getAllRSSItemsBySourceID(String sourceID){
//    	List<RSSItem> items = new ArrayList<RSSItem>();
//    	String selectQuery = "SELECT  * FROM " + TABLE_RSSITEMS 
//    			+ " itm WHERE itm." + KEY_SRCID + " = '" + sourceID + "'";
//    	SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
// 
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                RSSItem rssItem = new RSSItem();
//                rssItem.setID(Integer.parseInt(cursor.getString(0)));
//                rssItem.setTitle(cursor.getString(1));
//                rssItem.setLink(cursor.getString(2));
////                Log.d("DESCR","test!!!!!!" + cursor.getString(2));
//                rssItem.setDesc(cursor.getString(3));
//                rssItem.setSourceID(Integer.parseInt(cursor.getString(4)));
//                rssItem.setSeen(Integer.parseInt(cursor.getString(5)));
//                rssItem.setDate(cursor.getString(6));
//                // Adding rssItem to list
//                items.add(rssItem);
//            } while (cursor.moveToNext());
//        }
//    	return items;
//    }
//    
//    public List<RSSItem> getAllNotSeenRSSItemsBySourceID(String sourceID){
//    	List<RSSItem> items = new ArrayList<RSSItem>();
//    	String selectQuery = "SELECT * FROM " + TABLE_RSSITEMS
//    				+ " itm WHERE itm." + KEY_SEEN + " = 1 AND itm." 
//    				+ KEY_SRCID + " = '" + sourceID + "'"; 
//    	SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                RSSItem rssItem = new RSSItem();
//                rssItem.setID(Integer.parseInt(cursor.getString(0)));
//                rssItem.setTitle(cursor.getString(1));
//                rssItem.setLink(cursor.getString(2));
////                Log.d("DESCR","test!!!!!!" + cursor.getString(2));
//                rssItem.setDesc(cursor.getString(3));
//                rssItem.setSourceID(Integer.parseInt(cursor.getString(4)));
//                rssItem.setSeen(Integer.parseInt(cursor.getString(5)));
//                rssItem.setDate(cursor.getString(6));
//                // Adding rssItem to list
//                items.add(rssItem);
//            } while (cursor.moveToNext());
//        }
//		return items;
//    	
//    }
// 
//    // Updating single rssItem
//    public int updateRSSItem(RSSItem rssItem) {
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        ContentValues values = new ContentValues();
//        values.put(KEY_TITLE, rssItem.getTitle());
//        values.put(KEY_LINK, rssItem.getLink());
//        values.put(KEY_DESC, rssItem.getDesc());
//        values.put(KEY_SRCID, rssItem.getSourceID());
//        values.put(KEY_SEEN, rssItem.getSeen());
//        values.put(KEY_DATE, rssItem.getDate());
//        // updating row
//        return db.update(TABLE_RSSITEMS, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(rssItem.getID()) });
//    }
//    public int MarkRSSItemAsSeen(RSSItem rssItem) {
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        ContentValues values = new ContentValues();
//        values.put(KEY_TITLE, rssItem.getTitle());
//        values.put(KEY_LINK, rssItem.getLink());
//        values.put(KEY_DESC, rssItem.getDesc());
//        values.put(KEY_SRCID, rssItem.getSourceID());
//        values.put(KEY_SEEN, 1);
//        values.put(KEY_DATE, rssItem.getDate());
//        // updating row
//        return db.update(TABLE_RSSITEMS, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(rssItem.getID()) });
//    }
// 
//    // Deleting single rssItem
//    public void deleteRSSItem(RSSItem rssItem) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_RSSITEMS, KEY_ID + " = ?",
//                new String[] { String.valueOf(rssItem.getID()) });
//        db.close();
//    }
// 
// 
//    // Getting items Count
//    public int getRSSItemsCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_RSSITEMS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
// 
//        // return count
//        return cursor.getCount();
//    }
//    
//    public int getFeedItemsCount(String srcID){
//    	String countQuery = "SELECT  * FROM " + TABLE_RSSITEMS + " itm WHERE itm." + KEY_SRCID
//    			+ " = '" + srcID + "'";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
// 
//        // return count
//        return cursor.getCount();
//    	
//    }
//    public int getNotSeenFeedItemsCount(String srcID){
//    	String countQuery = "SELECT  * FROM " + TABLE_RSSITEMS + " itm WHERE itm." 
//    			+ KEY_SRCID	+ " = '" + srcID + "' AND itm." + KEY_SEEN + " = 1";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
// 
//        // return count
//        return cursor.getCount();
//    }
// // Getting single source
//    Source getSource(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
// 
//        Cursor cursor = db.query(TABLE_SOURCES, new String[] { KEY_SRC_ID,
//                KEY_SRC_NAME, KEY_SRC_URL, KEY_SRC_TAGS }, KEY_SRC_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
// 
//        Source source = new Source(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2), cursor.getString(3));
//        // return source
//        return source;
//    }
//     
//    // Getting All Sources
//    public List<Source> getAllSources() {
//        List<Source> sourceList = new ArrayList<Source>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_SOURCES;
// 
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
// 
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Source source = new Source();
//                source.setID(Integer.parseInt(cursor.getString(0)));
//                source.setName(cursor.getString(1));
//                source.setSourceURL(cursor.getString(2));
//                source.setTags(cursor.getString(3));
//                // Adding source to list
//                sourceList.add(source);
//            } while (cursor.moveToNext());
//        }
// 
//        // return source list
//        return sourceList;
//    }
// 
//    // Updating single source
//    public int updateSource(Source source) {
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        ContentValues values = new ContentValues();
//        values.put(KEY_SRC_NAME, source.getName());
//        values.put(KEY_SRC_URL, source.getSourceURL());
//        values.put(KEY_SRC_TAGS, source.getTags());
// 
//        // updating row
//        return db.update(TABLE_SOURCES, values, KEY_SRC_ID + " = ?",
//                new String[] { String.valueOf(source.getID()) });
//    }
// 
//    // Deleting single source
//    public void deleteSource(Source source) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_SOURCES, KEY_SRC_ID + " = ?",
//                new String[] { String.valueOf(source.getID()) });
//        db.close();
//    }
// 
// 
//    // Getting sources Count
//    public int getSourcesCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_SOURCES;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
// 
//        // return count
//        return cursor.getCount();
//    }
// 
//}