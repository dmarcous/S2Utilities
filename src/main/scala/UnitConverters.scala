package com.s2utils.converters

object UnitConverters
{
  // Geo Constants
  // Earth radius - meters
  val EARTH_RADIUS_METERS = 6378137

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

  // Convert metric distance to angular (WGS84) distance
  def metricToAngularDistance(meters_dist: Double): Double =
  {
    meters_dist * (180/math.Pi) / this.EARTH_RADIUS_METERS
  }

  // Convert angular (WGS84) distance to metric distance
  def angularToMetricDistance(angular_dist: Double): Double =
  {
    angular_dist * (math.Pi/180) * this.EARTH_RADIUS_METERS
  }

}
