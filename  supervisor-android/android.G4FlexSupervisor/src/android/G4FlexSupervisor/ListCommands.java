package android.G4FlexSupervisor;

import java.io.IOException;

import android.G4FlexSupervisor.socket.Connection;

public class ListCommands extends AsteriskManager {
	public ListCommands()  throws IOException, InterruptedException {
		super();
		String msg = "ListCommands";
		sendAction(msg);
		receiveListCommands();
		Connection.disconnect();
	}
	
	public ListCommands(String user,String password)  throws IOException, InterruptedException {
		super(user,password);
		String msg = "ListCommands";
		sendAction(msg);
		receiveListCommands();
		Connection.disconnect();
	}
	
	
	private static void receiveListCommands() throws IOException {
		String msg = "";
		String totalMsg = "";
		int c=0;
		while (c<2) {
			msg = Connection.getIn().readLine();
			if(msg.length()>3){
				totalMsg += "-" + msg + "\n";
			}else{
				c++;
			}
		}
		infoList.set(0, totalMsg);
	}
}
