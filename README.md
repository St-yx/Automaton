# Neuronales Netzwerk – Perzeptron

## Überblick
Dieses Projekt ist eine Java-Implementierung eines **Perzeptrons**, das grundlegende logische Operationen (**AND, OR, NAND, NOR**) lernen kann.  
Das Ziel ist es, den Lernprozess eines künstlichen Neurons anhand kleiner binärer Beispiele transparent darzustellen und später auf komplexere Logikformeln zu erweitern.

## Aufbau
Das Projekt besteht aktuell aus folgenden Klassen:

- **`Neuron`**  
  Enthält die `main`-Methode und steuert den Ablauf:
  - Abfrage der gewünschten logischen Operation vom Nutzer  
  - Erzeugung der Trainingsdaten  
  - Training des Perzeptrons mittels Lernalgorithmus  
  - Ausgabe der gelernten Ergebnisse  

- **`BinGen`**  
  Generiert alle möglichen binären Kombinationen (Inputs) für eine gegebene Anzahl von Variablen.  
  Zusätzlich wird eine Spalte für den Bias-Term (immer `1.0`) angehängt.

- **`Toolbelt`**  
  Sammlung kleiner mathematischer Hilfsmethoden:
  - Quadrat einer Zahl  
  - Skalarprodukt zweier Vektoren  
  - Stufenfunktion (Schwellwertfunktion)  

- **`Operation`** 
  Definiert die verfügbaren logischen Operationen (z. B. AND, OR, NAND, NOR).  
  Jede Operation besitzt einen Zielvektor, der als „Lernziel“ für das Perzeptron dient.  
  Über ein enum wird die Usereingabe dem Zeilvektor zugewiesen.

## Funktionsweise
1. Der Nutzer wählt eine logische Operation.  
2. Das Programm erstellt automatisch die binären Eingabekombinationen.  
3. Das Perzeptron wird trainiert, bis es fehlerfrei die gewünschte Operation abbildet.  
4. Die Ergebnisse für alle Eingabekombinationen werden ausgegeben.  

Beispielausgabe für **OR**:
```
0.0 OR 0.0 ist 0.0
0.0 OR 1.0 ist 1.0
1.0 OR 0.0 ist 1.0
1.0 OR 1.0 ist 1.0
```
