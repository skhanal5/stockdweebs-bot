package me.skhanal.StockDweebs;

import java.awt.Color;

/*
 * This class contains many of the credentials that we will use to connect to Discord's
 * API, Twitter's API, and the MongoDB database. In addition, it contains other 
 * information that is used repeatedly throughout the code. Most of the highly
 * confidential information are stored in environment variables.
 */

public final class Constants {
	
/*	public static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
	public static final String INVITE_LINK = System.getenv("INVITE_LINK");
	public static final String CLIENT_URL = System.getenv("CLIENT_URL");
	public static final String DATABASE_NAME = System.getenv("DATABASE_NAME");
	public static final String COLLECTION_NAME = System.getenv("COLLECTION_NAME");
	public static final String ACCESS_KEY = System.getenv("ACCESS_KEY");
	public static final	String ACCESS_SECRET = System.getenv("ACCESS_SECRET");
	public static final String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
	public static final String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
	public static final String LIST_DOC = System.getenv("LIST_DOC"); */
	
	public static final long TWITTER_ID = 1260551652479647745L;
	public static final Color BRAND_COLOR = new Color(25, 0, 44);
	public static final String TWITTER_LOGO = "https://www.sfcg.org/wp-content/uploads/2016/11/twitter-flat.png";
	public static final String YOUTUBE_LOGO = "https://www.sfcg.org/wp-content/uploads/2016/11/youtube-flat.png";
	public static final String STOCKDWEEBS_LOGO = "https://yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg=s900-c-k-c0x00ffffff-no-rj";
	
	/*
	 * Prevents users from making an instance of this class.
	 */
	private Constants() {
		throw new IllegalArgumentException();
	}
}