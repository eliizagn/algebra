package root.Groups;

class Multiplication implements Group<Double> {
  public Double neutral() {
    return 1.0;
  }

  @Override
  public Double operate(Double a, Double b) {
    return a * b;
  }

  @Override
  public Double inverse(Double element) {
    if (element == 0.0)
      return 0.0;
    else
      return 1 / element;
  }
}