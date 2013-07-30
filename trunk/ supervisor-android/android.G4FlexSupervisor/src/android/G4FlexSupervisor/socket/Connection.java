package android.G4FlexSupervisor.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.G4FlexSupervisor.AsteriskManager;

public class Connection {
	private static Socket socket;
	private static DataOutputStream out;
	private static BufferedReader in;
	private static String host;
	private static int port;
	
	public Connection(String host, int port) throws UnknownHostException, IOException {
		Connection.host=host;
		Connection.port=port;
		socket = new Socket(host, port);
		out = new DataOutputStream(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} 
	
	public static DataOutputStream getOut() {
		return out;
	}

	public static void setOut(DataOutputStream out) {
		Connection.out = out;
	}

	public static BufferedReader getIn() {
		return in;
	}

	public static void setIn(BufferedReader in) {
		Connection.in = in;
	}

	public static void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static boolean connect()throws IOException{
		socket = new Socket(Connection.host,Connection.port);
		out = new DataOutputStream(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		return AsteriskManager.login();
	}
}
