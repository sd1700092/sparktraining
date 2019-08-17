package impatient2.chapter6

class UnitConversion(val factor: Double) {
  private def convert(value: Double):Double = value * factor

  def apply(value: Double):Double = convert(value)

}

object InchesToCentimeters extends UnitConversion(2.54) {}

object GallonsToLiters extends UnitConversion(3.78541) {}

object MilesToKilometers extends UnitConversion(1.60934) {}
