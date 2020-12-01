package me.skhanal.StockDweebs;

import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotSetupListener extends ListenerAdapter {
	
	public static Guild currGuild;
	
	@Override
	public void onGuildJoin(GuildJoinEvent e) {
		currGuild = e.getGuild();
		currGuild.getDefaultChannel().sendMessage(setupEmbed(e)).queue();
	}

	public MessageEmbed setupEmbed(GuildJoinEvent e) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor("StockDweebs Bot", null, "https://images-ext-1.discordapp.net/external/PKfK4q2WAmoeELjQAuZCAdR8hIVfkbpyIpAc1fYLQY8/https/yt3.ggpht.com/ytc/AAUvwnjZewrii9lxuZap3nfEGk69IiqDHGcOA7UgpVl_hg%3Ds900-c-k-c0x00ffffff-no-rj?width=677&height=677");
		embed.setColor(Color.MAGENTA);
		embed.setDescription("Thank you for adding the StockDweebs Bot to " + e.getGuild().getName() + ". \n To improve your experience with our services please configure this bot with the following settings. \n \n If you have any questions or suggestions in regards to improving this bot, please contact [us](https://github.com/skhanal5/stockdweebs-bot/issues).");
		embed.addField("!setchannel [channelname]", "```\n Set which channel this bot will post messages and alerts on. ```", false);
		embed.addField("!alerts [on/off]", "```\n Turn on post notifications from the StockDweebs twitter and  youtube page. ```", false);
		return embed.build();
	}
}

