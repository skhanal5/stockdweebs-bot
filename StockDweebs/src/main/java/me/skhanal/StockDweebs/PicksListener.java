package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PicksListener extends ListenerAdapter{
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		String guildId = e.getGuild().getId();
		String currChannel = e.getTextChannel().getName();
		String definedChannel = SetupListener.database.getChannel(guildId);
		
		if (e.getMessage().getContentRaw().matches("!stockpicks|!watchlist") && (definedChannel.equals("null"))) {
			e.getChannel().sendMessage("You have not setup a channel for this bot to send alerts and messages on. Please do so immediately using the !setchannel command. If you need additional assistance, refer to !setup for help.").queue();
		} else if (e.getMessage().getContentRaw().matches("!stockpicks|!watchlist") && (!(definedChannel.equals(currChannel)))) {
			e.getChannel().sendMessage("Channel mismatch. The bot is currently set to the #" + definedChannel + " channel. Please use this command in that channel").queue();
		} else if (e.getMessage().getContentRaw().matches("!stockpicks")) {
			e.getChannel().sendMessage(createEmbed("!stockpicks")).queue();
		} else if (e.getMessage().getContentRaw().matches("!watchlist")) {
			e.getChannel().sendMessage(createEmbed("!watchlist")).queue();
		}
	}
	
	private MessageEmbed createEmbed(String input) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		if (input.equals("!stockpicks")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setDescription("https://www.dropbox.com/s/wdplmw3zcd8pkhn/StockDweebs%20-%20Weekly%20Stock%20Picks%20-%2012.21.2020.pdf?dl=0");
		} else if (input.equals("!watchlist")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setDescription("https://www.dropbox.com/s/wdplmw3zcd8pkhn/StockDweebs%20-%20Weekly%20Stock%20Picks%20-%2012.21.2020.pdf?dl=0");
		}
		return embedBuilder.build();
	}
}
