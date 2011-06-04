package net.densanlabs;

import java.util.Arrays;
import java.util.Date;

import twitter4j.Annotations;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.Place;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

public class SimpleStatus implements Status {

    private String[] contributors = null;
    private Date createdAt = null;
    private GeoLocation geoLocation = null;
    private long id = 0;
    private String inReplyToScreenName = null;
    private long inReplyToStatusId = 0;
    private long inReplyToUserId = 0;
    private Place place = null;
    private Status retweetedStatus = null;
    private String source = null;
    private String text = null;
    private User user = null;
    private boolean favorited = false;
    private boolean retweet = false;
    private boolean truncated = false;

    /**
     * contributorsを取得します。
     *
     * @return contributors
     */
    public String[] getContributors() {
        return contributors;
    }

    /**
     * createdAtを取得します。
     *
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * geoLocationを取得します。
     *
     * @return geoLocation
     */
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    /**
     * idを取得します。
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * inReplyToScreenNameを取得します。
     *
     * @return inReplyToScreenName
     */
    public String getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    /**
     * inReplyToStatusIdを取得します。
     *
     * @return inReplyToStatusId
     */
    public long getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    /**
     * inReplyToUserIdを取得します。
     *
     * @return inReplyToUserId
     */
    public long getInReplyToUserId() {
        return inReplyToUserId;
    }

    /**
     * placeを取得します。
     *
     * @return place
     */
    public Place getPlace() {
        return place;
    }

    @Override
    public RateLimitStatus getRateLimitStatus() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /**
     * retweetedStatusを取得します。
     *
     * @return retweetedStatus
     */
    public Status getRetweetedStatus() {
        return retweetedStatus;
    }

    /**
     * sourceを取得します。
     *
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * textを取得します。
     *
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * userを取得します。
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * favoritedを取得します。
     *
     * @return favorited
     */
    public boolean isFavorited() {
        return favorited;
    }

    /**
     * retweetを取得します。
     *
     * @return retweet
     */
    public boolean isRetweet() {
        return retweet;
    }

    /**
     * truncatedを取得します。
     *
     * @return truncated
     */
    public boolean isTruncated() {
        return truncated;
    }

    /**
     * contributorsを設定します。
     *
     * @param contributors
     *            contributors
     */
    public void setContributors(String[] contributors) {
        this.contributors = contributors;
    }

    /**
     * createdAtを設定します。
     *
     * @param createdAt
     *            createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * favoritedを設定します。
     *
     * @param favorited
     *            favorited
     */
    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    /**
     * geoLocationを設定します。
     *
     * @param geoLocation
     *            geoLocation
     */
    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * idを設定します。
     *
     * @param id
     *            id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * inReplyToScreenNameを設定します。
     *
     * @param inReplyToScreenName
     *            inReplyToScreenName
     */
    public void setInReplyToScreenName(String inReplyToScreenName) {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    /**
     * inReplyToStatusIdを設定します。
     *
     * @param inReplyToStatusId
     *            inReplyToStatusId
     */
    public void setInReplyToStatusId(long inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    /**
     * inReplyToUserIdを設定します。
     *
     * @param l
     *            inReplyToUserId
     */
    public void setInReplyToUserId(long l) {
        this.inReplyToUserId = l;
    }

    /**
     * placeを設定します。
     *
     * @param place
     *            place
     */
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "SimpleStatus [contributors=" + Arrays.toString(contributors)
                + ", createdAt=" + createdAt + ", favorited=" + favorited
                + ", geoLocation=" + geoLocation + ", id=" + id
                + ", inReplyToScreenName=" + inReplyToScreenName
                + ", inReplyToStatusId=" + inReplyToStatusId
                + ", inReplyToUserId=" + inReplyToUserId + ", place=" + place
                + ", retweet=" + retweet + ", retweetedStatus="
                + retweetedStatus + ", source=" + source + ", text=" + text
                + ", truncated=" + truncated + ", user=" + user + "]";
    }

    /**
     * retweetを設定します。
     *
     * @param retweet
     *            retweet
     */
    public void setRetweet(boolean retweet) {
        this.retweet = retweet;
    }

    /**
     * retweetedStatusを設定します。
     *
     * @param retweetedStatus
     *            retweetedStatus
     */
    public void setRetweetedStatus(Status retweetedStatus) {
        this.retweetedStatus = retweetedStatus;
    }

    /**
     * sourceを設定します。
     *
     * @param source
     *            source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * textを設定します。
     *
     * @param text
     *            text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * truncatedを設定します。
     *
     * @param truncated
     *            truncated
     */
    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    /**
     * userを設定します。
     *
     * @param user
     *            user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public int compareTo(Status t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
	public Annotations getAnnotations() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public HashtagEntity[] getHashtagEntities() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public long getRetweetCount() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public URLEntity[] getURLEntities() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public UserMentionEntity[] getUserMentionEntities() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean isRetweetedByMe() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
