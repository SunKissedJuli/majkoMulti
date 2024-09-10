package com.example.majkomulti.platform

import androidx.compose.runtime.Composable
import org.koin.core.component.KoinComponent

@Composable
public actual fun KoinComponent.FilesPicker(
    show: Boolean,
    initialDirectory: String?,
    fileExtensions: List<String>,
    onlyImage: Boolean,
    onFileSelected: FilesSelected
){}

@Composable
public actual fun ImagePicker(
    show: Boolean,
    initialDirectory: String?,
    onFileSelected: FileSelected
){}