package s_mach.similar

import breeze.linalg.DenseVector
import s_mach.similar.impl.{StringDistances, SimilarOps}
import s_mach.similar.impl.MetricOps._

object TestObjects {

  implicit val cartesianMetric = metricByCartesianDistance[DenseVector[Double]](a => a)

  implicit val intSim = SimilarOps.simByDistanceThresholdVals[Int](100, (a,b) => Math.abs(a - b))

  implicit val stringSim = SimilarOps.simString(StringDistances.levenshteinDistance)

  val gettys1 = "Four score and seven years ago our fathers brought forth, upon this continent, a new nation, conceived in liberty, and dedicated to the proposition that “all men are created equal.”\n\nNow we are engaged in a great civil war, testing whether that nation, or any nation so conceived, and so dedicated, can long endure. We are met on a great battle field of that war. We come to dedicate a portion of it, as a final resting place for those who died here, that the nation might live. This we may, in all propriety do.\n\nBut, in a larger sense, we can not dedicate – we can not consecrate – we can not hallow, this ground – The brave men, living and dead, who struggled here, have hallowed it, far above our poor power to add or detract. The world will little note, nor long remember what we say here; while it can never forget what they did here.\n\nIt is rather for us, the living, we here be dedicated to the great task remaining before us – that, from these honored dead we take increased devotion to that cause for which they here, gave the last full measure of devotion – that we here highly resolve these dead shall not have died in vain; that the nation, shall have a new birth of freedom, and that government of the people, by the people, for the people, shall not perish from the earth."
  val gettys2 = "Four score and seven years ago our fathers brought forth, upon this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.\n\nNow we are engaged in a great civil war, testing whether that nation, or any nation so conceived, and so dedicated, can long endure. We are met here on a great battlefield of that war. We have come to dedicate a portion of it, as a final resting place for those who here gave their lives that that nation might live. It is altogether fitting and proper that we should do this.\n\nBut in a larger sense, we can not dedicate — we can not consecrate — we can not hallow — this ground. The brave men, living and dead, who struggled here, have consecrated it far above our poor power to add or detract. The world will little note, nor long remember, what we say here, but can never forget what they did here.\n\nIt is for us, the living, rather to be dedicated here to the unfinished work which they have, thus far, so nobly carried on. It is rather for us to be here dedicated to the great task remaining before us — that from these honored dead we take increased devotion to that cause for which they gave the last full measure of devotion – that we here highly resolve that these dead shall not have died in vain; that this nation shall have a new birth of freedom; and that this government of the people, by the people, for the people, shall not perish from the earth."
}

