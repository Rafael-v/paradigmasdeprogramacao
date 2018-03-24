--  Trabalho 2 Parte 2  --
--     Rafael Vales     --

isBin :: String -> Bool
isBin [] = True
isBin (x:xs) = if (x /= '0' && x /= '1') then False else isBin xs

isBin' :: String -> Bool
isBin' str = all (\x -> x == '0' || x == '1' ) str

bin2dec :: [Int] -> Int
bin2dec [] = undefined
bin2dec bits = auxBin2Dec bits ((length bits)-1)

auxBin2Dec :: [Int] -> Int -> Int
auxBin2Dec [] _ = 0
auxBin2Dec (x:xs) n = x*(2^n) + auxBin2Dec xs (n-1)
