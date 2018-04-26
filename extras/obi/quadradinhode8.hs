{-
  Problema: Quadradinho de 8 (https://olimpiada.ic.unicamp.br/pratique/p1/2013/f2/quadrado/)
  Fernando ficou sabendo de um novo jogo chamado quadradinho de 8. Nesse jogo, é apresentado ao
  jogadoruma fileira de quadrados, um do lado do outro. Em cada quadrado há um número escrito.
  Veja abaixo um exemplo de fileira de quadrados:

  |3|4|6|0|2|9|

  Para ganhar, o jogador deve escolher alguns quadrados de forma que eles juntos formem apenas um
  retângulo contíguo e que a soma de seus números seja divisível por 8. Na fileira de quadrados
  acima, o jogador ganha se escolher os quadrados com os números 6, 0 e 2. O jogador perde se
  escolher os quadrados com 3, 4 e 9, apesar da soma ser divisivel por 8, os quadrados não estão
  juntos, eles acabam formando dois retângulos separados.
  Você deve estar pensando agora que Fernando quer sua ajuda para que você mostre a ele como ganhar
  o jogo, mas Fernando é um garoto muito esperto e sabe resolver o jogo rapidamente. Ele quer na
  verdade que você o ajude a descobrir de quantas formas é possível ganhar esse jogo.

  Entrada: A entrada possui duas linhas. A primeira linha contém apenas um inteiro N que indica o
  número de quadrados na fileira de um jogo. A segunda linha contém N inteiros indicando na ordem
  os números presentes nos quadrados da fileira de um jogo.
  Saída: Seu programa deve imprimir uma única linha, contendo apenas um inteiro, o número de
  maneiras de ganhar o jogo apresentado na entrada. Se não for possível que o jogador ganhe o
  jogo, imprima 0.
-}

quadradinhoDe8 :: Int -> [Int] -> Int
quadradinhoDe8 _ [] = 0
quadradinhoDe8 x lst = contaParte (reverse lst) + quadradinhoDe8 (x-1) (tail lst)

contaParte :: [Int] -> Int
contaParte [] = 0
contaParte lst = if (divPor8 (sum lst)) then 1 + contaParte (tail lst) else contaParte (tail lst)

divPor8 :: Int -> Bool
divPor8 x = (mod x 8) == 0
