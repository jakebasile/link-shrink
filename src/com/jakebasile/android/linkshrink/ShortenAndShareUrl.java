package com.jakebasile.android.linkshrink;

import android.content.Intent;
import android.widget.Toast;

public final class ShortenAndShareUrl extends ShortenUrl
{
	@Override
	protected void onUrlShortened(String shortUrl)
	{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, shortUrl);
		startActivity(Intent.createChooser(intent,
			getString(R.string.share_short_url_via)));
	}

	@Override
	protected void onUrlAlreadyShortened(String shortUrl)
	{
		Toast.makeText(this, R.string.link_already_shortened_share, Toast.LENGTH_LONG).show();
		onUrlShortened(shortUrl);
	}
}