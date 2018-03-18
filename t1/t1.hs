--     Trabalho 1     --
-- Prática: haskell02 --
import Data.Char

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
isInt :: String -> Bool
isInt [] = True
isInt (x:xs) = if isDigit x then isInt xs else False

isInt2 :: String -> Bool
isInt2 s = all isDigit s

-- 8. Escreva uma função lastName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu último sobrenome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome.
lastName :: String -> String
lastName s = last (words s)

-- 9. Escreva uma função userName :: String -> String que, dado o nome completo de uma pessoa, crie um nome de usuário (login) da pessoa, formado por: primeira letra do nome seguida do sobrenome, tudo em minúsculas.
userName :: String -> String
userName s = map toLower ([head s] ++ lastName s)

-- 10. Escreva uma função encodeName :: String -> String que substitua vogais em uma string, conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.
substituiVogal :: Char -> Char
substituiVogal c
    | (c == 'a') || (c == 'A') = '4'
    | (c == 'e') || (c == 'E') = '3'
    | (c == 'i') || (c == 'I') = '2'
    | (c == 'o') || (c == 'O') = '1'
    | (c == 'u') || (c == 'U') = '0'
    | otherwise = c

encodeName :: String -> String
encodeName s = map substituiVogal s

-- 11. Escreva uma função betterEncodeName :: String -> String que substitua vogais em uma string, conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00.
substituiVogal2 :: Char -> String
substituiVogal2 c
    | (c == 'a') || (c == 'A') = "4"
    | (c == 'e') || (c == 'E') = "3"
    | (c == 'i') || (c == 'I') = "1"
    | (c == 'o') || (c == 'O') = "0"
    | (c == 'u') || (c == 'U') = "00"
    | otherwise = [c]

--betterEncodeName :: String -> String

-- 12. Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema: strings de entrada com mais de 10 caracteres são truncadas, strings com até 10 caracteres são completadas com '.' até ficarem com 10 caracteres.
func :: [String] -> [String]
func s = map (\x -> if length x < 10 then x ++ replicate (10-length x) '.' else take 10 x) s
