--  Trabalho 2 Parte 1  --
--     Rafael Vales     --

isBin :: String -> Bool
isBin [] = True
isBin (x:xs) = if (x /= '0' && x /= '1') then False else isBin xs
