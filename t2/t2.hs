-- 1. Usando recursão, escreva uma função geraTabela :: Int -> [(Int,Int)] que produza uma lista com n tuplas, cada tupla com números de n a 1 e seus respectivos quadrados.
geraTabela :: Int -> [(Int,Int)]
geraTabela 0 = []
geraTabela x = (x,x*x):(geraTabela (x-1))

-- 2. Defina uma função recursiva que verifique se um dado caracter está contido numa string.
contido :: Char -> [Char] -> Bool
contido _ [] = False
contido c (x:xs) = if (x == c) then True else contido c xs
