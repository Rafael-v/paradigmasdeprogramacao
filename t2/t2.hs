-- 1. Usando recursão, escreva uma função geraTabela :: Int -> [(Int,Int)] que produza uma lista com n tuplas, cada tupla com números de n a 1 e seus respectivos quadrados.
geraTabela :: Int -> [(Int,Int)]
geraTabela 0 = []
geraTabela x = (x,x*x):(geraTabela (x-1))

-- 2. Defina uma função recursiva que verifique se um dado caracter está contido numa string.
contido :: Char -> [Char] -> Bool
contido _ [] = False
contido c (x:xs) = if (x == c) then True else contido c xs

-- 3. Defina uma função recursiva que receba uma lista de coordenadas de pontos 2D e desloque esses pontos em 2 unidades.
translate :: [(Float,Float)] -> [(Float,Float)]
translate [] = []
translate list = (\(x,y) -> (x+2, y+2))(head list):(translate (tail list))

-- 4. Defina uma função que receba um número n e retorne uma lista de n tuplas, cada tupla com números de 1 a n e seus respectivos quadrados.
geraTabela' :: Int -> [(Int,Int)]
geraTabela' x = inverte (geraTabela x)

inverte :: [(Int,Int)] -> [(Int,Int)]
inverte [] = []
inverte (x:xs) = (inverte xs)++[x]
