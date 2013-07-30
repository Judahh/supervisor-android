package android.G4FlexSupervisor;

import java.io.IOException;

import android.G4FlexSupervisor.socket.Connection;

public class Channels extends AsteriskManager {
	public Channels() throws IOException, InterruptedException {
		super();
		String msg = "CoreShowChannels";
		sendAction(msg);
		receiveCoreShowChannels();
		Connection.disconnect();
	}

	public Channels(String user, String password) throws IOException,
			InterruptedException {
		super(user, password);
		String msg = "CoreShowChannels";
		sendAction(msg);
		receiveCoreShowChannels();
		Connection.disconnect();
	}

	private static void receiveCoreShowChannels() throws IOException {
		String msg = "";
		String totalMsg = "";
		while (!totalMsg.contains("Event: CoreShowChannelsComplete")) {
			msg = Connection.getIn().readLine();
			totalMsg += msg + "\n";
		}
		SplitShowChannels(totalMsg);
	}

	private static void SplitShowChannels(String totalMsg) {
		String[] msgParts = totalMsg.split("Event: CoreShowChannel\n");
		String allNames = "";
		for (int iterator = 0; iterator < msgParts.length; iterator++) {
			if (iterator > 0) {
				String aux = msgParts[iterator].split("\n\n")[0];
				insertInfo(aux, "Channel: ", "BridgedChannel: ",
						"Application: ", "Duration: ");
			}
		}

		for (int i = 1; i < infoList.size(); i++) {
			String tempName = "SRC:"
					+ infoList.get(i).split("Channel: ")[1].split("-")[0];
			nameList.add(tempName);
			tempName += "\tDST:"
					+ infoList.get(i).split("BridgedChannel: ")[1].split("-")[0];
			allNames += tempName + "\n";
		}
		infoList.set(0, allNames);
	}
}
