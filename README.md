Автоматизация вычисления выражений в обратной польской нотации основана на использовании стека.
1. Обработка входного символа
    Если на вход подан операнд, он помещается на вершину стека.
    Если на вход подан знак операции, то соответствующая операция выполняется над требуемым количеством значений, извлечённых из стека, взятых в порядке добавления. Результат выполненной операции кладётся на вершину стека.
2. Если входной набор символов обработан не полностью, перейти к шагу 1.
3. После полной обработки входного набора символов результат вычисления выражения лежит на вершине стека.
