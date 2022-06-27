package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class VormerkkarteTest
{
    private Vormerkkarte _karte;
    private Vormerkkarte _karte2;
    private Kunde _kunde;
    private Kunde _kunde2;
    private Kunde _kunde3;
    private Medium _medium;

    private Queue<Kunde> _testQueue = new LinkedList<>();

    public VormerkkarteTest()
    {
        _kunde = new Kunde(new Kundennummer(123456), "muhammad", "ali");
        _kunde2 = new Kunde(new Kundennummer(173456), "michael", "jordan");
        _kunde3 = new Kunde(new Kundennummer(123486), "Rocky", "dirt");
        _medium = new CD("bar", "baz", "foo", 123);
        _karte = new Vormerkkarte(_medium);
        _karte2 = new Vormerkkarte(_medium);
    }

    @Test
    public void testeKonstruktor()
    {
        _karte.fuegeHinzu(_kunde);
        _karte.fuegeHinzu(_kunde2);
        _karte2.fuegeHinzu(_kunde);
        _karte2.fuegeHinzu(_kunde2);
        
        Queue<Kunde> _vormerkQ1 = new LinkedList<>();
        Queue<Kunde> _vormerkQ2 = new LinkedList<>();

        _vormerkQ1.add(_kunde);
        _vormerkQ1.add(_kunde2);
        _vormerkQ2.add(_kunde);
        _vormerkQ2.add(_kunde2);

        assertEquals(_medium, _karte.getMedium());
        assertEquals(_vormerkQ1, _karte.getVormerker());
        assertEquals(_vormerkQ2, _karte2.getVormerker());

        assertEquals(_medium, _karte.getMedium());
        assertEquals(_medium, _karte2.getMedium());
    }

    @Test
    public void testegetQueue()
    {
        _karte.fuegeHinzu(_kunde);
        _karte.fuegeHinzu(_kunde2);
        _testQueue.add(_kunde);
        _testQueue.add(_kunde2);
        assertEquals(_testQueue.size(), _karte.getQueue());
    }

    @Test
    public void testefuegeHinzu()
    {
        _karte.fuegeHinzu(_kunde3);
        _testQueue.add(_kunde3);

        assertEquals(_karte.getQueue(), _testQueue.size()); //testet ob, die Anzahl der Kunden im Queue wie erwartet hinzugefügt werden
        assertTrue(_karte.istDrin(_kunde3)); //testet, ob das richtige Objekt hinzugefügt wurde
    }

    @Test
    public void testeentferne()
    {
        _karte.fuegeHinzu(_kunde);
        _karte2.fuegeHinzu(_kunde);
        _testQueue.add(_kunde);
        
        _karte.entferne(_kunde);
        _testQueue.remove(_kunde);

        assertEquals(_testQueue.size(), _karte.getQueue()); //Löscht die Anzahl der Kunden im Queue tatsächlich wie erwartet
        assertTrue(!_karte.istDrin(_kunde)); //testet, ob der richtige Objekt gelöscht wurde
    }

    @Test
    public void testegibEmpty()
    {
        _karte.fuegeHinzu(_kunde);
        _karte.fuegeHinzu(_kunde2);

        assertFalse(_karte.gibEmpty());
        _karte.entferne(_kunde);
        _karte.entferne(_kunde2);
        assertTrue(_karte.gibEmpty());
    }

    @Test
    public void testegibKundeByIndex()
    {
        _karte.fuegeHinzu(_kunde);
        _karte.fuegeHinzu(_kunde2);
        _karte2.fuegeHinzu(_kunde);
        _karte2.fuegeHinzu(_kunde2);
        
        assertEquals(_kunde2, _karte.gibKundeByIndex(1));
        assertEquals(_kunde, _karte2.gibKundeByIndex(0));

    }

}
