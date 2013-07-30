package android.G4FlexSupervisor;

import java.io.IOException;

import android.G4FlexSupervisor.socket.Connection;

public class Queues extends AsteriskManager{
	public Queues() throws IOException, InterruptedException {
		super();
		String msg = "Queues";
		sendAction(msg);
		receiveQueues();
		Connection.disconnect();
	}
	
	public Queues(String user,String password) throws IOException, InterruptedException {
		super(user,password);
		String msg = "Queues";
		sendAction(msg);
		receiveQueues();
		Connection.disconnect();
	}
	
	private static void receiveQueues() throws IOException, InterruptedException {
		String msg = "";
		String totalMsg = "";
		while (!totalMsg.contains("\n\n\n")) {
			msg = Connection.getIn().readLine();
			System.out.println(msg);
			totalMsg += msg + "\n";
		}
		SplitQueues(totalMsg);
	}
	
	private static void SplitQueues(String totalMsg) throws IOException, InterruptedException {
		String[] queues = totalMsg.split("\n\n");
		String allNames="";
		for (int i = 0; i < queues.length; i++) {
			if (queues[i].length() > 5) {
				String info="";
				if (queues[i].charAt(0) == '\n') {
					queues[i] = queues[i].substring(1);
				}
				String tempName=queues[i].split(" has")[0];
				String aux = queues[i].split("max unlimited")[0];
				info=aux.substring(0, aux.length() - 1);
				info+="\nMembers:\n";
				if (queues[i].contains("Callers")) {
					
					String[] auxA = queues[i].split("Members:")[1].substring(2)
							.split("SIP/");
					for (int j = 1; j < auxA.length; j++) {
						getName("SIP/" + auxA[j].split("has")[0]);
						info+="  SIP/" + auxA[j].split("has")[0] + "\n";
					}

					auxA = queues[i].split("Members:")[1].substring(2).split(
							"IAX2/");
					for (int j = 1; j < auxA.length; j++) {
						getName("IAX2/" + auxA[j].split("has")[0]);
						info+="  IAX2/" + auxA[j].split("has")[0] + "\n";
					}
					
					auxA = queues[i].split("Members:")[1].substring(2).split(
                            "Agent/");
                    for (int j = 1; j < auxA.length; j++) {
                    	getName("Agent/" + auxA[j].split("has")[0]);
                        info += "  Agent/" + auxA[j].split("has")[0] + "\n";
                    }
                    
					nameList.add(tempName);
					allNames+=tempName+"\n";
					infoList.add(info);
				}
			}
		}
		infoList.set(0, allNames);
	}
	
	private static void getName(String totalMsg) throws IOException, InterruptedException {
		String ramal=totalMsg.split(" ")[0];
		Database db=new Database();
		db.DBGet(ramal);
	}
}
