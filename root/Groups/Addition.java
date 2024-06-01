package root.Groups;

class Addition implements Group<Integer> {
  @Override
  public Integer neutral() {
    return 0; // Нейтральный элемент для сложения
  }

  @Override
  public Integer operate(Integer a, Integer b) {
    return a + b; // Операция сложения
  }

  @Override
  public Integer inverse(Integer element) {
    return -element; // Обратный элемент для сложения
  }
}