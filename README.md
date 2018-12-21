# Web-crawler

The program searches for a user-given word from a given URL. It does so by making HTTP requests and then parsing the response subsequently saving all the URLs it encounters (leaving the URLs already visited). The program runs until the given word is found or the MAX_PAGE_LIMIT is reached.

- Creates a Mozilla USER_AGENT to allow the web server to think the robot is a normal web browser
- Uses Jsoup JAR to form the HTTP request and parse the response
