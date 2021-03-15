package test;

import sotfcom.model.CCMProcessor.CCMProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class HFUTNewsProcesser implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        String url = page.getUrl().toString();
        String title = page.getHtml().xpath("//title[@*]/text()").get();
        page.putField("url", url);
        page.putField("title", title);
    }

    public static void main(String[] args) {
        String url = "https://www.ccm.net";
        Spider.create(new CCMProcessor())
                .addUrl(url)
                .addPipeline(new ConsolePipeline())
                .thread(5)
                .run();
    }
}

