package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class LeaveEventListener extends ListenerAdapter{
	
	@Override
	public void onGuildLeave (GuildLeaveEvent e) {
		JoinEventListener.database.remove(e.getGuild().getId());
	}

}
