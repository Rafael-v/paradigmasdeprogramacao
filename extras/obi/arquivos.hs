/*
  Arquivos
  Aldo tem N arquivos em seu computador, cada um com um tamanho em bytes. Ele quer dividir estes arquivos
  em pastas, porém o sistema do computador é velho e só aceita pastas com as duas seguintes limitações:
  - Uma pasta pode ter no máximo dois arquivos
  - A soma dos tamanhos dos arquivos na pasta não pode exceder B bytes
  Como ele tem muitos arquivos ele prefere não criar muitas pastas. Dado o tamanho dos arquivos, calcule
  o número mínimo possível de pastas.

  Vamos supor um exemplo que temos os arquivos de tamanho 1, 2 e 3, e que o limite de bytes seja 3. A solução
  é colocar os dois primeiros arquivos juntos, totalizando apenas 2 pastas.

  Entrada: A entrada consiste de duas linhas. A primeira linha contém os números inteiros N e B. A segunda
  linha contém N inteiros indicando o tamanho de cada arquivo.
  Saída: Seu programa deve escrever uma única linha na saída, contendo um único número inteiro, a quantidade
  mínima possível de pastas.
*/

verif :: Int -> [Int] -> Int
verif 0 _ = 0
verif x list = if (length list == length (filter (/=x) list)) then x else verif (x-1) list

remove :: Int -> [Int] -> [Int]
remove _ [] = []
remove n (x:xs) = if (n == x) then xs else x:(remove n xs)
