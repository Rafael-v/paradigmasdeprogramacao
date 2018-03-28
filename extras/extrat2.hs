{- EXTRA 1
Pesquise sobre a funcao de alta ordem fold, tambem chamada de reduce, e suas variacoes foldr, foldl, foldr1, foldl1.
Forneca uma nova versao de alguma funcao da parte quatro, usando alguma variacao de fold.

T2 > parte4 > Exercício 3 > Defina uma função countValids :: String -> Int, que receba uma string e retorne a quantidade de seus caracteres contidos no intervalo ['a'..'z']. -}

countValids :: String -> Int
countValids str = foldl1 (+) (map (\x -> if (x == ' ') then 0 else 1) str)

--------------------------------------------------------------------------------------------------

{- EXTRA 4
Acesse esta url e descubra uma selecao de questoes de paradigmas de programacao das provas do enade e poscomp:
https://docs.google.com/spreadsheets/d/1vx4YGcJFa8HBIJ-YWO_rv1Bc7LnwJyNEFtgPJSVk65c/edit?usp=sharing
Escolha uma questao que envolva o paradigma funcional e explique sua resolucao.

POSCOMP 2012 - Questão 32 - Funcional Haskell
Em linguagens de programação declarativas, em especial aquelas que seguem o paradigma funcional, a lista é uma estrutura de dados fundamental.
Uma lista representa coleções de objetos de um único tipo, sendo composta por dois elementos: a cabeça (head) e o corpo (tail), exceto quando está vazia.
A cabeça é sempre o primeiro elemento e o corpo é uma lista com os elementos da lista original, excetuando-se o primeiro elemento.
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
Senão utiliza guardas para verificar qual, entre o primeiro e segundo elementos da lista, é maior.
O maior elemento entre os dois assumirá a posição da frente, seguido do menor, e depois o restante da lista que vem da chamada recursiva.

Resumidamente, pega dois elementos da lista de cada vez (primeiro e segundo, terceiro e quarto...) e deixa o menor deles na frente:
Input:  [5,3, 4,5, 2,1, 2,3, 4]
Output: [3,5, 4,5, 1,2, 2,3, 4] -}

--------------------------------------------------------------------------------------------------

{- EXTRA 5
Entregue seu codigo em Haskell usado para decifrar as mensagens acima. -}

-- Decodificar Hexadecimal: decodeHex
decodeHex :: String -> String
decodeHex str = hexToString (filter (\x -> x /= ' ') str)

hexToString :: String -> String
hexToString [] = []
hexToString (a:b:c) = chr (hexToInt (a:[b])) : (hexToString c)

hexToInt :: String -> Int
hexToInt (a:b:c) = (digitToInt a)*16 + (digitToInt b)

-- Decodificar Cifra de Cesar: decodeStr
decodeStr :: String -> Int -> String
decodeStr str n = map (\x -> shiftChar x n) str

shiftChar :: Char -> Int -> Char
shiftChar c n = if isLower c then decodeChar (mod ((encodeChar c) + n) 26) else c

encodeChar :: Char -> Int
encodeChar c = ord c - ord 'a'

decodeChar :: Int -> Char
decodeChar n = chr (ord 'a' + n)
