package com.datomata;
import classifier4J.summariser.SimpleSummariser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/summarize")
public class SummarizeController {

    @RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Summary> healthCheck(@RequestParam(value="text", required=false, defaultValue="") String text, @RequestParam(value="count", required=false, defaultValue="25") String count) {
        if(text == null || text.length() < 1) {
            return new ArrayList<Summary>();
        }

        int length = 25;

        try {
            length = Integer.parseInt(count);
        } catch (Exception e) {
            return Arrays.asList(new Summary(e.getMessage()));
        }
        return getSummaryForText(text, length);
    }

    private List<Summary> getSummaryForText(String text, int length) {
        ArrayList<Summary> retList = new ArrayList<>();
        retList.add(getSummaryBasedOnSimpleImportance(text, length));
        return retList;
    }

    private Summary getSummaryBasedOnSimpleImportance(String text, int length) {
        SimpleSummariser s = new SimpleSummariser();
        String summary = s.summarise(text, length);
        Summary entity = new Summary(summary);
        return entity;
    }
}
