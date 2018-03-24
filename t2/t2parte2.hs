--  Trabalho 2 Parte 2  --
--     Rafael Vales     --

-- 1. Escreva uma função recursiva isBin :: String -> Bool para verificar se uma dada String representa um número binário, ou seja, contém apenas caracteres '0' ou '1'.
isBin :: String -> Bool
isBin [] = True
isBin (x:xs) = if (x /= '0' && x /= '1') then False else isBin xs

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
