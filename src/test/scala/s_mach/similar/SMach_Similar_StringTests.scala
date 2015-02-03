package s_mach.similar
import org.scalatest.{FlatSpec,Matchers}
import breeze.linalg.{DenseVector, DenseMatrix}
import s_mach.similar.TestObjects._
import s_mach.similar.relative.SimilarOps._

/**
* Created by Gustavo on 1/27/15.
*/
class SMach_Similar_StringTests extends FlatSpec with Matchers {
    "selfCartesianProduct" should "1x1 test on strings" in {
      val hello = DenseVector[String]("Hello")
      hello.selfCartesianProduct should equal(DenseMatrix.ones[Double](1,1))
    }

    it should "2x2 test on strings" in {
      val result = DenseMatrix.ones[Double](2,2)
      result(0,1) = 0.2
      result(1,0) = 0.2
      DenseVector[String]("Hello", "Hi").selfCartesianProduct should equal(result)
    }

    it should "synthetic test" in {
      val as = DenseVector[String](
        (1 to 10)
          .map("a" * _)
          .toArray
      )
      val expectedResult = DenseMatrix.zeros[Double](as.length, as.length)
      for(r <- 0 until expectedResult.rows;
          c <- 0 to r) {
        expectedResult(r,c) = (c.toDouble + 1) / (r + 1)
        expectedResult(c,r) = expectedResult(r,c)
      }
      as.selfCartesianProduct should equal (expectedResult)
    }
}
