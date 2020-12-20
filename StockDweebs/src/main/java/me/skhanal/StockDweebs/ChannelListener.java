package me.skhanal.StockDweebs;

import java.util.List;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChannelListener extends ListenerAdapter {
	
	List<GuildChannel> CHANNEL_LIST;
	public static String CHANNEL_INPUT;
	public static String CHANNEL_ID;
	public static GuildChannel CURR_CHANNEL;
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		String user = e.getAuthor().getName();
		boolean checkPerms = e.getMember().hasPermission(Permission.ADMINISTRATOR);
		
		if (e.getMessage().getContentRaw().equals("!setchannel")) {
			e.getChannel().sendMessage("Invalid command or channel name. To use this command type !setchannel and the channel name following that statement with a space. If you need additional assistance, refer to !setup for help.").queue();
		} else if(e.getMessage().getContentRaw().startsWith("!setchannel") && checkPerms) {
			CHANNEL_INPUT = e.getMessage().getContentRaw().substring(12,e.getMessage().getContentRaw().length());
			CHANNEL_LIST = e.getGuild().getChannels();
			for(GuildChannel channel: CHANNEL_LIST) {
				if (channel.getName().equals(CHANNEL_INPUT)) {
					CURR_CHANNEL = channel;
					CHANNEL_ID = channel.getId();
					SetupListener.database.setChannel(e.getGuild().getId(), CHANNEL_INPUT);
				}
			}
			
			try {
				e.getGuild().getTextChannelById(CHANNEL_ID).sendMessage("This bot will now post alerts and messages in this channel.").queue();
				e.getChannel().sendMessage("All messages from this bot will now be posted in #" + CHANNEL_INPUT + ".").queue();
			} catch (NumberFormatException g) {
				e.getChannel().sendMessage("Invalid command or channel name. To use this command type !setchannel and the channel name following that statement with a space. If you need additional assistance, refer to !setup for help.").queue();
			} catch (IllegalArgumentException g) {
				e.getChannel().sendMessage("Invalid command or channel name. To use this command type !setchannel and the channel name following that statement with a space. If you need additional assistance, refer to !setup for help.").queue();
			} catch (NullPointerException g) {
				e.getChannel().sendMessage("Invalid command or channel name. To use this command type !setchannel and the channel name following that statement with a space. If you need additional assistance, refer to !setup for help.").queue();
			} 
		
		} else if ((e.getMessage().getContentRaw().startsWith("!setchannel") && !checkPerms)) {
			e.getChannel().sendMessage("User: " + user + " does not have valid permissions to use this command.").queue();
		}
	}
}
