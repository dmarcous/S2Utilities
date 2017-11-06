package com.S2Utilities.geo

import com.vividsolutions.jts.geom.{Point, Polygon}
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class GeographyUtilitiesTest extends FlatSpec
{
  val Eps = 1e-4
  val gf = GeographyUtilities.createGeometryFactory()
  val WKTPoint = "POINT (32,34)"
  val WKTPolygon = "POLYGON ((32,34), (32,34), (32,34), (32,34))"

  "createGeometryFactory" should "create a common GPS geography object" in
  {
    val expectedOutput = GeographyUtilities.SRID
    val observedOutput = GeographyUtilities.createGeometryFactory()

    observedOutput.getSRID should equal (expectedOutput)
  }

  "WKTtoGeometry" should "create a point geometry" in
  {
    val observedOutput = GeographyUtilities.WKTtoGeometry(this.WKTPoint, this.gf)

    observedOutput.isInstanceOf[Point] should be (true)
  }
  "WKTtoGeometry" should "create a polygon geometry" in
  {
    val observedOutput = GeographyUtilities.WKTtoGeometry(this.WKTPolygon, this.gf)

    observedOutput.isInstanceOf[Polygon] should be (true)
  }






}
