package tutorial.lib.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author JiaweiMao 2017.04.06
 * @since 1.0-SNAPSHOT
 */
public class JsoupTest {

    @Test
    void parse() {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
    }

    @Test
    void connect() throws IOException {
        Document doc = Jsoup.connect("https://www.baidu.com").get();
        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            System.out.println(linkHref);
            String linkText = link.text();
        }
    }

    @Test
    void parseFile() throws IOException {
        File input = new File("/tmp/input.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

    }
}
