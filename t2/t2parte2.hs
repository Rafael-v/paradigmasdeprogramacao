--  Trabalho 2 Parte 2  --
--     Rafael Vales     --

isBin :: String -> Bool
isBin [] = True
isBin (x:xs) = if (x /= '0' && x /= '1') then False else isBin xs

isBin' :: String -> Bool
isBin' str = all (\x -> x == '0' || x == '1' ) str
