package s_mach

import s_mach.similar.metric.Metric
import s_mach.similar.relative.{Shingler, Similar}

import scala.reflect.ClassTag
import scala.util.matching.Regex
import breeze.linalg._

package object similar {

  implicit class SMach_Similar_PimpMyIndexedSeq[A](val self: IndexedSeq[A]) extends AnyVal {
    def toDenseVector(implicit aClassTag: ClassTag[A]): DenseVector[A] = DenseVector[A](self.toArray)
  }

  implicit class SMach_Similar_PimpMyDenseVector[A](val self: DenseVector[A]) extends AnyVal {

    def selfCartesianProduct(implicit s: Similar[A],
                             aClassTag: ClassTag[A]) : DenseMatrix[Double] = {
      s.selfCartesianProduct(self)
    }

    def cartesianProduct(other : DenseVector[A])
                        (implicit s:Similar[A],
                         aClassTag: ClassTag[A]) : DenseMatrix[Double] = {
      s.cartesianProduct(self, other)
    }

    /**
     * Returns the object most similar to all other objects
     * @param s Similar of A's
     * @return A most similar to all other A's
     */
    def simCentroid(implicit s:Similar[A],
          aClassTag: ClassTag[A]) : A = {
      self(
        sum(selfCartesianProduct, Axis._1)
          .toArray
          .zipWithIndex
          .maxBy{case (value, index) => value}._2
      )
    }

    def metricCentroid(implicit metric: Metric[A],
                 aClassTag:ClassTag[A]) : A = {
      val positions = self.map(metric.position)
      val centroidPoint : DenseVector[Double] = positions
        .foldLeft(DenseVector.zeros[Double](self.length))(_ + _) :/ self.length.toDouble
      self.foldLeft(
        self(0)
      ){(candidate, next) => {
        if (norm(metric.position(candidate) - centroidPoint) > norm(metric.position(next) - centroidPoint)) {
          next
        } else {
          candidate
        }
      }}
    }

    def simGroupBy[K](threshhold: Double)
                     (f: A => K)
                     (implicit s:Similar[K]) : Map[K, DenseVector[A]] = {
      ???
      //def similarValueExists(k : K, seq : IndexedSeq[K]) : Boolean = seq.map(s.similar(k,_)).exists(_ > threshhold)
//      val ks = self.map(f)
//      self.map(a => (a,f(a))).toMap
//        .filter{case (a,k) => similarValueExists(k,ks)}
//        .groupBy(_._2)
//        .mapValues(as => as.map(_._1).toIndexedSeq)
    } //http://stackoverflow.com/questions/2338282/elegant-way-to-invert-a-map-in-scala
      //not sure what I just did here
      //function returns a map of K to Seq[A] generated by taking the A's that map to
      //very similar K's (determined by the threshold value) and grouping them together
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

    import s_mach.string.WordSplitter.Whitespace
    import s_mach.string._


    def wordgrams: Iterator[String] = {
      self.toWords
    }

    def ngrams(matcher: Regex) : Iterator[String] = {
      matcher.split(self).iterator
    }

  }

  implicit class SMach_Similar_PimpEverything[A](val self: A) extends AnyVal {

    def similar(rhs: A)(implicit canSimilar: Similar[A]) : Double = {
      canSimilar.similar(self, rhs)
    }

    def shingle[S](implicit shingler: Shingler[A,S]) : Traversable[S] = {
      shingler.shingle(self)
    }

    def distance(rhs: A)(implicit metric : Metric[A]) = {
      metric.distance(self, rhs)
    }

    def position(implicit metric : Metric[A]) = {
      metric position self
    }

  }
}