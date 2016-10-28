package com.code.challenge.services

import com.code.challenge.model.{User, UserRating}

class UserService(val userStorage: List[Long], val ratingService: RatingService, val movieService: MovieService) {

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

}
