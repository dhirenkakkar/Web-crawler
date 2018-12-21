public class CrawlerTest {
        /**
         * * A test class which creates a robot (which creates webs) and crawls the web.
         *
         * @param args - not used
         */
        public static void main(String[] args) {
            Robot crawler = new Robot();
            crawler.lookup("https://www.ubc.ca", "Computer");
        }
}
