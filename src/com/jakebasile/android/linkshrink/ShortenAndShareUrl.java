package com.jakebasile.android.linkshrink;

import android.content.Intent;

public final class ShortenAndShareUrl extends ShortenUrl
{
	@Override
	protected void OnUrlShortened(String shortUrl)
	{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, shortUrl);
		startActivity(Intent.createChooser(intent,
			getString(R.string.share_short_url_via)));
	}
}