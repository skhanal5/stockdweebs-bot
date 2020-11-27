package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotConfiguration extends ListenerAdapter {

	@Override
	public void onGuildJoin(GuildJoinEvent e) {
		Guild currGuild = e.getGuild();
		currGuild.getDefaultChannel().sendMessage("hello").queue();
		
	}
}
