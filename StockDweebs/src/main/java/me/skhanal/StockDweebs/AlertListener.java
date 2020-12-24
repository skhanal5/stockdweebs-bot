package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;

public class AlertListener extends ListenerAdapter {
	
	private static boolean ALERTS_ON = false;
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		String guildId = e.getGuild().getId();
		String currChannel = e.getTextChannel().getName();
		String definedChannel = SetupListener.database.getChannel(guildId);
		String user = e.getAuthor().getName();
		boolean checkPerms = e.getMember().hasPermission(Permission.ADMINISTRATOR);
		
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
		} else if (e.getMessage().getContentRaw().equals("!alerts on") && (definedChannel.equals(currChannel) && (ALERTS_ON == true))) {
			e.getChannel().sendMessage("Alerts are already on for the channel: " + definedChannel + ".").queue();
		} else if (e.getMessage().getContentRaw().equals("!alerts on")) {
			ALERTS_ON = true;
			e.getChannel().sendMessage(alertEmbed(e)).queue();
			TweetStream(e.getChannel());
		} else if (e.getMessage().getContentRaw().equals("!alerts off") && ALERTS_ON==true) {
			ALERTS_ON = false;
			e.getChannel().sendMessage(alertEmbed(e)).queue();
		}
	}
	
	private static MessageEmbed alertEmbed (MessageReceivedEvent e) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		if(ALERTS_ON == true) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setThumbnail(Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setDescription("Twitter and Youtube post notifications will be ON for the following channel: " + e.getChannel().getName());
		} else if (ALERTS_ON == false){
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setThumbnail(Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setDescription("Twitter and Youtube post notifications will be OFF for the following channel: " + e.getChannel().getName());
		}
		return embedBuilder.build();
	}
	
	private static void TweetStream(MessageChannel currChannel) {
		
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		twitterStream.setOAuthAccessToken(new AccessToken(Constants.ACCESS_KEY, Constants.ACCESS_SECRET));
		
		StatusListener listener = new StatusListener() {
			
			public void onStatus(Status status) {
                String url = "http://twitter.com" + status.getUser().getScreenName() + "/status/" + status.getId();
                currChannel.sendMessage(url).queue();;
            }
			
			public void onException(Exception e) {
				e.printStackTrace();
			}

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }


            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }


            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }
		};
		
		twitterStream.addListener(listener);
		FilterQuery query = new FilterQuery();
		query.follow(Constants.TWITTER_ID);
		twitterStream.filter(query);
	}
}
