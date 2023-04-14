# QuestionPro application with HackerNews API

### For Running application on docker:-
- To build and run mongoDB
  - `docker pull mongo:latest`
  - `docker run -d -p 27017:27017 --name questionprodb mongo:latest`
- To build application
  - `docker build -t questionproapplication-on-docker:1.0 .`
- To link and run application with mongoDB
  - `docker run -p 8080:8080 --name questionproapplication-on-docker --link questionprodb:mongo -d questionproapplication-on-docker:1.0`


