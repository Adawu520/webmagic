package sotfcom.model.parse;

import sotfcom.model.model.softmodel;
import us.codecraft.webmagic.Page;

public class Parse {
    public static void getsoftdata(Page page, softmodel data){
        String id = page.getUrl().replace("\\D","").toString();
        String name = page.getHtml().xpath("//*[@id=jArticleInside]/div[1]/section/div[2]/h1/span/text()").get();
        String uservote = page.getHtml().xpath("//*[@id=jArticleInside]/div[1]/section/div[2]/div[1]/dl[1]/dd[1]/a/span[1]/text()").get();
        String vote = page.getHtml().xpath("//*[@id=jArticleInside]/div[1]/section/div[2]/div[1]/dl[1]/dd[1]/a/span[3]/text()").get();
        String developer = page.getHtml().xpath("//*[@id=jArticleInside]/div[1]/section/div[2]/div[1]/dl[2]/dd[1]/text()").get();
        String softversion = page.getHtml().xpath("//*[@id=jArticleInside]/div[1]/section/div[2]/div[1]/dl[2]/dd[2]/text()").get();
        String description = page.getHtml().xpath("//*[@id=download_ctn]/div/div/div[1]/div/p/text()").get();

        data.setId(id);
        data.setName(name);
        data.setUservote(uservote);
        data.setVote(vote);
        data.setDeveloper(developer);
        data.setSoftwareVersion(softversion);
        data.setDescription(description);
    }

}
