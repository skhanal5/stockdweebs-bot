package me.skhanal.StockDweebs;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PicksListener extends ListenerAdapter{
	
	public static String DROPBOX_URL = "";
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		String guildId = e.getGuild().getId();
		String currChannel = e.getTextChannel().getName();
		String definedChannel = JoinEventHandler.database.getChannelName(guildId);
		
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
		LocalDate date = LocalDate.now(ZoneId.of("America/Montreal")).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String currWeek = date.format(formatter);
		String currURL = JoinEventHandler.database.getURL();
		
		EmbedBuilder embedBuilder = new EmbedBuilder();
		if (input.equals("!stockpicks")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setDescription("You can view the stockpicks for this week here: " + "\n" + "[StockDweebs - Weekly Lists - " + currWeek + "](" + currURL + ")" + "\n\n" + "Shared with Dropbox");
			embedBuilder.setThumbnail("https://images-ext-1.discordapp.net/external/TtMXwofJhLzXyMUKbDXite1frG79TKNbxlE63y0BrcI/https/www.dropbox.com/static/images/spectrum-icons/generated/content/content-pdf-large.png"); 
		} else if (input.equals("!watchlist")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setDescription("You can view the watchlist for this week here: " + "\n" + "[StockDweebs - Weekly Lists - " + currWeek + "](" + currURL + ")" + "\n\n" + "Shared with Dropbox");
			embedBuilder.setThumbnail("https://images-ext-1.discordapp.net/external/TtMXwofJhLzXyMUKbDXite1frG79TKNbxlE63y0BrcI/https/www.dropbox.com/static/images/spectrum-icons/generated/content/content-pdf-large.png");
		}
		return embedBuilder.build();
	}
}
