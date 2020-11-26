package me.skhanal.StockDweebs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class StockDweebs {

	public static void main(String[] args) {

		String token = "";

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			jsonObject = (JSONObject) parser.parse(new FileReader(
					"C:\\Users\\subod\\Documents\\GitHub\\stockdweebs-bot\\StockDweebs\\src\\main\\java\\me\\skhanal\\StockDweebs\\token.json"));
			token = (String) jsonObject.get("token");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		JDABuilder jdaBuilder = JDABuilder.createDefault(token);
		JDA jda = null;
		MyListener listener = new MyListener();
		jdaBuilder.addEventListeners(listener);
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
