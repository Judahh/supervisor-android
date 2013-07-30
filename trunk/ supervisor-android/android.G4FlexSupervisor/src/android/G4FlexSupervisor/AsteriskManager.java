package android.G4FlexSupervisor;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.G4FlexSupervisor.socket.Connection;

public abstract class AsteriskManager {
	public static ArrayList<String> nameList;
	public static ArrayList<String> infoList;
	private static String user;
	private static String password;
	private static String name;
	public static int id;
	
	public AsteriskManager(String host, int port, String user, String password) throws IOException {
		new Connection(host, port);
		login(user, password);
		AsteriskManager.id=-1;
		AsteriskManager.name="";
	}
	
	public AsteriskManager(String user, String password) throws IOException {
		Connection.connect();
		login(user, password);
		AsteriskManager.id=-1;
		AsteriskManager.name="";
	}
	
	public AsteriskManager() throws IOException {
		Connection.connect();
		login();
		AsteriskManager.id=-1;
		AsteriskManager.name="";
	}
	
	private static void refreshArrayList() {
		nameList = new ArrayList<String>();
		infoList = new ArrayList<String>();
		nameList.add("All");
		infoList.add("");
	}
	
	public static void sendAction(String action)  throws IOException{
		refreshArrayList();
		String msg = "Action: "+action+"\r\n\r\n";
		byte[] msgBytes = msg.getBytes();
		Connection.getOut().write(msgBytes);
	}

	public static boolean login(String user, String password) throws IOException {
		boolean Event=true;
		AsteriskManager.user=user;
		AsteriskManager.password=password;
		String msg;
		msg = "Login\r\nUsername: " + AsteriskManager.user + "\r\nSecret: "
				+ AsteriskManager.password;
		if(!Event){
			msg = msg + "\r\nEvent: off";
		}
		sendAction(msg);
		msg = "";
		String totalMsg = "";
		while (!totalMsg.contains("Authentication accepted\n")) {
			msg = Connection.getIn().readLine();
//			System.out.println(msg);
			totalMsg += msg + "\n";
			if(totalMsg.contains("Authentication accepted\n")){
				return true;
			}else if(totalMsg.contains("Authentication failed")){
				return false;
			}
		}
		return true;
	}
	
	public static boolean login() throws IOException {
		boolean Event=true;
		String msg;
		msg = "Login\r\nUsername: " + AsteriskManager.user + "\r\nSecret: "
				+ AsteriskManager.password;
		if(!Event){
			msg = msg + "\r\nEvent: off";
		}
		sendAction(msg);
		msg = "";
		String totalMsg = "";
		while (!totalMsg.contains("Authentication accepted\n")) {
			msg = Connection.getIn().readLine();
//			System.out.println(msg);
			totalMsg += msg + "\n";
			if(totalMsg.contains("Authentication accepted\n")){
				return true;
			}else if(totalMsg.contains("Authentication failed")){
				return false;
			}
		}
		return true;
	}
	
	protected static void insertInfo(String msgPart, String... args) {
		String[] infoParts = msgPart.split("\n");
		String aux = "";
		for (int i = 0; i < infoParts.length; i++) {
			for (int j = 0; j < args.length; j++) {
				if ((infoParts[i].contains(args[j])) && (!aux.contains(args[j]))) {
					aux = aux + infoParts[i] + "\n";
				}
			}
		}
		infoList.add(aux);
	}

	protected static void insertAllInfo(String msgPart) {
		infoList.add(msgPart);
	}

	public static boolean connect(String host, int port, String user,
			String password) throws UnknownHostException, IOException {
		new Connection(host, port);
		return login(user, password);
	}
}
