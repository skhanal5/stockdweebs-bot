package me.skhanal.StockDweebs;

import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	public static MongoClient mongoClient;
	public static MongoDatabase database;
	public static MongoCollection<Document> guildCollection;
	
	public static void main(String[]args) throws UnknownHostException {
		mongoClient = MongoClients.create(Constants.CLIENT_URL);
		database = mongoClient.getDatabase("StockDweebs");
		guildCollection = database.getCollection("GuildInfo");
	}
	
}
