package s_mach.similar

trait CanSimilar[A] {
  def similar(a1: A, a2: A) : Double
  def cartesianProduct(ma1: IndexedSeq[A], ma2: IndexedSeq[A]) : IndexedSeq[IndexedSeq[Double]] = ??? // default impl here
  def selfCartesianProduct(ma: IndexedSeq[A]) : IndexedSeq[IndexedSeq[Double]] = ??? // default impl here
}

class A(val v1: Int, val v2: String) {
  def copy(v1: Int, v2: String) = ???
//  override def hashCode() : Int = ???

  def canEqual(other: Any): Boolean = other.isInstanceOf[A]

  override def equals(other: Any): Boolean = other match {
    case that: A =>
      (that canEqual this) &&
        v1 == that.v1 &&
        v2 == that.v2
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(v1, v2)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object A {
  def apply(v1: Int, v2: String) = ???
  def unapply(a: A) : Option[(Int, String)] = Some ((a.v1,a.v2))
  def unapply(a: Any) : Option[(Int,String)] = a match {
    case a:A => Some((a.v1,a.v2))
    case _ => None
  }
}

//for (val i = 10; i < )
// Range(0,10).map { i => "asdf" }
//for(i <- 0 until 10) yield {
// "asdf"
// }
// Range(0,10).withFilter(_ != 6).map { i => "asdf" }
//for(i <- 10 until (0,-1) if i != 6) {
// "asdf"
// }
//
object Code {
  def mymethod: Unit = {
    val a: A = ???
    a match {
      case A(v1,v2) => // if
      case _ => // else
    }
    if(A.unapply(a).nonEmpty) {
      val (v1,v2) = A.unapply(a).get
      // do something
    } else {

    }
  }
}

/*
class Creature {
  private var _range : Int = 10
  def range : Int = _range
  def range(i: Int) = _range = i
}
trait Printable {
  def value: Int
  def print() : Unit
}

trait DefaultPrintable extends Printable {
  def print() = println(value)//println(this.toString)
}

class A(val value: Int)

object test {
  def mymethod = {
    val a : Printable = new A(0) {} with Printable {
      override def print() = println(value)
    }
    a.print()
    a.asInstanceOf[A]
    val a2 : Printable = new A(1) with DefaultPrintable
  }
}
*/
/*
trait MixinSimilar {
  def similar(rhs: this.type) : Double
}

class MyClass extends MixinSimilar {

}

object Test {
  implicit object CanSimilar_ShortString extends CanSimilar[String] {
    override def similar(a1: String, a2: String): Double = ???
  }
  implicit object CanSimilar_LongString extends CanSimilar[String] {

  }
}

object otherCode {
  import Test.CanSimilar_ShortString
  import s_mach.similar._
  def mymethod: Unit = {
    val s1 = "asdf"
    val s2 = "asdfg"
    //CanSimilar_ShortString.similar(s1,s2)

    s1 similar s2
  }
  implicit object CanSimilar_Address extends CanSimilar[Address] {
    override def similar(a1: Address, a2:Address) : Double = {
      a1.value similar a2.value
    }
  }
  case class Address(value: String)
  def mymethod2: Unit = {
    val addr1 = Address("a1")
    val addr2 = Address("a2")

    addr1 similar addr2
  }
}
*/