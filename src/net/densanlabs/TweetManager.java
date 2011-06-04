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
	// �N���C�A���g��Consumer Key
	private static final String CONSUMER_KEY = "tbo5erit2M2ZzN6n8tEYcA";
	// �N���C�A���g��Consumer Secret �O���ɘR��Ă͂����Ȃ�
	private static final String CONSUMER_SECRET = "tODurbdySLYU1pKjtB3MQTDRBGy562dHzVf7d62mm8";
	// accessToken
	private String accessToken = "";
	// secretToken �O���ɘR��Ă͂����Ȃ�
	private String secretToken = "";
	// ��x�Ɏ擾����ő��tweet��
	private final int MAX_TWEET_NUM = 20;
	// �O��擾����tweet�̏��
	private long sinceTweetID = 0;
	// �O��擾����mention��tweetID
	private long sinceMentionID = 0;
	// �O��擾����sendDirectMessage�̏��
	private long sinceSendDirectMessageID = 0;
	// �O��擾����directMessage�̏��
	private long sinceDirectMessageID = 0;
	
	//following ID���X�g key�̓��[�U��, value�̓��[�U��following���Ă���id
	private Map<String, List<Long>> followingUserIDList = new HashMap<String, List<Long>>();
        //follower
        private Map<String, List<Long>> followerUserIDList = new HashMap<String, List<Long>>();

	public TweetManager() {
		
	}

	/**
	 * �A�J�E���g����ǂݍ���
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void loadAccountProperties() {
		// �ݒ�ǂݍ���
		this.accessToken = "�����͎����Ŏ擾���ā[�[";
		this.secretToken = "�����������Ŏ擾���ā[�[";
	}


	/**
	 * Mention�̎擾
	 * 
	 * @param num
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getMentions(int num) throws TwitterException {
		List<Status> statuses = twitter.getMentions(new Paging(1, num));
		// Tweet�̈ꗗ
		List<Status> tweetList = new ArrayList<Status>();
		if (statuses != null && statuses.size() > 0) {
			// tweetID��ۑ����Ă���
			this.sinceMentionID = statuses.get(0).getId();
			// ��ԐV����tweet����Ԃ����Ɏ����Ă���
			// for (Status status : statuses) {
			// tweetList.add(0, status);
			// }
			Collections.reverse(statuses);
			tweetList.addAll(statuses);
		}
		return tweetList;
	}


	/**
	 * �܂��擾���Ă��Ȃ�Mention�����擾
	 * 
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getNewMentionData() throws TwitterException {
		// ��x���f�[�^���擾���Ă��Ȃ��Ƃ�
		if (sinceMentionID == 0) {
			return getMentions(MAX_TWEET_NUM);
		}
		// getFriendsTimeline�ł�Retweet�͕\������Ȃ��̂Œ���
		List<Status> statuses = twitter.getMentions(new Paging(sinceMentionID));
		// ����20���f�[�^�擾����������ƃf�[�^������\��������
		if (statuses.size() >= 20) {
			List<Status> ndata = twitter.getMentions(new Paging(1,
					MAX_TWEET_NUM));
			ndata = ndata.subList(21, ndata.size());
			for (Status s : ndata) {
				// ���łɎ擾�������Ƃ̂���f�[�^�������Ƃ�
				if (s.getId() <= sinceMentionID) {
					break;
				}
				statuses.add(s);
			}
		}

		// Tweet�̈ꗗ
		List<Status> tweetList = new ArrayList<Status>();
		if (statuses != null && statuses.size() > 0) {
			// tweetID��ۑ����Ă���
			sinceMentionID = statuses.get(0).getId();

			// ��ԐV����tweet����Ԃ����Ɏ����Ă���
			/*
			 * for (Status status : statuses) { tweetList.add(0, status); }
			 */
			Collections.reverse(statuses);
			tweetList.addAll(statuses);
		}
		return tweetList;
	}

	/**
	 * �܂��擾���Ă��Ȃ�tweet�����擾
	 * 
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getNewTimelineData() throws TwitterException {
		// ��x���f�[�^���擾���Ă��Ȃ��Ƃ�
		if (sinceTweetID == 0) {
			return getTimeline(MAX_TWEET_NUM);
		}
		// getFriendsTimeline�ł�Retweet�͕\������Ȃ��̂Œ���
		List<Status> statuses = twitter
				.getHomeTimeline(new Paging(sinceTweetID));
		// ����20���f�[�^�擾����������ƃf�[�^������\��������
		if (statuses.size() >= 20) {
			List<Status> ndata = twitter.getHomeTimeline(new Paging(1,
					MAX_TWEET_NUM));
			ndata = ndata.subList(21, ndata.size());
			for (Status s : ndata) {
				// ���łɎ擾�������Ƃ̂���f�[�^�������Ƃ�
				if (s.getId() <= sinceTweetID) {
					break;
				}
				statuses.add(s);
			}
		}
		// Tweet�̈ꗗ
		List<Status> tweetList = new ArrayList<Status>();
		if (statuses != null && statuses.size() > 0) {
			// tweetID��ۑ����Ă���
			sinceTweetID = statuses.get(0).getId();

			// ��ԐV����tweet����Ԃ����Ɏ����Ă���
			/*
			 * for (Status status : statuses) { tweetList.add(0, status); }
			 */
			Collections.reverse(statuses);
			tweetList.addAll(statuses);
		}
		return tweetList;
	}

	/**
	 * �^�C�����C�����擾 ��ԐV�����c�C�[�g�͗v�f�̈�ԉ��ɒǉ�
	 * 
	 * @param num
	 *            �w�肵��������tweet���擾
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getTimeline(int num) throws TwitterException {
		// getFriendsTimeline�ł�Retweet�͕\������Ȃ��̂Œ���
		List<Status> statuses = twitter.getHomeTimeline(new Paging(1, num));
		// Tweet�̈ꗗ
		List<Status> tweetList = new ArrayList<Status>();
		if (statuses != null && statuses.size() > 0) {
			// tweetID��ۑ����Ă���
			sinceTweetID = statuses.get(0).getId();

			// ��ԐV����tweet����Ԃ����Ɏ����Ă���
			// for (Status status : statuses) {
			// tweetList.add(0, status);
			// }
			Collections.reverse(statuses);
			tweetList.addAll(statuses);
		}
		return tweetList;
	}

	/**
	 * �w�肵�����[�h���܂�tweet��Ԃ�
	 * 
	 * @param sinceID
	 * @param searchWord
	 * @return
	 */
	public List<Status> getNewSearchResult(long sinceID, String searchWord) {
		// ��x���f�[�^���擾���Ă��Ȃ��Ƃ�
		if (sinceID == 0) {
			return getSearchResult(MAX_TWEET_NUM, searchWord);
		}

		// TODO:�����悤�ȃR�[�h���񏑂��Ă�D�����͏C���̕K�v�����邩��

		Query query = new Query(searchWord);
		// �擾����c�C�[�g�ő吔
		query.setRpp(this.MAX_TWEET_NUM);
		// �擾����y�[�W�ԍ�
		query.setPage(1);
		// �ǉ�: sinceID��o�^
		query.setSinceId(sinceID);
		// ��������
		QueryResult queryResult = null;
		try {
			queryResult = twitter.search(query);
		} catch (TwitterException ex) {
			Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE,
					"Twitter search�Ɏ��s���܂���", ex);
			ex.printStackTrace();
		}

		List<Status> tweetList = new ArrayList<Status>();

		if (queryResult != null) {
			for (Tweet tweet : queryResult.getTweets()) {
				// �擾�ł���ő���̏���Ԃ�
				SimpleUser user = new SimpleUser();
				// ���[�U��
				user.setName(tweet.getFromUser());
				user.setScreenName(tweet.getFromUser());
				// ���[�UID
				user.setId(tweet.getFromUserId());
				try {
					// ���[�U�C���[�W
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

				// ���ǉ�
				tweetList.add(status);
			}
		}
		// ���X�g�t�] ��Ԃ����炵��tweet����ԉ���
		Collections.reverse(tweetList);

		return tweetList;
	}

	/**
	 * �w�肵�����[�h���܂�tweet��Ԃ�
	 * 
	 * @param num
	 *            �w�肵��������tweet���擾
	 * @param searchWord
	 *            �����������P��
	 * @return
	 */
	public List<Status> getSearchResult(int num, String searchWord) {
		Query query = new Query(searchWord);
		// �擾����c�C�[�g�ő吔
		query.setRpp(num);
		// �擾����y�[�W�ԍ�
		query.setPage(1);
		// ��������
		QueryResult queryResult = null;
		try {
			queryResult = twitter.search(query);
		} catch (TwitterException ex) {
			Logger.getLogger(TweetManager.class.getName()).log(Level.SEVERE,
					"Twitter search�Ɏ��s���܂���", ex);
			ex.printStackTrace();
		}

		List<Status> tweetList = new ArrayList<Status>();

		if (queryResult != null) {
			for (Tweet tweet : queryResult.getTweets()) {
				// �擾�ł���ő���̏���Ԃ�
				SimpleUser user = new SimpleUser();
				// ���[�U��
				user.setName(tweet.getFromUser());
				user.setScreenName(tweet.getFromUser());
				// ���[�UID
				user.setId(tweet.getFromUserId());
				try {
					// ���[�U�C���[�W
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

				// ���ǉ�
				tweetList.add(status);
			}
		}

		// ���X�g�t�] ��Ԃ����炵��tweet����ԉ���
		Collections.reverse(tweetList);

		return tweetList;
	}

	/**
	 * �w�肵�����[�U�̂��C�ɓ�����擾
	 * 
	 * @param screenName
	 *            null�̏ꍇ�C�������g�̂��C�ɓ�����擾
	 */
	public List<Status> getFavoritesTweet(String screenName)
			throws TwitterException {
		// getFriendsTimeline�ł�Retweet�͕\������Ȃ��̂Œ���
		List<Status> statuses = null;
		if (screenName == null) {
			// �������g�̂��C�ɓ���擾
			statuses = twitter.getFavorites();
		} else {
			statuses = twitter.getFavorites(screenName);
		}
		// ���X�g�t�] ��Ԃ����炵��tweet����ԉ���
		Collections.reverse(statuses);
		return statuses;
	}

	/**
	 * �w�肵�����[�U�̔������擾
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

		// tweet�t�]
		if (statuses != null && statuses.size() > 0) {
			Collections.reverse(statuses);
		}

		return statuses;
	}
	
	/**
	 * �w�肵�����[�U�̔������擾
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

		// tweet�t�]
		if (statuses != null && statuses.size() > 0) {
			Collections.reverse(statuses);
		}

		return statuses;
	}
	

	/**
	 * �w�肵�����[�U���ێ����Ă��郊�X�g�ꗗ���擾
	 * 
	 * @param userScreenName
	 * @return
	 */
	public List<UserList> getUserLists(String userScreenName) {
		List<UserList> userlist = new ArrayList<UserList>();
		long cursor = -1;
		try {
			for (;;) {
				// ���[�U���X�g�擾
				PagableResponseList<UserList> list = this.twitter.getUserLists(
						userScreenName, cursor);
				userlist.addAll(list);

				if (list.hasNext()) {
					// ���̃J�[�\�����擾
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
	 * �w�肵�����[�U���t�H���[���Ă��郊�X�g��Ԃ�
	 * 
	 * @param userScreenName
	 * @return
	 */
	public List<UserList> getUserListSubscriptions(String userScreenName) {
		List<UserList> userlist = new ArrayList<UserList>();
		long cursor = -1;
		try {
			for (;;) {
				// ���[�U���X�g�擾
				PagableResponseList<UserList> list = this.twitter
						.getUserListSubscriptions(userScreenName, cursor);
				userlist.addAll(list);

				if (list.hasNext()) {
					// ���̃J�[�\�����擾
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
	 * �w�肵�����[�U���ǉ�����Ă��郊�X�g��Ԃ�
	 * 
	 * @param userScreenName
	 * @return
	 */
	public List<UserList> getUserListMemberships(String userScreenName) {
		List<UserList> userlist = new ArrayList<UserList>();
		long cursor = -1;
		try {
			for (;;) {
				// ���[�U���X�g�擾
				PagableResponseList<UserList> list = this.twitter
						.getUserListMemberships(userScreenName, cursor);
				userlist.addAll(list);

				if (list.hasNext()) {
					// ���̃J�[�\�����擾
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
	 * �w�肵�����X�g�̃c�C�[�g���擾
	 * 
	 * @param userScreenName
	 *            ���[�U��
	 * @param listID
	 *            ���X�gID
	 * @param num
	 *            �擾�c�C�[�g��
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
		// tweet�t�]
		if (tweetList != null && tweetList.size() > 0) {
			Collections.reverse(tweetList);
		}
		return tweetList;
	}

	/**
	 * �w�肵�����X�g�̍ŐV�����擾
	 * 
	 * @param userScreenName
	 *            ���[�U��
	 * @param listID
	 *            ���X�gID
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
		// tweet�t�]
		if (tweetList != null && tweetList.size() > 0) {
			Collections.reverse(tweetList);
		}
		return tweetList;
	}

	/**
	 * �w�肵�����[�U�̍ŐV�̔������擾
	 * 
	 * @param userID
	 * @param sinceID
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getNewUserTimeline(long userID, long sinceID)
			throws TwitterException {
		// ��x���f�[�^���擾���Ă��Ȃ��Ƃ�
		if (sinceID == 0) {
			return getUserTimeline(MAX_TWEET_NUM, userID);
		}

		List<Status> statuses = twitter.getUserTimeline(userID, new Paging(
				sinceID));

		// tweet�t�]
		if (statuses != null && statuses.size() > 0) {
			Collections.reverse(statuses);
		}

		return statuses;
	}
	
	/**
	 * �w�肵�����[�U�̍ŐV�̔������擾
	 * 
	 * @param screenName
	 * @param sinceID
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getNewUserTimeline(String screenName, long sinceID)
			throws TwitterException {
		// ��x���f�[�^���擾���Ă��Ȃ��Ƃ�
		if (sinceID == 0) {
			return getUserTimeline(MAX_TWEET_NUM, screenName);
		}

		List<Status> statuses = twitter.getUserTimeline(screenName, new Paging(
				sinceID));

		// tweet�t�]
		if (statuses != null && statuses.size() > 0) {
			Collections.reverse(statuses);
		}

		return statuses;
	}

	/**
	 * Twitter�փ��O�C��
	 */
	public void loginTwitter() throws FileNotFoundException, IOException {
		twitter = new TwitterFactory().getInstance();
		// �A�J�E���g����ǂݍ���
		loadAccountProperties();
		// ConsumerKey�Ȃǂ�ݒ�
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		// �����Ƀ��[�U�̃A�N�Z�X�g�[�N��������
		AccessToken ac = new AccessToken(accessToken, secretToken);
		twitter.setOAuthAccessToken(ac);
	}

	/**
	 * Configuration�𐶐�����
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
	 * ���񎞃A�N�Z�X�g�[�N�����擾����ۂɗ��p����
	 * 
	 * @param username
	 * @param password
	 * @throws TwitterException
	 */
	public void getAccessToken(String username, String password)
			throws TwitterException {
		// consumer key secret��ݒ�
		Configuration conf = this.getTwitterConfiguration();
		twitter = new TwitterFactory(conf).getInstance();
		// access token�擾
		AccessToken oAuthAccessToken = twitter.getOAuthAccessToken(username,
				password);
		this.accessToken = oAuthAccessToken.getToken();
		this.secretToken = oAuthAccessToken.getTokenSecret();
	}

	/**
	 * ���b�Z�[�W���Ԃ₭
	 * 
	 * @param message
	 * @throws TwitterException
	 */
	public void tweet(String message) throws TwitterException {
		twitter4j.Status status;
		status = twitter.updateStatus(message);
	}

	/**
	 * �ԐM���b�Z�[�W���Ԃ₭
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
	 * �_�C���N�g���b�Z�[�W�𑗐M
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
	 * �w�肵���X�e�[�^�XID�̔�����Retweet
	 * 
	 * @param statusID
	 * @throws TwitterException
	 */
	public void retweet(long statusID) throws TwitterException {
		twitter.retweetStatus(statusID);
	}

	/**
	 * �w�肵�����������C�ɓ���ɒǉ�
	 * 
	 * @param statusID
	 * @throws TwitterException
	 */
	public void createFavorite(long statusID) throws TwitterException {
		twitter.createFavorite(statusID);
	}

	/**
	 * �w�肵�������̂��C�ɓ������艺����
	 * 
	 * @param statusID
	 */
	public void destroyFavorite(long statusID) throws TwitterException {
		twitter.destroyFavorite(statusID);
	}

	/**
	 * API���������擾
	 * 
	 * @return
	 * @throws TwitterException
	 */
	public RateLimitStatus getRateLimitStatus() throws TwitterException {
		return twitter.getRateLimitStatus();
	}

	/**
	 * �������g�̃X�N���[������Ԃ�
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
	 * �w�肵�����[�U��following���[�U�ꗗ�̏ڍ׏����擾
	 * @param screenName �擾���������[�U
	 * @param page �y�[�W1������100���̏����擾
	 * @return
	 */
	public List<User> getFollowingUser(String screenName, int page) {
		if( !this.followingUserIDList.containsKey(screenName) ) {
			//�܂�screenName��following���Ă��郆�[�U�����擾���Ă��Ȃ�
			List<Long> following = this.getFollowingUserID(screenName);
			this.followingUserIDList.put(screenName, following);
		}
		//following���[�U�ꗗid�擾
		List<Long> getFollowingUserIds = null;
		List<Long> followingList = this.followingUserIDList.get(screenName);
		if( followingList != null && followingList.size() > 0 ) {
			//�f�[�^���݂��Ȃ�
			if( followingList.size() < page * 100 ) {
				return null;
			}
			int to = page * 100 + 99;
			if( followingList.size() < to){
				to = to - (to - followingList.size());
			}
			getFollowingUserIds = followingList.subList(page * 100, to);
		}
		
		//����
		List<User> result = new ArrayList<User>();
		
		int getDataSize = getFollowingUserIds.size();
		if( getDataSize > 0 ) {
			long[] ids = new long[ getDataSize ];
			for( int i=0; i < ids.length; i++) {
				ids[i] = getFollowingUserIds.get( i );
			}
			
			//ids�̃��[�U�ꗗ�擾
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
	 * �w�肵�����[�U��follower���[�U�ꗗ�̏ڍ׏����擾
	 * @param screenName �擾���������[�U
	 * @param page �y�[�W1������100���̏����擾
	 * @return
	 */
	public List<User> getFollowerUser(String screenName, int page) {
		if( !this.followerUserIDList.containsKey(screenName) ) {
			//�܂�screenName��following���Ă��郆�[�U�����擾���Ă��Ȃ�
			List<Long> follower = this.getFollowerUserID(screenName);
			this.followerUserIDList.put(screenName, follower);
		}
		//following���[�U�ꗗid�擾
		List<Long> getFollowerUserIds = null;
		List<Long> followerList = this.followerUserIDList.get(screenName);
		if( followerList != null && followerList.size() > 0 ) {
			//�f�[�^���݂��Ȃ�
			if( followerList.size() < page * 100 ) {
				return null;
			}
			int to = page * 100 + 99;
			if( followerList.size() < to){
				to = to - (to - followerList.size());
			}
			getFollowerUserIds = followerList.subList(page * 100, to);
		}

		//����
		List<User> result = new ArrayList<User>();

		int getDataSize = getFollowerUserIds.size();
		if( getDataSize > 0 ) {
			long[] ids = new long[ getDataSize ];
			for( int i=0; i < ids.length; i++) {
				ids[i] = getFollowerUserIds.get( i );
			}

			//ids�̃��[�U�ꗗ�擾
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
	 * Friend��ID�ꗗ���擾����	
	 * @param screenName
	 * @return
	 */
	public List<Long> getFollowingUserID(String screenName) {
		long cursor = -1;
		IDs ids = null;
		long[] friendIds = null;
		//id�ꗗ
		List<Long> result = new ArrayList<Long>();
		
		try {
			//friend�ꗗ�����ׂĎ擾
			do {
				ids = twitter.getFriendsIDs( screenName, cursor );
				//screenName�̃t�����hid�ꗗ���擾
				friendIds = ids.getIDs();
				if( friendIds.length > 0 ) {
					for(long id : friendIds ) {
						result.add( id );
					}
				}
				//���̃J�[�\���Ɉړ�
				cursor = ids.getNextCursor();
			}while( cursor != 0 );
			
		}catch(TwitterException e) {
			e.printStackTrace();
		}
		return result;		
	}

        /**
	 * Friend��follewer ID�ꗗ���擾����
	 * @param screenName
	 * @return
	 */
	public List<Long> getFollowerUserID(String screenName) {
		long cursor = -1;
		IDs ids = null;
		long[] friendIds = null;
		//id�ꗗ
		List<Long> result = new ArrayList<Long>();

		try {
			//friend�ꗗ�����ׂĎ擾
			do {
				ids = twitter.getFollowersIDs( screenName, cursor );
				//screenName�̃t�����hid�ꗗ���擾
				friendIds = ids.getIDs();
				if( friendIds.length > 0 ) {
					for(long id : friendIds ) {
						result.add( id );
					}
				}
				//���̃J�[�\���Ɉړ�
				cursor = ids.getNextCursor();
			}while( cursor != 0 );

		}catch(TwitterException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
