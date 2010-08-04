package com.jakebasile.android.urlshortener;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class Configure extends PreferenceActivity
{
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		addPreferencesFromResource(R.xml.preferences);
		final Preference bitlyUsername = (Preference)this.findPreference("bitlyUsername");
		final Preference bitlyKey = (Preference)this.findPreference("bitlyKey");
		final Preference getBitlyKey = (Preference)this.findPreference("getBitlyKey");
		getBitlyKey.setOnPreferenceClickListener(
			new OnPreferenceClickListener()
			{
				public boolean onPreferenceClick(Preference arg0)
				{
					Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://bit.ly/a/your_api_key"));
					startActivity(viewIntent);
					return true;
				}
			});
		Preference service = (Preference)this.findPreference("service");
		service.setOnPreferenceChangeListener(
			new OnPreferenceChangeListener()
			{
				public boolean onPreferenceChange(Preference pref, Object newValue)
				{
					String newVal = (String)newValue;
					if(newVal.equalsIgnoreCase("bit.ly"))
					{
						bitlyUsername.setEnabled(true);
						bitlyKey.setEnabled(true);
						getBitlyKey.setEnabled(true);
						Boolean displayToast = pref.getSharedPreferences().getString("bitlyUsername", "").trim().length() == 0
							|| pref.getSharedPreferences().getString("bitlyKey", "").trim().length() == 0;
						if(displayToast)
						{
							Toast.makeText(getApplicationContext(), R.string.bitlyinforemind, Toast.LENGTH_LONG).show();
						}
					}
					else
					{
						bitlyUsername.setEnabled(false);
						bitlyKey.setEnabled(false);
						getBitlyKey.setEnabled(false);
					}
					return true;
				}
			});
		String serv = service.getSharedPreferences().getString("service", "is.gd");
		if(serv.equalsIgnoreCase("bit.ly"))
		{
			bitlyUsername.setEnabled(true);
			bitlyKey.setEnabled(true);
			getBitlyKey.setEnabled(true);
			Boolean displayToast = service.getSharedPreferences().getString("bitlyUsername", "").trim().length() == 0
				|| service.getSharedPreferences().getString("bitlyKey", "").trim().length() == 0;
			if(displayToast)
			{
				Toast.makeText(getApplicationContext(), R.string.bitlyinforemind, Toast.LENGTH_LONG).show();
			}
		}
		else
		{
			bitlyUsername.setEnabled(false);
			bitlyKey.setEnabled(false);
			getBitlyKey.setEnabled(false);
		}
	}
}