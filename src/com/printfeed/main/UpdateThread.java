package com.printfeed.main;


import java.util.List;

import com.printfeed.fetchers.UrlFetcher;
import com.printfeed.model.FeedItem;
import com.printfeed.model.FeedSource;


public class UpdateThread implements Runnable{
	private PrintfeedDatabaseHandler _db;

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

	public UpdateThread(PrintfeedDatabaseHandler db){
		this._db = db;

	}


}