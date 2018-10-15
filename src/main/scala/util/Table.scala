package util

case class Table[A1, A2](header: List[A2], data: (A1, List[String])*){
  def get(row: A1, col: A2): Option[String] = data.find(_._1 == row).flatMap(x => Option(x._2.applyOrElse(header.indexOf(col), null)))
}
