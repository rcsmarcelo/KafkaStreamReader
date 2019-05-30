public class Tweet {
    private String Username;
    private String TweetText;
    private String DateSent;

    public Tweet(String username, String tttext, String date) {
        Username = username;
        TweetText = tttext;
        DateSent = date;
    }

    public String getUsername() {
        return Username;
    }

    public String getDateSent() {
        return DateSent;
    }

    public String getTweetText() {
        return TweetText;
    }
}
