package com.code.challenge.services

import com.code.challenge.model.Rating

class RatingService(val ratingStorage: List[Rating]) {

  def findOneBy(movieId: Long): Option[Rating] = ratingStorage.find(_.movieId == movieId)

  def findAllBy(userId: Long): List[Rating] = ratingStorage.filter(_.userId == userId)

  def getAverageRatingBy(movieId: Long) = {
    val rates = findOneBy(movieId).map(_.score)
    rates.sum / rates.size
  }

}