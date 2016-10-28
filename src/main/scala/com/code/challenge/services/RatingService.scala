package com.code.challenge.services

import com.code.challenge.model.Rating

class RatingService(val ratingStorage: List[Rating]) {

  def findAll: List[Rating] = ratingStorage

  def findOneBy(movieId: Long): Option[Rating] = ratingStorage.find(_.movieId == movieId)

  def findAllBy(userId: Long): List[Rating] = ratingStorage.filter(_.userId == userId)

  def findAllByMovie(movieId: Long): List[Rating] = ratingStorage.filter(_.movieId == movieId)

  def getAverageRatingBy(movieId: Long): Double = {
    val rates = findOneBy(movieId).map(_.score)
    rates.sum / rates.size
  }

}