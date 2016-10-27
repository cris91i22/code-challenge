package com.code.challenge.repository

trait Reader {

  /**
    *
    *
    *
    * @param filePath
    * @param delimiter
    * @param encoding
    * @return
    */
  def fromFile(filePath: String, delimiter: Char, encoding: String): List[Array[String]] = {
    val bufferedSource = io.Source.fromFile(filePath, encoding)
    val result = try {
      bufferedSource.getLines.toList.map{ line =>
        line.split(delimiter).map(_.trim)
      }
    } finally {
      bufferedSource.close
    }
    result
  }

}
