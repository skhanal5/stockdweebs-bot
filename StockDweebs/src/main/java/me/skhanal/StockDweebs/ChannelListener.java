package me.skhanal.StockDweebs;

import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
 * This class handles the !setchannel command specifically where the bot
 * is restricted to one channel if the command is used as intended
 * 
 * Pre-requisite: user must have the administrator command
 */

public class ChannelListener extends ListenerAdapter {
	
	/*
	 * In this method, the bot will first listen for the !setchannel command
	 * and determine whether or not they have the proper admin status to use it and
	 * whether or not the !setchannel command is used as intended. If so, it looks
	 * for the requested channel to post messages and if it exists it will 
	 * store this channel in this guild's respective document in the database.
	 */
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		String CHANNEL_INPUT = "";
		String CHANNEL_ID = "";
		List<TextChannel> CHANNEL_LIST;
		String user = e.getAuthor().getName();
		String guildId = e.getGuild().getId();
		boolean checkPerms = e.getMember().hasPermission(Permission.ADMINISTRATOR);
		
		/*
		 * First few conditional statements check if the command was used as intended and if this user has admin permissions
		 */
		
		if (e.getMessage().getContentRaw().equals("!setchannel")) {
			e.getChannel().sendMessage("Invalid command or channel name. To use this command type !setchannel and the channel name following that statement with a space. If you need additional assistance, refer to !setup for help.").queue();
		} else if ((e.getMessage().getContentRaw().startsWith("!setchannel") && (!checkPerms))) {
			e.getChannel().sendMessage("User: " + user + " does not have valid permissions to use this command.").queue();
		
			/*
		 * This conditional below will check if the user wants to overwrite the previous channel they specified
		 */
		
		} else if (e.getMessage().getContentRaw().startsWith("!setchannel") && (!(JoinEventListener.database.getChannelName(guildId).equals("null")))) { 
			CHANNEL_INPUT = e.getMessage().getContentRaw().substring(12,e.getMessage().getContentRaw().length());
			CHANNEL_LIST = e.getGuild().getTextChannels();
			for(TextChannel channel: CHANNEL_LIST) {
				if (channel.getName().equals(CHANNEL_INPUT)) {
					CHANNEL_ID = channel.getId();
					JoinEventListener.database.setChannel(guildId, CHANNEL_INPUT, CHANNEL_ID);
				}
			}
			
			/*
			 * If the specified channel exists then the bot will respond telling the user that it will post messages in that channel otherwise
			 * an exception will be thrown and the user will be informed with a warning message.
			 */
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
		
		/*
		 * This conditional below is designed for the user's first time setting the channel and will set the channel as intended
		 */
			
		} else if (e.getMessage().getContentRaw().startsWith("!setchannel")) {
			CHANNEL_INPUT = e.getMessage().getContentRaw().substring(12,e.getMessage().getContentRaw().length());
			CHANNEL_LIST = e.getGuild().getTextChannels();
			for(TextChannel channel: CHANNEL_LIST) {
				if (channel.getName().equals(CHANNEL_INPUT)) {
					CHANNEL_ID = channel.getId();
					JoinEventListener.database.setChannel(guildId, CHANNEL_INPUT, CHANNEL_ID);
				}
			}
			
			/*
			 * If the specified channel exists then the bot will respond telling the user that it will post messages in that channel otherwise
			 * an exception will be thrown and the user will be informed with a warning message.
			 */
			
			try {
				e.getGuild().getTextChannelById(CHANNEL_ID).sendMessage("This bot will now post alerts and messages in this channel.").queue();
				e.getChannel().sendMessage("All messages from this bot will now be posted in #" + CHANNEL_INPUT + ".").queue();
				AlertListener.startStream(e.getGuild(), guildId); //starts the twitter stream in the background but does not post tweets - will be used later
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
