package s_mach.similar

import org.scalatest.{FlatSpec,Matchers}
import s_mach.similar.TestObjects._

/**
 * Created by Gustavo on 11/19/14.
 */
class SMach_Similar_PimpMyStringTest extends FlatSpec with Matchers{
  "selfCartesian" should "1x1 test on numbers" in {
    Vector(1).selfCartesianProduct should equal(Vector(Vector(1.0)))
  }

  it should "2x2 test on numbers" in {
    Vector(1,2).selfCartesianProduct should equal(Vector(
      Vector(1.0, .99),
      Vector(.99, 1.0)))
  }

  it should "1x1 test on strings" in {
    Vector("Hello")
      .selfCartesianProduct should equal(Vector(Vector(1.0)))
  }

  it should "2x2 test on strings" in {
    Vector("Hello", "Hi")
      .selfCartesianProduct(SimilarOps.simByDistanceThreshold[String](1)(SimilarOps.levenshteinDistance)) should equal(Vector(
        Vector(1.0, 0.0),
        Vector(0.0, 1.0)))
  }

  it should "ignore empty sequences" in {
    Vector().selfCartesianProduct(intSim) should equal(Vector(Vector()))
  }



}
