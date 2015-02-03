package s_mach.similar.relative

import scala.util.Random

object SimilarOps {

  /**
   * Creates a Similar[A] that calculates similarity based on concepts
   * of distance, maximum distance, and the ratio of the distance to the
   * max distance. Helper functions are needed to define what "maximum distance"
   * and "distance" are for a particular type as well as the definition of "empty"
   * This version is for subclasses of AnyRef.
   *
   * If something is empty, it has similary 0.0 to anything else.
   * @param maxDistance Computes the maximum distance between 2 As
   * @param dist Computes the distance between As
   * @param isEmpty Determines if an A is empty
   * @tparam A An A
   * @return Similar[A] for similarity comparison
   */
  def simByDistanceThreshold[A <: AnyRef](maxDistance : (A,A) => Int,
                                          dist : (A,A) => Int,
                                          isEmpty : A => Boolean): Similar[A] = {
    new Similar[A] {
      override def similar(a1: A, a2: A): Double = {
        val max = maxDistance(a1, a2)
        if (a1 eq a2) {
          1.0
        } else if (isEmpty(a1) || isEmpty(a2)) {
          0.0
        } else {
          dist(a1, a2) match {
            case 0 => 1.0
            case within if within <= max => (max - within.toDouble) / max
            case _ => 0
          }
        }
      }
    }
  }

  /**
   * Creates a Similar[A] that calculates similarity based on concepts
   * of distance, maximum distance, and the ratio of the distance to the
   * max distance. Helper functions are needed to define what "maximum distance"
   * and "distance" are for a particular type.
   * This version is for subclasses of AnyVal.
   *
   * If something is empty, it has similary 0.0 to anything else.
   * @param maxDistance Computes the maximum distance between 2 As
   * @param dist Computes the distance between As
   * @tparam A An A
   * @return Similar[A] for similarity comparison
   */
  def simByDistanceThreshold[A <: AnyVal](maxDistance : Int,
                                          dist : (A,A) => Int): Similar[A] = {
    new Similar[A] {
      override def similar(a1: A, a2: A): Double = {
        if (a1 == a2) {
          1.0
        } else {
          dist(a1, a2) match {
            case 0 => 1.0
            case within if within <= maxDistance => (maxDistance - within.toDouble) / maxDistance
            case _ => 0
          }
        }
      }
    }
  }

  /**
   * Creates a Similar[A] based on a Shingler[A,S]
   *
   * @param shingler
   * @param hashFunctions
   * @param randomSeed
   * @tparam A
   * @tparam S
   * @return
   */
  def simByShingler[A, S](shingler : Shingler[A, S],
                          hashFunctions : Int = 50,
                          randomSeed : Int = 0x0BEEFDAD) : Similar[A] = new Similar[A] {
    override def similar(a1: A, a2: A): Double = {
      val shingleA = shingler.shingle(a1)
      val shingleB = shingler.shingle(a2)
      if(shingleA.isEmpty || shingleB.isEmpty) 0.0
      else {
        val rand = new Random(randomSeed) //need same sequence of randoms each time, close over seed
        val hashes : IndexedSeq[S => Int] = Range(0, hashFunctions)
            .map(_ => rand.nextInt(Integer.MAX_VALUE))
            .map(i => (s : S) => s.hashCode() ^ i)
        hashes
          .map((h : S => Int) => (shingleA.map(h).min, shingleB.map(h).min))
          .count(hash => hash._1 == hash._2).toDouble / hashFunctions
      }
    }
  }

  // this needs to calculate the union size somehow...
  // http://en.wikipedia.org/wiki/Jaccard_index

  // http://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient
//  def calcDiceCoefficient(a_intersect_b_size: Int, a_size: Int, b_size: Int) : Double = {
//    if (a_size == 0 || b_size == 0) 1 else {
//      a_intersect_b_size / (a_size + b_size)
//    }
//  }
}
