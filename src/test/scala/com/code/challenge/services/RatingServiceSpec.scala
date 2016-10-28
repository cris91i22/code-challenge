package com.code.challenge.services

import com.code.challenge.model.Rating
import com.code.challenge.repository.FileReader
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class RatingServiceSpec extends Specification {

  trait MyDataSet extends Scope {
    val dataSet = List(Rating(1L, 1L, 4),
                        Rating(1L, 2L, 4),
                        Rating(2L, 3L, 5),
                        Rating(2L, 2L, 5),
                        Rating(6L, 1L, 5))
    val service = new RatingService(dataSet)
  }

  trait DataSet extends Scope {
    val dataSet = FileReader.ratings
    val service = new RatingService(dataSet)
  }

  "Rating service spec" should {
    "Find all rating by movie id" in new MyDataSet {
      service.findAllByMovie(1L).size mustEqual 2
    }

    "Find all rating by user id" in new MyDataSet {
      service.findAllBy(6L).size mustEqual 1
    }

    "Find all ratings" in new MyDataSet {
      service.findAll.size mustEqual 5
    }

    "Get average rating by movie id" in new MyDataSet {
      val rating = service.getAverageRatingBy(1L)
      rating mustEqual 4.5
    }

    "Get average rating by movie id in all data set" in new DataSet {
      val rating = service.getAverageRatingBy(64L)
      rating mustEqual 4.445229681978798
    }

  }

}
