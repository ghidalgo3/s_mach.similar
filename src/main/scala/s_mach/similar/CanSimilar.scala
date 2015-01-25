package s_mach.similar
import breeze.linalg._
//import scala.collection.mutable

/**
 * Utility trait for being able to differentiate instances of A's
 *
 * @tparam A type
 */
trait CanSimilar[A] {
  def similar(a1: A, a2: A) : Double


  def cartesianProduct(ma1: Vector[A], ma2: Vector[A]) : Matrix[Double] = {
    val matrix = new DenseMatrix[Double](ma1.length, ma2.length)
    for(r <- 0 to ma1.length;
        c <- 0 to (r * ma2.length/ma1.length)) {
      matrix(r,c) = similar(ma1(r), ma2(c))
      matrix(c,r) = matrix(r,c)
    }
    matrix
  }

  def selfCartesianProduct(ma: Vector[A]) : Matrix[Double] = {
    val matrix = new DenseMatrix[Double](ma.length, ma.length)
    for(r <- 0 to ma.length;
        c <- 0 to r) {
      matrix(r,c) = similar(ma(r), ma(c))
      matrix(c,r) = matrix(r,c)
    }
    matrix
  }
}