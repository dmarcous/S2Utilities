package com.S2Utilities.geo

import com.S2Utilities.converters.UnitConverters
import com.vividsolutions.jts.geom._
import com.vividsolutions.jts.io.{WKBReader, WKBWriter, WKTReader, WKTWriter}

object GeographyUtilities
{
  // Use degrees SRID used by common GPS (WGS84)
  val SRID : Int = 4326

  def createGeometryFactory() : GeometryFactory =
  {
    new GeometryFactory(new PrecisionModel(PrecisionModel.maximumPreciseValue), this.SRID)
  }

  def WKTtoGeometry(WKT: String, gf: GeometryFactory) : Geometry =
  {
    val reader = new WKTReader(gf)
    reader.read(WKT)
  }

  def WKBtoGeometry(WKB: Array[Byte], gf: GeometryFactory) : Geometry =
  {
    val reader = new WKBReader(gf)
    reader.read(WKB)
  }

  def geometryToWKT(geometry: Geometry) : String =
  {
    val writer = new WKTWriter()
    writer.write(geometry)
  }

  def geometryToWKB(geometry: Geometry) : Array[Byte] =
  {
    val writer = new WKBWriter()
    writer.write(geometry)
  }

  // Calculate the haversine (sphere / geo) distance between 2 coordinates
  def haversineDistance(lon1: Double, lat1: Double, lon2: Double, lat2: Double): Double =
  {

    val dLat = (lat2 - lat1).toRadians
    val dLon = (lon2 - lon1).toRadians

    val a = math.pow(math.sin(dLat / 2), 2) +
      (math.cos(lat1) * math.cos(lat2) * math.pow(math.sin(dLon / 2), 2))
    val c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))

    c * UnitConverters.EARTH_RADIUS_METERS
  }
  // Coordinate (x=lon, y=lat)
  def haversineDistance(coordinate1: Coordinate, coordinate2: Coordinate): Double =
    this.haversineDistance(coordinate1.x, coordinate1.y, coordinate2.x, coordinate2.y)
  // Point (x=lon, y=lat)
  def haversineDistance(point1: Point, point2: Point): Double =
    this.haversineDistance(point1.getX, point1.getY, point2.getX, point2.getY)

  // TODO : simplify

}
