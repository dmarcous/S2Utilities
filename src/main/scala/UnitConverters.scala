package com.S2Utilities.converters

object UnitConverters
{
  // Geo Constants
  // Earth radius - meters
  val EARTH_RADIUS = 6378137

  // Convert degrees to radians
  def degreesToRadians(degrees: Double) : Double =
  {
      math.toRadians(degrees)
  }

  // Convert radians to degrees
  def radiansToDegrees (radians: Double) : Double =
  {
      math.toDegrees(radians)
  }
  // Convert radian coordinates to degree coordinates
  // inclination = theta
  // azimuth = phi
  def radianCoordinatesToCartesianCoordinates(radius: Double, inclination: Double, azimuth: Double) :
    (Double, Double, Double) =
  {
    val x = radius * math.sin(inclination) * math.cos(azimuth)
    val y = radius * math.sin(inclination) * math.sin(azimuth)
    val z = radius * math.cos(inclination)

    (x, y ,z)
  }

  def radianCoordinatesToCartesianCoordinates(radianCoordinates: (Double, Double, Double)) :
    (Double, Double, Double) =
      this.radianCoordinatesToCartesianCoordinates(radianCoordinates._1, radianCoordinates._2, radianCoordinates._3)

  def polarCoordinatesToCartesianCoordinates(radius: Double, inclination: Double, azimuth: Double) :
    (Double, Double, Double) = this.radianCoordinatesToCartesianCoordinates(radius, inclination, azimuth)

  def polarCoordinatesToCartesianCoordinates(polarCoordinates: (Double, Double, Double)) :
    (Double, Double, Double) =
      this.radianCoordinatesToCartesianCoordinates(polarCoordinates)

  // Convert degree coordinates to radian coordinates
  // inclination = theta
  // azimuth = phi
  def cartesianCoordinatesToRadianCoordinates(x: Double, y: Double, z: Double) : (Double, Double, Double) =
  {
    val radius = Math.sqrt((x * x) + (y * y) + (z * z))
    val inclination = math.acos(z / radius)
    val azimuth = math.atan2(y, x)

    (radius, inclination, azimuth)
  }

  def cartesianCoordinatesToRadianCoordinates(cartesianCoordinates: (Double, Double, Double)) :
  (Double, Double, Double) =
    this.cartesianCoordinatesToRadianCoordinates(cartesianCoordinates._1, cartesianCoordinates._2,
                                                 cartesianCoordinates._3)

  def cartesianCoordinatesToPolarCoordinates(x: Double, y: Double, z: Double) : (Double, Double, Double) =
    this.cartesianCoordinatesToRadianCoordinates(x, y, z)

  def cartesianCoordinatesToPolarCoordinates(cartesianCoordinates: (Double, Double, Double)) :
    (Double, Double, Double) =
      this.cartesianCoordinatesToRadianCoordinates(cartesianCoordinates)

  // Converts 2d cartesian coordiantes to 3d adjusted coordiantes to be used with geo functions
  def cartesianCoordinates2dTo3d(x: Double, y: Double) : (Double, Double, Double) =
  {
    val radianX = this.degreesToRadians(x)
    val radianY = this.degreesToRadians(y)

    val adjustedX = math.cos(radianY)* math.cos(radianX)
    val adjustedY = math.cos(radianY)* math.sin(radianX)
    val adjustedZ = math.sin(radianY)

    (adjustedX, adjustedY, adjustedZ)
  }

  def cartesianCoordinates2dTo3d(cartesianCoordinates: (Double, Double)) :
  (Double, Double, Double) =
    this.cartesianCoordinates2dTo3d(cartesianCoordinates._1, cartesianCoordinates._2)

  // Convert adjusted 3d Cartesian coordinates to 2d radian coordianted
  def adjusted3dCartesianCoordiantesTo2dRadianCoordiantes(x: Double, y: Double, z: Double) : (Double, Double) =
  {
    val lon = math.atan2(y, x)
    val hyp = math.sqrt((x * x) + (y * y))
    val lat = math.atan2(y, hyp)

    (lon, lat)
  }

  def adjusted3dCartesianCoordiantesTo2dRadianCoordiantes(cartesianCoordinates: (Double, Double, Double)) :
    (Double, Double) = this.adjusted3dCartesianCoordiantesTo2dRadianCoordiantes(
      cartesianCoordinates._1, cartesianCoordinates._2, cartesianCoordinates._3)

  // Convert metric distance to angular (WGS84) distance
  def metricToAngularDistance(meters_dist: Double): Double =
  {
    meters_dist * (180/math.Pi) / this.EARTH_RADIUS
  }

  // Convert angular (WGS84) distance to metric distance
  def angularToMetricDistance(angular_dist: Double): Double =
  {
    angular_dist * (math.Pi/180) * this.EARTH_RADIUS
  }

}
