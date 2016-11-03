package com.code.challenge.services

import com.code.challenge.model.{Rating, User, UserRating}

class UserService(val userStorage: List[Long], val ratingService: RatingService, val movieService: MovieService) {

  /**
    * Group each movie with his rates
    */
  val moviesWithRatings: Map[Long, List[Rating]] = ratingService.findAll.groupBy(_.movieId).map{ case (k, value) => k -> value.sortBy(- _.score) }


  def findAll: List[User] = userStorage.map { id =>
    val ratings = ratingService.findAllBy(id).map { r =>
      UserRating(r.movieId, r.score)
    }
    User(id, ratings)
  }

  def findOneBy(userId: Long): Option[User] = findAll.find(_.id == userId)

  def getFavoriteGenreBy(userId: Long): List[String] = (for {
    bestRate <- findOneBy(userId).map(_.ratings).flatMap(_.sortBy(- _.score).headOption)
    genres <- movieService.findOneBy(bestRate.movieId).map(_.genres)
  } yield genres) getOrElse Nil

  def getMoviesRecommendation(userId: Long): Option[List[UserRating]] = {
    val bestRate = {ratings: List[UserRating] => ratings.sortBy(- _.score) }

    /**
      * Get best movies rated by {userId}
      */
    val usrRatings: Option[List[UserRating]] = findOneBy(userId).map(_.ratings)
    val bestMoviesRated: Iterable[Long] = usrRatings.map(x => bestRate(x).take(3)).map(ur => ur.map(_.movieId)).getOrElse(Nil)

    /**
      * Obtain movies from best movies rated by {userId}
      */
    val movies: Iterable[Rating] = bestMoviesRated.map(moviesWithRatings).foldLeft(List[Rating]()){ case (s, v) => s ::: v.take(4) }

    /**
      * Filter movies with same {userId}
      */
    val usersEquallyRate: Iterable[Rating] = movies.filter(_.userId != userId)

    /**
      * Obtain other users who rated movies
      */
    val otherUsersMovies: Iterable[UserRating] = usersEquallyRate.flatMap(r => findOneBy(r.userId)).flatMap(x => bestRate(x.ratings.filterNot(x => bestMoviesRated.exists(_ == x.movieId))).take(3))

    /**
      * diff between other users movies and {userId} movies
      */
    usrRatings.map{x => (otherUsersMovies.toList diff x).distinct}

  }

}
