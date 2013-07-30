package android.G4FlexSupervisor.activity;

import java.io.IOException;
import java.net.UnknownHostException;

import android.G4FlexSupervisor.AsteriskManager;
import android.G4FlexSupervisor.R;
import android.G4FlexSupervisor.R.id;
import android.G4FlexSupervisor.R.layout;
import android.G4FlexSupervisor.socket.Connection;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends Activity {

	private EditText editTextUser;
	private EditText editTextPassword;
	private EditText editTextHost;
	private EditText editTextPort;
	private Button buttonConnect;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		editTextUser = (EditText) findViewById(R.id.editTextUser);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextHost = (EditText) findViewById(R.id.editTextHost);
		editTextPort = (EditText) findViewById(R.id.editTextPort);
		buttonConnect = (Button) findViewById(R.id.buttonConnect);

		buttonConnect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					String host = editTextHost.getText().toString();
					int port =Integer.parseInt(editTextPort.getText().toString());
					String user = editTextUser.getText().toString();
					String password = editTextPassword.getText().toString();
					
//					new Connection(host,port);
					if(AsteriskManager.connect(host, port, user, password)){
						Connection.disconnect();
						startSecondActivity();
					}else{
						AlertDialog.Builder dialogo = new AlertDialog.Builder(
								FirstActivity.this);
						dialogo.setTitle("Error");
						dialogo.setMessage("Wrong password or username!\n");
						dialogo.setNeutralButton("OK", null);
						dialogo.show();
					}
				} catch (UnknownHostException e) {
					AlertDialog.Builder dialogo = new AlertDialog.Builder(
							FirstActivity.this);
					dialogo.setTitle("Error");
					dialogo.setMessage("This client cant connect to server!\n");
					dialogo.setNeutralButton("OK", null);
					dialogo.show();
				} catch (IOException e) {
					AlertDialog.Builder dialogo = new AlertDialog.Builder(
							FirstActivity.this);
					dialogo.setTitle("Error");
					dialogo.setMessage("This client cant connect to server!\n");
					dialogo.setNeutralButton("OK", null);
					dialogo.show();
				} catch (NumberFormatException e) {
					AlertDialog.Builder dialogo = new AlertDialog.Builder(
							FirstActivity.this);
					dialogo.setTitle("Error");
					dialogo.setMessage("This client cant connect to server!\n");
					dialogo.setNeutralButton("OK", null);
					dialogo.show();
				}
			}
		});
	}
	
	private void startSecondActivity() {
		Intent it = new Intent(this,SecondActivity.class);
		startActivity(it);
	}
	
}