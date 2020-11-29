package me.skhanal.StockDweebs;

import java.util.ArrayList;
import java.util.List;
import me.skhanal.StockDweebs.Constants;
import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Driver{

	public static void main(String[] args) {
		List<GatewayIntent> gatewayIntents = new ArrayList<>();
		gatewayIntents.add(GatewayIntent.GUILD_MEMBERS);
		
		JDABuilder jdaBuilder = JDABuilder.createDefault(Constants.BOT_TOKEN);
		jdaBuilder.enableIntents(gatewayIntents);
		JDA jda = null;
		
		CommandListener listener = new CommandListener();
		jdaBuilder.addEventListeners(listener);
		
		BotConfiguration config = new BotConfiguration();
		jdaBuilder.addEventListeners(config);
		
		AlertListener alert = new AlertListener();
		jdaBuilder.addEventListeners(alert);
		
		ChannelListener channel = new ChannelListener();
		jdaBuilder.addEventListeners(channel);
		
		jdaBuilder.setActivity(Activity.watching("StockDweeb's Posts"));

		try {
			jda = jdaBuilder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
