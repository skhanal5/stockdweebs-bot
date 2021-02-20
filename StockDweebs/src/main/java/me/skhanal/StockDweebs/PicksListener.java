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

/*
 * This class will listen to the !stockpicks and respond with this week's 
 * stockpicks  if the command was used as intended.
 */

public class PicksListener extends ListenerAdapter{
	
	/*
	 * This method will listen for the command and if the command was used
	 * as intended then an embedded message will be sent otherwise an error
	 * message will be sent.
	 */
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		String guildId = e.getGuild().getId();
		String currChannel = e.getTextChannel().getName();
		String definedChannel = JoinEventListener.database.getChannelName(guildId);
		
		if (e.getMessage().getContentRaw().equals("!stockpicks") && (definedChannel.equals("null"))) {
			e.getChannel().sendMessage("You have not setup a channel for this bot to send alerts and messages on. Please do so immediately using the !setchannel command. If you need additional assistance, refer to !setup for help.").queue();
		} else if (e.getMessage().getContentRaw().equals("!stockpicks") && (!(definedChannel.equals(currChannel)))) {
			e.getChannel().sendMessage("Channel mismatch. The bot is currently set to the #" + definedChannel + " channel. Please use this command in that channel").queue();
		} else if (e.getMessage().getContentRaw().equals("!stockpicks")) {
			e.getChannel().sendMessage(createEmbed("!stockpicks")).queue();
		}
	}
	
	/*
	 * This method below will construct the embedded message containing the
	 * link to website containing the stockpicks.
	 */
	
	private MessageEmbed createEmbed(String input) {
		
		/*
		 * The lines below checks for the current date and will set it to the
		 * current Sunday to inform the user that the stockpicks list
		 * is intended for that entire week.
		 */
		
		LocalDate date = LocalDate.now(ZoneId.of("America/Montreal")).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String currWeek = date.format(formatter);
		String currURL = JoinEventListener.database.getURL();
		
		EmbedBuilder embedBuilder = new EmbedBuilder();
		if (input.equals("!stockpicks")) {
			embedBuilder.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
			embedBuilder.setColor(Constants.BRAND_COLOR);
			embedBuilder.setDescription("You can view the stockpicks for this week here: " + "\n" + "[StockDweebs - Weekly Stock Picks - " + currWeek + "](" + currURL + ")" + "\n\n" + "Powered by StockDweebs");
			embedBuilder.setThumbnail(Constants.STOCKDWEEBS_LOGO); 
		}
		return embedBuilder.build();
	}
}
