package impatient2.chapter8

class Point(val x: Int, val y: Double) {

}

class LabeledPoint(val label: String, override val x: Int, override val y: Double) extends Point(x, y) {

}