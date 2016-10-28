package com.code.challenge

import com.code.challenge.repository.FileReader
import com.code.challenge.services._

object Main extends App {

  /// leo from reader y levanto el storages/s

  println("Reading files and map to model...")

  val userService = UserService(FileReader.u2, FileReader.r2, FileReader.m2)
  val genreService = GenreService(FileReader.g2)
  val movieService = MovieService(FileReader.m2)
  val ratingService = RatingService(FileReader.r2)

//  println(a.findAllRatingsBy("64"))
//  println(a.getAverageRatingBy("64"))
//  println(a.getFavoriteGenreBy("1"))

}


