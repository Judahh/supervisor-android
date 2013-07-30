package android.G4FlexSupervisor;

import java.io.IOException;

import android.G4FlexSupervisor.socket.Connection;

public class IAX extends Peers{
	public IAX() throws IOException {
		super();
		String msg = "IAXpeerlist";
		sendAction(msg);
		receiveIAXpeers();
		Connection.disconnect();
	}
	
	public IAX(String user, String password) throws IOException {
		super(user,password);
		String msg = "IAXpeerlist";
		sendAction(msg);
		receiveIAXpeers();
		Connection.disconnect();
	}
	
	private static void receiveIAXpeers() throws IOException {
		String msg;
		String totalMsg = "";
		do {
			msg = Connection.getIn().readLine();
			totalMsg += msg;
			totalMsg += "\n";
		} while (!msg.equals("Event: PeerlistComplete"));
		SplitPeers(totalMsg.split("Event: PeerlistComplete")[0]);
	}
	

}
