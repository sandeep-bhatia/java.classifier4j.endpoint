package com.datomata;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tweets")
public class TweetsController {
    private static String consumerKey = "gVihK6FWXCLkXmjTtVQfvDIAf";
    private static String consumerSecret = "LFNIWbszAEOJd3UcI9pzGThdxjOiZQlHfXf4Uiz1vhs7F1Ejj4";
    private static String accessKey = "4707155724-dLTTKGaxGSzRd7CIRcjFIEYKStVjZW8Uj9gVpDi";
    private static String accessSecret = "eAqNqHay1GkJvnn7VJ7LTDqRJMrTMRfJ7uwfS7WhIUDC5";
    private static Twitter twitter;
    private static TwitterFactory tf;

    private static void initTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessKey)
                .setOAuthAccessTokenSecret(accessSecret);
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    private String tweetTags(String message) {
        try {
            Status status = twitter.updateStatus(message);
            return status.getText();
        } catch(Exception ex) {
            return ex.getMessage();
        }
    }

    @RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Tweet> healthCheck(@RequestParam(value="tags", required=false, defaultValue="") String tags) {
        return getTweetsForTags(tags);
    }

    private static ArrayList<Tweet> getTweetsForTags(String tags) {
        String[] tagArray = tags.split(",");
        System.out.println("tag detection initiated");

        if(tagArray != null && tagArray.length > 0) {
            System.out.println("found some tags, initiating the tweet retrieval");
            return getTweets(tagArray);
        }

        return null;
    }

    private static ArrayList<Tweet> getTweets (String[] tags) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        try {
            if(twitter == null) {
                initTwitter();
            }

            for(int index = 0; index < tags.length; index++) {
                Query query = new Query(tags[index]);
                QueryResult result = twitter.search(query);
                for (Status status : result.getTweets()) {
                    String url= "https://twitter.com/" + status.getUser().getScreenName()
                            + "/status/" + status.getId();

                    Tweet tweet = new Tweet(status.getId(), status.getText(), status.getUser().getName(), status.getUser().getProfileImageURL(), url);

                    tweets.add(tweet);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return tweets;
    }


}
