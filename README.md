# FaniRepo
Esercizi java.

Scopo dell'esercizio è la realizzazione di una libreria (package OldFashionPound) che implementa le 4 operazioni fondamentali (somma, sottrazione, moltiplicazione e divisione) di prezzi secondo il sistema monetario inglese antecedente al 1970 (1 sterlina=20 scellini, 1 scellino=12 pennies).

Un prezzo nel sistema monetario inglese antecedente al 1970 è definito nel seguente modo:
p=sterline
s=scellini
d=pence

Ad esempio un prezzo di  12p 6s 10d indica 12 sterline, 6 scellini e 10 pence

La somma e la sottrazione devono rispettivamente essere in grado di sommare e sottrarre due prezzi
Esempio somma: 5p 17s 8d + 3p 4s 10d= 9p 2s 6d
Esempio sottrazione: 5p 17s 8d - 3p 4s 10d= 2p 12s 10d
Se la sottrazione dà risultato negativo, il valore deve essere gestito e opportunamente rappresentato 
La moltiplicazione e divisione ricevono in input un prezzo e un intero (la moltiplicazione  la divisione per valori decimali non sono previste)
Esempio moltiplicazione: 5p 17s 8d * 2 = 11p 15 s 4d
Esempio divisione 5p 17s 8d / 3 = 1p 19s 2d (2p) (avanzano 2 penny - nell’output indicare il resto con racchiuso fra parentesi)
Altro esempio divisione 18p 16s 1d / 15 = 1p 5s 0d   (1s 1d) (avanzano 1 scellino e 1 penny - nell’output indicare il resto con racchiuso fra parentesi)

La libreria deve essere anche in grado di ricevere in input e produrre in output i prezzi in formato "Xp Ys Zd", come negli esempi riportati. 

