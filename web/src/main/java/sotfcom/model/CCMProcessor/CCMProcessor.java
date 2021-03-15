package sotfcom.model.CCMProcessor;

import com.db.MysqlControl;
import com.model.SoftwareModle;
import sotfcom.model.db.MySQLControl;
import sotfcom.model.model.softmodel;
import sotfcom.model.parse.Parse;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class CCMProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setRetrySleepTime(100);
    public Site getSite() {
        return site;
    }


    public void process(Page page) {
        if (page.getUrl().regex("https://ccm.net/download/*").match()){
            List<String> urls = page.getHtml().xpath("//h2").links().all();
            page.addTargetRequests(urls);
        }
        if (page.getUrl().regex("https://ccm.net/download/download-.").match()){
            softmodel data = new softmodel();
            Parse.getsoftdata(page,data);
            MySQLControl.insertdata(data);
        }
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new CCMProcessor());
        spider.addUrl("https://ccm.net/download/");
        for (int i = 2; i < 10; i++) {
            spider.addUrl("https://ccm.net/download/?page="+i);
        }
        spider.thread(5)
                .run();

    }

}