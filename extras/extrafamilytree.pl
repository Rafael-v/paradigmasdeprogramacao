% Desenho da árvore genealógica montada abaixo: https://i.imgur.com/r1imj3r.png

masculino(joao).
masculino(jorge).
masculino(angelo).
masculino(vinicius).
masculino(enzo).
masculino(thiago).
masculino(gustavo).
feminino(cristina).
feminino(maria).
feminino(nathalia).
feminino(claudia).
feminino(ana).
feminino(isabel).

conjuge(cristina,joao).
conjuge(joao,cristina).
conjuge(maria,jorge).
conjuge(jorge,maria).
conjuge(claudia,angelo).
conjuge(angelo,claudia).
conjuge(ana,vinicius).
conjuge(vinicius,ana).
descendente(cristina, nathalia).
descendente(cristina, claudia).
descendente(joao,nathalia).
descendente(joao,claudia).
descendente(maria,angelo).
descendente(maria,vinicius).
descendente(jorge,angelo).
descendente(jorge,vinicius).
descendente(claudia,isabel).
descendente(claudia,enzo).
descendente(angelo,isabel).
descendente(angelo,enzo).
descendente(vinicius,thiago).
descendente(vinicius,gustavo).
descendente(ana,thiago).
descendente(ana,gustavo).

avo(X,Y) :- descendente(X,A), descendente(A,Y).
pai(X,Y) :- masculino(X), descendente(X,Y).
mae(X,Y) :- feminino(X), descendente(X,Y).
filho(X,Y) :- masculino(X), descendente(Y,X).
filha(X,Y) :- feminino(X), descendente(Y,X).
irmao(X,Y) :- masculino(X), descendente(A, X), descendente(A, Y).
irma(X,Y) :- feminino(X), descendente(A, X), descendente(A, Y).
tio(X,Y) :- masculino(X), descendente(A,Y), irmao(X,A).
tia(X,Y) :- feminino(X), descendente(A,Y), irma(X,A).
primo(X,Y) :- masculino(X), descendente(A,Y), (tio(A,X); tia(A,X)).
prima(X,Y) :- feminino(X), descendente(A,Y), (tio(A,X); tia(A,X)).
sobrinho(X,Y) :- masculino(X), (tio(Y,X); tia(Y,X)).
sobrina(X,Y) :- feminino(X), (tio(Y,X); tia(Y,X)).
neto(X,Y) :- masculino(X), descendente(Y,A), descendente(A,X).
neta(X,Y) :- feminino(X), descendente(Y,A), descendente(A,X).
cunhado(X,Y) :- masculino(X), irmao(X,A), conjuge(A,Y).
cunhado(X,Y) :- masculino(X), conjuge(X,A), irma(A,Y).
cunhada(X,Y) :- feminino(X), irma(X,A), conjuge(A,Y).
cunhada(X,Y) :- feminino(X), conjuge(X,A), irmao(A,Y).
sogro(X,Y) :- masculino(X), conjuge(Y,A), descendente(X,A).
sogra(X,Y) :- feminino(X), conjuge(Y,A), descendente(X,A).
genro(X,Y) :- masculino(X), conjuge(X,A), descendente(Y,A).
nora(X,Y) :- feminino(X), conjuge(X,A), descendente(Y,A).
