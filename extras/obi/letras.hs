{-
  Problema: Letras (https://olimpiada.ic.unicamp.br/pratique/p1/2014/f1/letra/)
  Considere as definições abaixo:
  - Uma palavra é uma sequência de letras consecutivas.
  - Um texto é um conjunto de palavras separadas pelo caractere espaço em branco.
  Você foi contratado pela empresa Booble para escrever um programa que, dados uma letra
  e um texto, determina a porcentagem de palavras do texto que contém a letra dada.

  Entrada: A primeira linha da entrada contém um único caractere, a letra de interesse na
  pesquisa. A segunda linha contém um texto, como definido acima.
  Saída: Seu programa deve produzir uma única linha, contendo um único número real, a
  porcentagem de palavras do texto que contêm a letra dada, com precisão de uma casa decimal.
-}

letras :: Char -> String -> Float
letras c str = fromIntegral ((contaPalavras c (words str)) * 100) / fromIntegral (length (words str))

contaPalavras :: Char -> [String] -> Int
contaPalavras c str = length (filter (\x -> c `elem` x) str)
