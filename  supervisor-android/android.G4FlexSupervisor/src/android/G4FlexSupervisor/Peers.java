package android.G4FlexSupervisor;

import java.io.IOException;

public abstract class Peers extends AsteriskManager {
	Peers() throws IOException {
		super();
	}

	Peers(String user, String password) throws IOException {
		super(user, password);
	}

	protected static void SplitPeers(String totalMsg) {
		String[] msgParts = totalMsg.split("Event: PeerEntry\n");
		String allNames = "";
		for (int iterator = 0; iterator < msgParts.length; iterator++) {
			if (iterator > 0) {
				String aux = msgParts[iterator].split("\n\n")[0];
				insertInfo(aux, "Channeltype:", "ObjectName:", "IPaddress:",
						"Status:");
				// insertAllInfo(aux);
			}
		}

		for (int i = 1; i < infoList.size(); i++) {
			String tempName = infoList.get(i).split("ObjectName: ")[1]
					.split("\n")[0];
			nameList.add(tempName);
			allNames += tempName + "\n";
		}
		infoList.set(0, allNames);
	}
}
