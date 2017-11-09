package com.github.dmarcous.s2utils.converters

/** Enclosing objects for [[com.github.dmarcous.s2utils.converters.UnitConverters]]
  * methods for converting between units. */
object UnitConverters
{
  // Geo Constants
  // Earth radius - meters
  val EARTH_RADIUS_METERS = 6378137

  /** Convert degrees to radians
    *
    *  @param degrees degree value
    *  @return radians value
    */
  def degreesToRadians(degrees: Double): Double =
  {
      math.toRadians(degrees)
  }

  /** Convert radians to degrees
    *
    *  @param radians radians value
    *  @return degrees value
    */
  def radiansToDegrees (radians: Double): Double =
  {
      math.toDegrees(radians)
  }

  /** Convert metric distance to angular (WGS84) distance
    *
    *  @param meters_dist distance in meters
    *  @return distance in angle units
    */
  def metricToAngularDistance(meters_dist: Double): Double =
  {
    meters_dist * (180/math.Pi) / this.EARTH_RADIUS_METERS
  }

  /** Convert angular (WGS84) distance to metric distance
    *
    *  @param angular_dist distance in angles
    *  @return distance in meter units
    */
  def angularToMetricDistance(angular_dist: Double): Double =
  {
    angular_dist * (math.Pi/180) * this.EARTH_RADIUS_METERS
  }

}
