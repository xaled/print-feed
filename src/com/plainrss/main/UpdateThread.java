package com.plainrss.main;


import java.util.List;

import com.plainrss.model.FeedSource;
import com.plainrss.model.FeedItem;
import com.plainrss.dbhandler.RSSDatabaseHandler;
import com.plainrss.fetchers.UrlFetcher;


public class UpdateThread implements Runnable{
	private RSSDatabaseHandler _db;

	public void run() {
		
		// TODO Auto-generated method stub
//		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
	     
	    List<FeedSource> sources = this._db.getAllFeedSources();
	    for (FeedSource source : sources){
	    	List<FeedItem> items = UrlFetcher.fetchFeedItems(source);
			for (FeedItem itm : items){
				this._db.addFeedItem(itm);
			}
			
	    }
	    
	};

	public UpdateThread(RSSDatabaseHandler db){
		this._db = db;

	}


}