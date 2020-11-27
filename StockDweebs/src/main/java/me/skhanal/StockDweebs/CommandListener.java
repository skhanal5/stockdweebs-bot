package me.skhanal.StockDweebs;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

	private String url = "https://discord.com/oauth2/authorize?client_id=%s&scope=bot";

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if (e.getMessage().getContentRaw().equals("!watchlist")) {
			e.getChannel().sendMessage("This week's watchlist: ").queue();
		}

		if (e.getMessage().getContentRaw().equals("!stockpicks")) {
			e.getChannel().sendMessage("This week's stockpicks: ").queue();
		}

		if (e.getMessage().getContentRaw().equals("!invite")) {
			e.getChannel().sendMessage(String.format(url, e.getJDA().getSelfUser().getId())).queue();
			;
		}

		if (e.getMessage().getContentRaw().equals("!youtube")) {
			String input = e.getMessage().getContentRaw();
			e.getChannel().sendMessage(createEmbed(input)).queue();
		}

		if (e.getMessage().getContentRaw().equals("!twitter")) {
			String input = e.getMessage().getContentRaw();
			e.getChannel().sendMessage(createEmbed(input)).queue();
		}
		
		if(e.getMessage().getContentRaw().equals("!premium")) {
			e.getChannel().sendMessage("Premium subscription service will be coming soon. Stay tuned for updates on my social media!").queue();
		}
		
		if(e.getMessage().getContentRaw().equals("!help")) {
			String input = e.getMessage().getContentRaw();
			e.getChannel().sendMessage(createEmbed(input)).queue();
		}
		
		}

	public MessageEmbed createEmbed(String s) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		
		if (s.equals("!youtube")) {
			embedBuilder.setAuthor("Youtube", null, "https://www.sfcg.org/wp-content/uploads/2016/11/youtube-flat.png");
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
			embedBuilder.setThumbnail("https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
		} else if (s.equals("!twitter")) {
			embedBuilder.setAuthor("Twitter", null , "https://www.sfcg.org/wp-content/uploads/2016/11/twitter-flat.png");
			embedBuilder.setDescription("Ten weekly stock picks posted here every Sunday @ 9AM EST (pinned tweet).");
			embedBuilder.setTitle("StockDweebs Twitter", "https://twitter.com/StockDweebs");
			embedBuilder.setColor(Color.CYAN);
			embedBuilder.setThumbnail("https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
		} else {
			embedBuilder.setAuthor("StockDweebs Command List", null, "https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setThumbnail("https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setColor(Color.MAGENTA);
			embedBuilder.addField("Weekly Watchlist", "```\n !watchlist```", true);
			embedBuilder.addField("Weekly Stockpick", "```\n !stockpicks```", true);
			embedBuilder.addField("Premium Membership", "```\n !premium```", true);
			embedBuilder.addField("Alerts", "```\n !alerts```", true);
			embedBuilder.addField("Twitter", "```\n !twitter```", true);
			embedBuilder.addField("Youtube", "```\n !youtube```", true);
		}
		return embedBuilder.build();
	}

}
