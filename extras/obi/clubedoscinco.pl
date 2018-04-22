/*
  Problema: Clube dos Cinco (https://olimpiada.ic.unicamp.br/pratique/p1/2016/f1/clube/)
  No Clube dos Cinco são oferecidos três esportes aos associados: tiro com arco, badminton e canoagem.
  Cada associado pode participar de no máximo dois esportes, mas a administração do clube suspeita que
  algumas pessoas estejam ultrapassando esse limite. A fim de descobrir a verdade, perguntaram aos
  treinadores quantas pessoas estavam frequentando suas aulas, resultando nos seguintes dados:
  - O número A de pessoas que praticam tiro com arco;
  - O número B de pessoas que praticam badminton;
  - O número C de pessoas que praticam canoagem.
  Além disso, perguntaram aos membros quais esportes eles praticam. Obviamente, os associados que praticam
  três esportes mentiram, mas considere que outros falaram a verdade. Os dados dos associados foram resumidos
  nas seguintes informações:
  - O número D de pessoas que praticam tiro com arco e badminton;
  - O número E de pessoas que praticam tiro com arco e canoagem;
  - O número F de pessoas que praticam badminton e canoagem;
  - O número G de pessoas que não praticam nenhum esporte.
  Você ficou encarregado da a tarefa de descobrir se a suspeita é verdadeira. Dados o número N de associados
  do clube e os números A, B, C, D, E, F e G descritos acima, descubra se existe alguma pessoa que faz três
  esportes.
  Entrada: A primeira linha contém um inteiros N, representando o número de associados. A segunda linha contém
  sete inteiros A, B, C, D, E, F e G como descritos no enunciado.
  Saída: Seu programa deve produzir uma única linha, contendo uma única letra, "S" se algum associado participa
  de três esportes e "N", caso contrário.
*/

clubeDosCinco(N,A,B,C,D,E,F,G) :-
    A2 is (A - D - E),
    B2 is (B - D - F),
    C2 is (C - E - F),
    T is (A2 + B2 + C2 + D + E + F + G),
    N \= T.
