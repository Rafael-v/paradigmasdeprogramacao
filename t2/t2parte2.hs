--  Trabalho 2 Parte 2  --
--     Rafael Vales     --

-- 1. Escreva uma função recursiva isBin :: String -> Bool para verificar se uma dada String representa um número binário, ou seja, contém apenas caracteres '0' ou '1'.
isBin :: String -> Bool
isBin bin = if (length bin > 0) then charBin bin else False

charBin :: [Char] -> Bool
charBin [] = True
charBin (x:xs) = if (x /= '0' && x /= '1') then False else charBin xs

-- 2. Reescreva a função acima de forma não-recursiva. Dê outro nome para ela.
isBin' :: String -> Bool
isBin' str = all (\x -> x == '0' || x == '1') str

-- 3. Implemente a função auxBin2Dec de forma recursiva, para que bin2dec funcione corretamente.
bin2dec :: [Int] -> Int
bin2dec [] = undefined
bin2dec bits = auxBin2Dec bits ((length bits)-1)

auxBin2Dec :: [Int] -> Int -> Int
auxBin2Dec [] _ = 0
auxBin2Dec (x:xs) n = x*(2^n) + auxBin2Dec xs (n-1)

-- 4. Reescreva a função do exercício anterior de forma não-recursiva, usando funções pré-definidas em Haskell. Dê outro nome para a função.
bin2dec' :: [Int] -> Int
bin2dec' [] = undefined
bin2dec' bits = sum (zipWith (\x y -> x*(2^y)) bits [((length bits)-1),((length bits)-2)..0])

-- 5. Crie uma função recursiva dec2bin :: Int -> [Int] que receba um número inteiro positivo e retorne sua representação em binário, sob forma de uma lista de 0's e 1's.
dec2bin :: Int -> [Int]
dec2bin 0 = [0]
dec2bin n = reverse (calcBin n)

calcBin :: Int -> [Int]
calcBin 0 = []
calcBin n = (if (mod n 2 == 1) then 1 else 0):calcBin (div n 2)

-- 6. Implemente uma dessas funções: isHex :: String -> Bool ou hex2dec :: String -> Int ou dec2hex :: Int -> String
isHex :: String -> Bool
isHex [] = undefined
isHex str = all (`elem` "0123456789ABCDEFabcdef") str
