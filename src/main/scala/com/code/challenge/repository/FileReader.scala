package com.code.challenge.repository

import com.code.challenge.model._

object FileReader extends Reader {


  /**
    * Set up files
    */
  private val ratingsFile = fromFile("ml-100k/u.data", '\t', "UTF-8")
  private val genresFile = fromFile("ml-100k/u.genre", '|', "UTF-8")
  private val usersFile = fromFile("ml-100k/u.user", '|', "UTF-8")
  private val moviesFile = fromFile("ml-100k/u.item", '|', "ISO-8859-1")


  /**
    * Map files to base models
    */

  val ratings = ratingsFile.map{ line =>
    val Array(userId, itemId, rating, _) = line
    Rating(userId.toLong, itemId.toLong, rating.toInt)
  }

  val genres = genresFile.map{ line =>
    val Array(description, genreId) = line
    genreId.toLong -> description
  }.toMap

  val users = usersFile.map{ line =>
    val Array(userId, _, _, _, _) = line
    userId.toLong
  }

  val movies = moviesFile.map{ line =>
    val genresList = line.drop(5).zipWithIndex.collect{case(value, index) if value == "1" => genres(index)}.toList
    val movieId = line(0).toLong
    val title = line(1)
    movieId -> Movie(movieId, title, genresList)
  }.toMap

}
