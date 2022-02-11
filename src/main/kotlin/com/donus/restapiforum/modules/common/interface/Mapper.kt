package com.donus.restapiforum.modules.common.`interface`

interface Mapper<E, Request, Response> {
    fun entityToDTO(e: E): Response

    fun dtoToEntity(dto: Request): E
}
