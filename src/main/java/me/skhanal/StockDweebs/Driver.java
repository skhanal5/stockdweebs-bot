package me.skhanal.StockDweebs;

import javax.security.auth.login.LoginException;

public class Driver{

	public static void main (String[] args) throws LoginException, InterruptedException {
		BotInitializer discordBot = new BotInitializer();
		discordBot.start();
	}

}
