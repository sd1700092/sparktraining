package impatient2.chapter9

object Regex {
  def main(args: Array[String]): Unit = {
    val numPattern = "[0-9]+".r
    val wsnumwsPattern = """\s+[0-9]+\s+""".r
    for(matchString <- numPattern.findAllIn("99 bottles, 98 bottles"))
      println(matchString)
    val firstMatch = wsnumwsPattern.findFirstIn("99 bottles, 98 bottles")
    numPattern.replaceFirstIn("99 bottles, 98 bottles", "XX")
    numPattern.replaceAllIn("99 bottles, 98 bottles", "XX")
    numPattern.replaceSomeIn("99 bottles, 98 bottles",
      m=> if(m.matched.toInt % 2==0) Some("XX") else None
    )

    val numitemPattern = "([0-9]+) ([a-z]+)".r
    for(m<-numitemPattern.findAllMatchIn("99 bottles, 98 bottles"))
      println(m.group(1))

    val numitemPattern(num, item) = "99 bottles"
    for(numitemPattern(num, item) <- numitemPattern.findAllIn("99 bottles, 98 bottles"))
      println(f"$num, $item")
  }
}
