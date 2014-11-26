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

  /*
  "hello"
  ngrams where 1-gram, 2-gram, etc
  1-gram { "h","e","l","l","o" } (set of shingles or 'shingle set')
  2-gram { "he","el","ll","lo" }
  ...
  n-gram { ... }

  "hlelo" (typo)
  2-gram { "hl","le","el","lo" }

  jaccard's forumla = (# of matches, total in the set) = 0 to 100

  2,3-gram => 2-gram union 1-gram (best for short strings)
  3,4-gram => 3-gram union 4-gram (best for longer)
  5,6-gram => 5-gram union 6-gram (longest)

  weighted shingling
  type WeightedShingle = (String,Int)

  case class Address(
    street: String,
    streetNumber: String,
    countryCode: String,
    postalCode: String
  )

  shingle set for Address = shingle set of street union streetNumbe union countryCode, ...

  streetNumber because it contains numbers is very identifying but will be one of the shortest
  street is not as identifying since many streets just happen to be the same but it is longest string

  weighted shingle 1 => normal weight, 2+ more significance

  weighted shingle formula similar to jaccard's formula to compute the similarity from weighted shingles

   */
  implicit class SMach_Similar_PimpMyString(val self: String) extends AnyVal {
    def chargrams: Iterator[Char] = ???
    def wordgrams: Iterator[Word] = ???
    def ngrams(matcher: Regex) : Iterator[String] = ???
  }

  implicit class SMach_Similiar_PimpEverything[A](val self: A) extends AnyVal {
    def similar(rhs: A)(implicit canSimilar: CanSimilar[A]) : Double = canSimilar.similar(self, rhs)
    def shingle[S](implicit shingler: Shingler[A,S]) : List[S] = shingler.shingle(self)
  }

  implicit class SMach_Similar_PimpMyIterable[A](val self: Iterator[A]) extends AnyVal {
    // 1-gram => ksliding(Range(1,1))
    // 2-gram => ksliding(Range(2,2))
    // 2,3-gram => ksliding(Range(2,3))
    /** */
    def ksliding(k_range: Range) = k_range.iterator.flatMap(self.sliding(_,1))
  }
}