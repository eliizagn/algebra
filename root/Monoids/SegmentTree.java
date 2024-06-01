package root.Monoids;

class SegmentTree<T> {
    private T[] tree; // Массив, представляющий дерево отрезков
    private Monoid<T> monoid; // Моноид для выполнения операций на отрезках

    // Конструктор принимает массив данных и моноид
    public SegmentTree(T[] array, Monoid<T> monoid) {
        this.monoid = monoid;
        int n = array.length;
        //создать вместо массивов List, использовать коллекцию
        tree = (T[]) new Object[4 * n]; // Создаем массив для дерева отрезков, позволяющий заполнить до 4n элементов
        build(array, 0, 0, n - 1); // Вызываем метод build для построения дерева
    }

    // Построение дерева
    private void build(T[] array, int node, int start, int end) {
        if (start == end) { // Если начальный и конечный индексы равны (листовая вершина)
            tree[node] = array[start]; // Устанавливаем значение листовой вершины
        } else {
            int mid = (start + end) / 2; // Находим середину текущего отрезка
            build(array, 2 * node + 1, start, mid); // Рекурсивно строим левое поддерево
            build(array, 2 * node + 2, mid + 1, end); // Рекурсивно строим правое поддерево
            // Вычисляем значение текущей вершины как результат операции моноида
            tree[node] = monoid.operate(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    // Запрос на отрезке [l, r]
    public T query(int l, int r, int node, int start, int end) {
        if (r < start || l > end) { // Если отрезок [l, r] не пересекается с текущим отрезком [start, end]
            return monoid.neutral(); // Возвращаем нейтральный элемент моноида
        }
        if (l <= start && end <= r) { // Если текущий отрезок полностью содержится в [l, r]
            return tree[node]; // Возвращаем значение текущей вершины дерева
        }
        int mid = (start + end) / 2; // Находим середину текущего отрезка
        T leftQuery = query(l, r, 2 * node + 1, start, mid); // Рекурсивно запрашиваем левое поддерево
        T rightQuery = query(l, r, 2 * node + 2, mid + 1, end); // Рекурсивно запрашиваем правое поддерево
        return monoid.operate(leftQuery, rightQuery); // Возвращаем результат операции моноида для результатов запросов в поддеревьях
    }

    // Публичный метод запроса на отрезке [l, r], начиная с корня дерева
    public T query(int l, int r) {
        return query(l, r, 0, 0, tree.length / 4 - 1); // Вызываем метод запроса с корнем дерева (node=0) и границами всего массива
    }
}