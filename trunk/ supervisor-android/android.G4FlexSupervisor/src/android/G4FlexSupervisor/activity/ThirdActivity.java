package android.G4FlexSupervisor.activity;

import java.io.IOException;

import android.os.Bundle;
import android.G4FlexSupervisor.AsteriskManager;
import android.G4FlexSupervisor.Database;
import android.G4FlexSupervisor.R;
import android.G4FlexSupervisor.R.id;
import android.G4FlexSupervisor.R.layout;
import android.G4FlexSupervisor.R.menu;
import android.G4FlexSupervisor.socket.Connection;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ThirdActivity extends Activity {

	private ArrayAdapter<String> aAdap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		final TextView textViewText = (TextView) findViewById(R.id.textViewText);
		final TextView textViewSelect = (TextView) findViewById(R.id.textViewSelect);
		
		switch (AsteriskManager.id) {
			case 0 :
				textViewSelect.setText("Select queue:");
				break;
			case 1 :
				textViewSelect.setText("Select agent:");
				break;
			case 2 :
				textViewSelect.setText("Select SIPpeer:");
				break;
			case 3 :
				textViewSelect.setText("Select IAXpeer:");
				break;
			case 4 :
				textViewSelect.setText("Select Channel:");
				break;
			case 5 :
				textViewSelect.setText("Select queue:");
				break;
		}

		Button buttonBack = (Button) findViewById(R.id.buttonBack);
		final Spinner spinnerList = (Spinner) findViewById(R.id.spinnerList);
		aAdap = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, AsteriskManager.nameList);
		spinnerList.setAdapter(aAdap);
		spinnerList.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				if(AsteriskManager.id==5){
					if(position>0){
						try {
//							Database.DBGet("AGENTE","","");
							Database.DBGet((String)spinnerList.getItemAtPosition(position));
							textViewText.setText(AsteriskManager.infoList.get(0));
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else{
						textViewText.setText("");
					}
				}else{
					textViewText.setText(AsteriskManager.infoList.get(position));
				}
			}

			public void onNothingSelected(AdapterView<?> parentView) {
				// TODO Auto-generated method stub

			}
		});
		buttonBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Connection.disconnect();
				finish();
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	Connection.disconnect();
			finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_third, menu);
		return true;
	}
}
