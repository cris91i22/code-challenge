package com.code.challenge.services

class GenreService(val genreStorage: Map[Long, String]) {

  def findOneBy(genreId: Long): Option[String] = genreStorage.get(genreId)

  def findAll: List[String] = genreStorage.values.toList

}
