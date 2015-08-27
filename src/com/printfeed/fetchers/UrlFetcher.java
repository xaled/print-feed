package com.printfeed.fetchers;

import java.util.ArrayList;
import java.util.List;

import org.horrabin.horrorss.*;

import com.printfeed.model.FeedItem;
import com.printfeed.model.FeedSource;

public class UrlFetcher {
	public static List<FeedItem> fetchFeedItems(FeedSource source) {
		// new FeedItem(itm.getTitle(), itm.getLink(), itm.getDescription(),
		// source.getID(), itm.getPubDate().toString())
		RssParser rss = new RssParser();
		RssFeed feed;
		ArrayList<FeedItem> itemslist = new ArrayList<FeedItem>();
		try {
			feed = rss.load(source.getSourceURL());
			List<RssItemBean> items = feed.getItems();
			for (RssItemBean itm : items) {
				itemslist.add(new FeedItem(itm.getTitle(), itm.getLink(), itm
						.getDescription(), source.getID(), itm.getPubDate()
						.toString()));
			}
			return itemslist;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
