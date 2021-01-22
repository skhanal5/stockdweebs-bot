package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
 * This bot will listen to see if the bot has joined the discord server
 * to initialize a document in the database with the default values.
 */

public class JoinEventListener extends ListenerAdapter {
	
	public static MongoDB database = new MongoDB(); //creates instance of the MongoDB database
	
	/*
	 * When the bot joins, the !setchannel setting is set to null and the !alerts
	 * setting is set to off by default in the database.
	 */
	
	@Override
	public void onGuildJoin(GuildJoinEvent e) {
		
		String guildId = e.getGuild().getId();
		database.add(e.getGuild().getName(), guildId);
		database.setChannel(guildId, "null", "null");
		database.setAlerts(guildId, "off");
		e.getGuild().getDefaultChannel().sendMessage(setupEmbed(e)).queue(); //sends embed to the default channel - determined by discord
	}
	
	
	/*
	 * When the bot joins, the user will be prompted with the embedded message
	 * below that will tell the user how to setup this bot with !setchannel
	 * and !alerts.
	 */

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

