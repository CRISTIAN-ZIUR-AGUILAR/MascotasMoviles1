package edu.itvo.pets.presentation.composables


import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CrueltyFree
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HdrAuto
import androidx.compose.material.icons.filled.Panorama
import androidx.compose.material.icons.filled.Paragliding
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.itvo.pets.presentation.viewmodel.PetViewModel

@Composable
fun Pet(viewModel: PetViewModel = hiltViewModel(),
        modifier: Modifier = Modifier){
    val state  by  viewModel.uiState.collectAsStateWithLifecycle()
    var hasSaved by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()


    Card (modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(16.dp) // Padding alrededor del contenido desplazable
        )  {
                OutlinedTextField(
                    value = state.name,
                    onValueChange = { viewModel.onEvent(PetViewModel.PetEvent.NameChanged(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }),
                    label = {
                        Text(text="nombre: ")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.HdrAuto,
                            contentDescription = null,
                        )
                    },
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                value = state.description,
                onValueChange = { viewModel.onEvent(PetViewModel.PetEvent.DescriptionChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                }),
                label = {
                    Text(text="Descripción: ")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Description,
                        contentDescription = null,
                    )
                },
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                    value = state.type,
                    onValueChange = { viewModel.onEvent(PetViewModel.PetEvent.TypeChanged(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                    }),
                    label = {
                        Text(text="Tipo: ")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.CrueltyFree,
                            contentDescription = null,
                        )
                    },

                )
                Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.race,
                onValueChange = { viewModel.onEvent(PetViewModel.PetEvent.RaceChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                }),
                label = {
                    Text(text="Raza: ")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Category,
                        contentDescription = null,
                    )
                },
                )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.birthdate,
                onValueChange = { viewModel.onEvent(PetViewModel.PetEvent.BirthdateChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                }),
                label = {
                    Text(text="Fecha nacimiento: ")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = null,
                    )
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.image,
                onValueChange = { viewModel.onEvent(PetViewModel.PetEvent.ImageChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                }),
                label = {
                    Text(text="Foto: ")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Panorama,
                        contentDescription = null,
                    )
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                viewModel.onEvent(PetViewModel.PetEvent.AddClicked(
                    name= state.name,
                    description = state.description,
                    type = state.type,
                    race = state.race,
                    birthdate = state.birthdate,
                    image= state.image))
                    hasSaved = true

            }) {
                Text(text="Guardar")
            }
            if (hasSaved) {
                AlertDialog(
                    onDismissRequest = { hasSaved = false },
                    confirmButton = {
                        Button(onClick = { hasSaved = false }) {
                            Text("Aceptar")
                        }
                    },
                    title = { Text("Success") },
                    text = { Text("Guardado satisfactoriamente") }
                )
            }
        }
    }
}