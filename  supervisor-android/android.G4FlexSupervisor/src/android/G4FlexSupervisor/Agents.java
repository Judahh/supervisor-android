package android.G4FlexSupervisor;

import java.io.IOException;

import android.G4FlexSupervisor.socket.Connection;

public class Agents extends AsteriskManager{
	public Agents() throws IOException {
		super();
		String msg = "Agents";
		sendAction(msg);
		receiveAgents();
		Connection.disconnect();
	}

	public Agents(String user,String password) throws IOException {
		super(user,password);
		String msg = "Agents";
		sendAction(msg);
		receiveAgents();
		Connection.disconnect();
	}
	
	private static void receiveAgents() throws IOException {
		String msg = "";
		String totalMsg = "";
		while (!msg.equals("Event: AgentsComplete")) {
			msg = Connection.getIn().readLine();
			System.out.println(msg);
			totalMsg += msg;
			totalMsg += "\n";
		}
		SplitAgents(totalMsg.split("Event: AgentsComplete")[0]);
	}
	
	private static void SplitAgents(String totalMsg) {
		String[] msgParts = totalMsg.split("Event: Agents\n");
		String allNames="";
		for (int iterator = 0; iterator < msgParts.length; iterator++) {
			if (iterator > 0) {
				String aux = msgParts[iterator];
//				insertAllInfo(aux);
				insertInfo(aux, "Agent:", "Name:");
			}
		}
		for (int i = 1; i < infoList.size(); i++) {
			String tempName=infoList.get(i).split("Name: ")[1].split("\n")[0];
			nameList.add(tempName);
			allNames+=tempName+"\n";
		}
		infoList.set(0, allNames);
	}

}
