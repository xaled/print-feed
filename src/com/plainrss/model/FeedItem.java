package com.plainrss.model;

import java.io.Serializable;

public class  FeedItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private variables
	int _id;
	String _title;
	String _link;
	String _desc; //item's description
	String _date;
	int _seen = 0; //flag if the item is seen
	int _sourceID;
	

		// Empty Constructor
		public FeedItem(){
			
		}
		
		// Constructor with ID
		public FeedItem(int id, String title, String link, String desc, int sourceID, int seen, String date){
//		public RSSItem(int id, String title, String link){
			this._id = id;
			this._title = title;
			this._link = link;
			this._desc = desc;
			this._sourceID = sourceID;
			this._seen = seen;
			this._date = date;
			
		}
		
		
		// Constructor without ID with seen
		public FeedItem(String title, String link, String desc, int sourceID, int seen, String date){
//		public RSSItem(String title, String link){
			this._title = title;
			this._link = link;
			this._desc = desc;
			this._sourceID = sourceID;
			this._seen = seen;
			this._date = date;
			
		}
		
		// Constructor without seen
		public FeedItem(String title, String link, String desc, int sourceID, String date){
//			public RSSItem(String title, String link){
				this._title = title;
				this._link = link;
				this._desc = desc;
				this._sourceID = sourceID;
				this._date = date;
				this._seen = 0;
				
			}
			
		// getting ID
		public int getID(){
			return this._id;
			
		}
		// setting ID
		public void setID(int id){
			this._id = id;
			
		}
		
		// getting title
	    public String getTitle(){
	        return this._title;
	        
	    }
	     
	    // setting title
	    public void setTitle(String title){
	        this._title = title;
	    }
	     
	    // getting link
	    public String getLink(){
	        return this._link;
	        
	    }
	     
	    // setting link
	    public void setLink(String link){
	        this._link = link;
	        
	    }
	    
	    // getting description
	    public String getDesc(){
	        return this._desc;
	        
	    }
	     
	    // setting description
	    public void setDesc(String desc){
	        this._desc = desc;
	        
	    }
	    
		public int getSourceID() {
			return _sourceID;
			
		}

		public void setSourceID(int _sourceID) {
			this._sourceID = _sourceID;
			
		}
		
		public int getSeen() {
			return _seen;
			
		}

		public void setSeen(int _seen) {
			this._seen = _seen;
			
		}

		public String getDate() {
			return _date;
			
		}

		public void setDate(String _date) {
			this._date = _date;
			
		}

	}
