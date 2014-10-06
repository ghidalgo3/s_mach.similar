package s_mach.similar

trait CanSimilar[A] {
  def similar(a1: A, a2: A) : Double
  def cartesianProduct(ma1: IndexedSeq[A], ma2: IndexedSeq[A]) : IndexedSeq[IndexedSeq[Double]] = ??? // default impl here
  def selfCartesianProduct(ma: IndexedSeq[A]) : IndexedSeq[IndexedSeq[Double]] = ??? // default impl here
}
