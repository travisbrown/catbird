package io.catbird.util.effect

import cats.kernel.Eq
import cats.effect.laws.discipline.EffectTests
import cats.effect.laws.discipline.arbitrary._
import cats.effect.laws.util.{ TestContext, TestInstances }
import cats.instances.either._
import cats.instances.int._
import cats.instances.tuple._
import cats.instances.unit._
import cats.laws.discipline.arbitrary._
import com.twitter.conversions.time._
import io.catbird.util.{ ArbitraryInstances, Rerunnable }
import org.scalatest.FunSuite
import org.typelevel.discipline.scalatest.Discipline

class RerunnableSuite extends FunSuite with Discipline with ArbitraryInstances with TestInstances {
  implicit val context: TestContext = TestContext()
  implicit def rerunnableEq[A](implicit A: Eq[A]): Eq[Rerunnable[A]] = Rerunnable.rerunnableEqWithFailure[A](1.second)

  checkAll("Rerunnable[Int]", EffectTests[Rerunnable].effect[Int, Int, Int])
}
