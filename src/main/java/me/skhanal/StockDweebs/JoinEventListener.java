package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinEventListener extends ListenerAdapter {
	public static MongoDB database = new MongoDB();
	
	@Override
	public void onGuildJoin(GuildJoinEvent e) {
		
		String guildId = e.getGuild().getId();
		database.add(e.getGuild().getName(), guildId);
		database.setChannel(guildId, "null", "null");
		database.setAlerts(guildId, "off");
		e.getGuild().getDefaultChannel().sendMessage(setupEmbed(e)).queue();
	}
	

	private MessageEmbed setupEmbed(GuildJoinEvent e) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor("StockDweebs Bot", null, Constants.STOCKDWEEBS_LOGO);
		embed.setColor(Constants.BRAND_COLOR);
		embed.setDescription("Thank you for adding the StockDweebs Bot to " + e.getGuild().getName() + ". \n To improve your experience with our services please configure this bot with the following settings:");
		embed.addField("!setchannel [channelname]", "```\n Set which channel this bot will post messages and alerts on. ```", false);
		embed.addField("Contact me", "If you have any problems or suggestions regarding this bot, please contact [me](https://github.com/skhanal5/stockdweebs-bot/issues) here by making an issue.", false);
		return embed.build();
	}
}

