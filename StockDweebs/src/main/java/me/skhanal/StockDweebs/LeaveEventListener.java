package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
 * This class listens to see if the bot left the channel in order to
 * remove its document from the database to save storage.
 */

public class LeaveEventListener extends ListenerAdapter{
	
	/*
	 * When the bot leaves, it's document is removed from the database
	 * completely.
	 */
	
	@Override
	public void onGuildLeave (GuildLeaveEvent e) {
		JoinEventListener.database.remove(e.getGuild().getId());
	}

}
