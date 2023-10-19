package com.sti.cmart.exception.entity;

import com.sti.cmart.exception.core.ArchitectureException;
import org.springframework.http.HttpStatus;

import static com.sti.cmart.util.Constants.Entity.ENTITY_ALREADY_EXIST;
import static com.sti.cmart.util.Constants.Entity.ENTITY_ALREADY_EXIST_CODE;

public class EntityAlreadyExistException extends ArchitectureException {
    //region
    private static final long serialVersionUID = 1L;
    //endregion

    public EntityAlreadyExistException() {
        super();
        this.code = ENTITY_ALREADY_EXIST_CODE;
        this.msg = ENTITY_ALREADY_EXIST;
        this.status = HttpStatus.BAD_REQUEST;
    }
}
