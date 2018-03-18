-- Prática: haskell02

-- 1. Crie uma função isVowel :: Char -> Bool que verifique se um caracter é uma vogal ou não.
isVowel :: Char -> Bool
isVowel c = c `elem` "aeiouAEIOU"

-- 2. Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista.
addComma :: [String] -> [String]
addComma s = map (++ ",") s;

-- 3. Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista contendo as strings formatadas como itens de lista em HTML.
formatar :: String -> String
formatar s = "<LI>" ++ s ++ "</LI>"

htmlListItems :: [String] -> [String] -- SEM lambda
htmlListItems s = map (formatar) s

htmlListItemsL :: [String] -> [String] -- COM lambda
htmlListItemsL s = map (\x -> "<LI>" ++ x ++ "</LI>") s

-- 4. Defina uma função que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo.
semVogais :: String -> String -- SEM lambda
semVogais s = filter (not.isVowel) s

semVogaisL :: String -> String -- COM lambda
semVogaisL s = filter (\x -> not (x `elem` "aeiouAEIOU")) s

-- 5. Defina uma função que receba uma string, possivelmente contendo espaços, e que retorne outra string substituindo os demais caracteres por '-', mas mantendo os espaços.
trocaChar :: Char -> Char
trocaChar c = if (c /= ' ') then '-' else ' '

codifica :: String -> String -- SEM lambda
codifica s = map (trocaChar) s

codificaL :: String -> String -- COM lambda
codificaL s = map (\x -> if x /= ' ' then '-' else ' ') s

-- 6. Escreva uma função firstName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu primeiro nome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome.
firstName :: String -> String
firstName s = takeWhile(/= ' ') s

firstName2 :: String -> String
firstName2 s = head(words s)

-- 7. Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.
--isInt :: String -> Bool
