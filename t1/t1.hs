-- haskell02

-- 1
isVowel :: Char -> Bool
isVowel c = c `elem` "aeiouAEIOU"
-- 2
addComma :: [String] -> [String]
addComma s = map (++ ",") s;

-- 3
concatenar3 :: String -> String
concatenar3 s = "<LI>" ++ s ++ "</LI>"

htmlListItems1 :: [String] -> [String]
htmlListItems1 s = map (concatenar3) s

htmlListItems2 :: [String] -> [String]
htmlListItems2 s = map (\x -> "<LI>" ++ x ++ "</LI>") s

-- 4
semVogais :: [Char] -> [Char]
semVogais s = filter (not.isVowel) s

semVogais2 :: [Char] -> [Char]
semVogais2 s = filter (\x -> not (x `elem` "aeiouAEIOU")) s

-- 5
ehEspaco :: Char -> Char
ehEspaco c = if c == ' ' then ' ' else '-'

trocaEspaco :: String -> String
trocaEspaco s = map (ehEspaco) s

trocaEspaco2 :: String -> String
trocaEspaco2 s = map (\x -> if x /= ' ' then '-' else ' ') s

-- 6
firstName :: String -> String
firstName s = takeWhile(/= ' ') s

-- 7
