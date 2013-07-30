package android.G4FlexSupervisor.activity;

import java.io.IOException;

import android.os.Bundle;
import android.G4FlexSupervisor.Agents;
import android.G4FlexSupervisor.AsteriskManager;
import android.G4FlexSupervisor.Channels;
import android.G4FlexSupervisor.Database;
import android.G4FlexSupervisor.IAX;
import android.G4FlexSupervisor.ListCommands;
import android.G4FlexSupervisor.Queues;
import android.G4FlexSupervisor.R;
import android.G4FlexSupervisor.SIP;
import android.G4FlexSupervisor.R.id;
import android.G4FlexSupervisor.R.layout;
import android.G4FlexSupervisor.R.menu;
import android.app.Activity;

import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SecondActivity extends Activity {
	private static final String[] options = {"<select>", "Queues", "Agents",
			"SIPpeers", "IAXpeers", "CoreShowChannels", "Database", "Help"};
	private Spinner spinner;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, options);
		spinner = (Spinner) findViewById(R.id.spinnerOptions);
		spinner.setAdapter(arrayAdapter);
		Button buttonShow = (Button) findViewById(R.id.buttonShow);
		Button buttonDisconnect = (Button) findViewById(R.id.buttonDisconnect);
		buttonShow.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					int id = spinner.getSelectedItemPosition();
					switch (id) {
						case 1 :
							Queues queues = new Queues();
							break;
						case 2 :
							Agents agents = new Agents();
							break;
						case 3 :
							SIP sip = new SIP();
							break;
						case 4 :
							IAX iax = new IAX();
							break;
						case 5 :
							Channels channels = new Channels();
							break;
						case 6 :
							Database database = new Database();
							break;
						case 7 :
							ListCommands listcommands = new ListCommands();
							break;

					}
					if (id > 0) {
						AsteriskManager.id=id-1;
						startThirdActivity();
					}
				} catch (IOException e) {
					e.printStackTrace();
					finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
					finish();
				}
			}
		});
		buttonDisconnect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
	private void startThirdActivity() {
		Intent it = new Intent(this, ThirdActivity.class);
		startActivity(it);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_second, menu);
		return true;
	}
}
