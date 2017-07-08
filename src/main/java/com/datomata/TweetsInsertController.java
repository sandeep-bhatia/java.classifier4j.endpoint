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
@RequestMapping("/tweetInsert")
public class TweetsInsertController {
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

    private List<Tweet> tweetTags(String message) {
        ArrayList<Tweet> response =  new ArrayList<Tweet>();
        try {
            if(twitter == null) initTwitter();
            Status status = twitter.updateStatus(message);
            String url= "https://twitter.com/" + status.getUser().getScreenName()
                    + "/status/" + status.getId();
            response.add(new Tweet(status.getId(), status.getText(), status.getUser().getName(), status.getUser().getProfileImageURL(), url));
        } catch(Exception ex) {
            response.add(new Tweet(-1, ex.getMessage(), "", "", ""));
        }

        return response;
    }

    @RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Tweet> tweetTag(@RequestParam(value="tweetMessage", required=false, defaultValue = "") String tweetMessage) {
        return tweetTags(tweetMessage);
    }
}
