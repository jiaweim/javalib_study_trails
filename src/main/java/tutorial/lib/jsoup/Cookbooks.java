package tutorial.lib.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class Cookbooks
{

    private static void print(String msg, Object... args)
    {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width)
    {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }

    @Test
    @Disabled
    public void sample() throws IOException
    {
        String url = "https://news.ycombinator.com/";
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("abs:src"), src.attr("width"),
                        src.attr("height"), trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }

    /**
     * Documents consist of Elements and TextNodes
     */
    @Test
    public void parseDoc()
    {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
    }

    /**
     * Parse part of a document
     */
    @Test
    public void parseBody()
    {
        String html = "<div><p>Lorem ipsum.</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
    }

    @Test
    public void parseHtml() throws IOException
    {
        Document doc = Jsoup.connect("http://www.monosaccharidedb.org/display_monosaccharide.action?id=776")
                .timeout(8000).get();
        Element tbody = doc.getElementById("ms_main").child(0).child(0);
        for (int k = 1; k <= 5; k++) {
            System.out.println(tbody.child(k).child(1).text());

        }
    }

}
