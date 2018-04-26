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
um_entre _ _ [_] = False
um_entre _ _ [_,_] = False
um_entre x y (h:t) = if (x == h && y == head (tail t)) then True else um_entre x y t
