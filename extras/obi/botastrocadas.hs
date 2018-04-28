{-
  Problema: Botas Trocadas (https://olimpiada.ic.unicamp.br/pratique/p2/2017/f1/botas/)
  A divisão de Suprimentos de Botas e Calçados do Exército comprou um grande número de pares de botas de
  vários tamanhos para seus soldados. No entanto, por uma falha de empacotamento da fábrica contratada,
  nem todas as caixas entregues continham um par de botas correto, com duas botas do mesmo tamanho, uma
  para cada pé. O sargento mandou que os recrutas retirassem todas as botas de todas as caixas para
  reembalá-las, desta vez corretamente.

  Quando o sargento descobriu que você sabia programar, ele solicitou com a gentileza habitual que você
  escrevesse um programa que, dada a lista contendo a descrição de cada bota entregue, determina quantos
  pares corretos de botas poderão ser formados no total.

  Entrada: A primeira linha da entrada contém um inteiro N indicando o número de botas individuais entregues.
  Cada uma das N linhas seguintes descreve uma bota, contendo um número inteiro M e uma letra L, separados por
  um espaço em branco. M indica o número do tamanho da bota e L indica o pé da bota: L= ‘D’ indica que a bota
  é para o pé direito, L= ‘E’ indica que a bota é para o pé esquerdo.
  Saída: Seu programa deve imprimir uma única linha contendo um único número inteiro indicando o número total
  de pares corretos de botas que podem ser formados.
-}

import Data.List

botasTrocadas :: Int -> [(Int,Char)] -> Int
botasTrocadas _ lst = botasAux (sort lst)
  
botasAux :: [(Int,Char)] -> Int
botasAux [] = 0
botasAux (x:xs) = if (elem (parBota x) xs) then 1 + botasAux (delete (parBota x) xs) else botasAux xs
  
parBota :: (Int,Char) -> (Int,Char)
parBota (x,c) = if ( c == 'D' ) then (x,'E') else (x,'D')
