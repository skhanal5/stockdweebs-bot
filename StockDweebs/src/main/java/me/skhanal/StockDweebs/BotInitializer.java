package me.skhanal.StockDweebs;

import javax.security.auth.login.LoginException;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BotInitializer {
	
	
	GatewayIntent[] gatewayIntents = new GatewayIntent[] {GatewayIntent.GUILD_MEMBERS};
	ListenerAdapter[] listenerAdapter = new ListenerAdapter[] {new CommandListener(), new AlertListener(), new SetupListener(), new ChannelListener()};
		
	public void start() throws LoginException, InterruptedException{
		JDABuilder jdaBuilder = JDABuilder.createDefault(Constants.BOT_TOKEN);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, gatewayIntents);
		jdaBuilder.addEventListeners((Object[])listenerAdapter); //consider doing something about this line of code?
		
		jdaBuilder.setActivity(Activity.watching("StockDweeb's Posts"));

		JDA jda = jdaBuilder.build();
		jda.awaitReady();
	}

}
