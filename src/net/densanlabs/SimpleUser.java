package net.densanlabs;

import java.net.URL;
import java.util.Date;

import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.User;

/**
 * ���[�U���
 * 
 * @author nishio
 * 
 */
public class SimpleUser implements User {

    private long id;
    private String name;
    private String screenName;
    private String location;
    private String description;
    private boolean isContributorsEnabled;
    private URL profileImageURL;
    private URL URL;
    private boolean isProtected;
    private int followersCount;
    private Status status;
    private String profileBackgroundColor;
    private String profileTextColor;
    private String profileLinkColor;
    private String profileSidebarFillColor;
    private String profileSidebarBorderColor;
    private int friendsCount;
    private Date statusCreatedAt;
    private int favouritesCount;
    private int utcOffset;
    private String timeZone;
    private String profileBackgroundImageUrl;
    private boolean profileBackgroundTiled;
    private String lang;
    private int statusesCount;
    private boolean isGeoEnabled;
    private boolean isVerified;
    private boolean isFollowing;

    @Override
    public Date getCreatedAt() {
        return status.getCreatedAt();
    }

    /**
     * description���擾���܂��B
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * favouritesCount���擾���܂��B
     *
     * @return favouritesCount
     */
    public int getFavouritesCount() {
        return favouritesCount;
    }

    /**
     * followersCount���擾���܂��B
     *
     * @return followersCount
     */
    public int getFollowersCount() {
        return followersCount;
    }

    /**
     * friendsCount���擾���܂��B
     *
     * @return friendsCount
     */
    public int getFriendsCount() {
        return friendsCount;
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
     * lang���擾���܂��B
     *
     * @return lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * location���擾���܂��B
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * name���擾���܂��B
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * profileBackgroundColor���擾���܂��B
     *
     * @return profileBackgroundColor
     */
    public String getProfileBackgroundColor() {
        return profileBackgroundColor;
    }

    /**
     * profileBackgroundImageURL���擾���܂��B
     *
     * @return profileBackgroundImageURL
     */
    public String getProfileBackgroundImageUrl() {
        return profileBackgroundImageUrl;
    }

    /**
     * profileImageURL���擾���܂��B
     *
     * @return profileImageURL
     */
    public URL getProfileImageURL() {
        return profileImageURL;
    }

    /**
     * profileLinkColor���擾���܂��B
     *
     * @return profileLinkColor
     */
    public String getProfileLinkColor() {
        return profileLinkColor;
    }

    /**
     * profileSidebarBorderColor���擾���܂��B
     *
     * @return profileSidebarBorderColor
     */
    public String getProfileSidebarBorderColor() {
        return profileSidebarBorderColor;
    }

    /**
     * profileSidebarFillColor���擾���܂��B
     *
     * @return profileSidebarFillColor
     */
    public String getProfileSidebarFillColor() {
        return profileSidebarFillColor;
    }

    /**
     * profileTextColor���擾���܂��B
     *
     * @return profileTextColor
     */
    public String getProfileTextColor() {
        return profileTextColor;
    }

    @Override
    public RateLimitStatus getRateLimitStatus() {
        // TODO: ��������
        return null;
    }

    /**
     * screenName���擾���܂��B
     *
     * @return screenName
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * status���擾���܂��B
     *
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * statusCreatedAt���擾���܂��B
     *
     * @return statusCreatedAt
     */
    public Date getStatusCreatedAt() {
        return statusCreatedAt;
    }

    /**
     * statusesCount���擾���܂��B
     *
     * @return statusesCount
     */
    public int getStatusesCount() {
        return statusesCount;
    }

    public long getStatusId() {
        return status.getId();
    }

    public String getStatusInReplyToScreenName() {
        return status.getInReplyToScreenName();
    }

    public long getStatusInReplyToStatusId() {
        return status.getInReplyToStatusId();
    }

    public long getStatusInReplyToUserId() {
        return status.getInReplyToUserId();
    }

    public String getStatusSource() {
        return status.getSource();
    }

    public String getStatusText() {
        return status.getText();
    }

    /**
     * timeZone���擾���܂��B
     *
     * @return timeZone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * URL���擾���܂��B
     *
     * @return URL
     */
    public URL getURL() {
        return URL;
    }

    /**
     * utcOffset���擾���܂��B
     *
     * @return utcOffset
     */
    public int getUtcOffset() {
        return utcOffset;
    }

    /**
     * isContributorsEnabled���擾���܂��B
     *
     * @return isContributorsEnabled
     */
    public boolean isContributorsEnabled() {
        return isContributorsEnabled;
    }

    /**
     * isFollowing���擾���܂��B
     *
     * @return isFollowing
     */
    public boolean isFollowing() {
        return isFollowing;
    }

    /**
     * isGeoEnabled���擾���܂��B
     *
     * @return isGeoEnabled
     */
    public boolean isGeoEnabled() {
        return isGeoEnabled;
    }

    /**
     * profileBackgroundTiled���擾���܂��B
     *
     * @return profileBackgroundTiled
     */
    public boolean isProfileBackgroundTiled() {
        return profileBackgroundTiled;
    }

    /**
     * isProtected���擾���܂��B
     *
     * @return isProtected
     */
    public boolean isProtected() {
        return isProtected;
    }

    public boolean isStatusFavorited() {
        return status.isFavorited();
    }

    public boolean isStatusTruncated() {
        return status.isTruncated();
    }

    /**
     * isVerified���擾���܂��B
     *
     * @return isVerified
     */
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * isContributorsEnabled��ݒ肵�܂��B
     *
     * @param isContributorsEnabled
     *            isContributorsEnabled
     */
    public void setContributorsEnabled(boolean isContributorsEnabled) {
        this.isContributorsEnabled = isContributorsEnabled;
    }

    /**
     * description��ݒ肵�܂��B
     *
     * @param description
     *            description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * favouritesCount��ݒ肵�܂��B
     *
     * @param favouritesCount
     *            favouritesCount
     */
    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    /**
     * followersCount��ݒ肵�܂��B
     *
     * @param followersCount
     *            followersCount
     */
    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    /**
     * isFollowing��ݒ肵�܂��B
     *
     * @param isFollowing
     *            isFollowing
     */
    public void setFollowing(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    /**
     * friendsCount��ݒ肵�܂��B
     *
     * @param friendsCount
     *            friendsCount
     */
    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    /**
     * isGeoEnabled��ݒ肵�܂��B
     *
     * @param isGeoEnabled
     *            isGeoEnabled
     */
    public void setGeoEnabled(boolean isGeoEnabled) {
        this.isGeoEnabled = isGeoEnabled;
    }

    /**
     * id��ݒ肵�܂��B
     *
     * @param l
     *            id
     */
    public void setId(long l) {
        this.id = l;
    }

    /**
     * lang��ݒ肵�܂��B
     *
     * @param lang
     *            lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * location��ݒ肵�܂��B
     *
     * @param location
     *            location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * name��ݒ肵�܂��B
     *
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * profileBackgroundColor��ݒ肵�܂��B
     *
     * @param profileBackgroundColor
     *            profileBackgroundColor
     */
    public void setProfileBackgroundColor(String profileBackgroundColor) {
        this.profileBackgroundColor = profileBackgroundColor;
    }

    /**
     * profileBackgroundImageURL��ݒ肵�܂��B
     *
     * @param profileBackgroundImageURL
     *            profileBackgroundImageURL
     */
    public void setProfileBackgroundImageUrl(String profileBackgroundImageURL) {
        this.profileBackgroundImageUrl = profileBackgroundImageURL;
    }

    /**
     * profileBackgroundTiled��ݒ肵�܂��B
     *
     * @param profileBackgroundTiled
     *            profileBackgroundTiled
     */
    public void setProfileBackgroundTiled(boolean profileBackgroundTiled) {
        this.profileBackgroundTiled = profileBackgroundTiled;
    }

    /**
     * profileImageURL��ݒ肵�܂��B
     *
     * @param profileImageURL
     *            profileImageURL
     */
    public void setProfileImageURL(URL profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    /**
     * profileLinkColor��ݒ肵�܂��B
     *
     * @param profileLinkColor
     *            profileLinkColor
     */
    public void setProfileLinkColor(String profileLinkColor) {
        this.profileLinkColor = profileLinkColor;
    }

    /**
     * profileSidebarBorderColor��ݒ肵�܂��B
     *
     * @param profileSidebarBorderColor
     *            profileSidebarBorderColor
     */
    public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
        this.profileSidebarBorderColor = profileSidebarBorderColor;
    }

    /**
     * profileSidebarFillColor��ݒ肵�܂��B
     *
     * @param profileSidebarFillColor
     *            profileSidebarFillColor
     */
    public void setProfileSidebarFillColor(String profileSidebarFillColor) {
        this.profileSidebarFillColor = profileSidebarFillColor;
    }

    /**
     * profileTextColor��ݒ肵�܂��B
     *
     * @param profileTextColor
     *            profileTextColor
     */
    public void setProfileTextColor(String profileTextColor) {
        this.profileTextColor = profileTextColor;
    }

    /**
     * isProtected��ݒ肵�܂��B
     *
     * @param isProtected
     *            isProtected
     */
    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    /**
     * screenName��ݒ肵�܂��B
     *
     * @param screenName
     *            screenName
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * status��ݒ肵�܂��B
     *
     * @param status
     *            status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * statusCreatedAt��ݒ肵�܂��B
     *
     * @param statusCreatedAt
     *            statusCreatedAt
     */
    public void setStatusCreatedAt(Date statusCreatedAt) {
        this.statusCreatedAt = statusCreatedAt;
    }

    /**
     * statusesCount��ݒ肵�܂��B
     *
     * @param statusesCount
     *            statusesCount
     */
    public void setStatusesCount(int statusesCount) {
        this.statusesCount = statusesCount;
    }

    /**
     * timeZone��ݒ肵�܂��B
     *
     * @param timeZone
     *            timeZone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * URL��ݒ肵�܂��B
     *
     * @param URL
     *            URL
     */
    public void setURL(URL URL) {
        this.URL = URL;
    }

    /**
     * utcOffset��ݒ肵�܂��B
     *
     * @param utcOffset
     *            utcOffset
     */
    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    /**
     * isVerified��ݒ肵�܂��B
     *
     * @param isVerified
     *            isVerified
     */
    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "SimpleUser [URL=" + URL + ", description=" + description
                + ", favouritesCount=" + favouritesCount + ", followersCount="
                + followersCount + ", friendsCount=" + friendsCount + ", id="
                + id + ", isContributorsEnabled=" + isContributorsEnabled
                + ", isFollowing=" + isFollowing + ", isGeoEnabled="
                + isGeoEnabled + ", isProtected=" + isProtected
                + ", isVerified=" + isVerified + ", lang=" + lang
                + ", location=" + location + ", name=" + name
                + ", profileBackgroundColor=" + profileBackgroundColor
                + ", profileBackgroundImageUrl=" + profileBackgroundImageUrl
                + ", profileBackgroundTiled=" + profileBackgroundTiled
                + ", profileImageURL=" + profileImageURL
                + ", profileLinkColor=" + profileLinkColor
                + ", profileSidebarBorderColor=" + profileSidebarBorderColor
                + ", profileSidebarFillColor=" + profileSidebarFillColor
                + ", profileTextColor=" + profileTextColor + ", screenName="
                + screenName + ", status=" + status + ", statusCreatedAt="
                + statusCreatedAt + ", statusesCount=" + statusesCount
                + ", timeZone=" + timeZone + ", utcOffset=" + utcOffset + "]";
    }

    public int compareTo(User t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
	public int getListedCount() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	@Override
	public boolean isFollowRequestSent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	@Override
	public boolean isProfileUseBackgroundImage() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	@Override
	public boolean isShowAllInlineMedia() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	@Override
	public boolean isTranslator() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}
}
