{- EXTRA 1

Phvtxlvh vreuh d ixqfdr gh dowd rughp irog, wdpehp fkdpdgd gh uhgxfh, h vxdv yduldfrhv irogu, irogo, irogu1, irogo1.
Fruqhfd xpd qryd yhuvdr gh dojxpd ixqfdr gd sduwh txdwur, xvdqgr dojxpd yduldfdr gh irog.

Pesquise sobre a funcao de alta ordem fold, tambem chamada de reduce, e suas variacoes foldr, foldl, foldr1, foldl1.
Forneca uma nova versao de alguma funcao da parte quatro, usando alguma variacao de fold.

T2 > parte4 > Exercício 3 > Defina uma função countValids :: String -> Int, que receba uma string e retorne a quantidade de seus caracteres contidos no intervalo ['a'..'z']. -}

countValids :: String -> Int
countValids str = foldl1 (+) (map (\x -> if (x == ' ') then 0 else 1) str)


{- EXTRA 4

41 63 65 73 73 65 20 65 73 74 61 20 75 72 6c 20 65 20 64 65 73 63 75 62 72 61 20 75 6d 61 20 73 65 6c 65 63 61 6f 20 64 65 20 71 75 65 73 74 6f 65 73 20 64 65 20 70 61 72 61 64 69 67 6d 61 73 20 64 65 20 70 72 6f 67 72 61 6d 61 63 61 6f 20 64 61 73 20 70 72 6f 76 61 73 20 64 6f 20 65 6e 61 64 65 20 65 20 70 6f 73 63 6f 6d 70 3a 20 68 74 74 70 73 3a 2f 2f 64 6f 63 73 2e 67 6f 6f 67 6c 65 2e 63 6f 6d 2f 73 70 72 65 61 64 73 68 65 65 74 73 2f 64 2f 31 76 78 34 59 47 63 4a 46 61 38 48 42 49 4a 2d 59 57 4f 5f 72 76 31 42 63 37 4c 6e 77 4a 79 4e 45 46 74 67 50 4a 53 56 6b 36 35 63 2f 65 64 69 74 3f 75 73 70 3d 73 68 61 72 69 6e 67 20 45 73 63 6f 6c 68 61 20 75 6d 61 20 71 75 65 73 74 61 6f 20 71 75 65 20 65 6e 76 6f 6c 76 61 20 6f 20 70 61 72 61 64 69 67 6d 61 20 66 75 6e 63 69 6f 6e 61 6c 20 65 20 65 78 70 6c 69 71 75 65 20 73 75 61 20 72 65 73 6f 6c 75 63 61 6f 2e

Acesse esta url e descubra uma selecao de questoes de paradigmas de programacao das provas do enade e poscomp: https://docs.google.com/spreadsheets/d/1vx4YGcJFa8HBIJ-YWO_rv1Bc7LnwJyNEFtgPJSVk65c/edit?usp=sharing Escolha uma questao que envolva o paradigma funcional e explique sua resolucao.

POSCOMP 2012 - Questão 32 - Funcional Haskell
Em linguagens de programação declarativas, em especial aquelas que seguem o paradigma funcional, a lista é uma estrutura de dados fundamental.
Uma lista representa coleções de objetos de um único tipo, sendo composta por dois elementos: a cabeça (head) e o corpo (tail), exceto quando está vazia. A cabeça é sempre o primeiro elemento e o corpo é uma lista com os elementos da lista original, excetuando-se o primeiro elemento.
O programa Haskell, a seguir, apresenta uma função que utiliza essa estrutura de dados. -}
poscomp :: [Int] -> [Int]
poscomp [] = []
poscomp [x] = [x]
poscomp (a:b:c) | a > b = b : (a : poscomp c)
    | otherwise = a : (b : poscomp c) 
{- Uma chamada a esta função através da consulta poscomp [5,3,4,5,2,1,2,3,4] produzirá o resultado:
a) [1,2,2,3,3,4,4,5,5]
b) [3,5,4,5,1,2,2,3,4]
c) [5,3,4,5,2,1,2,3,4]
d) [5,4,3,2,1]
e) [5,3,4,2,1]

Produzirá como resultado a resposta B ([3,5,4,5,1,2,2,3,4]).
Caso a lista for vazia ou tiver apenas um elemento, retorna sem mudanças.
Senão utiliza guardas para verificar qual, entre o primeiro e segundo elementos da lista, é maior. O maior elemento entre os dois assumirá a posição da frente, seguido do menor, e depois o restante da lista que vem da chamada recursiva.

Resumidamente, pega dois elementos da lista de cada vez (primeiro e segundo, terceiro e quarto...) e deixa o menor deles na frente:
Input:  [5,3, 4,5, 2,1, 2,3, 4]
Output: [3,5, 4,5, 1,2, 2,3, 4] -}
