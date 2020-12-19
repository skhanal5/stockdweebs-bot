package me.skhanal.StockDweebs;

import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoDB {

	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> guildInfo;

	public MongoDB() {
		mongoClient = MongoClients.create(Constants.CLIENT_URL);
		database = mongoClient.getDatabase("StockDweebs");
		guildInfo = database.getCollection("GuildInfo");
	}

	public void add(String guildName, String guildId) {
		Document guildDoc = new Document();
		guildDoc.append("guild-name", guildName).append("guild-id", guildId);
		guildInfo.insertOne(guildDoc);
	}

	// delete's document in database on leave event
	public void remove(String guildId) {
		guildInfo.deleteMany(Filters.in("guild-id", List.of(guildId)));
	}

	//update's document in database when !setchannel is initialized/updated?
	public void update(String guildId, String channelId) {
		guildInfo.updateOne(Filters.in("guild-id", guildId), Updates.set("default-channel", channelId));
	}

	public static void main(String[] args) {
		MongoDB db = new MongoDB();
		//db.add("Hector's Traphouse", "12314241421");
		//db.add("bot-test-1", "21313413413");
		//db.update("12314241421", "Stock-Dweebs");
		db.remove("12314241421");
	}

}
