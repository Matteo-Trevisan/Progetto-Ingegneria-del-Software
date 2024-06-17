# Ingegneria del Software - Appello 1
Progetto di Ingegneria del Software per il corso di Ingegeria Informatica.

Autore: Matteo Trevisan

Matricola: 2066662

## Descrizione
Il progetto consiste nell'implementazione di una classe ListAdapter in ambiente
CLDC 1.1 e relativi test.

## Sviluppo
Il progetto era da sviluppare in modalità test-driven development, per cui lo sviluppo è 
avvenuto nelle seguenti fasi:
1. Definizione delle interfacce con relativa documentazione
2. Definizione della classe ListAdapter senza implementazione dei metodi
3. Scrittura dei test per i metodi della classe ListAdapter
4. Implementazione dei metodi della classe ListAdapter
5. Aggiornamento della classe basato sui risultati dei test

## Documentazione
La documentazione del progetto è stata generata utilizzando il tool Javadoc,
è possibile visualizzarla aprendo il file `docs/javadocs/index.html`.

Tag personalizzati usati nella javadoc:

-tag "doc.testCaseDesign:a:Test Case Design:"
-tag "doc.testDescription:a:Test Description:"
-tag doc.preCondition:a:Pre-Condition:
-tag doc.postCondition:a:Post-Condition:
-tag "doc.expectedResults:a:Expected Results:"
-link https://docs.oracle.com/javame/config/cldc/ref-impl/cldc1.1/jsr139

## Test
I test sono stati scritti utilizzando il framework JUnit 4.12, i jar necessari 
all'esecuzione dei test sono presenti nella cartella `JUnit`.

## Link
- [Documentazione Progetto](javadocs/index.html)
- [Documentazione CLDC 1.1](https://docs.oracle.com/javame/config/cldc/ref-impl/cldc1.1/jsr139/)
- [Documentazione Java 1.4.2](https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html)