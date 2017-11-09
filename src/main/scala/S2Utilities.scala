package com.github.dmarcous.s2utils.s2

import collection.JavaConverters._
import com.google.common.geometry.{S2Cell, S2CellId}
import scala.collection.mutable.ListBuffer

/** Enclosing objects for [[com.github.dmarcous.s2utils.s2.S2Utilities]]
  * methods for handling S2Cells and related S2 family objects  */
object S2Utilities
{

  /** Get cell unique string representation (= full token)
    * Full token is as string representation of the cell's ID as a "bigInt" numeric
    *
    *  @param cellId S2CellId object
    *  @return full token string representation
    */
  def getS2CellFullToken(cellId : S2CellId): String =
  {
    BigInt.long2bigInt(cellId.id).toString
  }
  /** Get cell unique string representation (= full token)
    * Full token is as string representation of the cell's ID as a "bigInt" numeric
    *
    *  @param cell S2Cell object
    *  @return full token string representation
    */
  def getS2CellFullToken(cell : S2Cell): String =
  {
    this.getS2CellFullToken(cell.id)
  }

  /** Get cell object from a unique representation (= full token)
    * Full token is as string representation of the cell's ID as a "bigInt" numeric
    *
    *  @param cellToken full token string representation
    *  @return S2CellId object
    */
  def getS2CellIdFromFullToken(cellToken : String): S2CellId =
  {
    new S2CellId(BigInt(cellToken).toLong)
  }
  /** Get cell object from a unique representation (= full token)
    * Full token is as string representation of the cell's ID as a "bigInt" numeric
    *
    *  @param cellToken full token string representation
    *  @return S2Cell object
    */
  def getS2CellFromFullToken(cellToken : String): S2Cell =
  {
    new S2Cell(this.getS2CellIdFromFullToken(cellToken))
  }

  /** Get center coordinate of cell (longitude, latitude)
    *
    *  @param cellId S2CellId object
    *  @return cell's center coordinate tuple (longitude, latitude)
    */
  def getCellCenter(cellId : S2CellId): (Double, Double) =
  {
    val centerPoint = cellId.toLatLng
    (centerPoint.lngDegrees, centerPoint.latDegrees)
  }
  /** Get center coordinate of cell (longitude, latitude)
    *
    *  @param cell S2Cell object
    *  @return cell's center coordinate tuple (longitude, latitude)
    */
  def getCellCenter(cell : S2Cell): (Double, Double) =
  {
    this.getCellCenter(cell.id)
  }
  /** Get center coordinate of cell (longitude, latitude)
    *
    *  @param cellToken S2Cell full token
    *  @return cell's center coordinate tuple (longitude, latitude)
    */
  def getCellCenter(cellToken : String): (Double, Double) =
  {
    this.getCellCenter(this.getS2CellIdFromFullToken(cellToken))
  }

  /** Get a list of all cell's immediate neighbours on the same level
    *
    *  @param cellId S2CellId object
    *  @return list of neighbours represented as S2CellId objects
    */
  def getAllCellNeighbours(cellId : S2CellId): List[S2CellId] =
  {
    var output = ListBuffer[S2CellId]().asJava
    cellId.getAllNeighbors(cellId.level, output)
    output.asScala.toList
  }
  /** Get a list of all cell's immediate neighbours on the same level
    *
    *  @param cell S2Cell object
    *  @return list of neighbours represented as S2CellId objects
    */
  def getAllCellNeighbours(cell : S2Cell): List[S2CellId] =
  {
    this.getAllCellNeighbours(cell.id)
  }
  /** Get a list of all cell's immediate neighbours on the same level
    *
    *  @param cellToken S2Cell full token
    *  @return list of neighbours represented as S2CellId objects
    */
  def getAllCellNeighbours(cellToken : String): List[S2CellId] =
  {
    this.getAllCellNeighbours(this.getS2CellIdFromFullToken(cellToken))
  }

  /** Get a list of all cell's immediate neighbours on the same level - full token representations
    *
    *  @param cellId S2CellId object
    *  @return list of neighbours represented as S2Cell full tokens
    */
  def getAllCellNeighboursTokens(cellId : S2CellId): List[String] =
  {
    this.getAllCellNeighbours(cellId).map { case(curCell) => this.getS2CellFullToken(curCell)}
  }
  /** Get a list of all cell's immediate neighbours on the same level - full token representations
    *
    *  @param cell S2Cell object
    *  @return list of neighbours represented as S2Cell full tokens
    */
  def getAllCellNeighboursTokens(cell: S2Cell): List[String] =
  {
    this.getAllCellNeighboursTokens(cell.id)
  }
  /** Get a list of all cell's immediate neighbours on the same level - full token representations
    *
    *  @param cellToken S2Cell full token
    *  @return list of neighbours represented as S2Cell full tokens
    */
  def getAllCellNeighboursTokens(cellToken : String): List[String] =
  {
    this.getAllCellNeighboursTokens(this.getS2CellIdFromFullToken(cellToken))
  }

  /** Get containing cell (parent) at a given level
    *
    *  @param cellId S2CellId object
    *  @param level containing S2Cell's level
    *  @return parent S2CellId object
    */
  def getCellParent(cellId : S2CellId, level: Int): S2CellId =
  {
    cellId.parent(level)
  }
  /** Get immediate (current level - 1) containing cell (parent)
    *
    *  @param cellId S2CellId object
    *  @return parent S2CellId object
    */
  def getCellParent(cellId : S2CellId): S2CellId =
  {
    cellId.parent()
  }
  /** Get containing cell (parent) at a given level
    *
    *  @param cell S2Cell object
    *  @param level containing S2Cell's level
    *  @return parent S2Cell object
    */
  def getCellParent(cell: S2Cell, level: Int): S2Cell =
  {
    new S2Cell(this.getCellParent(cell.id, level))
  }
  /** Get immediate (current level - 1) containing cell (parent)
    *
    *  @param cell S2CellId object
    *  @return parent S2Cell object
    */
  def getCellParent(cell: S2Cell): S2Cell =
  {
    new S2Cell(this.getCellParent(cell.id))
  }
  /** Get containing cell (parent) at a given level
    *
    *  @param cellToken full token string representation
    *  @param level containing S2Cell's level
    *  @return parent S2Cell full token
    */
  def getCellParent(cellToken : String, level: Int): String =
  {
    this.getS2CellFullToken(this.getCellParent(this.getS2CellIdFromFullToken(cellToken), level))
  }
  /** Get immediate (current level - 1) containing cell (parent)
    *
    *  @param cellToken full token string representation
    *  @return parent S2Cell full token
    */
  def getCellParent(cellToken : String): String =
  {
    this.getS2CellFullToken(this.getCellParent(this.getS2CellIdFromFullToken(cellToken)))
  }
}
