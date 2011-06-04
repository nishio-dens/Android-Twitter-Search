package net.densanlabs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import twitter4j.Status;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class TwitterClientMain extends Activity {

	private TweetManager tweetManager = null;

	public void init() {
		tweetManager = new TweetManager();
		tweetManager.loadAccountProperties();
		try {
			tweetManager.loginTwitter();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// tweet managerの初期化
		init();

		// button clickのアクション追加
		Button updateButton = (Button) findViewById(R.id.button1);
		updateButton.setOnClickListener(new TweetUpdateButtonAction(this));
	}

	/**
	 * 
	 * @author nishio
	 * 
	 */
	class TweetUpdateButtonAction implements OnClickListener {
		// activity
		private Activity mainActivity = null;
		// adapter
		ArrayAdapter<String> adapter = null;

		/**
		 * 
		 * @param activity
		 */
		public TweetUpdateButtonAction(Activity activity) {
			this.mainActivity = activity;
		}

		/**
		 * Button click時のアクション, tweetを更新
		 * 
		 * @param v
		 */
		@Override
		public void onClick(View v) {
			EditText searchWordText = (EditText)findViewById(R.id.editText1);
			String searchWord = searchWordText.getText().toString();
			try {
				if( searchWord == null || searchWord.equals("") ) {
					adapter.add("SEARCH ERROR\n正しいワードを入力してください");
				}else {
					List<Status> status = tweetManager.getSearchResult(20, searchWord);
					this.adapter = new ArrayAdapter<String>(this.mainActivity,
							android.R.layout.simple_list_item_1) {

						/**
						 * Fontが大きすぎるので小さくする
						 */
						@Override
						public View getView(int position, View convertView, android.view.ViewGroup parent) {
							TextView view = (TextView)super.getView(position, convertView, parent);
							int textSize = 10;
							view.setTextSize( textSize );
							view.setText(view.getText());
							return view;
						}
					};
					
					for (Status s : status) {
						if (s.getUser() != null) {
							adapter.insert(s.getUser().getName() + "さんの発言(" + s.getCreatedAt().toString() + "):\n\n"
									+ s.getText(), 0);
						}
					}
				}
			} catch (Exception e) {
				Log.e("TWEET ERROR", e.toString());
			}

			ListView tweetList = (ListView) findViewById(R.id.listView1);
			tweetList.setAdapter(adapter);
		}

	}
}