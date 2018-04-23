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

import Data.List

letras :: String -> Int
letras s = letrasAux (length s) s

letrasAux :: Int -> String -> Int
letrasAux 0 _ = 0
letrasAux n s = if (length (filter (\x -> crescente x) (combinacoes n s)) > 0) then n else letrasAux (n-1) s

crescente :: [Char] -> Bool
crescente [] = True
crescente [_] = True
crescente (x:xs) = if (head xs < x) then False else crescente xs

combinacoes :: Int -> [Char] -> [String] -- Function da internet
combinacoes 0 _ = [[]]
combinacoes n list = [ (x:xs) | (x:ys) <- tails list, xs <- combinacoes (n-1) ys ]
