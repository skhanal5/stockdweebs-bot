package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class LeaveEventHandler extends ListenerAdapter{
	
	@Override
	public void onGuildLeave (GuildLeaveEvent e) {
		JoinEventHandler.database.remove(e.getGuild().getId());
	}

}
