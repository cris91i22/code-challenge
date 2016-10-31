package com.code.challenge.services

import com.code.challenge.model.{Movie, Rating, UserRating}
import com.code.challenge.repository.FileReader
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class UserServiceSpec extends Specification {

  trait MyDataSet extends Scope {
    val dataSet = List(1L, 2L, 3L)
    val ratingService = new RatingService(List(Rating(1L, 1L, 5),
                                              Rating(1L, 2L, 2),
                                              Rating(1L, 3L, 4),
                                              Rating(2L, 1L, 4),
                                              Rating(2L, 2L, 5),
                                              Rating(3L, 1L, 3),
                                              Rating(3L, 2L, 4),
                                              Rating(3L, 4L, 5)))
    val movieService = new MovieService(Map(1L -> Movie(1L, "Imitation Game", List("Action")),
                                            2L -> Movie(2L, "Back To The Future", List("Fantasy")),
                                            3L -> Movie(3L, "Terminator", List("War")),
                                            4L -> Movie(4L, "Limitless", List("Sci-Fi"))))
    val service = new UserService(dataSet, ratingService, movieService)
  }

  trait DataSet extends Scope {
    val dataSet = FileReader.users
    val ratingService = new RatingService(FileReader.ratings)
    val movieService = new MovieService(FileReader.movies)

    val service = new UserService(dataSet, ratingService, movieService)
  }

  "User service spec" should {
    "Find all users" in new MyDataSet {
      service.findAll.size mustEqual 3
    }

    "Find one user by {userId}" in new MyDataSet {
      service.findOneBy(2L) must beSome
    }

    "Favorite genre by {userId}" in new MyDataSet {
      val favorite = service.getFavoriteGenreBy(1L)
      favorite must beSome
      favorite.get mustEqual List("Action")
    }

    "Movies recommendation by {userId}" in new MyDataSet {
      val reco = service.getMoviesRecommendation(1L)
      reco must beSome(List(UserRating(4L, 5)))
    }

    "Find all users in all data set" in new DataSet {
      service.findAll.size mustEqual 943
    }

    "Find user by {userId} in all data set" in new DataSet {
      val user = service.findOneBy(2L)
      user must beSome
      user.get.id mustEqual 2L
      user.get.ratings.size mustNotEqual empty
      service.findOneBy(11111L) must beNone
    }

    "Favorite genre by {userId} in all data set" in new DataSet {
      val favorite = service.getFavoriteGenreBy(1L)
      favorite must beSome
      favorite.get mustEqual List("Comedy", "Romance")
    }

    "Movies recommendation by {userId} in all data set" in new DataSet {
      val reco = service.getMoviesRecommendation(1L)
      reco must beSome
      reco.get must contain(UserRating(276L, 5))
    }

  }

}
