package me.skhanal.StockDweebs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Token {

	private static String token = "";
	public Token() {
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
	}
	
	public String getToken() {
		return token;
	}
	
}
