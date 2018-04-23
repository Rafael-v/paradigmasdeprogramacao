{-
  Problema: Troco (https://olimpiada.ic.unicamp.br/pratique/p1/2013/f2/troco/)
  Você está num supermercado e está na fila do caixa para comprar alguns produtos. Assim que você
  termina de passar as compras pelo caixa, se lembra que tem várias moedas em seu bolso, algumas
  repetidas, e fica pensando se com elas dá para pagar exatamente o valor das compras (para assim
  se livrar destas moedas e ficar com os bolsos mais leves). Você consegue pagar o valor exato da
  conta usando estas moedas?

  Entrada: A primeira linha da entrada contém dois números inteiros V e M, indicando, respectivamente,
  o valor final da compra e o número de moedas que você tem em seu bolso. A segunda linha contém M
  números inteiros que descrevem o valor M_i de cada moeda existente em seu bolso.
  Saída: Seu programa deve imprimir apenas uma linha, contendo apenas um caractere: S caso seja
  possível pagar o valor exato da conta usando apenas suas moedas, ou N caso contrário.
-}

import Data.List

troco :: Int -> Int -> [Int] -> Bool
troco v m list = if (length list == m) then trocoAux v (length list) list else False

trocoAux :: Int -> Int -> [Int] -> Bool
trocoAux _ 0 _ = False
trocoAux v n list = if (length (filter (\x -> sum x == v) (combinacoes n list)) > 0) then True else trocoAux v (n-1) list

combinacoes :: Int -> [Int] -> [[Int]] -- Function da internet
combinacoes 0 _ = [[]]
combinacoes n list = [ (x:xs) | (x:ys) <- tails list, xs <- combinacoes (n-1) ys ]
