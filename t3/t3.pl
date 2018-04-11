% 1. Defina um predicado zeroInit(L) que é verdadeiro se L for uma lista que inicia com o número 0 (zero).
zeroInit(L) :- [0|_] = L.

% 2. Defina um predicado has5(L) que é verdadeiro se L for uma lista de 5 elementos.
has5(L) :- L = [_,_,_,_,_].

% 3. Defina um predicado hasN(L,N) que é verdadeiro se L for uma lista de N elementos.
hasN(L,N) :- length(L,N).

% 4 Defina um predicado potN0(N,L), de forma que L seja uma lista de potências de 2, com expoentes de N a 0.
potN0(0,[1]).
potN0(N,L) :-
    N > 0,
    L = [H|T],
    H is 2**N,
    N1 is N - 1,
    potN0(N1,T).
