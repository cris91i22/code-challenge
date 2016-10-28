package com.code.challenge.services

import com.code.challenge.repository.FileReader
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class GenreServiceSpec extends Specification {

  trait MyDataSet extends Scope {
    val dataSet = Map(1L -> "Action",
                      2L -> "Adventure",
                      3L -> "Animation")
    val service = new GenreService(dataSet)
  }

  trait DataSet extends Scope {
    val dataSet = FileReader.genres
    val service = new GenreService(dataSet)
  }

  "Genre service spec" should {
    "Find one genre" in new MyDataSet {
      service.findOneBy(1L) must beSome("Action")
    }

    "Find one genre in all data set" in new DataSet {
      service.findOneBy(10L) must beSome("Film-Noir")
    }

    "Find all genres" in new MyDataSet {
      service.findAll.size mustEqual 3
      service.findAll mustEqual List("Action", "Adventure", "Animation")
    }

  }

}
