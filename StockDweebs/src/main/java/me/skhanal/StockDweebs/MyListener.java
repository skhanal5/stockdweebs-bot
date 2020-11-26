package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MyListener extends ListenerAdapter{
	
	public void onMessageReceived(MessageReceivedEvent e) {
		if (e.getMessage().getContentRaw().equals("!watchlist")) {
			e.getChannel().sendMessage("This week's watchlist: ").queue();
		}
	}

}
