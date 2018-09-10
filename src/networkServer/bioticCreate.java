package networkServer;

import gameMechanics.Biotic;
import gameMechanics.JsonImport;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.Math.toIntExact;

public class bioticCreate {

    static JSONObject blackJson = JsonImport.getJsonObjectFromRaw("{\r\n" +
            "\"program\":\r\n" +
            "[\r\n" +
            "{\"IF\":[\"different_color\"],\r\n" +
            "\"THAN\":[\"hack\"]\r\n" +
            "},\r\n" +
            "{\"IF\":[\"always\"],\r\n" +
            "\"THAN\":[\"replicate\"]\r\n" +
            "},\r\n" +
            "{\"IF\":[\"always\"],\r\n" +
            "\"THAN\":[\"network\"]\r\n" +
            "},\r\n" +
            "{\"IF\":[\"same_color\"],\r\n" +
            "\"THAN\":[\"follow\"]\r\n" +
            "},\r\n" +
            "{\"IF\":[\"always\"],\r\n" +
            "\"THAN\":[\"run_away\"]\r\n" +
            "},\r\n" +
            "\r\n" +
            "]\r\n" +
            "\r\n" +
            ",\"location\":[102,102]\r\n" +
            "}");

    static Color[] color = {Color.BLACK, Color.RED, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GREEN, Color.ORANGE,Color.PINK, Color.YELLOW};

    static ArrayList<String> hosts = new ArrayList<String>();

    static int index;

    static ArrayList<Biotic> bioticQueue = new ArrayList<Biotic>();

    static public ArrayList<Biotic> getBiotics(){
        ArrayList<Biotic> bioticQueueCopy = new ArrayList<Biotic>(bioticQueue);
        bioticQueue.clear();
        return bioticQueueCopy;
    }
    static public void addBiotic(String ip, JSONObject data){

        if (hosts.contains(ip)){
            index = hosts.indexOf(ip);
        }
        else{
            hosts.add(ip);
            index = hosts.indexOf(ip);
        }

        int x = toIntExact( (Long)( (ArrayList)data.get("location")).get(0));
        int y = toIntExact( (Long) ( (ArrayList)data.get("location")).get(1));
        String result = UUID.nameUUIDFromBytes(ip.getBytes()).toString();
        System.out.println("UUID: " + result + "\nX: " + x + "\nY: " + y + "\nColor: " + color[index]);
        new Biotic(result, data, x, y, color[index]);

    }
}
