package com.github.dmarcous.s2utils.converters

import com.github.dmarcous.s2utils.s2.S2Utilities
import com.google.common.geometry.{S2Cell, S2CellId, S2LatLng}
import com.vividsolutions.jts.geom.{Coordinate, Point}

/** Enclosing objects for [[com.github.dmarcous.s2utils.converters.CoordinateConverters]]
  * methods for converting coordinate objects. */
object CoordinateConverters
{

  /** Convert radian coordinates to degree coordinates
    * inclination = theta
    * azimuth = phi
    *
    *  @param radius radius length
    *  @param inclination inclination angle (theta)
    *  @param azimuth azimuth angle (phi)
    *  @return Cartesian coordinates tuple (x, y, z)
    */
  def radianCoordinatesToCartesianCoordinates(radius: Double, inclination: Double, azimuth: Double) :
    (Double, Double, Double) =
  {
    val x = radius * math.sin(inclination) * math.cos(azimuth)
    val y = radius * math.sin(inclination) * math.sin(azimuth)
    val z = radius * math.cos(inclination)

    (x, y ,z)
  }

  /** Convert radian coordinates to degree coordinates
    * inclination = theta
    * azimuth = phi
    *
    *  @param radianCoordinates (radius - radius length, inclination - inclination angle (theta),
    *                           azimuth - azimuth angle (phi))
    *  @return Cartesian coordinates tuple (x, y, z)
    */
  def radianCoordinatesToCartesianCoordinates(radianCoordinates: (Double, Double, Double)) :
    (Double, Double, Double) =
      this.radianCoordinatesToCartesianCoordinates(radianCoordinates._1, radianCoordinates._2, radianCoordinates._3)

  /** Convert polar coordinates to degree coordinates
    * inclination = theta
    * azimuth = phi
    *
    *  @param radius radius length
    *  @param inclination inclination angle (theta)
    *  @param azimuth azimuth angle (phi)
    *  @return Cartesian coordinates tuple (x, y, z)
    */
  def polarCoordinatesToCartesianCoordinates(radius: Double, inclination: Double, azimuth: Double) :
    (Double, Double, Double) = this.radianCoordinatesToCartesianCoordinates(radius, inclination, azimuth)

  /** Convert polar coordinates to degree coordinates
    * inclination = theta
    * azimuth = phi
    *
    *  @param polarCoordinates (radius - radius length, inclination - inclination angle (theta),
    *                           azimuth - azimuth angle (phi))
    *  @return Cartesian coordinates tuple (x, y, z)
    */
  def polarCoordinatesToCartesianCoordinates(polarCoordinates: (Double, Double, Double)) :
    (Double, Double, Double) =
      this.radianCoordinatesToCartesianCoordinates(polarCoordinates)

  /** Convert degree coordinates to radian coordinates
    *
    *  @param x first component
    *  @param y second component
    *  @param z third component
    *  @return radian coordinates tuple (radius, inclination (theta), azimuth (phi))
    */
  def cartesianCoordinatesToRadianCoordinates(x: Double, y: Double, z: Double) : (Double, Double, Double) =
  {
    val radius = Math.sqrt((x * x) + (y * y) + (z * z))
    val inclination = math.acos(z / radius)
    val azimuth = math.atan2(y, x)

    (radius, inclination, azimuth)
  }

  /** Convert degree coordinates to radian coordinates
    *
    *  @param cartesianCoordinates (x (first component), y (second component), z (third component))
    *  @return radian coordinates tuple (radius, inclination (theta), azimuth (phi))
    */
  def cartesianCoordinatesToRadianCoordinates(cartesianCoordinates: (Double, Double, Double)) :
  (Double, Double, Double) =
    this.cartesianCoordinatesToRadianCoordinates(cartesianCoordinates._1, cartesianCoordinates._2,
                                                 cartesianCoordinates._3)

  /** Convert degree coordinates to polar coordinates
    *
    *  @param x first component
    *  @param y second component
    *  @param z third component
    *  @return polar coordinates tuple (radius, inclination (theta), azimuth (phi))
    */
  def cartesianCoordinatesToPolarCoordinates(x: Double, y: Double, z: Double) : (Double, Double, Double) =
    this.cartesianCoordinatesToRadianCoordinates(x, y, z)

  /** Convert degree coordinates to polar coordinates
    *
    *  @param cartesianCoordinates (x (first component), y (second component), z (third component))
    *  @return polar coordinates tuple (radius, inclination (theta), azimuth (phi))
    */
  def cartesianCoordinatesToPolarCoordinates(cartesianCoordinates: (Double, Double, Double)) :
    (Double, Double, Double) =
      this.cartesianCoordinatesToRadianCoordinates(cartesianCoordinates)

  /** Converts 2d cartesian coordinates to 3d adjusted coordinates to be used with geo functions
    *
    *  @param x first component - longitude
    *  @param y second component - latitude
    *  @return adjusted 3d coordinates tuple (x, y, z)
    */
  def cartesianCoordinates2dTo3d(x: Double, y: Double) : (Double, Double, Double) =
  {
    val radianX = UnitConverters.degreesToRadians(x)
    val radianY = UnitConverters.degreesToRadians(y)

    val adjustedX = math.cos(radianY)* math.cos(radianX)
    val adjustedY = math.cos(radianY)* math.sin(radianX)
    val adjustedZ = math.sin(radianY)

    (adjustedX, adjustedY, adjustedZ)
  }

  /** Converts 2d cartesian coordinates to 3d adjusted coordinates to be used with geo functions
    *
    *  @param cartesianCoordinates (x (first component - longitude), y (second component - latitude))
    *  @return adjusted 3d coordinates tuple (x, y, z)
    */
  def cartesianCoordinates2dTo3d(cartesianCoordinates: (Double, Double)) :
  (Double, Double, Double) =
    this.cartesianCoordinates2dTo3d(cartesianCoordinates._1, cartesianCoordinates._2)

  /** Convert adjusted 3d Cartesian coordinates to 2d radian coordinates
    *
    *  @param x first component - longitude
    *  @param y second component - latitude
    *  @param z generated)
    *  @return adjusted 2d radian coordinates tuple (lon, lat)
    */
  def adjusted3dCartesianCoordinatesTo2dRadianCoordinates(x: Double, y: Double, z: Double) : (Double, Double) =
  {
    val lon = math.atan2(y, x)
    val hyp = math.sqrt((x * x) + (y * y))
    val lat = math.atan2(y, hyp)

    (lon, lat)
  }

  /** Convert adjusted 3d Cartesian coordinates to 2d radian coordinates
    *
    *  @param cartesianCoordinates (x (first component - longitude), y (second component - latitude), z (generated))
    *  @return adjusted 2d radian coordinates tuple (lon, lat)
    */
  def adjusted3dCartesianCoordinatesTo2dRadianCoordinates(cartesianCoordinates: (Double, Double, Double)) :
    (Double, Double) = this.adjusted3dCartesianCoordinatesTo2dRadianCoordinates(
      cartesianCoordinates._1, cartesianCoordinates._2, cartesianCoordinates._3)

  /** Converts a longitude and latitude to S2CellId object
    *
    *  @param lon longitude
    *  @param lat latitude
    *  @param lvl S2Cell level
    *  @return S2CellId object
    */
  def lonLatToS2CellID(lon: Double, lat: Double, lvl: Int): S2CellId =
  {
    S2CellId.fromLatLng(S2LatLng.fromDegrees(lat, lon)).parent(lvl)
  }
  /** Converts a coordinate to S2CellId object
    *
    *  @param coordinate (x = lon, y=lat)
    *  @param lvl S2Cell level
    *  @return S2CellId object
    */
  def coordinateToS2CellID(coordinate: Coordinate, lvl: Int): S2CellId =
  {
    this.lonLatToS2CellID(coordinate.x, coordinate.y, lvl)
  }
  /** Converts a point to S2CellId object
    *
    *  @param point (x = lon, y=lat)
    *  @param lvl S2Cell level
    *  @return S2CellId object
    */
  def pointToS2CellID(point: Point, lvl: Int): S2CellId =
  {
    this.lonLatToS2CellID(point.getX, point.getX, lvl)
  }

  /** Converts a longitude and latitude to S2Cell object
    *
    *  @param lon longitude
    *  @param lat latitude
    *  @param lvl S2Cell level
    *  @return S2Cell object
    */
  def lonLatToS2Cell(lon: Double, lat: Double, lvl: Int): S2Cell =
  {
    new S2Cell(this.lonLatToS2CellID(lon, lat, lvl))
  }
  /** Converts a coordinate to S2Cell object
    *
    *  @param coordinate (x = lon, y=lat)
    *  @param lvl S2Cell level
    *  @return S2Cell object
    */
  def coordinateToS2Cell(coordinate: Coordinate, lvl: Int): S2Cell =
  {
    this.lonLatToS2Cell(coordinate.x, coordinate.y, lvl)
  }
  /** Converts a point to S2Cell object
    *
    *  @param point (x = lon, y=lat)
    *  @param lvl S2Cell level
    *  @return S2Cell object
    */
  def pointToS2Cell(point: Point, lvl: Int): S2Cell =
  {
    this.lonLatToS2Cell(point.getX, point.getX, lvl)
  }

  /** Converts a longitude and latitude to S2Cell full token
    *
    *  @param lon longitude
    *  @param lat latitude
    *  @param lvl S2Cell level
    *  @return S2Cell full token
    */
  def lonLatToS2CellFullToken(lon: Double, lat: Double, lvl: Int): String =
  {
    S2Utilities.getS2CellFullToken(this.lonLatToS2CellID(lon, lat, lvl))
  }
  /** Converts a coordinate to S2Cell full token
    *
    *  @param coordinate (x = lon, y=lat)
    *  @param lvl S2Cell level
    *  @return S2Cell full token
    */
  def coordinateToS2CellFullToken(coordinate: Coordinate, lvl: Int): String =
  {
    this.lonLatToS2CellFullToken(coordinate.x, coordinate.y, lvl)
  }
  /** Converts a point to S2Cell full token
    *
    *  @param point (x = lon, y=lat)
    *  @param lvl S2Cell level
    *  @return S2Cell full token
    */
  def pointToS2CellFullToken(point: Point, lvl: Int): String =
  {
    this.lonLatToS2CellFullToken(point.getX, point.getX, lvl)
  }

}
