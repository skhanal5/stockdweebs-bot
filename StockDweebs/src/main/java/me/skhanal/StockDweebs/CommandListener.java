package me.skhanal.StockDweebs;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		String guildId = e.getGuild().getId();
		String currChannel = e.getTextChannel().getName();
		String definedChannel = JoinEventHandler.database.getChannel(guildId);
		
		if (e.getMessage().getContentRaw().matches("!invite|!youtube|!twitter|!premium") && (definedChannel.equals("null"))){
			e.getChannel().sendMessage("You have not setup a channel for this bot to send alerts and messages on. Please do so immediately using the !setchannel command. If you need additional assistance, refer to !setup for help.").queue();
		} else if (e.getMessage().getContentRaw().matches("!invite|!youtube|!twitter|!premium") && (!(definedChannel.equals(currChannel)))) {
			e.getChannel().sendMessage("Channel mismatch. The bot is currently set to the #" + definedChannel + " channel. Please use this command in that channel").queue();
		} else if (e.getMessage().getContentRaw().equals("!invite")) {
			e.getChannel().sendMessage(createEmbed("!invite")).queue();
		} else if (e.getMessage().getContentRaw().equals("!youtube")) {
			e.getChannel().sendMessage(createEmbed("!youtube")).queue();
		} else if (e.getMessage().getContentRaw().equals("!twitter")) {
			e.getChannel().sendMessage(createEmbed("!twitter")).queue();
		} else if(e.getMessage().getContentRaw().equals("!premium")) {
			e.getChannel().sendMessage(createEmbed("!premium")).queue();
		} else if(e.getMessage().getContentRaw().equals("!commands")) {
			e.getChannel().sendMessage(createEmbed("!commands")).queue();
		} else if (e.getMessage().getContentRaw().equals("!setup")) {
			e.getChannel().sendMessage(createEmbed("!setup")).queue();
		} else if (e.getMessage().getContentRaw().equals("!help")) {
			e.getChannel().sendMessage(createEmbed("!help")).queue();
		}
	}

	private MessageEmbed createEmbed(String input) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		
		if (input.equals("!youtube")) {
			embedBuilder.setAuthor("Youtube", null, Constants.YOUTUBE_LOGO);
			embedBuilder.setDescription("Every Sunday at 10:00 AM EST - weekly analysis on S&P 500 / Dow Jones Industrial Average / Nasdaq\r\n" + 
					"\r\n" + 
					"Every day at 9:00 AM EST - daily analysis on Bitcoin\r\n" + 
					"\r\n" + 
					"I am a professional trader with over 7 years of equities and cryptocurrency experience.\r\n" + 
					"\r\n" + 
					"I've worked at an energy trading company, a Fortune 500 company, and one of the top management consulting firms in the world.\r\n" + 
					"\r\n" + 
					"I left my high-paying salary as a management consultant in early 2018 for a career in investing and trading.\r\n" + 
					"\r\n" + 
					"I've been a full-time equities trader ever since.\r\n" + 
					"\r\n" + 
					"I give detailed analysis on TradingView.\r\n" + 
					"\r\n" + 
					"Over the past 7 years, I've dedicated my time and effort to helping others learn about the financial markets.\r\n" + 
					"\r\n" + 
					"All my ideas are for education purposes. There is a high-degree of risks involving leverage trading. Trade at your own risk. This is not financial advice");
			embedBuilder.setTitle("StockDweebs Channel", "https://www.youtube.com/channel/UCDgY58ASVilUUQ5TYOVTp1g/featured");
			embedBuilder.setColor(Color.RED);
			embedBuilder.setThumbnail(Constants.STOCKDWEEBS_LOGO);
		} else if (input.equals("!twitter")) {
			embedBuilder.setAuthor("Twitter", null , Constants.TWITTER_LOGO);
			embedBuilder.setDescription("Ten weekly stock picks posted here every Sunday @ 9AM EST (pinned tweet).");
			embedBuilder.setTitle("StockDweebs Twitter", "https://twitter.com/StockDweebs");
			embedBuilder.setColor(new Color (29,161,242));
			embedBuilder.setThumbnail(Constants.STOCKDWEEBS_LOGO);
		} else if (input.equals("!commands")){
			embedBuilder.setAuthor("StockDweebs Command List", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setThumbnail(Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.addField("Weekly Watchlist", "```\n !watchlist```", false);
			embedBuilder.addField("Weekly Stockpicks", "```\n !stockpicks```", false);
			embedBuilder.addField("Premium Membership", "```\n !premium```", false);
			embedBuilder.addField("Set Channel", "```\n !setchannel [channelname]```", false);
			embedBuilder.addField("Alerts", "```\n !alerts [on/off]```", false);
			embedBuilder.addField("Twitter", "```\n !twitter```", false);
			embedBuilder.addField("Youtube", "```\n !youtube```", false);
			embedBuilder.addField("Add this bot to other servers!", "```\n !invite```", false);
		} else if (input.equals("!invite")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setThumbnail(Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setTitle("Add this bot to your other servers!", Constants.INVITE_LINK);
		} else if (input.equals("!premium")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setDescription("Premium subscription service will be coming soon. Stay tuned for updates on my [social media](https://twitter.com/StockDweebs)!");
		} else if (input.equals("!setup")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setDescription("To improve your experience with our services please configure this bot with the following settings: ");
			embedBuilder.addField("!setchannel [channelname]", "```\n Set which channel this bot will post messages and alerts on. ```", false);
			embedBuilder.addField("Contact me", "If you have any problems or suggestions regarding this bot, please contact [me](https://github.com/skhanal5/stockdweebs-bot/issues) by making an issue.", false);
		} else if (input.equals("!help")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setDescription("To use this bot, first the server administrator must use the **!setchannel [channelname]** command to specify a channel that the bot will use to post alerts and messages in. \n"
					+ "\n From there, you will be able to use the other commands. Here is a description of each command: \n \n");
			embedBuilder.addField("!setup", "*Shows how to configure the bot in case you need it again.*", false);
			embedBuilder.addField("!alerts [on/off]", "*Turn on or off Twitter post alerts from the StockDweebs twitter page. This command is restricted to **administrators** only.*", false);
			embedBuilder.addField("!twitter", "*View the official StockDweebs Twitter page*", false);
			embedBuilder.addField("!youtube", "*View the official StockDweebs Youtube channel*", false);
			embedBuilder.addField("!premium", "*Posts information regarding premium subscription*", false);
			embedBuilder.addField("!watchlist", "*Shows this week's watchlist*", false);
			embedBuilder.addField("!stockpicks", "*Shows this week's stockpicks*", false);
			embedBuilder.addField("!invite", "*Posts invite link for this bot*", false);
			embedBuilder.addField("!commands", "*Posts full command list*", false);
			embedBuilder.addBlankField(true);
			embedBuilder.addField("Contact me", "If you have any problems or suggestions regarding this bot, please contact [me](https://github.com/skhanal5/stockdweebs-bot/issues) by making an issue.", false);
		}
		return embedBuilder.build();
	}

}
