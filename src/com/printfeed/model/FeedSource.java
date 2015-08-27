package com.printfeed.model;


public class FeedSource {
	// private variables
	int _id;
	String _name;
	String _sourceURL;
	String _tags;
	
	// Empty Constructor
	public FeedSource(){
		
	}
	
	// Constructor with ID
	public FeedSource(int id, String name, String sourceURL, String tags){
		this._id = id;
		this._name = name;
		this._sourceURL = sourceURL;
		this._tags = tags;
		
	}
	
	// Constructor without ID
	public FeedSource(String name, String sourceURL, String tags){
		this._name = name;
		this._sourceURL = sourceURL;
		this._tags = tags;
		
	}
		
	// getting ID
	public int getID(){
		return this._id;
		
	}
	// setting ID
	public void setID(int id){
		this._id = id;
	}
	// getting name
    public String getName(){
        return this._name;
    }
     
    // setting name
    public void setName(String name){
        this._name = name;
    }
     
    // getting source's URL
    public String getSourceURL(){
        return this._sourceURL;
    }
     
    // setting source URL
    public void setSourceURL(String URL){
        this._sourceURL = URL;
    }
 // getting tags
    public String getTags(){
        return this._tags;
    }
     
    // setting tags
    public void setTags(String tags){
        this._tags = tags;
    }

}
