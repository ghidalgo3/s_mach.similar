package s_mach.similar

trait Shingler[A,S] {
  def shingle(a: A) : List[S]
}
