package blabla

import java.net.URL

import model.Pronoun
import org.htmlcleaner.HtmlCleaner

class Client {

  def request(word: String, pronoun: Pronoun) = {
    val cleaner = new HtmlCleaner
    val root = cleaner.clean(new URL(s"https://en.bab.la/conjugation/german/$word"))
    root.getElementsByAttValue("class", "quick-result-entry", true, true).find {
      x =>
        val option = x.getElementsByAttValue("class", "quick-result-option", true, true).headOption
        option.exists(_.getText.toString == "Infinitiv")
    }.map (
      x => x.getElementsByAttValue("class", "sense-group-results", true, true).head.getText
    )
  }

  def requestGender(word: String): Option[String] = {
    val cleaner = new HtmlCleaner
    val root = cleaner.clean(new URL(s"https://de.bab.la/woerterbuch/deutsch-englisch/$word"))
    root.getElementsByAttValue("class", "suffix", true, true).find {
      x => String.valueOf(x.getText).matches("\\{(Neutrum|Maskulin|Feminin)\\}")
    }.map(x => String.valueOf(x.getText).trim).map {
      case "{Neutrum}" => "N"
      case "{Maskulin}" => "M"
      case "{Feminin}" => "F"
    }
  }

}
