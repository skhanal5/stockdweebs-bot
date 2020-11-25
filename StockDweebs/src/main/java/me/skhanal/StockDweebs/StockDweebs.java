package me.skhanal.StockDweebs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import javax.security.auth.login.LoginException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class StockDweebs {
	public static void main(String[]args) {
	
		JSONParser parser = new JSONParser();
		String token = "";
		JSONObject jsonObject = null;
		
		try {
			jsonObject = (JSONObject) parser.parse(new FileReader("C:\\Users\\subod\\Documents\\GitHub\\stockdweebs-bot\\StockDweebs\\src\\main\\java\\me\\skhanal\\StockDweebs\\token.json"));
			token = (String) jsonObject.get("token");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ParseException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		JDABuilder jdaBuilder = JDABuilder.createDefault(token);
		JDA jda = null;
		
		try {
			jda = jdaBuilder.build();
		} catch (LoginException e){
			e.printStackTrace();
		} 
		
		
		System.out.println("test");
	} 
	
}
