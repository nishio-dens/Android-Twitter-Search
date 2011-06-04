package net.densanlabs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import twitter4j.Annotations;
import twitter4j.DirectMessage;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserMentionEntity;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.auth.*;

public class TweetManager {

	private Twitter twitter = null;
	// クライアントのConsumer Key
	private static final String CONSUMER_KEY = "tbo5erit2M2ZzN6n8tEYcA";
	// クライアントのConsumer Secret 外部に漏れてはいけない
	private static final String CONSUMER_SECRET = "tODurbdySLYU1pKjtB3MQTDRBGy562dHzVf7d62mm8";
	// accessToken
	private String accessToken = "";
	// secretToken 外部に漏れてはいけない
	private String secretToken = "";
	// 一度に取得する最大のtweet数
	private final int MAX_TWEET_NUM = 20;
	// 前回取得したtweetの情報
	private long sinceTweetID = 0;
	// 前回取得したmentionのtweetID
	private long sinceMentionID = 0;
	// 前回取得したsendDirectMessageの情報
	private long sinceSendDirectMessageID = 0;
	// 前回取得したdirectMessageの情報
	private long sinceDirectMessageID = 0;
	
	//following IDリスト keyはユーザ名, valueはユーザがfollowingしているid
	private Map<String, List<Long>> followingUserIDList = new HashMap<String, List<Long>>();
        //follower
        private Map<String, List<Long>> followerUserIDList = new HashMap<String, List<Long>>();

	public TweetManager() {
		
	}

	/**
	 * アカウント情報を読み込む
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void loadAccountProperties() {
		// 設定読み込み
		this.accessToken = "ここは自分で取得してーー";
		this.secretToken = "ここも自分で取得してーー";
	}


	/**
	 * Mentionの取得
	 * 
	 * @param num
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getMentions(int num) throws TwitterException {
		List<Status> statuses = twitter.getMentions(new Paging(1, num));
		// Tweetの一覧
		List<Status> tweetList = new ArrayList<Status>();
		if (statuses != null && statuses.size() > 0) {
			// tweetIDを保存しておく
			this.sinceMentionID = statuses.get(0).getId();
			// 一番新しいtweetを一番したに持ってくる
			// for (Status status : statuses) {
			// tweetList.add(0, status);
			// }
			Collections.reverse(statuses);
			tweetList.addAll(statuses);
		}
		return tweetList;
	}


	/**
	 * まだ取得していないMention情報を取得
	 * 
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getNewMentionData() throws TwitterException {
		// 一度もデータを取得していないとき
		if (sinceMentionID == 0) {
			return getMentions(MAX_TWEET_NUM);
		}
		// getFriendsTimelineではRetweetは表示されないので注意
		List<Status> statuses = twitter.getMentions(new Paging(sinceMentionID));
		// もし20件データ取得したらもっとデータがある可能性がある
		if (statuses.size() >= 20) {
			List<Status> ndata = twitter.getMentions(new Paging(1,
					MAX_TWEET_NUM));
			ndata = ndata.subList(21, ndata.size());
			for (Status s : ndata) {
				// すでに取得したことのあるデータだったとき
				if (s.getId() <= sinceMentionID) {
					break;
				}
				statuses.add(s);
			}
		}

		// Tweetの一覧
		List<Status> tweetList = new ArrayList<Status>();
		if (statuses != null && statuses.size() > 0) {
			// tweetIDを保存しておく
			sinceMentionID = statuses.get(0).getId();

			// 一番新しいtweetを一番したに持ってくる
			/*
			 * for (Status status : statuses) { tweetList.add(0, status); }
			 */
			Collections.reverse(statuses);
			tweetList.addAll(statuses);
		}
		return tweetList;
	}

	/**
	 * まだ取得していないtweet情報を取得
	 * 
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getNewTimelineData() throws TwitterException {
		// 一度もデータを取得していないとき
		if (sinceTweetID == 0) {
			return getTimeline(MAX_TWEET_NUM);
		}
		// getFriendsTimelineではRetweetは表示されないので注意
		List<Status> statuses = twitter
				.getHomeTimeline(new Paging(sinceTweetID));
		// もし20件データ取得したらもっとデータがある可能性がある
		if (statuses.size() >= 20) {
			List<Status> ndata = twitter.getHomeTimeline(new Paging(1,
					MAX_TWEET_NUM));
			ndata = ndata.subList(21, ndata.size());
			for (Status s : ndata) {
				// すでに取得したことのあるデータだったとき
				if (s.getId() <= sinceTweetID) {
					break;
				}
				statuses.add(s);
			}
		}
		// Tweetの一覧
		List<Status> tweetList = new ArrayList<Status>();
		if (statuses != null && statuses.size() > 0) {
			// tweetIDを保存しておく
			sinceTweetID = statuses.get(0).getId();

			// 一番新しいtweetを一番したに持ってくる
			/*
			 * for (Status status : statuses) { tweetList.add(0, status); }
			 */
			Collections.reverse(statuses);
			tweetList.addAll(statuses);
		}
		return tweetList;
	}

	/**
	 * タイムラインを取得 一番新しいツイートは要素の一番下に追加
	 * 
	 * @param num
	 *            指定した数だけtweetを取得
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getTimeline(int num) throws TwitterException {
		// getFriendsTimelineではRetweetは表示されないので注意
		List<Status> statuses = twitter.getHomeTimeline(new Paging(1, num));
		// Tweetの一覧
		List<Status> tweetList = new ArrayList<Status>();
		if (statuses != null && statuses.size() > 0) {
			// tweetIDを保存しておく
			sinceTweetID = statuses.get(0).getId();

			// 一番新しいtweetを一番したに持ってくる
			// for (Status status : statuses) {
			// tweetList.add(0, status);
			// }
			Collections.reverse(statuses);
			tweetList.addAll(statuses);
		}
		return tweetList;
	}

	/**
	 * 指定したワードを含むtweetを返す
	 * 
	 * @param sinceID
	 * @param searchWord
	 * @return
	 */
	public List<Status> getNewSearchResult(long sinceID, String searchWord) {
		// 一度もデータを取得していないとき
		if (sinceID == 0) {
			return getSearchResult(MAX_TWEET_NUM, searchWord);
		}

		// TODO:同じようなコードを二回書いてる．ここは修正の必要があるかも

		Query query = new Query(searchWord);
		// 取得するツイート最大数
		query.setRpp(this.MAX_TWEET_NUM);
		// 取得するページ番号
		query.setPage(1);
		// 追加: sinceIDを登録
		query.setSinceId(sinceID);
		// 検索結果
		QueryResult queryResult = null;
		try {
			queryResult = twitter.search(query);
		} catch (TwitterException ex) {
			Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE,
					"Twitter searchに失敗しました", ex);
			ex.printStackTrace();
		}

		List<Status> tweetList = new ArrayList<Status>();

		if (queryResult != null) {
			for (Tweet tweet : queryResult.getTweets()) {
				// 取得できる最大限の情報を返す
				SimpleUser user = new SimpleUser();
				// ユーザ名
				user.setName(tweet.getFromUser());
				user.setScreenName(tweet.getFromUser());
				// ユーザID
				user.setId(tweet.getFromUserId());
				try {
					// ユーザイメージ
					user
							.setProfileImageURL(new URL(tweet
									.getProfileImageUrl()));
				} catch (MalformedURLException ex) {
					Logger.getLogger(TweetManager.class.getName()).log(
							Level.SEVERE, null, ex);
				}

				SimpleStatus status = new SimpleStatus();
				status.setCreatedAt(tweet.getCreatedAt());
				status.setId(tweet.getId());
				status.setSource(tweet.getSource());
				status.setText(tweet.getText());
				status.setUser(user);

				// 情報追加
				tweetList.add(status);
			}
		}
		// リスト逆転 一番あたらしいtweetを一番下に
		Collections.reverse(tweetList);

		return tweetList;
	}

	/**
	 * 指定したワードを含むtweetを返す
	 * 
	 * @param num
	 *            指定した数だけtweetを取得
	 * @param searchWord
	 *            検索したい単語
	 * @return
	 */
	public List<Status> getSearchResult(int num, String searchWord) {
		Query query = new Query(searchWord);
		// 取得するツイート最大数
		query.setRpp(num);
		// 取得するページ番号
		query.setPage(1);
		// 検索結果
		QueryResult queryResult = null;
		try {
			queryResult = twitter.search(query);
		} catch (TwitterException ex) {
			Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE,
					"Twitter searchに失敗しました", ex);
			ex.printStackTrace();
		}

		List<Status> tweetList = new ArrayList<Status>();

		if (queryResult != null) {
			for (Tweet tweet : queryResult.getTweets()) {
				// 取得できる最大限の情報を返す
				SimpleUser user = new SimpleUser();
				// ユーザ名
				user.setName(tweet.getFromUser());
				user.setScreenName(tweet.getFromUser());
				// ユーザID
				user.setId(tweet.getFromUserId());
				try {
					// ユーザイメージ
					user
							.setProfileImageURL(new URL(tweet
									.getProfileImageUrl()));
				} catch (MalformedURLException ex) {
					Logger.getLogger(TweetManager.class.getName()).log(
							Level.SEVERE, null, ex);
				}

				SimpleStatus status = new SimpleStatus();
				status.setCreatedAt(tweet.getCreatedAt());
				status.setId(tweet.getId());
				status.setSource(tweet.getSource());
				status.setText(tweet.getText());
				status.setUser(user);

				// 情報追加
				tweetList.add(status);
			}
		}

		// リスト逆転 一番あたらしいtweetを一番下に
		Collections.reverse(tweetList);

		return tweetList;
	}

	/**
	 * 指定したユーザのお気に入りを取得
	 * 
	 * @param screenName
	 *            nullの場合，自分自身のお気に入りを取得
	 */
	public List<Status> getFavoritesTweet(String screenName)
			throws TwitterException {
		// getFriendsTimelineではRetweetは表示されないので注意
		List<Status> statuses = null;
		if (screenName == null) {
			// 自分自身のお気に入り取得
			statuses = twitter.getFavorites();
		} else {
			statuses = twitter.getFavorites(screenName);
		}
		// リスト逆転 一番あたらしいtweetを一番下に
		Collections.reverse(statuses);
		return statuses;
	}

	/**
	 * 指定したユーザの発言を取得
	 * 
	 * @param num
	 * @param userID
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getUserTimeline(int num, long userID)
			throws TwitterException {
		List<Status> statuses = twitter.getUserTimeline(userID, new Paging(1,
				num));

		// tweet逆転
		if (statuses != null && statuses.size() > 0) {
			Collections.reverse(statuses);
		}

		return statuses;
	}
	
	/**
	 * 指定したユーザの発言を取得
	 * 
	 * @param num
	 * @param screenName
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getUserTimeline(int num, String screenName)
			throws TwitterException {
		List<Status> statuses = twitter.getUserTimeline(screenName, new Paging(1,
				num));

		// tweet逆転
		if (statuses != null && statuses.size() > 0) {
			Collections.reverse(statuses);
		}

		return statuses;
	}
	

	/**
	 * 指定したユーザが保持しているリスト一覧を取得
	 * 
	 * @param userScreenName
	 * @return
	 */
	public List<UserList> getUserLists(String userScreenName) {
		List<UserList> userlist = new ArrayList<UserList>();
		long cursor = -1;
		try {
			for (;;) {
				// ユーザリスト取得
				PagableResponseList<UserList> list = this.twitter.getUserLists(
						userScreenName, cursor);
				userlist.addAll(list);

				if (list.hasNext()) {
					// 次のカーソルを取得
					cursor = list.getNextCursor();
				} else {
					break;
				}
			}
		} catch (TwitterException ex) {
			Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return userlist;
	}

	/**
	 * 指定したユーザをフォローしているリストを返す
	 * 
	 * @param userScreenName
	 * @return
	 */
	public List<UserList> getUserListSubscriptions(String userScreenName) {
		List<UserList> userlist = new ArrayList<UserList>();
		long cursor = -1;
		try {
			for (;;) {
				// ユーザリスト取得
				PagableResponseList<UserList> list = this.twitter
						.getUserListSubscriptions(userScreenName, cursor);
				userlist.addAll(list);

				if (list.hasNext()) {
					// 次のカーソルを取得
					cursor = list.getNextCursor();
				} else {
					break;
				}
			}
		} catch (TwitterException ex) {
			Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return userlist;
	}

	/**
	 * 指定したユーザが追加されているリストを返す
	 * 
	 * @param userScreenName
	 * @return
	 */
	public List<UserList> getUserListMemberships(String userScreenName) {
		List<UserList> userlist = new ArrayList<UserList>();
		long cursor = -1;
		try {
			for (;;) {
				// ユーザリスト取得
				PagableResponseList<UserList> list = this.twitter
						.getUserListMemberships(userScreenName, cursor);
				userlist.addAll(list);

				if (list.hasNext()) {
					// 次のカーソルを取得
					cursor = list.getNextCursor();
				} else {
					break;
				}
			}
		} catch (TwitterException ex) {
			Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return userlist;
	}

	/**
	 * 指定したリストのツイートを取得
	 * 
	 * @param userScreenName
	 *            ユーザ名
	 * @param listID
	 *            リストID
	 * @param num
	 *            取得ツイート数
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getUserListStatuses(String userScreenName, int listID,
			int num) {
		List<Status> tweetList = null;
		try {
			tweetList = this.twitter.getUserListStatuses(userScreenName,
					listID, new Paging(1, num));
		} catch (TwitterException ex) {
			Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		// tweet逆転
		if (tweetList != null && tweetList.size() > 0) {
			Collections.reverse(tweetList);
		}
		return tweetList;
	}

	/**
	 * 指定したリストの最新情報を取得
	 * 
	 * @param userScreenName
	 *            ユーザ名
	 * @param listID
	 *            リストID
	 * @param sinceID
	 * @return
	 */
	public List<Status> getNewUserListStatuses(String userScreenName,
			int listID, long sinceID) {
		if (sinceID == 0) {
			return getUserListStatuses(userScreenName, listID, MAX_TWEET_NUM);
		}
		List<Status> tweetList = null;
		try {
			tweetList = this.twitter.getUserListStatuses(userScreenName,
					listID, new Paging(sinceID));
		} catch (TwitterException ex) {
			Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		// tweet逆転
		if (tweetList != null && tweetList.size() > 0) {
			Collections.reverse(tweetList);
		}
		return tweetList;
	}

	/**
	 * 指定したユーザの最新の発言を取得
	 * 
	 * @param userID
	 * @param sinceID
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getNewUserTimeline(long userID, long sinceID)
			throws TwitterException {
		// 一度もデータを取得していないとき
		if (sinceID == 0) {
			return getUserTimeline(MAX_TWEET_NUM, userID);
		}

		List<Status> statuses = twitter.getUserTimeline(userID, new Paging(
				sinceID));

		// tweet逆転
		if (statuses != null && statuses.size() > 0) {
			Collections.reverse(statuses);
		}

		return statuses;
	}
	
	/**
	 * 指定したユーザの最新の発言を取得
	 * 
	 * @param screenName
	 * @param sinceID
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getNewUserTimeline(String screenName, long sinceID)
			throws TwitterException {
		// 一度もデータを取得していないとき
		if (sinceID == 0) {
			return getUserTimeline(MAX_TWEET_NUM, screenName);
		}

		List<Status> statuses = twitter.getUserTimeline(screenName, new Paging(
				sinceID));

		// tweet逆転
		if (statuses != null && statuses.size() > 0) {
			Collections.reverse(statuses);
		}

		return statuses;
	}

	/**
	 * Twitterへログイン
	 */
	public void loginTwitter() throws FileNotFoundException, IOException {
		twitter = new TwitterFactory().getInstance();
		// アカウント情報を読み込む
		loadAccountProperties();
		// ConsumerKeyなどを設定
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		// ここにユーザのアクセストークンを入れる
		AccessToken ac = new AccessToken(accessToken, secretToken);
		twitter.setOAuthAccessToken(ac);
	}

	/**
	 * Configurationを生成する
	 * 
	 * @return
	 */
	private Configuration getTwitterConfiguration() {
		ConfigurationBuilder confbuilder = new ConfigurationBuilder();
		confbuilder.setOAuthConsumerKey(CONSUMER_KEY);
		confbuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		return confbuilder.build();
	}

	/**
	 * 初回時アクセストークンを取得する際に利用する
	 * 
	 * @param username
	 * @param password
	 * @throws TwitterException
	 */
	public void getAccessToken(String username, String password)
			throws TwitterException {
		// consumer key secretを設定
		Configuration conf = this.getTwitterConfiguration();
		twitter = new TwitterFactory(conf).getInstance();
		// access token取得
		AccessToken oAuthAccessToken = twitter.getOAuthAccessToken(username,
				password);
		this.accessToken = oAuthAccessToken.getToken();
		this.secretToken = oAuthAccessToken.getTokenSecret();
	}

	/**
	 * メッセージをつぶやく
	 * 
	 * @param message
	 * @throws TwitterException
	 */
	public void tweet(String message) throws TwitterException {
		twitter4j.Status status;
		status = twitter.updateStatus(message);
	}

	/**
	 * 返信メッセージをつぶやく
	 * 
	 * @param message
	 * @param replyToStatusID
	 * @throws TwitterException 
	 */
	public void replyTweet(String message, long replyToStatusID) throws TwitterException {
		twitter4j.Status status;
		// status = twitter.updateStatus(message, replyToStatusID);
		StatusUpdate updateMsg = new StatusUpdate(message);
		updateMsg.setInReplyToStatusId(replyToStatusID);
		status = twitter.updateStatus(updateMsg);
	}

	/**
	 * ダイレクトメッセージを送信
	 * 
	 * @param screenName
	 * @param text
	 * @throws TwitterException
	 */
	public void sendDirectMessage(String screenName, String text)
			throws TwitterException {
		twitter.sendDirectMessage(screenName, text);
	}

	/**
	 * 指定したステータスIDの発言をRetweet
	 * 
	 * @param statusID
	 * @throws TwitterException
	 */
	public void retweet(long statusID) throws TwitterException {
		twitter.retweetStatus(statusID);
	}

	/**
	 * 指定した発言をお気に入りに追加
	 * 
	 * @param statusID
	 * @throws TwitterException
	 */
	public void createFavorite(long statusID) throws TwitterException {
		twitter.createFavorite(statusID);
	}

	/**
	 * 指定した発言のお気に入りを取り下げる
	 * 
	 * @param statusID
	 */
	public void destroyFavorite(long statusID) throws TwitterException {
		twitter.destroyFavorite(statusID);
	}

	/**
	 * API制限数を取得
	 * 
	 * @return
	 * @throws TwitterException
	 */
	public RateLimitStatus getRateLimitStatus() throws TwitterException {
		return twitter.getRateLimitStatus();
	}

	/**
	 * 自分自身のスクリーン名を返す
	 * 
	 * @return
	 */
	public String getScreenName() {
		try {
			if (twitter != null) {
				return twitter.getScreenName();
			}
		} catch (TwitterException e) {

		}
		return null;
	}
	
	/**
	 * 指定したユーザのfollowingユーザ一覧の詳細情報を取得
	 * @param screenName 取得したいユーザ
	 * @param page ページ1あたり100件の情報を取得
	 * @return
	 */
	public List<User> getFollowingUser(String screenName, int page) {
		if( !this.followingUserIDList.containsKey(screenName) ) {
			//まだscreenNameがfollowingしているユーザ情報を取得していない
			List<Long> following = this.getFollowingUserID(screenName);
			this.followingUserIDList.put(screenName, following);
		}
		//followingユーザ一覧id取得
		List<Long> getFollowingUserIds = null;
		List<Long> followingList = this.followingUserIDList.get(screenName);
		if( followingList != null && followingList.size() > 0 ) {
			//データ存在しない
			if( followingList.size() < page * 100 ) {
				return null;
			}
			int to = page * 100 + 99;
			if( followingList.size() < to){
				to = to - (to - followingList.size());
			}
			getFollowingUserIds = followingList.subList(page * 100, to);
		}
		
		//結果
		List<User> result = new ArrayList<User>();
		
		int getDataSize = getFollowingUserIds.size();
		if( getDataSize > 0 ) {
			long[] ids = new long[ getDataSize ];
			for( int i=0; i < ids.length; i++) {
				ids[i] = getFollowingUserIds.get( i );
			}
			
			//idsのユーザ一覧取得
			try {
				ResponseList<User> users = twitter.lookupUsers( ids );
				for(User u : users) {
					result.add( u );
				}
			} catch (TwitterException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

        /**
	 * 指定したユーザのfollowerユーザ一覧の詳細情報を取得
	 * @param screenName 取得したいユーザ
	 * @param page ページ1あたり100件の情報を取得
	 * @return
	 */
	public List<User> getFollowerUser(String screenName, int page) {
		if( !this.followerUserIDList.containsKey(screenName) ) {
			//まだscreenNameがfollowingしているユーザ情報を取得していない
			List<Long> follower = this.getFollowerUserID(screenName);
			this.followerUserIDList.put(screenName, follower);
		}
		//followingユーザ一覧id取得
		List<Long> getFollowerUserIds = null;
		List<Long> followerList = this.followerUserIDList.get(screenName);
		if( followerList != null && followerList.size() > 0 ) {
			//データ存在しない
			if( followerList.size() < page * 100 ) {
				return null;
			}
			int to = page * 100 + 99;
			if( followerList.size() < to){
				to = to - (to - followerList.size());
			}
			getFollowerUserIds = followerList.subList(page * 100, to);
		}

		//結果
		List<User> result = new ArrayList<User>();

		int getDataSize = getFollowerUserIds.size();
		if( getDataSize > 0 ) {
			long[] ids = new long[ getDataSize ];
			for( int i=0; i < ids.length; i++) {
				ids[i] = getFollowerUserIds.get( i );
			}

			//idsのユーザ一覧取得
			try {
				ResponseList<User> users = twitter.lookupUsers( ids );
				for(User u : users) {
					result.add( u );
				}
			} catch (TwitterException e) {
				e.printStackTrace();
			}

		}
		return result;
	}
	
	
	/**
	 * FriendのID一覧を取得する	
	 * @param screenName
	 * @return
	 */
	public List<Long> getFollowingUserID(String screenName) {
		long cursor = -1;
		IDs ids = null;
		long[] friendIds = null;
		//id一覧
		List<Long> result = new ArrayList<Long>();
		
		try {
			//friend一覧をすべて取得
			do {
				ids = twitter.getFriendsIDs( screenName, cursor );
				//screenNameのフレンドid一覧を取得
				friendIds = ids.getIDs();
				if( friendIds.length > 0 ) {
					for(long id : friendIds ) {
						result.add( id );
					}
				}
				//次のカーソルに移動
				cursor = ids.getNextCursor();
			}while( cursor != 0 );
			
		}catch(TwitterException e) {
			e.printStackTrace();
		}
		return result;		
	}

        /**
	 * Friendのfollewer ID一覧を取得する
	 * @param screenName
	 * @return
	 */
	public List<Long> getFollowerUserID(String screenName) {
		long cursor = -1;
		IDs ids = null;
		long[] friendIds = null;
		//id一覧
		List<Long> result = new ArrayList<Long>();

		try {
			//friend一覧をすべて取得
			do {
				ids = twitter.getFollowersIDs( screenName, cursor );
				//screenNameのフレンドid一覧を取得
				friendIds = ids.getIDs();
				if( friendIds.length > 0 ) {
					for(long id : friendIds ) {
						result.add( id );
					}
				}
				//次のカーソルに移動
				cursor = ids.getNextCursor();
			}while( cursor != 0 );

		}catch(TwitterException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
