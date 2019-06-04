public class Tweet {
    private String Username;
    private String TweetText;
    private String DateSent;

    public Tweet() {}

    public Tweet(String username, String tttext, String date) {
        Username = username;
        TweetText = tttext;
        DateSent = date;
    }

    public void setUsername(String username) { this.Username = username; }

    public void setTweetText(String tweetText) { this.TweetText = tweetText; }

    public void setDateSent(String dateSent) { this.DateSent = dateSent; }

    public String getUsername() { return Username; }

    public String getDateSent() { return DateSent; }

    public String getTweetText() { return TweetText; }

    public String toString() { return "@" + this.Username +
            ": " + this.TweetText; }
}
