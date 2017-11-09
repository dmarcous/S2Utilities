package com.github.dmarcous.s2utils.s2

import collection.JavaConverters._
import com.google.common.geometry.{S2Cell, S2CellId}
import scala.collection.mutable.ListBuffer

object S2Utilities
{

  // Get cell unique string representation (= full token)
  def getS2CellFullToken(cellId : S2CellId): String =
  {
    BigInt.long2bigInt(cellId.id).toString
  }
  def getS2CellFullToken(cell : S2Cell): String =
  {
    this.getS2CellFullToken(cell.id)
  }

  // Get cell object from a unique representation (= full token)
  def getS2CellIdFromFullToken(cellToken : String): S2CellId =
  {
    new S2CellId(BigInt(cellToken).toLong)
  }
  def getS2CellFromFullToken(cellToken : String): S2Cell =
  {
    new S2Cell(this.getS2CellIdFromFullToken(cellToken))
  }

  // Get center coordinate of cell (longitude, latitude)
  def getCellCenter(cellId : S2CellId): (Double, Double) =
  {
    val centerPoint = cellId.toLatLng
    (centerPoint.lngDegrees, centerPoint.latDegrees)
  }
  def getCellCenter(cell : S2Cell): (Double, Double) =
  {
    this.getCellCenter(cell.id)
  }
  def getCellCenter(cellToken : String): (Double, Double) =
  {
    this.getCellCenter(this.getS2CellIdFromFullToken(cellToken))
  }

  // Get a list of all cell's immediate neighbours on the same level
  def getAllCellNeighbours(cellId : S2CellId): List[S2CellId] =
  {
    var output = ListBuffer[S2CellId]().asJava
    cellId.getAllNeighbors(cellId.level, output)
    output.asScala.toList
  }
  def getAllCellNeighbours(cell : S2Cell): List[S2CellId] =
  {
    this.getAllCellNeighbours(cell.id)
  }
  def getAllCellNeighbours(cellToken : String): List[S2CellId] =
  {
    this.getAllCellNeighbours(this.getS2CellIdFromFullToken(cellToken))
  }

  // Get a list of all cell's immediate neighbours on the same level - token representations
  def getAllCellNeighboursTokens(cellId : S2CellId): List[String] =
  {
    this.getAllCellNeighbours(cellId).map { case(curCell) => this.getS2CellFullToken(curCell)}
  }
  def getAllCellNeighboursTokens(cell: S2Cell): List[String] =
  {
    this.getAllCellNeighboursTokens(cell.id)
  }
  def getAllCellNeighboursTokens(cellToken : String): List[String] =
  {
    this.getAllCellNeighboursTokens(this.getS2CellIdFromFullToken(cellToken))
  }

  // Get containing cell (parent)
  def getCellParent(cellId : S2CellId, level: Int): S2CellId =
  {
    cellId.parent(level)
  }
  def getCellParent(cellId : S2CellId): S2CellId =
  {
    cellId.parent()
  }
  def getCellParent(cell: S2Cell, level: Int): S2Cell =
  {
    new S2Cell(this.getCellParent(cell.id, level))
  }
  def getCellParent(cell: S2Cell): S2Cell =
  {
    new S2Cell(this.getCellParent(cell.id))
  }
  def getCellParent(cellToken : String, level: Int): String =
  {
    this.getS2CellFullToken(this.getCellParent(this.getS2CellIdFromFullToken(cellToken), level))
  }
  def getCellParent(cellToken : String): String =
  {
    this.getS2CellFullToken(this.getCellParent(this.getS2CellIdFromFullToken(cellToken)))
  }
}
