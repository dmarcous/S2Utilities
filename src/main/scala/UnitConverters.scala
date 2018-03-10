package com.github.dmarcous.s2utils.converters

import com.google.common.geometry.S2Projections

/** Enclosing objects for [[com.github.dmarcous.s2utils.converters.UnitConverters]]
  * methods for converting between units. */
object UnitConverters
{
  // Geo Constants
  // Earth radius - meters
  val EARTH_RADIUS_METERS = 6378137
  // Area constants
  val S2_LVL_0_AREA = S2Projections.MAX_AREA.getValue(0)
  val KM_SQUARED_LVL_0_AREA = 85011012.19
  val M_SQUARED_LVL_0_AREA = KM_SQUARED_LVL_0_AREA/0.0000010000
  val UNIT_SPHERE_AREA_TO_M_SQUARED_AREA_FACTOR = M_SQUARED_LVL_0_AREA/S2_LVL_0_AREA

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

  /** Convert metric area to unit sphere area
    *
    *  @param metricArea area in squared meters
    *  @return area on the unit sphere for S2Cell area calculations
    */
  def metricToUnitSphereArea(metricArea: Double): Double =
  {
    metricArea / UNIT_SPHERE_AREA_TO_M_SQUARED_AREA_FACTOR
  }

  /** Convert unit sphere to metric area area
    *
    *  @param unitSphereArea area on the unit sphere
    *  @return area in squared meters
    */
  def unitSphereToMetricArea(unitSphereArea: Double): Double =
  {
    unitSphereArea * UNIT_SPHERE_AREA_TO_M_SQUARED_AREA_FACTOR
  }
}
