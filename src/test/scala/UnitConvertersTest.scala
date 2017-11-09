package com.s2utils.converters

import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class UnitConvertersTest extends FlatSpec
{
  val degrees = 95.0000006
  val radians = 1.6580628
  val metersDistance = 199.929805
  val anglesDistance = 0.001796

  val Eps = 1e-4

  "degrees2radians" should "Convert degrees to radians" in
  {
    val expectedOutput = this.radians
    val observedOutput = UnitConverters.degreesToRadians(this.degrees)

    observedOutput should equal (expectedOutput +- this.Eps)
  }

  "radians2degrees" should "Convert radians to degrees" in
  {
    val expectedOutput = this.degrees
    val observedOutput = UnitConverters.radiansToDegrees(this.radians)

    observedOutput should equal (expectedOutput +- this.Eps)
  }

  "metricToAngularDistance" should "Convert metric distance to angular (WGS84) distance" in
  {
    val expectedOutput = this.anglesDistance
    val observedOutput = UnitConverters.metricToAngularDistance(this.metersDistance)

    observedOutput should equal (expectedOutput +- this.Eps)
  }

  "angularToMetricDistance" should "Convert angular (WGS84) distance to metric distance" in
  {
    val expectedOutput = this.metersDistance
    val observedOutput = UnitConverters.angularToMetricDistance(this.anglesDistance)

    observedOutput should equal (expectedOutput +- this.Eps)
  }



}
