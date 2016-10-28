package com.code.challenge.services

import com.code.challenge.model.Movie

class MovieService(val movieStorage: Map[Long, Movie]) {

  def findOneBy(movieId: Long): Option[Movie] = movieStorage.get(movieId)

  def findAll: List[Movie] = movieStorage.values.toList

}
