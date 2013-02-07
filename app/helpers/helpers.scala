package me.elrod.qsltagger.helpers

object Helpers {
  def numberSuffix(number: Long): String = {
    if (number >= 11 && number <= 13) {
      return number + "th"
    }
    number % 10 match {
      case 1 => number + "st"
      case 2 => number + "nd"
      case 3 => number + "rd"
      case _ => number + "th"
    }
  }
}
