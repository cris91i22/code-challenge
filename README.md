# Take-home assignment.


The idea of this assignment is for you to demonstrate to us that you can do some
real-world data-processing tasks in a purely functional way.

The assignment is open-ended: it is up to you to decide how to structure your code and what libraries you use.

You will be using the MovieLens dataset as an example. You can fetch the dataset with:

```shell
curl -O http://files.grouplens.org/datasets/movielens/ml-100k.zip
```

It is a 5MB file, so all the data will easily fit into memory.

The zip file contains a `README`  about the structure of the data.

The files we are interested in:

- Ratings - `u.data`
- Genres  - `u.genre`
- Users   - `u.user`
- Movies  - `u.item`

Your tasks are:

- Set up a new SBT project
- Explore the files and write a data model to represent the data in Scala. Include relationships.
- Write functions to load the data from files. Feel free to code
  the parsing logic or use a CSV library,whichever you feel is more
  appropriate.
- Write functions to do the following:
  - Find all the ratings for a movie.
  - Find the average rating for a movie
  - Find a user's favourite genre, based on their ratings
  - Find a movie recommendation for a user. This one is the most
    open-ended. An idea might be to take the movies a user has rated
    highly and find users who have also rated those movies highly,
    then recommend another movie rated highly by those other users.
- Write tests for everything and demonstrate the usage of your code
  in the tests.

#### Notes:

- This isn't really to demonstrate framework knowledge. No need to
   for Akka/Play/a database/etc. It is more about your ability to
   write clear and concise functional code.
-  The interview will have discussion about the code you wrote.
- Expected time taken is 4-8 hours.
- Since this is open-ended, feel free to make your own call on
  anything unspecified, or ask us :)
