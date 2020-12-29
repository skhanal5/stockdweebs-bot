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

public class AlertListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) { 	
		String guildId = e.getGuild().getId();
		String currChannel = e.getTextChannel().getName();
		String definedChannel = JoinEventHandler.database.getChannel(guildId);
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
		} else if (e.getMessage().getContentRaw().equals("!alerts on") && (definedChannel.equals(currChannel) && (JoinEventHandler.database.getAlerts(guildId).equals("on")))) {
			e.getChannel().sendMessage("Alerts are already on for the channel: " + definedChannel + ".").queue();
		} else if (e.getMessage().getContentRaw().equals("!alerts on")) {
			JoinEventHandler.database.setAlerts(guildId, "on");
			e.getChannel().sendMessage(alertEmbed(e, true)).queue();
		} else if (e.getMessage().getContentRaw().equals("!alerts off") && (JoinEventHandler.database.getAlerts(guildId).equals("on"))) {
			JoinEventHandler.database.setAlerts(guildId, "off");
			e.getChannel().sendMessage(alertEmbed(e, false)).queue();
		}
	}
	
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
	
	public static void startStream(MessageChannel currChannel, String guildId) {
		
		StatusListener listener = new StatusListener() {
			
			@Override
			public void onStatus(Status status) {
				if (status.getText().contains("View the report")) {
					int startingIndex = status.getText().indexOf("https://");
					int endingIndex = startingIndex+23;
					JoinEventHandler.database.setURL(status.getText().substring(startingIndex, endingIndex));
					
				}
                String url = "http://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();
                if (JoinEventHandler.database.getAlerts(guildId).equals("on")) {
                	 currChannel.sendMessage(url).queue();
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
		
		BotInitializer.twitterStream.addListener(listener);
		FilterQuery query = new FilterQuery();
		query.follow(Constants.TWITTER_ID);
		BotInitializer.twitterStream.filter(query);
	}
}
