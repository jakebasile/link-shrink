/*
 * Copyright 2010-2012 Jake Basile
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
