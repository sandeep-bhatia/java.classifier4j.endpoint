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
@RequestMapping("/article")
public class ArticleDetailsController {

    @RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Summary> healthCheck(@RequestParam(value="tenantId", required=true, defaultValue="") String tenantId, @RequestParam(value="articleId", required=true, defaultValue="") String articleId) {
        if(tenantId == "" || articleId == "") {
            return new ArrayList<Summary>();
        }
        return getSummaryForText(articleId, tenantId);
    }

    private String getTableName(String tenantId) {
        if(tenantId == "abfb7d5ca26b4db29c2837c331838c51") {
            return "V2.ArticleContent";
        }

        return "V2.ArticleContent";
    }

    private List<Summary> getSummaryForText(String articleId, String tenantId) {
        ArrayList<Summary> retList = new ArrayList<>();
        String tableName = getTableName(tenantId);
        String result = "";
        
        if(tableName.length() > 0) {
            result = DynamoDBWrapper.getInstance().readArticleDetails(tableName, tenantId, articleId);
        }

        retList.add(new Summary(result));
        return retList;
    }

    private Summary getSummaryBasedOnSimpleImportance(String text, int length) {
        SimpleSummariser s = new SimpleSummariser();
        String summary = s.summarise(text, length);
        Summary entity = new Summary(summary);
        return entity;
    }
}
