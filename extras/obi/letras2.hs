{-
  Problema: Letras (https://olimpiada.ic.unicamp.br/pratique/p1/2015/f2/letras/)
  Uma cadeia de caracteres é uma sequência de letras do alfabeto. Uma cadeia de caracteres crescente
  é uma sequência de letras onde a próxima letra (da esquerda para a direita) nunca ocorre antes no
  alfabeto do que a letra anterior. Por exemplo ABBD é crescente, enquanto ABBAD não é crescente.

  Uma subsequência de uma cadeia de caracteres é uma cadeia de caracteres que pode ser obtida a partir
  da remoção de zero ou mais caracteres da cadeia de caracteres original. Por exemplo ANNA é uma
  subsequência de BANANAS. Outro exemplo seria ANNS, que é uma subsequência crescente de BANANAS.

  Dada uma cadeia de caracteres S, escreva um programa para determinar o tamanho da maior subsequência
  de S que é uma cadeia de caracteres crescente.

  Entrada: A entrada consiste em uma única linha, contendo uma cadeia de caracteres S.
  Saída: Seu programa deve produzir uma única linha, contendo um único inteiro, o tamanho da maior
  subsequência de S que é uma cadeia de caracteres crescente.
-}

letras :: [Char] -> Int
letras s = 0

crescente :: [Char] -> Bool
crescente [_] = True
crescente (x:xs) = if (head xs < x) then False else crescente xs
