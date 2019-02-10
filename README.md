# Web-crawler

A script that searches for a user-given word from a given URL.The program runs until the given word is found or the MAX_PAGE_LIMIT is reached. 

# Working

- Makes an HTTP requests and then parsing the response subsequently saving all the URLs it encounters (leaving the URL already visited). 
- Creates a fake Mozilla USER_AGENT to allow the web server to think the robot is a normal web browser.
- Uses Jsoup JAR to form the HTTP request and parse the response to compare to the given string.
