package codigo.labplc.mx.traxi.configuracion;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import codigo.labplc.mx.traxi.R;

public class UserSettingActivity extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.settings);
		

	}
}
