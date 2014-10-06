package s_mach.similar

// http://en.wikipedia.org/wiki/Levenshtein_distance
object LevenshteinDistance {
  implicit object CanSimilar_String_String extends CanSimilar[String] {
    override def similar(t1: String, t2: String): Double = ???
  }
}
