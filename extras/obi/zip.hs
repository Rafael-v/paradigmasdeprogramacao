{-
Problema: Zip (https://olimpiada.ic.unicamp.br/pratique/p1/2017/f3/zip/)
Um jogo de cartas que faz muito sucesso no reino da Nlogônia é chamado zip. Nesse jogo, apenas
os valores das cartas são utilizados (ás a rei), os naipes das cartas são ignorados. Para
simplificar, neste problema vamos considerar os valores das cartas como inteiros de 1 a 13.

Em cada partida do jogo cada jogador recebe duas cartas. Cada jogador então mostra uma de suas
cartas, e os jogadores fazem suas apostas (na Nlogônia só é permitido apostar grãos de feijão).
Após as apostas, os jogadores mostram a sua segunda carta.

As regras para determinar quem ganha a partida são simples, baseadas nos valores das cartas de
cada jogador:

- se as duas cartas têm o mesmo valor, o jogador recebe como pontuação na partida duas vezes a
soma dos valores das cartas.
- se os valores das duas cartas são números consecutivos (por exemplo, 2 e 3, ou 13 e 12), o
jogador recebe como pontuação na partida três vezes a soma dos valores das cartas.
- caso contrário, o jogador recebe como pontuação na partida a soma dos valores das cartas.
Ganha a partida o jogador que tiver recebido a maior pontuação. Se houver empate, a aposta
acumula para a próxima partida.

Lia e Carolina estão jogando zip, e querem que você escreva um programa para conferir quem
ganhou cada partida.

Entrada: A entrada é composta por quatro linhas, cada uma contendo um inteiro. As duas primeiras
linhas indicam as cartas de Lia, as duas últimas linhas indicam as cartas de Carolina.
Saída: Seu programa deve produzir uma única linha, contendo o nome da jogadora que venceu a partida.
Se houve empate, a linha deve conter a palavra empate (em minúsculas).
-}

jogoZip :: Int -> Int -> Int -> Int -> String
jogoZip x1 x2 y1 y2 = jogoZipAux (pontuacao x1 x2) (pontuacao y1 y2)

jogoZipAux :: Int -> Int -> String
jogoZipAux x y
  | (x > y) = "Lia"
  | (x < y) = "Carolina"
  | otherwise = "empate"

pontuacao :: Int -> Int -> Int
pontuacao a b
  | (a == b) = 2*(a+b)
  | (abs (a-b) == 1) = 3*(a+b)
  | otherwise = a+b
