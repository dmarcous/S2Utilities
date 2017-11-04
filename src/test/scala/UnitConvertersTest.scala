package com.S2Utilities.converters

import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class UnitConvertersTest extends FlatSpec
{
  val degrees = 95.0000006
  val radians = 1.6580628
  val cartesianCoordinates2d : (Double, Double) = (32.0718015, 34.777112)
  val adjustedCartesianCoordinates3d : (Double, Double, Double) = (0.6960213,0.4361361,0.5703854)
  val cartesianCoordinates3d : (Double, Double, Double) = (32.0718015, 34.777112, 33.3333)
  val radianCoordinates3d : (Double, Double, Double) = (57.871900, 0.956988, 0.825845)
  val adjustedRadianCoordinates2d : (Double, Double) = (0.559758, 0.4881245)
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

  "radianCoordinatesTocartesianCoordinates" should "Convert radian coordinates to degree coordinates" in
  {
    val expectedOutput = this.cartesianCoordinates3d
    val observedOutput = UnitConverters.radianCoordinatesToCartesianCoordinates(this.radianCoordinates3d)

    observedOutput._1 should equal (expectedOutput._1 +- this.Eps)
    observedOutput._2 should equal (expectedOutput._2 +- this.Eps)
    observedOutput._3 should equal (expectedOutput._3 +- this.Eps)
  }

  "cartesianCoordinatesToRadianCoordinates" should "Convert degree coordinates to radian coordinates" in
  {
    val expectedOutput = this.radianCoordinates3d
    val observedOutput = UnitConverters.cartesianCoordinatesToRadianCoordinates(this.cartesianCoordinates3d)

    observedOutput._1 should equal (expectedOutput._1 +- this.Eps)
    observedOutput._2 should equal (expectedOutput._2 +- this.Eps)
    observedOutput._3 should equal (expectedOutput._3 +- this.Eps)
  }

  "cartesianCoordinates2dTo3d" should "Converts 2d cartesian coordiantes to 3d adjusted coordiantes " in
  {
    val expectedOutput = this.adjustedCartesianCoordinates3d
    val observedOutput = UnitConverters.cartesianCoordinates2dTo3d(this.cartesianCoordinates2d)

    observedOutput._1 should equal (expectedOutput._1 +- this.Eps)
    observedOutput._2 should equal (expectedOutput._2 +- this.Eps)
    observedOutput._3 should equal (expectedOutput._3 +- this.Eps)
  }

  "adjusted3dCartesianCoordiantesTo2dRadianCoordiantes" should
    "Convert adjusted 3d Cartesian coordinates to 2d radian coordianted" in
  {
    val expectedOutput = this.adjustedRadianCoordinates2d
    val observedOutput =
      UnitConverters.adjusted3dCartesianCoordiantesTo2dRadianCoordiantes(this.adjustedCartesianCoordinates3d)

    observedOutput._1 should equal (expectedOutput._1 +- this.Eps)
    observedOutput._2 should equal (expectedOutput._2 +- this.Eps)
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
