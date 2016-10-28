package com.code.challenge.services

import com.code.challenge.model.{Movie, Rating}
import com.code.challenge.repository.FileReader
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class UserServiceSpec extends Specification {

  trait MyDataSet extends Scope {
    val dataSet = List(1L, 2L, 3L)
    val ratingService = new RatingService(List(Rating(1L, 1L, 4),
                                              Rating(1L, 2L, 4),
                                              Rating(2L, 1L, 5),
                                              Rating(2L, 2L, 5),
                                              Rating(3L, 1L, 5)))
    val movieService = new MovieService(Map(1L -> Movie(1L, "Imitation Game", List("Action")),
                                            2L -> Movie(2L, "Back To The Future", List("Action"))))
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

    "Find one user by id" in new MyDataSet {
      service.findOneBy(2L) must beSome
    }
  }

}
