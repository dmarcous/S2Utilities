package com.S2Utilities.s2

import collection.JavaConverters._
import com.google.common.geometry.{S2Cell, S2CellId}
import scala.collection.mutable.ListBuffer

object S2Utilities
{

  def getS2CellFullToken(cellId : S2CellId): String =
  {
    BigInt.long2bigInt(cellId.id).toString
  }

  // TODO : get s2cell from token
  def getS2CellIdFromFullToken(cellToken : String): S2CellId =
  {
    new S2CellId(BigInt(cellToken).toLong)
  }
  def getS2CellFromFullToken(cellToken : String): S2Cell =
  {
    new S2Cell(new S2CellId(BigInt(cellToken).toLong))
  }

  // TODO : get metric avg area


  // TODO : get center point from cell
  // TODO : get center point from token

  // TODO : get neighbors from cell
  def getAllCellNeighbours(cell : S2Cell): List[S2CellId] =
  {
    var output = ListBuffer[S2CellId]().asJava
    val cellId = cell.id
    cellId.getAllNeighbors(cellId.level, output)
    output.asScala.toList
  }
  def getAllCellNeighbours(cellToken : String): List[String] =
  {
    var output = ListBuffer[S2CellId]().asJava
    val cellId = this.getS2CellIdFromFullToken(cellToken)
    cellId.getAllNeighbors(cellId.level, output)
    output.asScala.map { case(curCell) => this.getS2CellFullToken(curCell)}.toList
  }


  // TODO : get containing cell from cell
  // TODO : get containing cell token from cell token


}
