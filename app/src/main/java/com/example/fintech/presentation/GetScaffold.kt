package com.example.fintech.presentation

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random


@Composable
fun GetScaffold(){
    val scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = SnackbarHostState()
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Compose - Room Add Remove Update")},
                backgroundColor = Color(0xFFC0E8D5),
            )
        },
        content = {MainContent(scaffoldState)},
        backgroundColor = Color(0xFFEDEAE0),
    )
}


@Composable
fun MainContent(scaffoldState: ScaffoldState){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val model : StudentViewModel = viewModel(
        factory = StudentViewModelFactory(
            context.applicationContext as Application
        )
    )
    val list:List<Student> = model.students.observeAsState(listOf()).value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        //contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                Button(onClick = {
                    model.insert(
                        Student(
                            null,
                            UUID.randomUUID().toString(),
                            Random.nextInt(10,90)
                        )
                    )
                    scope.launch{
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Student added",
                        )
                    }
                }) {
                    Text(text = "Add Student")
                }
                Button(onClick = {
                    model.clear()
                    scope.launch{
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "All Students deleted",
                        )
                    }

                }) {
                    Text(text = "Clear")
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(list.size) { index ->
                    Card(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(Alignment.CenterVertically)
                    ) {
                        Row(
                            modifier = Modifier.padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${list[index].id}",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 12.dp)
                            )

                            Text(
                                text = " : " + list[index].fullName.take(10),
                            )

                            Text(
                                text = " : " + list[index].result,
                                style = TextStyle(
                                    color = if (list[index].result >= 33)
                                        Color(0xFF3B7A57)
                                    else Color(0xFFAB274F)),
                                modifier = Modifier.weight(2F)
                            )

                            IconButton(onClick = {
                                list[index].result = Random.nextInt(10,100)
                                model.update(list[index])
                                scope.launch{
                                    scaffoldState.snackbarHostState
                                        .showSnackbar(
                                            "Student updated id" +
                                                    " : ${list[index].id}",
                                        )
                                }
                            }) {
                                Icon(Icons.Filled.Edit,"")
                            }

                            IconButton(onClick = {
                                model.delete(list[index])
                                scope.launch{
                                    scaffoldState.snackbarHostState
                                        .showSnackbar(
                                            "Student deleted id" +
                                                    " : ${list[index].id}",
                                        )
                                }

                            }) {
                                Icon(Icons.Filled.Delete,"")
                            }
                        }
                    }
                }
            }
        }
    }
}