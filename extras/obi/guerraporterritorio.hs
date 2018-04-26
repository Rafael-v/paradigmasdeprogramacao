{-
  Problema: Guerra por território (https://olimpiada.ic.unicamp.br/pratique/p1/2012/f2/guerra/)
  Tombólia do Oeste e Tombólia do Leste travaram uma guerra durante 50 anos. O motivo da guerra era o
  tamanho do território de cada país. Pelo bem da população dos dois países, os governos resolveram
  fazer um tratado para finalizar a guerra. O tratado consiste em fazer um divisão justa, e certamente
  contínua, do território. Eles resolveram pedir sua ajuda para calcular o ponto de divisão do território.
  Depois de tantos anos de guerra, os países não podem lhe pagar uma viagem para ver previamente o
  território que será dividido. Ao invés disso, eles prepararam uma lista a1,a2,…,aN de inteiros que
  indicam o tamanho de cada seção do território. A seção a1 é vizinha da seção a2 que por sua vez é
  vizinha da seção a3; e assim por diante. Os governos querem uma divisão em uma seção k de tal forma
  que a1 + a2 + … + ak = ak+1 + ak+2 + … + aN.

  Sua tarefa é dada uma lista de inteiros positivos a1, a2,..., aN , determinar a seção k tal que soma
  dos comprimentos das seções a1 até ak é igual a soma dos comprimentos das seções ak+1 até aN.

  Entrada: A primeira linha da entrada contém um inteiro N indicando o número de seções do território.
  A segunda linha da entrada contém N inteiros a1, a2;,.., aN separados por um único espaço que indicam
  os comprimentos das seções.
  Saída: Seu programa deve imprimir uma única linha contendo um inteiro que indica a seção do território
  onde acontecerá a divisão.

  Nota: é garantido que sempre existe uma divisão que satisfaz as condições dos países.
-}

guerraPorTerritorio :: Int -> [Int] -> Int
guerraPorTerritorio _ lst = guerraAux 0 lst

guerraAux :: Int -> [Int] -> Int
guerraAux _ [] = 0
guerraAux n (x:xs) = if ( n == sum (x:xs) ) then 0 else 1 + guerraAux (n+x) xs
