-- Implementação em Haskell da lista prática de Prolog

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

-- 2. Defina um predicado has5(L) que é verdadeiro se L for uma lista de 5 elementos.
has5 :: [Int] -> Bool
has5 lst = (length lst == 5)

-- 3. Defina um predicado hasN(L,N) que é verdadeiro se L for uma lista de N elementos.
hasN :: [Int] -> Int -> Bool
hasN lst n = (length lst == n)

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
-- Ou simplesmente:
zipmult' :: [Int] -> [Int] -> [Int]
zipmult' lst1 lst2 = zipWith (*) lst1 lst2

-- 6. Defina um predicado potencias(N,L), de forma que L seja uma lista com as N primeiras potências de 2, sendo a primeira 2^0 e assim por diante.
potencias :: Int -> [Int]
potencias n = [2^x | x <- [0..(n-1)]]
