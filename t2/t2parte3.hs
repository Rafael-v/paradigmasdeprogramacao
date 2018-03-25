--  Trabalho 2 Parte 3  --
--     Rafael Vales     --
import Data.Char

{- Implementação de uma função isEanOk :: String -> Bool, que verifica se uma dada string representa um número EAN-13 com dígito verificador válido.
https://www.gs1.org/services/how-calculate-check-digit-manually -}

isEanOk :: String -> Bool
isEanOk str = if (length str) /= 13
  then False
  else (digitToInt (last str) == calcCheckDigit str)

calcCheckDigit :: String -> Int
calcCheckDigit code = 10 - (mod (calcSum code) 10)

calcSum :: String -> Int
calcSum code = sum (zipWith (*) (stringToIntList code) [1,3,1,3,1,3,1,3,1,3,1,3])

stringToIntList :: String -> [Int]
stringToIntList [] = []
stringToIntList (x:xs) = (digitToInt x):(stringToIntList xs)
