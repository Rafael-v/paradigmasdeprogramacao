{-
  Problema: Calculadora (https://olimpiada.ic.unicamp.br/pratique/p1/2011/f2/calculadora/)
  Solicitando Boas Contas (SBC) é uma organização de inspeção de calculadoras. Todos os fabricantes procuram
  ter o selo de qualidade da SBC, que faz com que os clientes comprem o produto sem preocupação com contas erradas.

  Você está encarregado de testar máquinas que fazem apenas operações de multiplicação e divisão. Além disso,
  o termo a ser digitado em cada operação (que dividirá ou multiplicará o número atualmente exibido no visor)
  só pode conter um único dígito.

  A calculadora exibe o número 1 quando ligada. Depois disso, o usuário pode digitar um número com um único dígito
  e escolher se esse número deve multiplicar ou dividir o número exibido anteriormente; o resultado da operação
  escolhida é então exibido na calculadora. Pode-se repetir esse processo indefinidamente.

  Apesar de só podermos entrar com números inteiros de um dígito, o visor da calculadora permite exibir números com
  múltiplos dígitos e até mesmo números fracionários.

  Dada uma sequência de operações que foram realizadas nessa calculadora logo depois de ligada, sua tarefa é conferir
  o resultado exibido.

  Entrada: A primeira e única linha da entrada contém um inteiro N. Cada uma das próximas N linhas contém um dígito e
  um caractere "*" ou "/", que representam uma operação realizada na calculadora.
  Saída: Seu programa deve imprimir uma única linha contendo o resultado que deve ser exibido pela calculadora ao
  final das operações.
-}

import Data.Char

calculadora :: Int -> [Char] -> Int
calculadora _ lst = fromEnum (calcula (reverse lst))

calcula :: [Char] -> Float
calcula [] = 1.0
calcula (x:xs)
  | (x == '+') = calcula (tail xs) + toEnum (digitToInt (head xs))
  | (x == '-') = calcula (tail xs) - toEnum (digitToInt (head xs))
  | (x == '*') = calcula (tail xs) * toEnum (digitToInt (head xs))
  | (x == '/') = calcula (tail xs) / toEnum (digitToInt (head xs))
  | otherwise = 0
