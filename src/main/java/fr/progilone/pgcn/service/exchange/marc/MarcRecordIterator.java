package fr.progilone.pgcn.service.exchange.marc;

import org.marc4j.MarcReader;
import org.marc4j.marc.Record;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implémentation de l'interface Iterator pour MarcReader
 */
public class MarcRecordIterator implements Iterator<Record> {

    private final MarcReader reader;

    public MarcRecordIterator(final MarcReader reader) {
        this.reader = reader;
    }

    @Override
    public boolean hasNext() {
        return reader != null && reader.hasNext();
    }

    @Override
    public Record next() {
        if (reader == null) {
            throw new NoSuchElementException();
        }
        return reader.next();
    }
}
