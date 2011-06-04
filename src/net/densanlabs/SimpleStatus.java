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
     * contributors���擾���܂��B
     *
     * @return contributors
     */
    public String[] getContributors() {
        return contributors;
    }

    /**
     * createdAt���擾���܂��B
     *
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * geoLocation���擾���܂��B
     *
     * @return geoLocation
     */
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    /**
     * id���擾���܂��B
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * inReplyToScreenName���擾���܂��B
     *
     * @return inReplyToScreenName
     */
    public String getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    /**
     * inReplyToStatusId���擾���܂��B
     *
     * @return inReplyToStatusId
     */
    public long getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    /**
     * inReplyToUserId���擾���܂��B
     *
     * @return inReplyToUserId
     */
    public long getInReplyToUserId() {
        return inReplyToUserId;
    }

    /**
     * place���擾���܂��B
     *
     * @return place
     */
    public Place getPlace() {
        return place;
    }

    @Override
    public RateLimitStatus getRateLimitStatus() {
        // TODO �����������ꂽ���\�b�h�E�X�^�u
        return null;
    }

    /**
     * retweetedStatus���擾���܂��B
     *
     * @return retweetedStatus
     */
    public Status getRetweetedStatus() {
        return retweetedStatus;
    }

    /**
     * source���擾���܂��B
     *
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * text���擾���܂��B
     *
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * user���擾���܂��B
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * favorited���擾���܂��B
     *
     * @return favorited
     */
    public boolean isFavorited() {
        return favorited;
    }

    /**
     * retweet���擾���܂��B
     *
     * @return retweet
     */
    public boolean isRetweet() {
        return retweet;
    }

    /**
     * truncated���擾���܂��B
     *
     * @return truncated
     */
    public boolean isTruncated() {
        return truncated;
    }

    /**
     * contributors��ݒ肵�܂��B
     *
     * @param contributors
     *            contributors
     */
    public void setContributors(String[] contributors) {
        this.contributors = contributors;
    }

    /**
     * createdAt��ݒ肵�܂��B
     *
     * @param createdAt
     *            createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * favorited��ݒ肵�܂��B
     *
     * @param favorited
     *            favorited
     */
    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    /**
     * geoLocation��ݒ肵�܂��B
     *
     * @param geoLocation
     *            geoLocation
     */
    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * id��ݒ肵�܂��B
     *
     * @param id
     *            id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * inReplyToScreenName��ݒ肵�܂��B
     *
     * @param inReplyToScreenName
     *            inReplyToScreenName
     */
    public void setInReplyToScreenName(String inReplyToScreenName) {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    /**
     * inReplyToStatusId��ݒ肵�܂��B
     *
     * @param inReplyToStatusId
     *            inReplyToStatusId
     */
    public void setInReplyToStatusId(long inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    /**
     * inReplyToUserId��ݒ肵�܂��B
     *
     * @param l
     *            inReplyToUserId
     */
    public void setInReplyToUserId(long l) {
        this.inReplyToUserId = l;
    }

    /**
     * place��ݒ肵�܂��B
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
     * retweet��ݒ肵�܂��B
     *
     * @param retweet
     *            retweet
     */
    public void setRetweet(boolean retweet) {
        this.retweet = retweet;
    }

    /**
     * retweetedStatus��ݒ肵�܂��B
     *
     * @param retweetedStatus
     *            retweetedStatus
     */
    public void setRetweetedStatus(Status retweetedStatus) {
        this.retweetedStatus = retweetedStatus;
    }

    /**
     * source��ݒ肵�܂��B
     *
     * @param source
     *            source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * text��ݒ肵�܂��B
     *
     * @param text
     *            text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * truncated��ݒ肵�܂��B
     *
     * @param truncated
     *            truncated
     */
    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    /**
     * user��ݒ肵�܂��B
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	@Override
	public HashtagEntity[] getHashtagEntities() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	@Override
	public long getRetweetCount() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	@Override
	public URLEntity[] getURLEntities() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	@Override
	public UserMentionEntity[] getUserMentionEntities() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	@Override
	public boolean isRetweetedByMe() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}
}
