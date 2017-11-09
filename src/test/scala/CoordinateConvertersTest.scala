package com.s2utils.converters

import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class CoordinateConvertersTest extends FlatSpec
{
  val cartesianCoordinates2d : (Double, Double) = (34.777112, 32.0718015)
  val adjustedCartesianCoordinates3d : (Double, Double, Double) = (0.6960213, 0.4833351, 0.530981)
  val cartesianCoordinates3d : (Double, Double, Double) = (34.777112, 32.0718015, 33.3333)
  val radianCoordinates3d : (Double, Double, Double) = (57.871900, 0.956988, 0.744951)
  val adjustedRadianCoordinates2d : (Double, Double) = (0.606975, 0.518359)
  val s2CellId = 1521455257954025472L
  val s2CellToken = "1521455257954025472"
  val s2lvl = 14

  val Eps = 1e-4

  "radianCoordinatesToCartesianCoordinates" should "Convert radian coordinates to degree coordinates" in
  {
    val expectedOutput = this.cartesianCoordinates3d
    val observedOutput = CoordinateConverters.radianCoordinatesToCartesianCoordinates(this.radianCoordinates3d)

    observedOutput._1 should equal (expectedOutput._1 +- this.Eps)
    observedOutput._2 should equal (expectedOutput._2 +- this.Eps)
    observedOutput._3 should equal (expectedOutput._3 +- this.Eps)
  }

  "cartesianCoordinatesToRadianCoordinates" should "Convert degree coordinates to radian coordinates" in
  {
    val expectedOutput = this.radianCoordinates3d
    val observedOutput = CoordinateConverters.cartesianCoordinatesToRadianCoordinates(this.cartesianCoordinates3d)

    observedOutput._1 should equal (expectedOutput._1 +- this.Eps)
    observedOutput._2 should equal (expectedOutput._2 +- this.Eps)
    observedOutput._3 should equal (expectedOutput._3 +- this.Eps)
  }

  "cartesianCoordinates2dTo3d" should "Converts 2d cartesian coordiantes to 3d adjusted coordiantes " in
  {
    val expectedOutput = this.adjustedCartesianCoordinates3d
    val observedOutput = CoordinateConverters.cartesianCoordinates2dTo3d(this.cartesianCoordinates2d)

    observedOutput._1 should equal (expectedOutput._1 +- this.Eps)
    observedOutput._2 should equal (expectedOutput._2 +- this.Eps)
    observedOutput._3 should equal (expectedOutput._3 +- this.Eps)
  }

  "adjusted3dCartesianCoordiantesTo2dRadianCoordiantes" should
    "Convert adjusted 3d Cartesian coordinates to 2d radian coordianted" in
  {
    val expectedOutput = this.adjustedRadianCoordinates2d
    val observedOutput =
      CoordinateConverters.adjusted3dCartesianCoordiantesTo2dRadianCoordiantes(this.adjustedCartesianCoordinates3d)

    observedOutput._1 should equal (expectedOutput._1 +- this.Eps)
    observedOutput._2 should equal (expectedOutput._2 +- this.Eps)
  }

  "lonLatToS2CellID" should "Converts a longitude and latitude to S2CellId object " in
  {
    val expectedOutput = this.s2CellId
    val observedOutput = CoordinateConverters.lonLatToS2CellID(this.cartesianCoordinates2d._1,
      this.cartesianCoordinates2d._2, this.s2lvl)

    observedOutput.id() should equal (expectedOutput)
  }

  "lonLatToS2Cell" should "Converts a longitude and latitude to S2Cell object" in
  {
    val expectedOutput = this.s2CellId
    val observedOutput = CoordinateConverters.lonLatToS2Cell(this.cartesianCoordinates2d._1,
      this.cartesianCoordinates2d._2, this.s2lvl)

    observedOutput.id().id() should equal (expectedOutput)
  }

  "lonLatToS2CellToken" should "Converts a longitude and latitude to S2Cell object" in
  {
    val expectedOutput = this.s2CellToken
    val observedOutput = CoordinateConverters.lonLatToS2CellFullToken(this.cartesianCoordinates2d._1,
      this.cartesianCoordinates2d._2, this.s2lvl)

    observedOutput should equal (expectedOutput)
  }

}
