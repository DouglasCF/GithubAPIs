package br.com.fornaro.githubapis.data.source.local.database.mappers

interface EntityMapper<T, R> {

    fun fromDomain(domain: R): T

    fun fromEntity(entity: T): R
}