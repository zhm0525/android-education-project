package com.example.samplecomponenttest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final int REQUEST_MY_ACTIVITY = 0;
	public static final int REQUEST_IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button)findViewById(R.id.btnGoogle);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
				startActivity(intent);
			}
		});
		
		btn = (Button)findViewById(R.id.btnContact);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
				startActivity(intent);
			}
		});
		
		btn = (Button)findViewById(R.id.btnMy);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MyActivity.class);
				intent.putExtra(MyActivity.PARAM_NAME, "ysi");
				intent.putExtra(MyActivity.PARAM_AGE, 39);
				startActivityForResult(intent, REQUEST_MY_ACTIVITY);
			}
		});
		
		btn = (Button)findViewById(R.id.btnPicture);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(intent,REQUEST_IMAGE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_MY_ACTIVITY && resultCode == RESULT_OK) {
			int resultAge = data.getIntExtra(MyActivity.RESULT_AGE, 0);
			Toast.makeText(this, "result : " + resultAge, Toast.LENGTH_SHORT).show();
		} else if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
			ImageView iv = (ImageView)findViewById(R.id.imageView1);
			Uri imageUri = data.getData();
			iv.setImageURI(imageUri);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
