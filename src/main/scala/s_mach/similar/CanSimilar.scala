package s_mach.similar

import scala.collection.mutable

trait CanSimilar[A] {
  def similar(a1: A, a2: A) : Double

//  def fSimilar(a1: A, a2: A) : Double = {
//    if(a1.eq(a2)) 1.0 else similar(a1,a2)
//  }

  def cartesianProduct(ma1: IndexedSeq[A], ma2: IndexedSeq[A]) : IndexedSeq[IndexedSeq[Double]] = {
    ma1.map(a => ma2.map(similar(a,_)))
//    val result = mutable.ArraySeq.fill(ma1.length, ma2.length)(1.0)
//    for (i <- 0 until ma1.length;
//         j <- i until ma2.length) {
//      result(i)(j) = similar(ma1(i), ma2(j))
//      result(j)(i) = result(i)(j)
//    } //use this version to cut computations in half if needed
//    result
  }

  def selfCartesianProduct(ma: IndexedSeq[A]) : IndexedSeq[IndexedSeq[Double]] = {
    ma.map(a => ma.map(similar(a, _)))
  }
}