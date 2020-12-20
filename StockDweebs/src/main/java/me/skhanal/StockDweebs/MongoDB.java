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
	
	public String getChannel(String guildId) {
		String currChannel = "";
		FindIterable<Document> search = guildInfo.find(Filters.in("guild-id", guildId));
		for (Document d: search) {
			currChannel = (String)d.get("default-channel-name");
		}
		return currChannel;
	}

	//add's default channel name into the database
	public void setChannel(String guildId, String channelName) {
		guildInfo.updateOne(Filters.in("guild-id", guildId), Updates.set("default-channel-name", channelName));
	}

	public static void main(String[] args) {
		//MongoDB db = new MongoDB();
		//System.out.println("HELLO" + db.getChannel("775162291728678953"));
	}

}
