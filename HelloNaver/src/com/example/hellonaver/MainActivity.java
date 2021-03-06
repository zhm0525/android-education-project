package com.example.hellonaver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.begentgroup.xmlparser.XMLParser;
import com.example.hellonaver.NetworkManager.OnResultListener;

public class MainActivity extends ParentActivity {

	ListView listView;
//	ArrayAdapter<MovieItem> mAdapter;
	MyAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView)findViewById(R.id.listView1);
//		mAdapter = new ArrayAdapter<MovieItem>(this, android.R.layout.simple_list_item_1);
		mAdapter = new MyAdapter();
		listView.setAdapter(mAdapter);
		Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				new NaverTask().execute();
//				NetworkManager.getInstance().getNaverMovie(new OnResultListener<NaverMovies>() {
//					
//					@Override
//					public void onSuccess(NaverMovies result) {
//						for (MovieItem item : result.items) {
//							mAdapter.add(item);
//						}
//						
//					}
//					
//					@Override
//					public void onFail(int code) {
//						Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
//					}
//				});
				NetworkManager.getInstance().getNaverMovie(MainActivity.this, "사랑", new OnResultListener<NaverMovies>() {

					@Override
					public void onSuccess(NaverMovies result) {
//						for (MovieItem item : result.items) {
//							mAdapter.add(item);
//						}

						mAdapter.addAll(result.items);
					}
					
					@Override
					public void onFail(int code) {
						Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}

	
//	class NaverTask extends AsyncTask<Void, Integer, NaverMovies> {
//		@Override
//		protected NaverMovies doInBackground(Void... params) {
//			try {
//				URL url = new URL("http://openapi.naver.com/search?key=55f1e342c5bce1cac340ebb6032c7d9a&display=10&start=1&target=movie&query=" + URLEncoder.encode("사랑", "utf8"));
//				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//				int responseCode = conn.getResponseCode();
//				if (responseCode == HttpURLConnection.HTTP_OK) {
//					XMLParser parser = new XMLParser();
//					NaverMovies result = parser.fromXml(conn.getInputStream(), "channel", NaverMovies.class);
//					return result;
//				}
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			return null;
//		}
//		
//		@Override
//		protected void onPostExecute(NaverMovies result) {
//			super.onPostExecute(result);
//			if (result != null) {
//				for (MovieItem item : result.items) {
//					mAdapter.add(item);
//				}
//			} else {
//				Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
//			}
//		}
//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
