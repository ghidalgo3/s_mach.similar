package s_mach

import scala.util.matching.Regex

package object similar {
  implicit class SMach_Similar_PimpMyIndexedSeq[A](val self: IndexedSeq[A]) extends AnyVal {
    def selfCartesianProduct(implicit s:CanSimilar[A]) : IndexedSeq[IndexedSeq[Double]] = ???
    def cartesianProduct(implicit s:CanSimilar[A]) : IndexedSeq[IndexedSeq[Double]] = ???
    def centroid(implicit s:CanSimilar[A]) : A = ???
    def simGroupBy[K](threshhold: Double)(f: A => K)(implicit s:CanSimilar[K]) : Map[K, IndexedSeq[A]] = ???
  }

  case class Word(value: String) extends AnyVal

  implicit class SMach_Similar_PimpMyString(val self: String) extends AnyVal {
    def chargrams: Iterator[Char] = ???
    def wordgrams: Iterator[Word] = ???
    def ngrams(matcher: Regex) : Iterator[String] = ???
  }

  implicit class SMach_Similiar_PimpEverything[A](val self: A) extends AnyVal {
    def shingle[S](implicit shingler: Shingler[A,S]) : List[S] = shingler.shingle(self)
  }

  implicit class SMach_Similar_PimpMyIterable[A](val self: Iterator[A]) extends AnyVal {
    def ksliding(k_range: Range) = k_range.iterator.flatMap(self.sliding(_,1))
  }
}