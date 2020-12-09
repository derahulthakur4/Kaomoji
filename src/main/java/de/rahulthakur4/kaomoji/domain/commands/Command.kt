package de.rahulthakur4.kaomoji.domain.commands

interface Command<out T> {
    fun execute(): T
}