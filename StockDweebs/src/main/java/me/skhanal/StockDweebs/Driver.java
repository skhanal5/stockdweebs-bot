package me.skhanal.StockDweebs;

import javax.security.auth.login.LoginException;

/*
 * Main driver for the discord bot
 */

public class Driver {
	
	/*
	 * The method below will create an instance of the bot and turn the bot 
	 * online with the given token credentials along with the
	 * twitter stream with its respective credentials
	 */

	public static void main(String[] args) throws LoginException, InterruptedException {
		BotInitializer discordBot = new BotInitializer();
		discordBot.start();
	}

}
