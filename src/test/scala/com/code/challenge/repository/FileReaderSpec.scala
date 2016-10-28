package com.code.challenge.repository

import org.specs2.mutable.Specification

class FileReaderSpec extends Specification {

  "File reader spec" should {
    "verify collections size" in {
      FileReader.movies.size mustEqual 1682
      FileReader.users.size mustEqual 943
      FileReader.genres.size mustEqual 19
      FileReader.ratings.size mustEqual 100000
    }

    "verify movies" in {
      val movie = FileReader.movies.get(1)
      movie must beSome
      movie.get.title must not be empty
      movie.get.genres mustEqual List(FileReader.genres(3), FileReader.genres(4), FileReader.genres(5))
    }

    "verify ratings" in {
      val rating = FileReader.ratings.find(_.userId == 1L)
      rating must beSome
      rating.get.userId mustEqual 1L
      rating.get.movieId mustEqual 61
      rating.get.score mustEqual 4
    }

    "verify genres" in {
      val genre = FileReader.genres.get(1L)
      genre must beSome
      genre.get must not be empty
    }

    "verify users" in {
      val user = FileReader.users.headOption
      user must beSome
      user.get mustEqual 1L
    }

  }

}
