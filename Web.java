import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Web {
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    // Fake USER_AGENT so the web server thinks the robot is a normal web browser, picked from http://www.netinstructions.com
    private List<String> links = new LinkedList<>();
    private Document htmlDocument;


    /**
     * Makes HTTP request, checks the response, and gathers up all the links on the page.
     * Finally performs searchword for the given word to the robot
     *
     * @param url - The URL to visit
     * @return whether or not the crawl was successful
     */
    public boolean crawl(String url)
    {
        try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            if(connection.response().statusCode() == 200) // 200 is the HTTP OK status code
            {
                System.out.println("\n Received web page at " + url);
            }
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("Retrieval failure");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            for(Element link : linksOnPage)
            {
                this.links.add(link.absUrl("href"));
            }
            return true;
        }
        catch(IOException ioe)
        {
            return false;
        }
    }


    /**
     * Searches the body of the HTML document retrieved (Only be called after a successful crawl).
     *
     * @param word - The string to look for
     * @return whether or not the word was found
     */
    public boolean searchWord(String word)
    {
        // Defensive coding for post successful crawl.
        if(this.htmlDocument == null)
        {
            System.out.println("ERROR! crawl() not called prior to search@");
            return false;
        }
        System.out.println("Searching for " + word + "...");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(word.toLowerCase());
    }

    public List<String> getLinks()
    {
        return this.links;
    }

}