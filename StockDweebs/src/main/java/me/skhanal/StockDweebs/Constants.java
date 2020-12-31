package me.skhanal.StockDweebs;

import java.awt.Color;

public final class Constants {
	public static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
	public static final String INVITE_LINK = System.getenv("INVITE_LINK");
	public static final String CLIENT_URL = System.getenv("CLIENT_URL");
	public static final String DATABASE_NAME = System.getenv("DATABASE_NAME");
	public static final String COLLECTION_NAME = System.getenv("COLLECTION_NAME");
	public static final String ACCESS_KEY = System.getenv("ACCESS_KEY");
	public static final	String ACCESS_SECRET = System.getenv("ACCESS_SECRET");
	public static final String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
	public static final String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
	public static final String LIST_DOC = System.getenv("LIST_DOC");
	
	public static final long TWITTER_ID = 762836089732083712L;
	public static final Color BRAND_COLOR = new Color(103, 114, 229);
	public static final String TWITTER_LOGO = "https://www.sfcg.org/wp-content/uploads/2016/11/twitter-flat.png";
	public static final String YOUTUBE_LOGO = "https://www.sfcg.org/wp-content/uploads/2016/11/youtube-flat.png";
	public static final String STOCKDWEEBS_LOGO = "https://pbs.twimg.com/profile_images/1302540747179057153/ekdTSrB6_400x400.jpg";
	public static final String PDF_LOGO = "https://images-ext-1.discordapp.net/external/TtMXwofJhLzXyMUKbDXite1frG79TKNbxlE63y0BrcI/https/www.dropbox.com/static/images/spectrum-icons/generated/content/content-pdf-large.png";
	
	private Constants() {
		throw new IllegalArgumentException();
	}
}