package pl.summer.model.entry;

import pl.summer.model.dto.EntryDto;

/**
 * Created by Piotr Borczyk on 22.06.2017.
 */
public interface EntryTransformer {
     EntryDto transform(EntryDto entryDto);
}
