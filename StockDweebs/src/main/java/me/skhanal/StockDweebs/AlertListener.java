package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/*
 * This listener below will look for the !alerts [on/off] command and will
 * either turn on alerts or turn off alerts depending on what the user
 * types in. 
 * 
 * Pre-requisite: user must have the administrator command
 */

public class AlertListener extends ListenerAdapter {
	
	/*
	 * This method will listen to the !alerts command and will post an embedded message
	 * or a warning depending on how the user has used the command.
	 */
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) { 	
		String guildId = e.getGuild().getId();
		String currChannel = e.getTextChannel().getName();
		String definedChannel = JoinEventListener.database.getChannelName(guildId);
		String user = e.getAuthor().getName();
		boolean checkPerms = e.getMember().hasPermission(Permission.ADMINISTRATOR);
		
		/*
		 * The first few lines of the conditional statements checks if the user
		 * has appropriately used the commands and has the required admin
		 * permissions to use this command. If not, a warning message will be
		 * sent to the user.
		 */
		
		if (e.getMessage().getContentRaw().equals("!alerts")) {
			e.getChannel().sendMessage("Invalid command. To use this command type !alerts and either \"on\" or \"off\" (without quotes) following that statement with a space. If you need additional assistance, refer to !setup for help").queue();
		} else if (e.getMessage().getContentRaw().startsWith("!alerts") && ((!(e.getMessage().getContentRaw().endsWith(" on"))) && (!(e.getMessage().getContentRaw().endsWith(" off"))))){
			e.getChannel().sendMessage("Invalid command. To use this command type !alerts and either \"on\" or \"off\" (without quotes) following that statement with a space. If you need additional assistance, refer to !setup for help").queue();
		} else if ((e.getMessage().getContentRaw().matches("!alerts on|!alerts off")) && (!checkPerms)) {
			e.getChannel().sendMessage("User: " + user + " does not have valid permissions to use this command.").queue();
		} else if ((e.getMessage().getContentRaw().matches("!alerts on|!alerts off")) && (definedChannel.equals("null"))) {
			e.getChannel().sendMessage("You have not setup a channel for this bot to send alerts and posts on. Please do so immediately using the !setchannel command. If you need additional assistance, refer to !setup for help.").queue();
		} else if ((e.getMessage().getContentRaw().matches("!alerts on|!alerts off")) && (!(definedChannel.equals(currChannel)))) {
			e.getChannel().sendMessage("Channel mismatch. The bot is currently set to the #" + definedChannel + " channel. Please use this command in that channel").queue();
		} else if (e.getMessage().getContentRaw().equals("!alerts on") && (definedChannel.equals(currChannel) && (JoinEventListener.database.getAlerts(guildId).equals("on")))) {
			e.getChannel().sendMessage("Alerts are already on for the channel: " + definedChannel + ".").queue();
		} else if (e.getMessage().getContentRaw().equals("!alerts on")) {
			e.getChannel().sendMessage(alertEmbed(e, true)).queue();
			JoinEventListener.database.setAlerts(guildId, "on"); //stores the alert setting in the database
		} else if (e.getMessage().getContentRaw().equals("!alerts off") && (JoinEventListener.database.getAlerts(guildId).equals("on"))) {
			e.getChannel().sendMessage(alertEmbed(e, false)).queue();
			JoinEventListener.database.setAlerts(guildId, "off"); //stores the alert setting in the database
		} 
	}
	
	/*
	 * If either !alerts on or !alerts off was used as intended, an embedded
	 * message will be constructed and sent informing the user that the alerts 
	 * will be on or off for that channel.
	 */
	
	private static MessageEmbed alertEmbed (MessageReceivedEvent e, boolean checkAlerts) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		if(checkAlerts == true) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setThumbnail(Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setDescription("Twitter and Youtube post notifications will be ON for the following channel: " + e.getChannel().getName());
		} else if (checkAlerts == false){
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setThumbnail(Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setDescription("Twitter and Youtube post notifications will be OFF for the following channel: " + e.getChannel().getName());
		}
		return embedBuilder.build();
	}
	
	/*
	 * Configures the twitter stream so that posts from ONLY the StockDweeb's
	 * twitter are posted and other twitter handles are filtered out
	 */
	
	public static void startStream (Guild guild, String guildId) {
		StatusListener listener = new StatusListener() {
			
			@Override
			public void onStatus(Status status) {
				
				/*
				 * This first statement checks if the StockDweeb's twitter posted
				 * its weekly watchlist and if so it will store the link to the watchlist
				 * in the database 
				 */
				
				if (status.getText().contains("View the report") && status.getUser().getId() == Constants.TWITTER_ID) {
					int startingIndex = status.getText().indexOf("https://");
					int endingIndex = startingIndex+23;
					JoinEventListener.database.setURL(status.getText().substring(startingIndex, endingIndex));
				}
				
				/*
				 * This conditional below essentially checks if alerts are on and if so, it will
				 * post the link of the tweets into the specified discord channel
				 */
				
                if (JoinEventListener.database.getAlerts(guildId).equals("on") && status.getUser().getId() == Constants.TWITTER_ID) {
                    String tweet = "http://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();
                	TextChannel currChannel = guild.getTextChannelById(JoinEventListener.database.getChannelID(guildId));
                	currChannel.sendMessage(tweet).queue();
                }
            }
			
			@Override
			public void onException(Exception e) {
				e.printStackTrace();
			}

			@Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

			@Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

			@Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

			@Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }
		};
		
		/*
		 * The statements below will filter out other twitter accounts 
		 * so that the stream will only pull StockDweebs' account.
		 */
		
		BotInitializer.twitterStream.addListener(listener);
		FilterQuery query = new FilterQuery();
		query.follow(Constants.TWITTER_ID);
		BotInitializer.twitterStream.filter(query);
	}
}
