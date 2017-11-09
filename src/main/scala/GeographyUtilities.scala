package com.github.dmarcous.s2utils.geo

import com.github.dmarcous.s2utils.converters.UnitConverters
import com.vividsolutions.jts.geom._
import com.vividsolutions.jts.io.{WKBReader, WKBWriter, WKTReader, WKTWriter}
import com.vividsolutions.jts.simplify.TopologyPreservingSimplifier

/** Enclosing objects for [[com.github.dmarcous.s2utils.geo.GeographyUtilities]]
  * methods for handling [[com.vividsolutions.jts.geom.Geometry]] objects. */
object GeographyUtilities
{
  // Use degrees SRID used by common GPS (WGS84)
  val SRID : Int = 4326

  /** Create a new geography factory object to be used for other geo functions
    *
    */
  def createGeometryFactory() : GeometryFactory =
  {
    new GeometryFactory(new PrecisionModel(PrecisionModel.maximumPreciseValue), this.SRID)
  }

  /** Convert WKT strings to a geometry object
    *
    *  @param WKT a WKT string representation of a geometry
    *  @param gf Geometry Factory (see : [[GeographyUtilities.createGeometryFactory]]
    *  @return a geometry object
    */
  def WKTtoGeometry(WKT: String, gf: GeometryFactory) : Geometry =
  {
    val reader = new WKTReader(gf)
    reader.read(WKT)
  }

  /** Convert WKB strings to a geometry object
    *
    *  @param WKB a WKB byte array representation of a geometry
    *  @param gf Geometry Factory (see : [[GeographyUtilities.createGeometryFactory]]
    *  @return a geometry object
    */
  def WKBtoGeometry(WKB: Array[Byte], gf: GeometryFactory) : Geometry =
  {
    val reader = new WKBReader(gf)
    reader.read(WKB)
  }

  /** Create a WKT representation of a geometry
    *
    *  @param geometry a geometry object
    *  @return a WKT string representation of a geometry
    */
  def geometryToWKT(geometry: Geometry) : String =
  {
    val writer = new WKTWriter()
    writer.write(geometry)
  }

  /** Create a WKB representation of a geometry
    *
    *  @param geometry a geometry object
    *  @return a WKB string representation of a geometry
    */
  def geometryToWKB(geometry: Geometry) : Array[Byte] =
  {
    val writer = new WKBWriter()
    writer.write(geometry)
  }

  /** Calculate the haversine (sphere / geo) distance between 2 coordinates
    *
    *  @param lon1 coordinate 1 - longitude
    *  @param lat1 coordinate 1 - latitude
    *  @param lon2 coordinate 2 - longitude
    *  @param lat2 coordinate 2 - latitude
    *  @return distance in meters between the 2 coordinates given
    */
  def haversineDistance(lon1: Double, lat1: Double, lon2: Double, lat2: Double): Double =
  {

    val dLat = (lat2 - lat1).toRadians
    val dLon = (lon2 - lon1).toRadians

    val a = math.pow(math.sin(dLat / 2), 2) +
      (math.cos(lat1.toRadians) * math.cos(lat2.toRadians) * math.pow(math.sin(dLon / 2), 2))
    val c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))

    c * UnitConverters.EARTH_RADIUS_METERS
  }
  /** Calculate the haversine (sphere / geo) distance between 2 coordinates
    *
    *  @param coordinate1 (longitude, latitude)
    *  @param coordinate2 (longitude, latitude)
    *  @return distance in meters between the 2 coordinates given
    */
  def haversineDistance(coordinate1: Coordinate, coordinate2: Coordinate): Double =
    this.haversineDistance(coordinate1.x, coordinate1.y, coordinate2.x, coordinate2.y)
  /** Calculate the haversine (sphere / geo) distance between 2 points
    *
    *  @param point1 (longitude, latitude)
    *  @param point2 (longitude, latitude)
    *  @return distance in meters between the 2 points given
    */
  def haversineDistance(point1: Point, point2: Point): Double =
    this.haversineDistance(point1.getX, point1.getY, point2.getX, point2.getY)

  /** Get a simplified expanded geometry, close to the original one
    *
    *  @param geometry a geometry object
    *  @param metersTolerance maximal metric distance from geometry convex to be used for simplification
    *  @return a simplified geometry object
    */
  def simplifyGeometry(geometry: Geometry, metersTolerance: Double = 5.0): Geometry =
  {
    TopologyPreservingSimplifier.simplify(
      (geometry.convexHull().buffer(UnitConverters.metricToAngularDistance(metersTolerance/2))) ,
      UnitConverters.metricToAngularDistance(metersTolerance/2))
  }
  /** Get a rectangular simplified expanded geometry, close to the original one
    *
    *  @param geometry a geometry object
    *  @param metersTolerance maximal metric distance from geometry convex to be used for simplification
    *  @return a rectangular simplified geometry object
    */
  def boxSimplifyGeometry(geometry: Geometry, metersTolerance: Double = 5.0): Geometry =
  {
    TopologyPreservingSimplifier.simplify(
      (geometry.getEnvelope.buffer(UnitConverters.metricToAngularDistance(metersTolerance/2))) ,
      UnitConverters.metricToAngularDistance(metersTolerance/2))
  }

  /** Merge multiple coordinates into a single simplified geometry
    *
    *  @param coordinates a collection of coordinates
    *  @param gf Geometry Factory (see : [[GeographyUtilities.createGeometryFactory]]
    *  @param metersTolerance maximal metric distance from geometry convex to be used for simplification
    *  @return a simplified geometry object
    */
  def mergeCoordinates(coordinates: Array[Coordinate], gf: GeometryFactory, metersTolerance: Double): Geometry =
  {
    this.simplifyGeometry(gf.createPolygon(coordinates), metersTolerance)
  }
  /** Merge multiple points into a single simplified geometry
    *
    *  @param coordinates a collection of points (lon, lat)
    *  @param gf Geometry Factory (see : [[GeographyUtilities.createGeometryFactory]]
    *  @param metersTolerance maximal metric distance from geometry convex to be used for simplification
    *  @return a simplified geometry object
    */
  def mergeCoordinates(coordinates: Array[Point], gf: GeometryFactory, metersTolerance: Double): Geometry =
  {
    this.simplifyGeometry(gf.createMultiPoint(coordinates).convexHull, metersTolerance)
  }
  /** Merge multiple coordinates into a single simplified geometry
    *
    *  @param coordinates a collection of tuples (lon, lat)
    *  @param gf Geometry Factory (see : [[GeographyUtilities.createGeometryFactory]]
    *  @param metersTolerance maximal metric distance from geometry convex to be used for simplification
    *  @return a simplified geometry object
    */
  def mergeCoordinates(coordinates: Array[(Double, Double)], gf: GeometryFactory, metersTolerance: Double = 5.0): Geometry =
  {
    val geoCoordinates = coordinates.map{case(lon: Double, lat: Double) => new Coordinate(lon, lat)}
    this.mergeCoordinates(geoCoordinates, gf, metersTolerance)
  }
}
