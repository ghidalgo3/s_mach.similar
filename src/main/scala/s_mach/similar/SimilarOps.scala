package s_mach.similar

object SimilarOps {
  // http://en.wikipedia.org/wiki/Jaccard_index
  def calcJaccardIndex(a_intersect_b_size: Int, a_size: Int, b_size: Int) : Double = ???
  // http://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient
  def calcDiceCoefficient(a_intersect_b_size: Int, a_size: Int, b_size: Int) : Double = ???

  // http://en.wikipedia.org/wiki/Levenshtein_distance
  def levenshteinDistance(s1: String, s2: String) : Int = ???

  // http://en.wikipedia.org/wiki/Hamming_distance
  def hammingDistance(s1: String, s2: String) : Int = ???

  // http://en.wikipedia.org/wiki/Needleman%E2%80%93Wunsch_algorithm
  def needlemanWunsch(s1: String, s2: String) : Int = ???

  // Implement more algos here: http://web.archive.org/web/20081224234350/http://www.dcs.shef.ac.uk/~sam/stringmetrics.html
}
