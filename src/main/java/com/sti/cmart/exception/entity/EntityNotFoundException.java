package com.sti.cmart.exception.entity;

import com.sti.cmart.exception.core.ArchitectureException;
import org.springframework.http.HttpStatus;

import static com.sti.cmart.util.Constants.Entity.ENTITY_NOT_FOUND;
import static com.sti.cmart.util.Constants.Entity.ENTITY_NOT_FOUND_CODE;

public class EntityNotFoundException extends ArchitectureException {
    //region
    private static final long serialVersionUID = 1L;
    //endregion

    public EntityNotFoundException() {
        super();
        this.code = ENTITY_NOT_FOUND_CODE;
        this.msg = ENTITY_NOT_FOUND;
        this.status = HttpStatus.NOT_FOUND;
    }
}
