package me.skhanal.StockDweebs;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoDB {

	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> guildInfo;
	
	//initializes static fields
	static {
		mongoClient = MongoClients.create(Constants.CLIENT_URL);
		database = mongoClient.getDatabase(Constants.DATABASE_NAME);
		guildInfo = database.getCollection(Constants.COLLECTION_NAME);
	}

	//adds this guild to the database
	public void add(String guildName, String guildId) {
		Document guildDoc = new Document();
		guildDoc.append("guild-name", guildName).append("guild-id", guildId);
		guildInfo.insertOne(guildDoc);
	}

	// delete's document in database on leave event
	public void remove(String guildId) {
		guildInfo.deleteMany(Filters.in("guild-id", List.of(guildId)));
	}

	//sets default channel name into the database
	public void setChannel(String guildId, String channelName, String channelId) {
		guildInfo.updateOne(Filters.in("guild-id", guildId), Updates.set("default-channel-name", channelName));
		guildInfo.updateOne(Filters.in("guild-id", guildId), Updates.set("default-channel-id", channelId));
	}
	
	//sets alerts to either on or off
	public void setAlerts(String guildId, String channelName) {
		guildInfo.updateOne(Filters.in("guild-id", guildId), Updates.set("alerts-setting", channelName));
	}
	
	//sets this week's dropbox url to the database
	public void setURL(String DROPBOX_URL) {
		guildInfo.updateOne(Filters.in("weekly-lists", Constants.LIST_DOC), Updates.set("this-week's-list", DROPBOX_URL));
	}
	
	//retrieves the default channel alerts are being posted on
	public String getChannelName(String guildId) {
		String currChannel = "";
		FindIterable<Document> search = guildInfo.find(Filters.in("guild-id", guildId));
		for (Document d: search) {
			currChannel = (String)d.get("default-channel-name");
		}
		return currChannel;
	}
	
	//retrieves the default channel's snowflake id
	public String getChannelID(String guildId) {
		String channelId = "";
		FindIterable<Document> search = guildInfo.find(Filters.in("guild-id", guildId));
		for (Document d: search) {
			channelId = (String)d.get("default-channel-id");
		}
		return channelId;
	}
	
	//retrieves whether alerts are on or off
	public String getAlerts(String guildId) {
		String currAlerts = "";
		FindIterable<Document> search = guildInfo.find(Filters.in("guild-id", guildId));
		for (Document d: search) {
			currAlerts = (String)d.get("alerts-setting");
		}
		return currAlerts;
	}
	
	//retrieves this week's dropbox page for the stockpicks and watchlist from the database
	public String getURL() {
		String currURL = "";
		FindIterable<Document> search = guildInfo.find(Filters.in("weekly-lists", Constants.LIST_DOC));
		for (Document d: search) {
			currURL = (String)d.get("this-week's-list");
		}
		return currURL;
	}
}
