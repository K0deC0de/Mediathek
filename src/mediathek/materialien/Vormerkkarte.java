package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import java.util.LinkedList;

import java.util.Queue;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

/**
 * Diese Klasse stellt eine Karte dar, die beim Vormerk-Vorgang benutzt wird.
 */
public class Vormerkkarte
{
    // Eigenschaften einer Vormerkkarte
    private Medium _medium;
    private final Queue<Kunde> _vormerkQueue;

    /**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     * 
     * @param medium Ein verliehene Medium.
     * 
     * @require medium != null 
     *    
     * @ensure Vormerkkarte != null 
     * @ensure #getMedium() == medium
     */
    public Vormerkkarte(Medium medium)
    {
        assert medium != null : "Vorbedingung verletzt: medium != null";

        _medium = medium;
        _vormerkQueue = new LinkedList<Kunde>();
        
    }

    /**
     * Gibt an welche Kunden sich im Queue befinden 
     * 
     * @return Kunden in einer Queue
     * 
     * @ensure result != null
     */
    public Queue<Kunde> getVormerker()
    {
        return _vormerkQueue;
    }

    /**
     * Gibt an wie viele Kunden sich im Queue befinden
     * 
     * @return anzahl der Kunden in einer Queue
     * 
     * @ensure result != null
     */
    public int getQueue()
    {
        return _vormerkQueue.size();
    }

    /**
     * Füge ein Kunde zu der Queue hinzu(Tail).
     * 
     * @param kunde
     * 
     * @require assert _vormerkQueue.size() <= 2;
     * @require assert _vormerkQueue.size() >= 0;
     * @require kunde != null
     */
    public void fuegeHinzu(Kunde kunde)
    {
        
        assert kunde != null;
        assert _vormerkQueue.size() <= 2 : "Vorbedingung verletzt: _vormerkQueue.size() <= 2";
        assert _vormerkQueue.size() >= 0 : "Vorbedingung verletzt: _vormerkQueue.size() >= 0";
        if (_vormerkQueue.size() <= 2 && _vormerkQueue.size() >= 0);
        {
            _vormerkQueue.add(kunde);
        }
    }

    /**
     * Entferne ein Kunde aus der Queue(Head).
     *
     * @param kunde
     * 
     * @require kunde != null
     * @require _vormerkQueue.contains(kunde) stellt sicher, dass tatsächlich ein Kunde drin zum Löschen ist
     */
    public void entferne(Kunde kunde)
    {
        assert kunde != null;
        assert _vormerkQueue.contains(kunde);

        for (Kunde i : _vormerkQueue)
        {
            if (kunde == i)
            {
                _vormerkQueue.remove(kunde);
                break;
            }
        }
    }

    /**
     * Gibt an, ob der Queue leer ist oder nicht.
     * 
     * @return true wenn der Queue leer ist also 0
     * 
     * @ensure result != null
     */
    public boolean gibEmpty()
    {
        return(_vormerkQueue.isEmpty());
    }

    /**
     * prüft, ob jemand in der Queue ist oder nicht.
     *
     * @param kunde
     * 
     * @return true wenn der Queue nict leer ist
     * @require kunde !=null
     */
    public boolean istDrin(Kunde kunde)
    {
        assert kunde != null : "Vorbedingung verletzt: kunde ist null";
        return (_vormerkQueue.contains(kunde));
    }

//    /**
//     * Gibt eine Vormerkungsnummer, damit können wir ein Index für unseren Queue nutzen.
//     *
//     * @param kunde
//     * 
//     * @return counter die Vormerkungsnummer auf der Queue
//     * 
//     * @require kunde != null
//     * @require _vormerkQueue.contains(kunde)
//     * @require _vormerkQueue.size() <= 2
//     */
//    public int gibVormerkungsnummer(Kunde kunde)
//    {
//        assert kunde != null;
//        assert _vormerkQueue.contains(kunde);
//        assert _vormerkQueue.size() <= 2;
//
//        int counter = 0;
//        for (Kunde i : _vormerkQueue)
//        {
//            if (kunde == i)
//            {
//                break;
//            }
//            else
//                counter++;
//        }
//        return counter;
//    }

	/**
	* Gibt den Kunden zurück, der an der ersten Stelle steht.
	*
	* @return Kunde an der ersten Stelle der Queue
	*/
	public Kunde gibErsteStelle()
	{
        return _vormerkQueue.element();
    }
    
    /**
	* Gibt an, ob ein Kunde ein Medium ausleihen darf
	*
	* @param kunde 
	*   
	* @require kunde !=null
	*/
	public boolean istVerleihenMoeglich(Kunde kunde)
	{
	    assert kunde != null : "Vorbedingung verletzt: kunde ist null";
        return gibEmpty() || kunde.equals(gibKundeByIndex(0));
    }
    
    /**
     * Gibt das Medium, dessen Vormerkung auf der Karte vermerkt ist, zurück.
     *
     * @param kunde
     * 
     * @return Das Medium, dessen Vormerkung auf dieser Karte vermerkt ist.
     *
     * @require kunde != null
     * 
     * @ensure result != null
     */
    public boolean istVomerkungMoeglich(Kunde kunde) 
    {
        assert kunde != null : "Vorbedingung verletzt: kunde ist null";
        
        return (_vormerkQueue.size() < 3 && !_vormerkQueue.contains(kunde));
    }

    /**
     * Gibt das Medium, dessen Vormerkung auf der Karte vorgemerkt ist, zurück.
     * 
     * @return Das Medium, dessen Vormerkung auf dieser Karte vorgemerkt ist.
     * 
     * @ensure result != null
     */
    public Medium getMedium()
    {
        return _medium;
    }

    /**
     * Wir entfernen elemente aus der Queue, bis wir zu den gesuchten Element kommen,
     * wir fügen die gelöschten Element wieder in der Queue(Tail) oder erstellen eine neue 
     * und packen sie dahin.
     * 
     * @param x
     * 
     * @require _vormerkQueue.size() <= 3
     * @require _vormerkQueue.size() >= 0
     * @require i >= 0
     * @require i <= 2
     */
    public Kunde gibKundeByIndex(int x)
    {
        assert x >= 0 : "Vorbedingung verletzt";
        assert x <= 2 : "Vorbedingung verletzt";
        assert _vormerkQueue.size() <= 3 : "Vorbedingung verletzt";
        assert _vormerkQueue.size() >= 0 : "Vorbedingung verletzt";
        
        int size = _vormerkQueue.size();
        
        Kunde kunde = null;
        
        for(int i = 0; i < size; i++)
        {
            if(i == x)
            {
            	kunde = _vormerkQueue.remove();
            	_vormerkQueue.add(kunde);
            }
            else
            {
                _vormerkQueue.add(_vormerkQueue.remove());
            }
        }
        return kunde;        
    }    
}
