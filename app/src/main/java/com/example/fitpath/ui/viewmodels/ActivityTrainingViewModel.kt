package com.example.fitpath.ui.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpath.data.model.DailyExercise
import com.example.fitpath.data.model.Exercise
import com.example.fitpath.data.dao.DailyExerciseDao
import com.example.fitpath.data.dao.ExerciseDao
import com.example.fitpath.data.model.Set
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ActivityTrainingViewModel @Inject constructor (var dao: DailyExerciseDao, private var daoExercise: ExerciseDao):ViewModel() {

    private var secondFlow = MutableStateFlow(0)
    var secondFlowImmutable : StateFlow<Int> = secondFlow

    private var minuteFlow = MutableStateFlow(0)
    var minuteFlowImmutable : StateFlow<Int> = minuteFlow

    private var hourFlow = MutableStateFlow(0)
    var hourFlowImmutable : StateFlow<Int> = hourFlow

    private var timerStatusLiveData = MutableLiveData(false)
    var liveDataTimerStatus :LiveData<Boolean> = timerStatusLiveData

    var second = 0
    var hour = 0
    var minute = 0
    private lateinit var job:Job
    var rep : String = ""
    private var finalRep : String = ""
    var weight : String = ""
    private var finalWeight : String = ""
    var set = 1
    private var finalSet = ""
    var exerciseTitle = ""
    var exerciseStatus = false
    var setStatus = false
    var exercises = ArrayList<Exercise>()
    var setList = ArrayList<Set>()
    private var lastID : DailyExercise? = null
    var date:String = ""
    var time:String = ""
    var timerStatus = true

    fun timer(){
        job = viewModelScope.launch{
            while (second<60){
                second++
                secondFlow.value = second
                delay(1000)
                if (second == 59){
                    delay(1000)
                    second = 0
                    minute++
                    minuteFlow.value = minute
                    continue
                }
                if (minute == 59){
                    delay(1000)
                    minute = 0
                    hour++
                    hourFlow.value = hour
                    continue
                }
            }
        }
    }
    fun timerStop(){
        job.cancel()
        second = 0
        hour = 0
        minute = 0
        secondFlow.value = 0
        minuteFlow.value = 0
        hourFlow.value = 0
        setList.clear()
        exercises.clear()
    }
    fun finishExercise(){
        getLastID()
        viewModelScope.launch {
            delay(1000)
            lastID.let {
                val exercise = Exercise(
                    0,
                    lastID!!.exercise_daily_id,
                    exerciseTitle,finalSet,finalRep,finalWeight)
                exercises.add(exercise)
                addExercise(exercise)
            }
            set=1
            rep = ""
            weight = ""
            weight = ""
            finalRep = ""
            finalWeight = ""
            finalSet = ""
            exerciseStatus = false
            setStatus = false
        }
    }
    fun addSetStart(){
        set++
        finalSet+="$set "
    }
    fun addSetStop(){
        val set = Set(set.toString(),rep,weight)
        setList.add(set)
    }
    fun addRepViewModel(){
        finalRep+="$rep "
    }
    fun addWeightViewModel(){
        finalWeight+="$weight "
    }
    /*fun getExercises(dailyExercise_LastID:Int){
        val job = CoroutineScope(Dispatchers.Main).launch {
            livedataExercises.value = daoExercise.getExercises(dailyExercise_LastID)
        }
    }*/
    fun addExerciseViewModel(exercise_name: String){
        lastID.let {
            val editedExercise = DailyExercise(
                lastID!!.exercise_daily_id,
                exercise_name,
                time,
                date)
            updateDailyExercise(editedExercise)
        }
    }
    fun deleteLastExercise(){
        lastID.let {
            deleteDailyExercise(lastID!!)
        }
    }
    fun getLastID(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            lastID = dao.getLastID()
        }
    }
    fun addExercise(exercise: Exercise){
        val job = CoroutineScope(Dispatchers.Main).launch {
            daoExercise.addExercise(exercise)
        }
    }
    fun addDailyExercise(dailyExercise: DailyExercise){
        val job = CoroutineScope(Dispatchers.Main).launch {
            dao.addDailyExercise(dailyExercise)
        }
    }
    fun updateDailyExercise(dailyExercise: DailyExercise){
        val job = CoroutineScope(Dispatchers.Main).launch {
            dao.updateDailyExercise(dailyExercise)
        }
    }
    fun deleteDailyExercise(dailyExercise: DailyExercise){
        val job = CoroutineScope(Dispatchers.Main).launch {
            dao.deleteDailyExercise(dailyExercise)
        }
    }

}