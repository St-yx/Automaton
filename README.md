# Neuronales Netzwerk – Perzeptron

## Überblick
Dieses Projekt ist eine Java-Implementierung eines **Perzeptrons**, das grundlegende logische Operationen (**AND, OR, NAND, NOR**) lernen kann.  
Das Ziel ist es, den Lernprozess eines künstlichen Neurons anhand kleiner binärer Beispiele transparent darzustellen.

## Aufbau
Das Projekt besteht aus folgenden Klassen:

- **`Automaton`**  
  Enthält die `main`-Methode und steuert den Ablauf:
  - Abfrage der gewünschten logischen Operation und Anzahl der Eingangsvariablen vom Nutzer  
  - Erzeugung der Trainingsdaten  
  - Training des Perzeptrons mittels Lernalgorithmus  
  - Ausgabe der gelernten Ergebnisse  

- **`BinGen`**  
  Generiert alle möglichen binären Kombinationen (Inputs) für eine gegebene Anzahl von Variablen.  
  Zusätzlich wird eine Spalte für den Bias-Wert (immer `1.0`) angehängt.

- **`Toolbelt`**  
  Sammlung kleiner mathematischer Hilfsmethoden

- **`Neuron`** 
  Definiert die verfügbaren logischen Operationen (z. B. AND, OR, NAND, NOR).  
  Der Zielvektor, der als „Lernziel“ für das Perzeptron dient, wird für die ausgewählte Operation generiert.

## Funktionsweise
1. Der Nutzer wählt eine logische Operation und die Menge der Eingangsvariablen.  
2. Das Programm erstellt automatisch die binäre Zielfunktion zum trainieren.  
3. Das Perzeptron wird trainiert, bis der richtige Gewichtsvektor generiert wurde, um fehlerfrei die gewünschte Operation abzubilden.  
4. Die Ergebnisse für alle Eingabekombinationen werden ausgegeben.  

Beispielausgabe für **OR** mit **2 Eingangsvariablen**:
```
0.0 OR 0.0 ist 0.0
0.0 OR 1.0 ist 1.0
1.0 OR 0.0 ist 1.0
1.0 OR 1.0 ist 1.0
```
