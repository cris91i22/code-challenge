package com.code.challenge.services

import com.code.challenge.model.Movie
import org.specs2.mutable.Specification
import com.code.challenge.repository.FileReader
import org.specs2.specification.Scope

class MovieServiceSpec extends Specification {

  trait MyDataSet extends Scope {
    val dataSet = Map(1L -> Movie(1L, "Imitation Game", List("Action")),
                      2L -> Movie(2L, "Back To The Future", List("Action")))
    val service = new MovieService(dataSet)
  }

  trait DataSet extends Scope {
    val dataSet = FileReader.movies
    val genres = FileReader.genres
    val service = new MovieService(dataSet)
  }

  "Movie service spec" should {
    "Find one movie" in new MyDataSet {
      service.findOneBy(1L) must beSome(Movie(1L, "Imitation Game", List("Action")))
    }

    "Find one movie in all data set" in new DataSet {
      val movie = service.findOneBy(64L)
      movie must beSome
      movie.get.id mustEqual 64L
      movie.get.title mustEqual "Shawshank Redemption, The (1994)"
      movie.get.genres mustEqual List(genres(8))
    }

    "Find all movies" in new MyDataSet {
      service.findAll.size mustEqual 2
    }
  }

}
