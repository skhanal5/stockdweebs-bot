package me.skhanal.StockDweebs;

import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChannelListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		String CHANNEL_INPUT = "";
		String CHANNEL_ID = "";
		List<TextChannel> CHANNEL_LIST;
		String user = e.getAuthor().getName();
		String guildId = e.getGuild().getId();
		boolean checkPerms = e.getMember().hasPermission(Permission.ADMINISTRATOR);
		
		if (e.getMessage().getContentRaw().equals("!setchannel")) {
			e.getChannel().sendMessage("Invalid command or channel name. To use this command type !setchannel and the channel name following that statement with a space. If you need additional assistance, refer to !setup for help.").queue();
		} else if ((e.getMessage().getContentRaw().startsWith("!setchannel") && (!checkPerms))) {
			e.getChannel().sendMessage("User: " + user + " does not have valid permissions to use this command.").queue();
		} else if (e.getMessage().getContentRaw().startsWith("!setchannel") && (!(JoinEventListener.database.getChannelName(guildId).equals("null")))) { 
			CHANNEL_INPUT = e.getMessage().getContentRaw().substring(12,e.getMessage().getContentRaw().length());
			CHANNEL_LIST = e.getGuild().getTextChannels();
			for(TextChannel channel: CHANNEL_LIST) {
				if (channel.getName().equals(CHANNEL_INPUT)) {
					CHANNEL_ID = channel.getId();
					JoinEventListener.database.setChannel(guildId, CHANNEL_INPUT, CHANNEL_ID);
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
			
		} else if (e.getMessage().getContentRaw().startsWith("!setchannel")) {
			CHANNEL_INPUT = e.getMessage().getContentRaw().substring(12,e.getMessage().getContentRaw().length());
			CHANNEL_LIST = e.getGuild().getTextChannels();
			for(TextChannel channel: CHANNEL_LIST) {
				if (channel.getName().equals(CHANNEL_INPUT)) {
					CHANNEL_ID = channel.getId();
					JoinEventListener.database.setChannel(guildId, CHANNEL_INPUT, CHANNEL_ID);
				}
			}
			
			try {
				e.getGuild().getTextChannelById(CHANNEL_ID).sendMessage("This bot will now post alerts and messages in this channel.").queue();
				e.getChannel().sendMessage("All messages from this bot will now be posted in #" + CHANNEL_INPUT + ".").queue();
				AlertListener.startStream(e.getGuild(), guildId);
			} catch (NumberFormatException g) {
				e.getChannel().sendMessage("Invalid command or channel name. To use this command type !setchannel and the channel name following that statement with a space. If you need additional assistance, refer to !setup for help.").queue();
			} catch (IllegalArgumentException g) {
				e.getChannel().sendMessage("Invalid command or channel name. To use this command type !setchannel and the channel name following that statement with a space. If you need additional assistance, refer to !setup for help.").queue();
			} catch (NullPointerException g) {
				e.getChannel().sendMessage("Invalid command or channel name. To use this command type !setchannel and the channel name following that statement with a space. If you need additional assistance, refer to !setup for help.").queue();
			} 
		}
	}
}
