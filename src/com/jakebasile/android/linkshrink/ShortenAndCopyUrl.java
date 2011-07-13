package com.jakebasile.android.linkshrink;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.widget.Toast;

public final class ShortenAndCopyUrl extends ShortenUrl
{
	@Override
	protected void OnUrlShortened(String shortUrl)
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String service = prefs.getString("service", null);
		ClipboardManager cb = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		cb.setText(shortUrl);
		String display = String.format(getResources().getString(R.string.copiedtoclip),
			service);
		Toast.makeText(getApplicationContext(), display, Toast.LENGTH_SHORT).show();
	}
}
