package me.skhanal.StockDweebs;

import java.awt.Color;
import me.skhanal.StockDweebs.ChannelListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AlertListener extends ListenerAdapter {
		
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		if ((e.getMessage().getContentRaw().toLowerCase().matches("!alerts on|!alerts off")) && (ChannelListener.CHANNEL_ID==null)) {
			e.getChannel().sendMessage("You have not setup a channel for this bot to send alerts and posts on. Please do so immediately using the !setchannel command. If you need additional assistance, refer to !setup for help.").queue();
		} else if ((e.getMessage().getContentRaw().toLowerCase().matches("!alerts on|!alerts off")) && (!(e.getTextChannel().getId().equals(ChannelListener.CHANNEL_ID)))) {
			e.getChannel().sendMessage("Channel mismatch. The bot is currently set to the #" + ChannelListener.CHANNEL_INPUT + " channel. Please use this command in that channel").queue();
		} else if (e.getMessage().getContentRaw().toLowerCase().equals("!alerts on") && e.getTextChannel().getId().equals(ChannelListener.CHANNEL_ID)) {
			e.getChannel().sendMessage(alertEmbed("!alerts on", e)).queue();
		} else if (e.getMessage().getContentRaw().toLowerCase().equals("!alerts off") && e.getTextChannel().getId().equals(ChannelListener.CHANNEL_ID)) {
			e.getChannel().sendMessage(alertEmbed("!alerts off", e)).queue();
		}
	}
	
	public MessageEmbed alertEmbed (String s, MessageReceivedEvent e) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		if(s.equals("!alerts on")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, "https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setColor(Color.MAGENTA);
			embedBuilder.setThumbnail("https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setDescription("Twitter and Youtube post notifications will be ON for the following channel: " + e.getChannel().getName());
		} else if (s.equals("!alerts off")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, "https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setColor(Color.MAGENTA);
			embedBuilder.setThumbnail("https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setDescription("Twitter and Youtube post notifications will be OFF for the following channel: " + e.getChannel().getName());
		}
		return embedBuilder.build();
	}

}
