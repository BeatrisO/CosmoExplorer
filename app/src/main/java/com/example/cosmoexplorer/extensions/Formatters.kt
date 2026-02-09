package com.example.cosmoexplorer.extensions

fun String.toDisplayDate(): String =
    split("-").reversed().joinToString("/")

fun Long.toUsd(): String =
    "US$ ${"%,d".format(this)}"
