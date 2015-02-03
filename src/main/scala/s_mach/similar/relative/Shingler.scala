package s_mach.similar.relative

trait Shingler[A,S] {
  def shingle(a: A) : List[S]

//  def similar(implicit s_similar : Similar[S]) : Double
}

//object StringShingler extends Shingler[String, String]{
//
//  val shingleSize = 2
//
//  override def shingle(a: String): List[String] = {
//    a.iterator
//      .ksliding(shingleSize to shingleSize).map(_.mkString).toList
//  }
//}
