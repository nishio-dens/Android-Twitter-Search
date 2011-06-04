package net.densanlabs;

import java.net.URL;
import java.util.Date;

import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.User;

/**
 * ユーザ情報
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
     * descriptionを取得します。
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * favouritesCountを取得します。
     *
     * @return favouritesCount
     */
    public int getFavouritesCount() {
        return favouritesCount;
    }

    /**
     * followersCountを取得します。
     *
     * @return followersCount
     */
    public int getFollowersCount() {
        return followersCount;
    }

    /**
     * friendsCountを取得します。
     *
     * @return friendsCount
     */
    public int getFriendsCount() {
        return friendsCount;
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
     * langを取得します。
     *
     * @return lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * locationを取得します。
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * nameを取得します。
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * profileBackgroundColorを取得します。
     *
     * @return profileBackgroundColor
     */
    public String getProfileBackgroundColor() {
        return profileBackgroundColor;
    }

    /**
     * profileBackgroundImageURLを取得します。
     *
     * @return profileBackgroundImageURL
     */
    public String getProfileBackgroundImageUrl() {
        return profileBackgroundImageUrl;
    }

    /**
     * profileImageURLを取得します。
     *
     * @return profileImageURL
     */
    public URL getProfileImageURL() {
        return profileImageURL;
    }

    /**
     * profileLinkColorを取得します。
     *
     * @return profileLinkColor
     */
    public String getProfileLinkColor() {
        return profileLinkColor;
    }

    /**
     * profileSidebarBorderColorを取得します。
     *
     * @return profileSidebarBorderColor
     */
    public String getProfileSidebarBorderColor() {
        return profileSidebarBorderColor;
    }

    /**
     * profileSidebarFillColorを取得します。
     *
     * @return profileSidebarFillColor
     */
    public String getProfileSidebarFillColor() {
        return profileSidebarFillColor;
    }

    /**
     * profileTextColorを取得します。
     *
     * @return profileTextColor
     */
    public String getProfileTextColor() {
        return profileTextColor;
    }

    @Override
    public RateLimitStatus getRateLimitStatus() {
        // TODO: いつか実装
        return null;
    }

    /**
     * screenNameを取得します。
     *
     * @return screenName
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * statusを取得します。
     *
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * statusCreatedAtを取得します。
     *
     * @return statusCreatedAt
     */
    public Date getStatusCreatedAt() {
        return statusCreatedAt;
    }

    /**
     * statusesCountを取得します。
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
     * timeZoneを取得します。
     *
     * @return timeZone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * URLを取得します。
     *
     * @return URL
     */
    public URL getURL() {
        return URL;
    }

    /**
     * utcOffsetを取得します。
     *
     * @return utcOffset
     */
    public int getUtcOffset() {
        return utcOffset;
    }

    /**
     * isContributorsEnabledを取得します。
     *
     * @return isContributorsEnabled
     */
    public boolean isContributorsEnabled() {
        return isContributorsEnabled;
    }

    /**
     * isFollowingを取得します。
     *
     * @return isFollowing
     */
    public boolean isFollowing() {
        return isFollowing;
    }

    /**
     * isGeoEnabledを取得します。
     *
     * @return isGeoEnabled
     */
    public boolean isGeoEnabled() {
        return isGeoEnabled;
    }

    /**
     * profileBackgroundTiledを取得します。
     *
     * @return profileBackgroundTiled
     */
    public boolean isProfileBackgroundTiled() {
        return profileBackgroundTiled;
    }

    /**
     * isProtectedを取得します。
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
     * isVerifiedを取得します。
     *
     * @return isVerified
     */
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * isContributorsEnabledを設定します。
     *
     * @param isContributorsEnabled
     *            isContributorsEnabled
     */
    public void setContributorsEnabled(boolean isContributorsEnabled) {
        this.isContributorsEnabled = isContributorsEnabled;
    }

    /**
     * descriptionを設定します。
     *
     * @param description
     *            description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * favouritesCountを設定します。
     *
     * @param favouritesCount
     *            favouritesCount
     */
    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    /**
     * followersCountを設定します。
     *
     * @param followersCount
     *            followersCount
     */
    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    /**
     * isFollowingを設定します。
     *
     * @param isFollowing
     *            isFollowing
     */
    public void setFollowing(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    /**
     * friendsCountを設定します。
     *
     * @param friendsCount
     *            friendsCount
     */
    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    /**
     * isGeoEnabledを設定します。
     *
     * @param isGeoEnabled
     *            isGeoEnabled
     */
    public void setGeoEnabled(boolean isGeoEnabled) {
        this.isGeoEnabled = isGeoEnabled;
    }

    /**
     * idを設定します。
     *
     * @param l
     *            id
     */
    public void setId(long l) {
        this.id = l;
    }

    /**
     * langを設定します。
     *
     * @param lang
     *            lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * locationを設定します。
     *
     * @param location
     *            location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * nameを設定します。
     *
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * profileBackgroundColorを設定します。
     *
     * @param profileBackgroundColor
     *            profileBackgroundColor
     */
    public void setProfileBackgroundColor(String profileBackgroundColor) {
        this.profileBackgroundColor = profileBackgroundColor;
    }

    /**
     * profileBackgroundImageURLを設定します。
     *
     * @param profileBackgroundImageURL
     *            profileBackgroundImageURL
     */
    public void setProfileBackgroundImageUrl(String profileBackgroundImageURL) {
        this.profileBackgroundImageUrl = profileBackgroundImageURL;
    }

    /**
     * profileBackgroundTiledを設定します。
     *
     * @param profileBackgroundTiled
     *            profileBackgroundTiled
     */
    public void setProfileBackgroundTiled(boolean profileBackgroundTiled) {
        this.profileBackgroundTiled = profileBackgroundTiled;
    }

    /**
     * profileImageURLを設定します。
     *
     * @param profileImageURL
     *            profileImageURL
     */
    public void setProfileImageURL(URL profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    /**
     * profileLinkColorを設定します。
     *
     * @param profileLinkColor
     *            profileLinkColor
     */
    public void setProfileLinkColor(String profileLinkColor) {
        this.profileLinkColor = profileLinkColor;
    }

    /**
     * profileSidebarBorderColorを設定します。
     *
     * @param profileSidebarBorderColor
     *            profileSidebarBorderColor
     */
    public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
        this.profileSidebarBorderColor = profileSidebarBorderColor;
    }

    /**
     * profileSidebarFillColorを設定します。
     *
     * @param profileSidebarFillColor
     *            profileSidebarFillColor
     */
    public void setProfileSidebarFillColor(String profileSidebarFillColor) {
        this.profileSidebarFillColor = profileSidebarFillColor;
    }

    /**
     * profileTextColorを設定します。
     *
     * @param profileTextColor
     *            profileTextColor
     */
    public void setProfileTextColor(String profileTextColor) {
        this.profileTextColor = profileTextColor;
    }

    /**
     * isProtectedを設定します。
     *
     * @param isProtected
     *            isProtected
     */
    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    /**
     * screenNameを設定します。
     *
     * @param screenName
     *            screenName
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * statusを設定します。
     *
     * @param status
     *            status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * statusCreatedAtを設定します。
     *
     * @param statusCreatedAt
     *            statusCreatedAt
     */
    public void setStatusCreatedAt(Date statusCreatedAt) {
        this.statusCreatedAt = statusCreatedAt;
    }

    /**
     * statusesCountを設定します。
     *
     * @param statusesCount
     *            statusesCount
     */
    public void setStatusesCount(int statusesCount) {
        this.statusesCount = statusesCount;
    }

    /**
     * timeZoneを設定します。
     *
     * @param timeZone
     *            timeZone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * URLを設定します。
     *
     * @param URL
     *            URL
     */
    public void setURL(URL URL) {
        this.URL = URL;
    }

    /**
     * utcOffsetを設定します。
     *
     * @param utcOffset
     *            utcOffset
     */
    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    /**
     * isVerifiedを設定します。
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
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean isFollowRequestSent() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean isProfileUseBackgroundImage() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean isShowAllInlineMedia() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean isTranslator() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
