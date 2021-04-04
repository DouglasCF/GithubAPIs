package br.com.fornaro.githubapis.data.source.remote.mappers

interface Mapper<T, R> {
    fun map(input: T): R
}