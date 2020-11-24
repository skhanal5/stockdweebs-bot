package me.skhanal.StockDweebs;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;

public class StockDweebs {
	
	public static void main(String[]args) {
		JDABuilder jdabuilder = JDABuilder.createDefault("NzgwNjkxMTAwOTY0MzU2MTQ2.X7yxPw.9VvTAS43w6hQkvM8ZLmngDcHn4U");
		
		try {
			jdabuilder.build();
		} catch (LoginException e){
			e.printStackTrace();
		}
	}
	
}
