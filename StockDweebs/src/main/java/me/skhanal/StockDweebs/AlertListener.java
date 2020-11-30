package me.skhanal.StockDweebs;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.WebhookAction;

public class AlertListener extends ListenerAdapter {
	
	private boolean ALERTS_ON = false;
		
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		if (e.getMessage().getContentRaw().equals("!alerts")) {
			e.getChannel().sendMessage("Invalid command. To use this command type !alerts and either \"on\" or \"off\" (without quotes) following that statement with a space. If you need additional assistance, refer to !setup for help").queue();
		} else if (e.getMessage().getContentRaw().startsWith("!alerts") && ((!(e.getMessage().getContentRaw().endsWith(" on"))) && (!(e.getMessage().getContentRaw().endsWith(" off"))))){
			e.getChannel().sendMessage("Invalid command. To use this command type !alerts and either \"on\" or \"off\" (without quotes) following that statement with a space. If you need additional assistance, refer to !setup for help").queue();
		} else if ((e.getMessage().getContentRaw().matches("!alerts on|!alerts off")) && (ChannelListener.CHANNEL_ID==null)) {
			e.getChannel().sendMessage("You have not setup a channel for this bot to send alerts and posts on. Please do so immediately using the !setchannel command. If you need additional assistance, refer to !setup for help.").queue();
		} else if ((e.getMessage().getContentRaw().matches("!alerts on|!alerts off")) && (!(e.getTextChannel().getId().equals(ChannelListener.CHANNEL_ID)))) {
			e.getChannel().sendMessage("Channel mismatch. The bot is currently set to the #" + ChannelListener.CHANNEL_INPUT + " channel. Please use this command in that channel").queue();
		} else if (e.getMessage().getContentRaw().equals("!alerts on") && (e.getTextChannel().getId().equals(ChannelListener.CHANNEL_ID))) {
			ALERTS_ON = true;
			e.getChannel().sendMessage(alertEmbed(e)).queue();
			twitterWebhook(e).queue();
		} else if (e.getMessage().getContentRaw().equals("!alerts off") && (e.getTextChannel().getId().equals(ChannelListener.CHANNEL_ID))) {
			ALERTS_ON = false;
			e.getChannel().sendMessage(alertEmbed(e)).queue();
		}
	}
	
	public MessageEmbed alertEmbed (MessageReceivedEvent e) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		if(ALERTS_ON == true) {
			embedBuilder.setAuthor("StockDweebs Bot", null, "https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setColor(Color.MAGENTA);
			embedBuilder.setThumbnail("https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setDescription("Twitter and Youtube post notifications will be ON for the following channel: " + e.getChannel().getName());
		} else if (ALERTS_ON == false){
			embedBuilder.setAuthor("StockDweebs Bot", null, "https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setColor(Color.MAGENTA);
			embedBuilder.setThumbnail("https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
			embedBuilder.setDescription("Twitter and Youtube post notifications will be OFF for the following channel: " + e.getChannel().getName());
		}
		return embedBuilder.build();
	}

	
	public WebhookAction twitterWebhook(MessageReceivedEvent e) {
		WebhookAction webhookBuilder = null;
		File myFile = new File("C:\\Users\\subod\\Downloads\\icon.jpg");
		TextChannel webhookChannel = e.getMessage().getTextChannel();
		
		try {
			webhookBuilder = webhookChannel.createWebhook("StockDweebs Twitter").setName("StockDweebs Twitter").setAvatar(Icon.from(myFile));
		} catch (IOException f) {
			System.out.println("invalid file");
		}
		
		return webhookBuilder;
	}

}
