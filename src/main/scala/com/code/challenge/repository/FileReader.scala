package com.code.challenge.repository

import com.code.challenge.model._

object FileReader extends Reader {

  val ratingsFile = fromFile("ml-100k/u.data", '\t', "UTF-8")
  val genresFile = fromFile("ml-100k/u.genre", '|', "UTF-8")
  val usersFile = fromFile("ml-100k/u.user", '|', "UTF-8")
  val moviesFile = fromFile("ml-100k/u.item", '|', "ISO-8859-1")

  val ratings = ratingsFile.map{ line =>
    val Array(userId, itemId, rating, _) = line
    (userId, itemId, rating)
  }

  val genresMap = genresFile.map{ line =>
    val Array(description, genreId) = line
    genreId -> description
  }.toMap

  val users = usersFile.map{ line =>
    val Array(userId, _, _, _, _) = line
    userId
  }

  val movies = moviesFile.map{ line =>
    val c = line.drop(5).zipWithIndex.collect{case(value, index) if value == "1" => genresMap(index.toString)}
    (line(0), line(1), c.toList)
  }

  val getUsers = users.par.map { id =>
    val rate = ratings.groupBy(_._1).get(id)
    val mappedRating = rate.map { r =>
      r.map(x => Rating(x._2, x._3))
    }.getOrElse(Nil)

    User(id, mappedRating)
  }

  val getMovies = movies.par.map { case (id, title, genres) =>
    Movie(id, title, genres)
  }


}
