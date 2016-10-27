package com.code.challenge

import com.code.challenge.repository.FileReader
import com.code.challenge.services.Services

object Main extends App {

  val a = new Services

  /// leo from reader y levanto el storages/s

  FileReader.ratingsFile

  val userService = UserService()

  println(a.findAllRatingsBy("64"))
  println(a.getAverageRatingBy("64"))
  println(a.getFavoriteGenreBy("1"))

}


