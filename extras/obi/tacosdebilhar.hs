/*
  Problema: Tacos de bilhar (https://olimpiada.ic.unicamp.br/pratique/p1/2016/f1/tacos-bilhar/)
  Jogos de bilhar, em que tacos são usados para arremessar uma bola contra outras em uma mesa,
  têm muitas variantes, como sinunca, mata-mata, bilhar francês e outras. São muito antigos,
  havendo relatos sobre jogos similares desde 1340. O Sr. Jorge é um renomado artesão que fabrica
  tacos de bilhar sob encomenda. Jogadores de todo o mundo procuram o Sr. Jorge, para confeccionar
  tacos nos mais diversos comprimentos, pois seus tacos são perfeitos, bem balanceados e muito
  bonitos. Cada vez que um cliente pede um taco de um dado comprimento, o Sr. Jorge primeiro verifica
  se ele tem um taco com esse comprimento no estoque. Se tem, ele envia o taco para o cliente. Se não
  tem, ele faz duas cópias do taco, envia uma para o cliente e guarda a outra no estoque. Assim, ele
  nunca tem no estoque mais do que um taco com um determinado comprimento. O estoque do Sr. Jorge
  está muito grande, e ele tem perdido muito tempo procurando por tacos. Ele pensa em usar um sistema
  computadorizado para manter o seu estoque de tacos, e precisa de sua ajuda. Dadas as consultas ao
  estoque calcule o número total de tacos fabricados, supondo que inicialmente o estoque esteja vazio..
  
  Entrada: A primeira linha da entrada contém um inteiro C que indica o número de consultas ao estoque.
  A segunda   linha contém C números inteiros, indicando as consultas ao estoque. Cada valor de consulta
  indica o comprimento de um taco desejado. As consultas são dadas na entrada na ordem em que o Sr. Jorge
  as executa. Assuma que o estoque está vazio inicialmente.
  Saída: Seu programa deverá imprimir um único número, o número de tacos fabricados.
*/

tacosDeBilhar :: Int -> [Int] -> Int
tacosDeBilhar n list = if (length list /= n) then -1 else (2 * tacos list)

tacos :: [Int] -> Int
tacos [] = 0
tacos list = qtdTacos (length list - length (delTacosTamX (head list) list)) + tacos (delTacosTamX (head list) list)

qtdTacos :: Int -> Int
qtdTacos x = if (x `mod` 2 == 0) then div x 2 else (div x 2)+1

delTacosTamX :: Int -> [Int] -> [Int]
delTacosTamX x list = filter (/=x) list
