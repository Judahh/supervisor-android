package android.G4FlexSupervisor;

import java.io.IOException;

import android.G4FlexSupervisor.socket.Connection;

public class SIP extends Peers {
	public SIP() throws IOException {
		super();
		String msg = "SIPpeers";
		sendAction(msg);
		receiveSIPpeers();
		Connection.disconnect();
	}
	
	public SIP(String user, String password) throws IOException {
		super(user, password);
		String msg = "SIPpeers";
		sendAction(msg);
		receiveSIPpeers();
		Connection.disconnect();
	}
	
	private static void receiveSIPpeers() throws IOException {
		String msg = "a";
		String totalMsg = "";
		while (!msg.equals("EventList: Complete")) {
			msg = Connection.getIn().readLine();
			totalMsg += msg;
			totalMsg += "\n";
		}
		SplitPeers(totalMsg.split("Event: PeerlistComplete")[0]);
	}
}
