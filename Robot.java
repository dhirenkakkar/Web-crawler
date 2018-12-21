import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Robot {

    private static final int MAX_PAGES = 10;
    private Set<String> pagesChecked = new HashSet<>();
    private List<String> pagesToCheck = new LinkedList<>();

    /**
     * Launching point of the robot. Calls to create webs which make the
     * HTTP requests and parse the response.
     *
     * @param URL - Starting URL
     * @param word - Word the user is searching for
     */
    public void lookup(String URL, String word)
    {
        while(this.pagesChecked.size() < MAX_PAGES)
        {
            String currURL;
            Web web = new Web();
            if(this.pagesToCheck.isEmpty())
            {
                currURL = URL;
                this.pagesChecked.add(URL);
            }
            else
            {
                currURL = this.nextURL();
            }
            web.crawl(currURL);
            boolean success = web.searchWord(word);
            if(success)
            {
                System.out.println(String.format("Word %s found at %s", word, currURL));
                break;
            }
            this.pagesToCheck.addAll(web.getLinks());
        }
    }

    /**
     * Returns the next URL to visit encountered by the robot (in the order
     * encountered provided it hasn't been already visited)
     *
     * @return the next URL to visit
     */
    private String nextURL() {
        String URL1;
        do {
            URL1 = this.pagesToCheck.remove(0);
        } while(this.pagesChecked.contains(URL1));
        this.pagesChecked.add(URL1);
        return URL1;
    }
}
