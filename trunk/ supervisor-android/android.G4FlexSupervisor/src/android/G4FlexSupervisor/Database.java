package android.G4FlexSupervisor;

import java.io.IOException;

import android.G4FlexSupervisor.socket.Connection;

public class Database extends AsteriskManager {
	
	public Database() throws IOException, InterruptedException {
//		Queues queues = new Queues();
		nameList.set(0, "<select>");
		for (int i = 0; i < infoList.size(); i++) {
			infoList.set(i, "");
		}
//		DBGet();
	}
	
	public static void sendFamily(String action, String family)
			throws IOException {
		String msg = action + "\r\nFamily: " + family;
		sendAction(msg);
	}

	public static void sendFamily(String action, String family, String key)
			throws IOException {
		String msg = action + "\r\nFamily: " + family + "\r\nKey: " + key;
		sendAction(msg);
	}

	public static void sendFamily(String action, String family, String key,
			String subKey) throws IOException {
		String msg = action + "\r\nFamily: " + family + "\r\nKey: " + key + "/"
				+ subKey;
		sendAction(msg);
	}

	public static void sendFamilyWithValue(String action, String family,
			String key, String val) throws IOException {
		String msg = action + "\r\nFamily: " + family + "\r\nKey: " + key + ":"
				+ val;
		sendAction(msg);
	}

	public static void DBGet(String family, String key, String queue)
			throws IOException, InterruptedException {
		Connection.connect();
		login();
		sendFamily("DBGet", family);
		receiveDBGet();
		Connection.disconnect();
	}

	public static void DBGet(String ramal) throws IOException,
			InterruptedException {
		Connection.connect();
		login();
		sendFamily("DBGet", "FILA", ramal);
		receiveDBGet();
		Connection.disconnect();
	}

	private static String receiveDBGet() throws IOException {
		String msg = "";
		String totalMsg = "";
		while ((!totalMsg.contains("\n\n")) || (!totalMsg.contains("Val:"))) {
			msg = Connection.getIn().readLine();
			System.out.println(msg);
			totalMsg += msg + "\n";
		}
		return SplitDBGet(totalMsg);
	}

	private static String SplitDBGet(String totalMsg) {
		String msg = totalMsg.substring(totalMsg.indexOf("Val:") + 5);
		String msgParts[] = msg.split(";");
		msg = "";
		for (int i = 0; i < msgParts.length; i++) {
			msg += msgParts[i] + "\n";
		}

		if (infoList.size() == 0) {
			infoList.add(msg);
		} else {
			infoList.set(0, msg);
		}
		return null;
	}

}
