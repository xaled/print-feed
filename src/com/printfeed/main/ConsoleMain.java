package com.printfeed.main;

import java.util.List;

import com.printfeed.model.FeedItem;
import com.printfeed.model.FeedSource;

public class ConsoleMain {

	public static void main(String[] args) {
		System.out.println("welcome to plain rss");
		System.out.println("updating database");
		
		RSSDatabaseHandler db =new RSSDatabaseHandler();
		
		UpdateThread thrd = new UpdateThread(db);
		thrd.run();//normally start()
		
		System.out.println("database is updated");
		
		List<FeedSource> sources = db.getAllFeedSources();
		System.out.println("available sources are:");
		for(FeedSource src:sources){
			System.out.println("+ " + src.getName() + " : " + src.getSourceURL());
		}
		List<FeedItem> items = db.getAllFeedItems();
		System.out.println("available feeds are:");
		

	}
	
	

}
