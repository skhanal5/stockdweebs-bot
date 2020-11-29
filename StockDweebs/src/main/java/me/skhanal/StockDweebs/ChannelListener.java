package me.skhanal.StockDweebs;

import java.util.List;

import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChannelListener extends ListenerAdapter {
	
	List<GuildChannel> CHANNEL_LIST;
	public static String CHANNEL_INPUT;
	public static String CHANNEL_ID;
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		if(e.getMessage().getContentRaw().startsWith("!setchannel")) {
			CHANNEL_INPUT = e.getMessage().getContentRaw().substring(12,e.getMessage().getContentRaw().length());
			CHANNEL_LIST = e.getGuild().getChannels();
			for(GuildChannel channel: CHANNEL_LIST) {
				if (channel.getName().equals(CHANNEL_INPUT)) {
					CHANNEL_ID = channel.getId();
				}
			}
			
			try {e.getGuild().getTextChannelById(CHANNEL_ID).sendMessage("Yo").queue();
				e.getChannel().sendMessage("Success").queue();
			} catch (NumberFormatException g) {
				e.getChannel().sendMessage("Invalid or Nonexistent Channel Name").queue();
			} catch (IllegalArgumentException g) {
				e.getChannel().sendMessage("Invalid or Nonexistent Channel Name").queue();
			} catch (NullPointerException g) {
				e.getChannel().sendMessage("Invalid or Nonexistent Channel Name").queue();
			} 
		}
		
	}
}
