package s_mach.similar

object LevenshteinDistance {
  implicit object CanSimilar_String_String extends CanSimilar[String] {
    override def similar(a1: String, a2: String): Double = {
      val editDistance = SimilarOps.levenshteinDistance(a1,a2)
      val len = Math.max(a1.length, a2.length)
      1.0 - editDistance.toDouble/len
    }
  }
}
