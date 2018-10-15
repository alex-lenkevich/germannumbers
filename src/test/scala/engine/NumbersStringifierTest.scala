package engine

import org.testng.annotations.Test
import org.testng.Assert._

class NumbersStringifierTest {

  @Test def testConvert: Unit = {
    assertEquals(NumbersStringifier.stringlify(1001001001L), "Milliarde Million tausendein")
    assertEquals(NumbersStringifier.stringlify(111222333444L), "hundertelf Milliarden zweihundertzweiundzwanzig Millionen dreihundertdreiunddreißigtausendvierhundertvierundvierzig")
    assertEquals(NumbersStringifier.stringlify(234567890123L), "zweihundertvierunddreißig Milliarden fünfhundertsiebenundsechzig Millionen achthundertneunzigtausendhundertdreiundzwanzig")
    assertEquals(NumbersStringifier.stringlify(0L), "Null")
    NumbersStringifier.nums.foreach {
      case (num, text) => assertEquals(NumbersStringifier.stringlify(num), text)
    }

    assertEquals(NumbersStringifier.stringlify(NumbersStringifier.Billion), "Milliarde")
    assertEquals(NumbersStringifier.stringlify(NumbersStringifier.Million), "Million")
    assertEquals(NumbersStringifier.stringlify(NumbersStringifier.Thousand), "tausend")
  }

}
