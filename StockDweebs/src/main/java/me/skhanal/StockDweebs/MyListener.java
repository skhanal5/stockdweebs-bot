package me.skhanal.StockDweebs;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MyListener extends ListenerAdapter{
	
	private String url = "https://discord.com/oauth2/authorize?client_id=%s&scope=bot";
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if (e.getMessage().getContentRaw().equals("!watchlist")) {
			e.getChannel().sendMessage("This week's watchlist: ").queue();
		}
		
		if (e.getMessage().getContentRaw().equals("!stockpicks")) {
			e.getChannel().sendMessage("This week's stockpicks: ").queue();
		}
		
		if(e.getMessage().getContentRaw().equals("!invite")) {
			e.getChannel().sendMessage(String.format(url, e.getJDA().getSelfUser().getId())).queue();;
		}
		
		if(e.getMessage().getContentRaw().equals("!invite")) {
			e.getChannel().sendMessage(String.format(url, e.getJDA().getSelfUser().getId())).queue();;
		}
		
		if(e.getMessage().getContentRaw().equals("!youtube")) {
			e.getChannel().sendMessage("Official StockDweeb's YouTube Channel: ").queue();
		}
		
		if(e.getMessage().getContentRaw().equals("!twitter")) {
			e.getChannel().sendMessage("Official StockDweeb's Twitter Profile: ").queue();
		}
	
	}

}
