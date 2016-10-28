package com.code.challenge.services

import com.code.challenge.model.{Rating, User, UserRating}

class UserService(val userStorage: List[Long], val ratingService: RatingService, val movieService: MovieService) {

  val moviesWithRatings: Map[Long, List[Rating]] = ratingService.findAll.groupBy(_.movieId).map{ case (k, value) => k -> value.sortWith(_.score > _.score) }


  def findAll: List[User] = userStorage.map { id =>
    val ratings = ratingService.findAllBy(id).map { r =>
      UserRating(r.movieId, r.score)
    }
    User(id, ratings)
  }

  def findOneBy(userId: Long): Option[User] = findAll.find(_.id == userId)

  def getFavoriteGenreBy(userId: Long): Option[List[String]] = {
    val bestRate = findOneBy(userId).map(_.ratings).flatMap(_.sortWith{case (r1, r2) => r1.score > r2.score}.headOption)
    bestRate.flatMap{ r =>
      movieService.findOneBy(r.movieId).map(_.genres)
    }
  }

  def getMoviesRecommendation(userId: Long) = {
    def bestRate = {ratings: List[UserRating] => ratings.sortWith{_.score > _.score} }

    /**
      * Get best movies rated by {userId}
      */
    val usrRatings: Option[List[UserRating]] = findOneBy(userId).map(_.ratings)
    val bestMoviesRated: Iterable[Long] = usrRatings.map(x => bestRate(x).take(3)).map(ur => ur.map(_.movieId)).getOrElse(Nil)

    /**
      * Obtain movies from best movies rated by {userId}
      */
    val movies: Iterable[Rating] = bestMoviesRated.flatMap(moviesWithRatings)

    /**
      * Obtain all ratings from best movies rated {userId}
      */
    val usersEquallyRate: Iterable[Rating] = movies.filter(_.userId != userId).take(3)

    /**
      * Obtain other users movies rated
      */
    val otherUsersMovies: Iterable[UserRating] = usersEquallyRate.flatMap(r => findOneBy(r.userId)).flatMap(x => bestRate(x.ratings.filterNot(x => bestMoviesRated.exists(_ == x.movieId))).take(3))

    /**
      * diff between other users movies and {userId} movies
      */
    usrRatings.map{x => otherUsersMovies.toList diff x}

  }

}
