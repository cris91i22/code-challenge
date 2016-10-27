package com.code.challenge.model

case class User(id: String,
                ratings: List[Rating])

case class Rating(movieId: String,
                  score: String)

case class Movie(id: String,
                 title: String,
                 genres: List[String])
