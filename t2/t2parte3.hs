--  Trabalho 2 Parte 3  --
--     Rafael Vales     --

-- Implementação de uma função isEanOk :: String -> Bool, que verifica se uma dada string representa um número EAN-13 com dígito verificador válido.

isEanOk :: String -> Bool
isEanOk str = if (length str) /= 13
  then False
  else (last str == calcCheckDigit str)
