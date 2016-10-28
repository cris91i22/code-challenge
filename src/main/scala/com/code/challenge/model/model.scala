package com.code.challenge.model

case class User(id: Long,
                ratings: List[UserRating])

case class UserRating(movieId: Long,
                      score: Int)

case class Movie(id: Long,
                 title: String,
                 genres: List[String])


case class Rating(userId: Long,
                  movieId: Long,
                  score: Int)