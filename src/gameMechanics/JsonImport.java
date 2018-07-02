package gameMechanics;

//Importing File
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//JSON Parser
import org.json.simple.*;
import org.json.simple.parser.*;


public class JsonImport {
	
	private static String getRawFileData(String FilePath){
		
		String raw_data = "";
		
		File file = new File(FilePath);
		try {
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) {
					raw_data = raw_data + scanner.nextLine().replace("\r\n", "");
				}
			scanner.close();
			return raw_data;
		}
		catch (FileNotFoundException e) {
		 return null;
		}
	}
	
	public static JSONObject getJsonObject(String filePath) {
		
		String raw_data = getRawFileData(filePath);
	
		JSONParser JParser = new JSONParser();
		
		JSONObject JObj = new JSONObject();
		
		try {
			JObj = (JSONObject) JParser.parse(raw_data);
		} catch (ParseException e) {
			System.out.println("JSON Parse Failed");
			return null;
		}
		
		return JObj;		
	}
	public static JSONObject getJsonObjectFromRaw(String raw_data) {
		
	
		JSONParser JParser = new JSONParser();
		
		JSONObject JObj = new JSONObject();
		
		try {
			JObj = (JSONObject) JParser.parse(raw_data);
		} catch (ParseException e) {
			System.out.println("JSON Parse Failed");
			return null;
		}
		
		return JObj;		
	}
}
