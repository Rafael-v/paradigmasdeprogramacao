-- Implementação em Haskell da lista prática de Prolog

import Data.List

{- ./prolog2 -}

-- 1. Defina um predicado ao_lado(X, Y, L) para determinar se X está imediatamente ao lado de Y na lista L. Neste caso, X pode estar imediatamente à esquerda OU à direita de Y.
ao_lado :: Int -> Int -> [Int] -> Bool
ao_lado _ _ [] = False
ao_lado _ _ [_] = False
ao_lado x y (h:t) = if (x == h && y == head t) then True else ao_lado x y t

-- 2. Defina um predicado um_entre(X, Y, L) para determinar se os elementos X e Y da lista L estão separados por exatamente um elemento.
um_entre :: Int -> Int -> [Int] -> Bool
um_entre _ _ [] = False
um_entre x y (h:t)
    | (length (h:t) <= 2) = False
    | (x == h && y == head (tail t)) = True
    | otherwise = um_entre x y t


{- ./prolog3 -}

-- 1. Defina um predicado zeroInit(L) que é verdadeiro se L for uma lista que inicia com o número 0 (zero)
zeroInit :: [Int] -> Bool
zeroInit [] = False
zeroInit (x:xs) = (x == 0)
-- Ou
zeroInit' :: [Int] -> Bool
zeroInit' (0:_) = True
zeroInit' _ = False

-- 2. Defina um predicado has5(L) que é verdadeiro se L for uma lista de 5 elementos.
has5 :: [Int] -> Bool
has5 lst = (length lst == 5)
-- Ou
has5' :: [Int] -> Bool
has5' [_,_,_,_,_] = True
has5' _ = False

-- 3. Defina um predicado hasN(L,N) que é verdadeiro se L for uma lista de N elementos.
hasN :: [Int] -> Int -> Bool
hasN [] 0 = True
hasN [] _ = False
hasN lst n = hasN (tail lst) (n-1)
-- Ou
hasN' :: [Int] -> Int -> Bool
hasN' lst n = (length lst == n)

-- 4. Defina um predicado potN0(N,L), de forma que L seja uma lista de potências de 2, com expoentes de N a 0.
potN0 :: Int -> [Int]
potN0 n = [2^x | x <- [n,(n-1)..0]]
-- Sem list comprehension
potN0' :: Int -> [Int]
potN0' 0 = [1]
potN0' n = (2^n):(potN0' (n-1))

-- 5. Defina um predicado zipmult(L1,L2,L3), de forma que cada elemento da lista L3 seja o produto dos elementos de L1 e L2 na mesma posição do elemento de L3.
zipmult :: [Int] -> [Int] -> [Int]
zipmult [] [] = []
zipmult (x:xs) (y:ys) = (x*y):(zipmult xs ys)
-- Ou
zipmult' :: [Int] -> [Int] -> [Int]
zipmult' lst1 lst2 = zipWith (*) lst1 lst2

-- 6. Defina um predicado potencias(N,L), de forma que L seja uma lista com as N primeiras potências de 2, sendo a primeira 2^0 e assim por diante.
potencias :: Int -> [Int]
potencias n = [2^x | x <- [0..(n-1)]]

-- 7. Defina um predicado positivos(L1,L2), de forma que L2 seja uma lista só com os elementos positivos de L1.
positivos :: [Int] -> [Int]
positivos [] = []
positivos (x:xs) = if (x > 0) then x:(positivos xs) else positivos xs

-- 8. Considere que L1 e L2 sejam permutações de uma lista de elementos distintos, sem repetições. Sabendo disso, defina um predicado mesmaPosicao(A,L1,L2) para verificar se um elemento A está na mesma posição nas listas L1 e L2.
mesmaPosicao :: Char -> [Char] -> [Char] -> Bool
mesmaPosicao _ [] _ = False
mesmaPosicao _ _ [] = False
mesmaPosicao c (x:xs) (y:ys) = if (c == x && c == y) then True else mesmaPosicao c xs ys

-- 9. Dada uma lista de N alunos, deseja-se escolher NP alunos (NP < N) para formar uma comissão. Para isso, defina um predicado comissao(NP,LP,C), que permita gerar as possíveis combinações C com NP elementos da lista LP.
comissao :: Int -> [String] -> [[String]]
comissao 0 _ = [[]]
comissao n list = [ (x:xs) | (x:ys) <- tails list, xs <- comissao (n-1) ys ]

-- 10. (Adaptado de OBI2006-F1N1) Tem-se N azulejos 10cm x 10cm e, com eles, deve-se montar um conjunto de quadrados de modo a utilizar todos os azulejos dados, sem sobrepô-los. Inicialmente, deve-se montar o maior quadrado possível; então, com os azulejos que sobraram, deve-se montar o maior quadrado possível, e assim sucessivamente. Por exemplo, se forem dados 31 azulejos, o conjunto montado terá 4 quadrados. Para resolver este problema, você deverá definir um predicado azulejos(NA, NQ), de forma que NQ seja o número de quadrados que se deve montar com NA azulejos.
azulejos :: Int -> Int
azulejos 0 = 0
azulejos x = 1 + azulejos (x - (floor (sqrt (fromIntegral x)))^2)
