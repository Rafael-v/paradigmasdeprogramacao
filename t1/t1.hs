-- Prática: haskell02

-- 1. Crie uma função isVowel :: Char -> Bool que verifique se um caracter é uma vogal ou não.
isVowel :: Char -> Bool
isVowel c = c `elem` "aeiouAEIOU"

-- 2. Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista.
addComma :: [String] -> [String]
addComma s = map (++ ",") s;

-- 3. Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista contendo as strings formatadas como itens de lista em HTML.
concatenar3 :: String -> String
concatenar3 s = "<LI>" ++ s ++ "</LI>"

htmlListItems1 :: [String] -> [String]
htmlListItems1 s = map (concatenar3) s

htmlListItems2 :: [String] -> [String]
htmlListItems2 s = map (\x -> "<LI>" ++ x ++ "</LI>") s

-- 4. Defina uma função que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo.
semVogais :: [Char] -> [Char]
semVogais s = filter (not.isVowel) s

semVogais2 :: [Char] -> [Char]
semVogais2 s = filter (\x -> not (x `elem` "aeiouAEIOU")) s

-- 5. Defina uma função que receba uma string, possivelmente contendo espaços, e que retorne outra string substituindo os demais caracteres por '-', mas mantendo os espaços.
ehEspaco :: Char -> Char
ehEspaco c = if c == ' ' then ' ' else '-'

trocaEspaco :: String -> String
trocaEspaco s = map (ehEspaco) s

trocaEspaco2 :: String -> String
trocaEspaco2 s = map (\x -> if x /= ' ' then '-' else ' ') s

-- 6. Escreva uma função firstName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu primeiro nome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome.
firstName :: String -> String
firstName s = takeWhile(/= ' ') s

-- 7. Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.
