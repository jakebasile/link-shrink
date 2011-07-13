package com.jakebasile.android.linkshrink;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.widget.Toast;

public final class ShortenAndCopyUrl extends ShortenUrl
{
	@Override
	protected void onUrlShortened(String shortUrl)
	{
		String display = copy(shortUrl);
		Toast.makeText(getApplicationContext(), display, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onUrlAlreadyShortened(String shortUrl)
	{
		Toast.makeText(this, R.string.link_already_shortened_copy, Toast.LENGTH_LONG).show();
		copy(shortUrl);
	}

	private String copy(String shortUrl)
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String service = prefs.getString("service", null);
		ClipboardManager cb = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		cb.setText(shortUrl);
		String display = String.format(getResources().getString(R.string.copiedtoclip),
			service);
		return display;
	}
}
