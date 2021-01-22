package me.skhanal.StockDweebs;

import java.util.List;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;

public class BotInitializer {
	
	//initializes an instance of the Twitter4j twitter stream that we will be using to pull tweets
	public static TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
	
	//gives the bot more flexibility to listen to a wider array of events - view discord developer portal for more information
	private GatewayIntent[] gatewayIntents = new GatewayIntent[] {GatewayIntent.GUILD_MEMBERS};
	
	// The list below contains all of the listener classes the bot will listen to when an event is triggered
	private ListenerAdapter[] listenerAdapter = new ListenerAdapter[] {new CommandListener(), new AlertListener(), new JoinEventListener(), new ChannelListener(), new PicksListener(), new LeaveEventListener()};
	
	/*
	 * This method will set the bot with the proper credentials and settings for our use. It will also connect to the Twitter API
	 * with the proper credentials so we can make use of the twitter stream
	 */
	public void start() throws LoginException, InterruptedException{
		
		JDABuilder jdaBuilder = JDABuilder.createDefault(Constants.BOT_TOKEN);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, gatewayIntents);
		jdaBuilder.addEventListeners((Object[])listenerAdapter);
		
		//sets the discord bot's status
		jdaBuilder.setActivity(Activity.watching("StockDweeb's Posts"));

		JDA jda = jdaBuilder.build();
		jda.awaitReady();
		
		twitterStream.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		twitterStream.setOAuthAccessToken(new AccessToken(Constants.ACCESS_KEY, Constants.ACCESS_SECRET));
		
		/*
		 * The loop below accounts for an edge case where heroku's servers restart
		 * the bot which effectively terminates and relaunches the bot. In order to
		 * account for this, I have initialized the twitter stream during the 
		 * bot's startup for any server that has the alert setting on
		 * (temporary solution).
		 * 
		 * Note: If you are not using Heroku, the code below is NOT needed. 
		 */
		
		List<Guild> guilds = jda.getGuilds();
		for (Guild currGuild : guilds) {
			if (JoinEventListener.database.getAlerts(currGuild.getId()).equals("on")){
				AlertListener.startStream(currGuild, currGuild.getId());
			}
		}
	}

}
