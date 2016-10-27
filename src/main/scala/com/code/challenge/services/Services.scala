package com.code.challenge.services

import com.code.challenge.model.User
import com.code.challenge.repository.FileReader

class Services {

  val fl = FileReader

  def findAllRatingsBy(movieId: String) = {
    fl.ratings.filter(_._2 == movieId).map(_._3)
  }

  def getAverageRatingBy(movieId: String) = {
    val rates = fl.ratings.filter(_._2 == movieId).map(_._3.toDouble)
    rates.sum / rates.size
  }

  def getFavoriteGenreBy(userId: String): Option[List[String]] = {
    val bestRate = fl.getUsers.find(_.id == userId).map(_.ratings)
      .flatMap(_.sortWith{case (r1, r2) => r1.score > r2.score}.headOption)

    bestRate.flatMap{ r =>
      fl.getMovies.find(_.id == r.movieId).map(_.genres)
    }
  }

  def getMovieReco(userId: String) = {

  }

}

class UserService(val userStorage: Map[Long, User]) {
  def findUser(id: Long): Option[User] = userStorage.get(id)
}