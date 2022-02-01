package com.example.fintech.presentation


import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StudentViewModel(application:Application)
    : AndroidViewModel(application){
    private val db:RoomSingleton = RoomSingleton
        .getInstance(application)

    internal val students : LiveData<MutableList<Student>> =
        db.studentDao().getStudents()

    fun insert(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            db.studentDao().insert(student)
        }
    }

    fun update(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            db.studentDao().update(student)
        }
    }

    fun delete(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            db.studentDao().delete(student)
        }
    }

    fun clear(){
        viewModelScope.launch(Dispatchers.IO) {
            db.studentDao().clear()
        }
    }
}


class StudentViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            return StudentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}