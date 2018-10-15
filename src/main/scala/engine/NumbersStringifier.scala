package engine

object NumbersStringifier {

  def Thousand = 1000L
  def Million = Thousand * Thousand
  def Billion = Thousand * Million
  def Trillion = Thousand * Billion

  def nums = Map[Long, String](
    1L -> "ein",
    2L -> "zwei",
    3L -> "drei",
    4L -> "vier",
    5L -> "fünf",
    6L -> "sechs",
    7L -> "sieben",
    8L -> "acht",
    9L -> "neun",
    10L -> "zehn",
    11L -> "elf",
    12L -> "zwölf",
    13L -> "dreizehn",
    14L -> "vierzehn",
    15L -> "fünfzehn",
    16L -> "sechzehn",
    17L -> "siebzehn",
    18L -> "achtzehn",
    19L -> "neunzehn",
    20L -> "zwanzig",
    30L -> "dreißig",
    40L -> "vierzig",
    50L -> "fünfzig",
    60L -> "sechzig",
    70L -> "seibzig",
    80L -> "achtzig",
    90L -> "neunzig",
    100L -> "hundert",
    Thousand -> "tausend"
  )

  def stringlify(longVal: Long): String = {
    val billions = longVal / Billion
    val millions = (longVal % Billion) / Million
    val thousands = (longVal % Million) / Thousand
    val units = longVal % Thousand

    val billionsText: Option[String] = billions match {
      case 0 => None
      case 1 => Some("Milliarde")
      case x => Some(s"${toNumUpToThousand(x)} Milliarden")
    }

    val millionsText: Option[String] = millions match {
      case 0 => None
      case 1 => Some("Million")
      case x => Some(s"${toNumUpToThousand(x)} Millionen")
    }

    val thousendsText: Option[String] = thousands match {
      case 0 => None
      case 1 => Some("tausend")
      case x => Some(s"${toNumUpToThousand(x)}tausend")
    }

    val unitsText: Option[String] = units match {
      case 0 => None
      case x => Some(toNumUpToThousand(x))
    }

    val thousendsAndUnits: Option[String] = Seq(thousendsText, unitsText).collect { case Some(x) => x } match {
      case Seq() => None
      case seq => Some(seq.mkString(""))
    }
    Seq(billionsText, millionsText, thousendsAndUnits).collect{case Some(x) => x} match {
      case Seq() => "Null"
      case seq => seq.mkString(" ")
    }
  }

  private def toNumUpToThousand(num: Long) = {
    val hundredsText = num / 100 match {
      case 0 => None
      case 1 => Some(nums(100L))
      case h => Some(s"${nums(h)}hundert")
    }

    val tensText = num % 100 match {
      case 0L => None
      case n if n >= 20 =>
        val tensText = (num % 100) / 10 match {
          case 0 => None
          case t => Some(nums(t * 10))
        }
        val unitsText = num % 10 match {
          case 0 => None
          case u => Some(nums(u))
        }
        Seq(unitsText, tensText).collect{case Some(x) => x} match {
          case Seq() => None
          case seq => Some(seq.mkString("und"))
        }
      case n => Some(nums(n))
    }
    Seq(hundredsText, tensText).collect{case Some(x) => x} match {
      case Seq() => "Null"
      case seq => seq.mkString("")
    }
  }


}
