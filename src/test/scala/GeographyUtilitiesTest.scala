package com.github.dmarcous.s2utils.geo

import com.vividsolutions.jts.geom.{Coordinate, GeometryFactory, Point, Polygon}
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class GeographyUtilitiesTest extends FlatSpec
{
  val Eps = 1e-4
  val gf: GeometryFactory = GeographyUtilities.createGeometryFactory()
  val lon1 = 34.777112
  val lat1 = 32.071801
  val lon2 = 34.775912
  val lat2 = 32.074929
  val metersTolerance = 5.0
  val geomPoint: Point = this.gf.createPoint(new Coordinate(34.777112, 32.0718015))
  val coordArray: Array[Coordinate] = Array[Coordinate](
    new Coordinate(34.77902663579209, 32.07188370752443),
    new Coordinate(34.77835901861101, 32.072536041862776),
    new Coordinate(34.77877460943262, 32.073301695735104),
    new Coordinate(34.77896149333242, 32.07336861791771),
    new Coordinate(34.77921050340524, 32.07340207899063),
    new Coordinate(34.77915910606839, 32.073262805671305),
    new Coordinate(34.779080886641395, 32.07336899706137),
    new Coordinate(34.77919578626347, 32.07339336685584),
    new Coordinate(34.779425585507624, 32.07344210642529),
    new Coordinate(34.780119063284474, 32.07268856587259),
    new Coordinate(34.77902663579209, 32.07188370752443)
  )
  val pointArray: Array[Point] =
    Array[Point](
      this.gf.createPoint(new Coordinate(34.77902663579209, 32.07188370752443)),
      this.gf.createPoint(new Coordinate(34.77835901861101, 32.072536041862776)),
      this.gf.createPoint(new Coordinate(34.77877460943262, 32.073301695735104)),
      this.gf.createPoint(new Coordinate(34.77896149333242, 32.07336861791771)),
      this.gf.createPoint(new Coordinate(34.77921050340524, 32.07340207899063)),
      this.gf.createPoint(new Coordinate(34.77915910606839, 32.073262805671305)),
      this.gf.createPoint(new Coordinate(34.779080886641395, 32.07336899706137)),
      this.gf.createPoint(new Coordinate(34.77919578626347, 32.07339336685584)),
      this.gf.createPoint(new Coordinate(34.779425585507624, 32.07344210642529)),
      this.gf.createPoint(new Coordinate(34.780119063284474, 32.07268856587259)),
      this.gf.createPoint(new Coordinate(34.77902663579209, 32.07188370752443)))
  val geomPolygon: Polygon =
    this.gf.createPolygon(Array[Coordinate](new Coordinate(34.7787880897522, 32.07328373362353),
    new Coordinate(34.778380393981934, 32.072529153467684), new Coordinate(34.77901339530945, 32.071901847151985),
    new Coordinate(34.780097007751465, 32.0726927978857), new Coordinate(34.77942109107971, 32.0734201028668),
    new Coordinate(34.7787880897522, 32.07328373362353)))
  val geomComplexPolygon: Polygon =
    this.gf.createPolygon(Array[Coordinate](
      new Coordinate(34.77902663579209, 32.07188370752443),
      new Coordinate(34.77835901861101, 32.072536041862776),
      new Coordinate(34.77877460943262, 32.073301695735104),
      new Coordinate(34.77896149333242, 32.07336861791771),
      new Coordinate(34.77921050340524, 32.07340207899063),
      new Coordinate(34.77915910606839, 32.073262805671305),
      new Coordinate(34.779080886641395, 32.07336899706137),
      new Coordinate(34.77919578626347, 32.07339336685584),
      new Coordinate(34.779425585507624, 32.07344210642529),
      new Coordinate(34.780119063284474, 32.07268856587259),
      new Coordinate(34.77902663579209, 32.07188370752443)
    ))
  val geomSimplePolygon: Polygon =
    this.gf.createPolygon(Array[Coordinate](
      new Coordinate(34.77903995683976, 32.071865626978045),
      new Coordinate(34.778337647698756, 32.0725429440782), new Coordinate(34.77875999301838, 32.07331874615749),
      new Coordinate(34.77895798091366, 32.07339079942764), new Coordinate(34.77943084798882, 32.07346393903302),
      new Coordinate(34.78014112009905, 32.072684340544306), new Coordinate(34.77903995683976, 32.071865626978045)))
  val geomBoxedPolygon: Polygon =
    this.gf.createPolygon(Array[Coordinate](new Coordinate(34.778380393981934, 32.07187938926988),
    new Coordinate(34.778364513861206, 32.073435982987526), new Coordinate(34.78011288787219, 32.073435982987526),
    new Coordinate(34.78011288787219, 32.07188596703126), new Coordinate(34.778380393981934, 32.07187938926988)))
  val WKBPoint: Array[Byte] =
    Array[Byte](0, 0, 0, 0, 1, 64, 65, 99, 120, 103, -16, -86, 34, 64, 64, 9, 48, -54, -93, 38, -31)
  val WKTPoint: String = "POINT (34.777112 32.0718015)"
  val WKTPolygon: String = "POLYGON ((34.7787880897522 32.07328373362353, 34.778380393981934 32.072529153467684, " +
    "34.77901339530945 32.071901847151985, 34.780097007751465 32.0726927978857, " +
    "34.77942109107971 32.0734201028668, 34.7787880897522 32.07328373362353))"

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
  it should "create a polygon geometry" in
  {
    val observedOutput = GeographyUtilities.WKTtoGeometry(this.WKTPolygon, this.gf)

    observedOutput.isInstanceOf[Polygon] should be (true)
  }

  "geometryToWKT" should "create a point geometry WKT" in
  {
    val expectedOutput = this.WKTPoint
    val observedOutput = GeographyUtilities.geometryToWKT(this.geomPoint)

    observedOutput should equal(expectedOutput)
  }
  it should "create a polygon geometry WKT" in
  {
    val expectedOutput = this.WKTPolygon
    val observedOutput = GeographyUtilities.geometryToWKT(this.geomPolygon)

    observedOutput should equal(expectedOutput)
  }

  "geometryToWKB" should "create a point geometry WKB" in
  {
    val expectedOutput = this.WKBPoint
    val observedOutput = GeographyUtilities.geometryToWKB(this.geomPoint)

    observedOutput should equal(expectedOutput)
  }

  "WKBtoGeometry" should "Convert WKB representation of a point" in
  {
    val expectedOutput = this.geomPoint
    val observedOutput = GeographyUtilities.WKBtoGeometry(this.WKBPoint, this.gf)

    observedOutput should equal(expectedOutput)
  }

  "haversineDistance" should "Calculate the haversine (sphere / geo) distance between 2 coordinates" in
  {
    val expectedOutput = 366.14388
    val observedOutput = GeographyUtilities.haversineDistance(this.lon1, this.lat1, this.lon2, this.lat2)

    observedOutput should equal(expectedOutput +- this.Eps)
  }

  "simplifyGeometry" should "Get a simplified geometry" in
  {
    val expectedOutput = this.geomSimplePolygon
    val observedOutput = GeographyUtilities.simplifyGeometry(this.geomComplexPolygon)

    observedOutput should equal(expectedOutput)
  }

  "boxSimplifyGeometry" should "Get a rectangular simplified geometry" in
  {
    val expectedOutput = this.geomBoxedPolygon
    val observedOutput = GeographyUtilities.boxSimplifyGeometry(this.geomPolygon)

    observedOutput should equal(expectedOutput)
  }

  "mergeCoordinates" should "Merge multiple coordinates into a single simplified geometry" in
  {
    val expectedOutput = this.geomSimplePolygon
    val observedOutput = GeographyUtilities.mergeCoordinates(this.coordArray, this.gf, this.metersTolerance)

    observedOutput should equal(expectedOutput)
  }
  it should "Merge multiple points into a single simplified geometry" in
  {
    val expectedOutput = this.geomSimplePolygon
    val observedOutput = GeographyUtilities.mergeCoordinates(this.pointArray, this.gf, this.metersTolerance)

    observedOutput should equal(expectedOutput)
  }
}
