package me.skhanal.StockDweebs;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;

public class BotInitializer {
	
	public static TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
	GatewayIntent[] gatewayIntents = new GatewayIntent[] {GatewayIntent.GUILD_MEMBERS};
	ListenerAdapter[] listenerAdapter = new ListenerAdapter[] {new CommandListener(), new AlertListener(), new SetupListener(), new ChannelListener()};
		
	public void start() throws LoginException, InterruptedException{
		JDABuilder jdaBuilder = JDABuilder.createDefault(Constants.BOT_TOKEN);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, gatewayIntents);
		jdaBuilder.addEventListeners((Object[])listenerAdapter); //consider doing something about this line of code?
		
		jdaBuilder.setActivity(Activity.watching("StockDweeb's Posts"));

		JDA jda = jdaBuilder.build();
		jda.awaitReady();
		
		twitterStream.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		twitterStream.setOAuthAccessToken(new AccessToken(Constants.ACCESS_KEY, Constants.ACCESS_SECRET));
	}

}
