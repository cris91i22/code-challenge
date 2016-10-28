package com.code.challenge

import com.code.challenge.repository.FileReader
import com.code.challenge.services._

object Main extends App {

  println("################################### \n")
  println("Load application\n")
  println("Reading files and map to model...\n")
  val userStorage = FileReader.users
  val genreStorage = FileReader.genres
  val movieStorage = FileReader.movies
  val ratingStorage = FileReader.ratings


  println("################################### \n")
  println("Creating services to be used... \n")
  val ratingService = new RatingService(ratingStorage)
  val movieService = new MovieService(movieStorage)
  val genreService = new GenreService(genreStorage)
  val userService = new UserService(userStorage, ratingService, movieService)


  println("###################################\n")
  println("Test services...\n")
  println(ratingService.findAllByMovie(10L))
  println(ratingService.getAverageRatingBy(64L))
  println(userService.getFavoriteGenreBy(10L))
  println(userService.getMoviesRecommendation(1L))

}


