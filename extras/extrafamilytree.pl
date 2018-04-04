masculino(angelo).
masculino(beto).
ferminino(daniela).

descendente(angelo, daniela).
descendente(daniela, beto).

filho(X,Y) :- masculino(X), descendente(X,Y).
