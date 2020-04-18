package com.github.dmarcous.s2utils.s2

import com.google.common.geometry.{S2CellId}
import com.github.dmarcous.s2utils.converters.{CoordinateConverters}
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class S2UtilitiesTest extends FlatSpec
{
  val Eps = 1e-4
  val lon1 = 34.777112
  val lat1 = 32.071801
  val lvl = 15
  val lvlParentL13 = 13
  val centerLon = 34.778222
  val centerLat = 32.070811
  val cellIdL15 = CoordinateConverters.lonLatToS2CellID(this.lon1, this.lat1, this.lvl)
  val cellTokenL15 = "1521455259027767296"
  val parentTokenL14 = "1521455257954025472"
  val parentTokenL13 = "1521455245069123584"
  val cellNeighboursIds =
    List[S2CellId](
      S2CellId.fromToken("151d4b7e40000000"),
      S2CellId.fromToken("151d4b7940000000"),
      S2CellId.fromToken("151d4b7ec0000000"),
      S2CellId.fromToken("151d4b81c0000000"),
      S2CellId.fromToken("151d4b7fc0000000"),
      S2CellId.fromToken("151d4b78c0000000"),
      S2CellId.fromToken("151d4b8040000000"),
      S2CellId.fromToken("151d4b8240000000")
    )
  val cellNeighboursTokens =
    List[String](
      "1521455254732800000",
      "1521455233257963520",
      "1521455256880283648",
      "1521455269765185536",
      "1521455261175250944",
      "1521455231110479872",
      "1521455263322734592",
      "1521455271912669184"
    )
  val smallMetricArea = 150.0
  val smallAreaMinimumLvl = 19
  val largeMetricArea = 500.0
  val largeAreaMinimumLvl = 18


  "getS2CellFullToken" should "Get cell unique string representation (= full token)" in
  {
    val expectedOutput = this.cellTokenL15
    val observedOutput = S2Utilities.getS2CellFullToken(this.cellIdL15)

    observedOutput should equal(expectedOutput)
  }

  "getS2CellIdFromFullToken" should "Get cell object from a unique representation (= full token)" in
  {
    val expectedOutput = this.cellIdL15
    val observedOutput = S2Utilities.getS2CellIdFromFullToken(this.cellTokenL15)

    observedOutput should equal(expectedOutput)
  }

  "getCellCenter" should "Get center coordinate of cell (longitude, latitude)" in
  {
    val expectedOutput = (this.centerLon, this.centerLat)
    val observedOutput = S2Utilities.getCellCenter(this.cellTokenL15)

    observedOutput._1 should equal (expectedOutput._1 +- this.Eps)
    observedOutput._2 should equal (expectedOutput._2 +- this.Eps)
  }

  "getCellParent" should "Get immediate containing cell (parent)" in
  {
    val expectedOutput = this.parentTokenL14
    val observedOutput = S2Utilities.getCellParent(this.cellTokenL15)

    observedOutput should equal(expectedOutput)
  }
  it should "Get containing cell (parent) in lvl given" in
  {
    val expectedOutput = this.parentTokenL13
    val observedOutput = S2Utilities.getCellParent(this.cellTokenL15, this.lvlParentL13)

    observedOutput should equal(expectedOutput)
  }

  "getAllCellNeighbours" should "Get a list of all cell's immediate neighbours on the same level" in
  {
    val expectedOutput = this.cellNeighboursIds
    val observedOutput = S2Utilities.getAllCellNeighbours(this.cellTokenL15)

    observedOutput should equal(expectedOutput)
  }
  "getAllCellNeighboursTokens" should "Get a list of all cell's immediate neighbours on the same level" in
  {
    val expectedOutput = this.cellNeighboursTokens
    val observedOutput = S2Utilities.getAllCellNeighboursTokens(this.cellTokenL15)

    observedOutput should equal(expectedOutput)
  }

  "getLevelForArea" should "Get the level enclosing minimally enclosing the given area for a large area" in
  {
    val expectedOutput = this.largeAreaMinimumLvl
    val observedOutput = S2Utilities.getLevelForArea(this.largeMetricArea)

    observedOutput should equal(expectedOutput)
  }
  it should "Get the level enclosing minimally enclosing the given area for a small area" in
  {
    val expectedOutput = this.smallAreaMinimumLvl 
    val observedOutput = S2Utilities.getLevelForArea(this.smallMetricArea)

    observedOutput should equal(expectedOutput)
  }
}
