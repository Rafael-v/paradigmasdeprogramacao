{-
  Problema: Chaves (https://olimpiada.ic.unicamp.br/pratique/p2/2016/f1/chaves/)
  Seu amigo Juca está enfrentando problemas com programação. Na linguagem C, algumas partes do código
  devem ser colocadas entre chaves "\ \" e ele frequentemente esquece de colocá-las ou as coloca de
  forma errada. Porém, como Juca tem dificuldade para entender os erros de compilação, ele nunca sabe
  exatamente o que procurar. Por isso ele te pediu para fazer um programa que determine se um código
  está com as chaves balanceadas, ou seja, se é válido. Um código está com as chaves balanceadas se:

  - Não há chaves (como por exemplo "Bom" ou "Correto");
  - O código é composto por uma sequência de códigos válidos (como por exemplo "Bom Correto" ou "\\\\" ou "\\Correto"); ou
  - O código é formado por um código válido entre chaves (como por exemplo "\\\\" ou "\Bom\").
  O código de Juca é composto por N linhas de até 100 caracteres cada. Pode haver linhas vazias e espaços consecutivos.
  
  Entrada: A primeira linha contém um inteiro N, representando o número de linhas no código. As N linhas seguintes
  contém até 100 caracteres.
  Saída: Seu programa deve produzir uma única linha, contendo uma única letra, "S" se o código está com as chaves
  balanceadas e "N", caso contrário.
-}

chaves :: Int -> [String] -> Bool
chaves _ lst = chavesAux (concat lst) 0

chavesAux :: String -> Int -> Bool
chavesAux [] n = (n == 0)
chavesAux (x:xs) n
  | (n < 0) = False
  | (x == '{') = chavesAux xs (n+1)
  | (x == '}') = chavesAux xs (n-1)
  | otherwise = chavesAux xs n
