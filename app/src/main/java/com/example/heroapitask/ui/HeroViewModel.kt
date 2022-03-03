package com.example.heroapitask.ui


import android.util.Log
import androidx.lifecycle.*
import com.example.heroapitask.network.Data
import com.example.heroapitask.network.DataDao
import com.example.heroapitask.network.Hero
import com.example.heroapitask.network.HeroApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

enum class HeroApiStatus{LOADING, ERROR, DONE}
class HeroViewModel (private val dataDao: DataDao):ViewModel() {

    private val _status=MutableLiveData<HeroApiStatus>()
    private val _hero=MutableLiveData<Data>()
    val status:LiveData<HeroApiStatus> = _status
    val hero:LiveData<Data> = _hero

    private var _datalist= MutableLiveData<List<Data>>()
    val datalist: LiveData<List<Data>> = _datalist



  fun getHeroList() {
       _status.value= HeroApiStatus.LOADING
        viewModelScope.launch {
            try{
                val hlist:Hero=HeroApi.retrofitService.getHero()
                val ldata:List<Data> = hlist.data
                addData(ldata)
//                _datalist.value=ldata
                _datalist.value=ldata
                _status.value=HeroApiStatus.DONE


            }catch (e:Exception) {
                Log.e("msg", "${e.message}")
                _datalist.value = listOf()
                _status.value = HeroApiStatus.ERROR
            }
        }

   }

   private fun insertData(data: List<Data>){
        viewModelScope.launch(Dispatchers.IO) {
            dataDao.insert(data)
        }
    }

//   @OptIn(FlowPreview::class)

   fun getData(){
        viewModelScope.launch {
            try {
                val data = dataDao.getAllData()
//                val d1=data.flatMapConcat { it.asFlow() }.toList()
                _datalist.value = data
                _status.value=HeroApiStatus.DONE


            } catch (e: Exception) {
                Log.e("msg", "${e.message}")
            }

        }
    }

    private fun addData(ldata:List<Data>){
                insertData(ldata)
    }



    fun onHeroClicked(hero: Data){
        _hero.value=hero
    }

}

class HeroViewModelFactory(private val dataDao: DataDao) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HeroViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return HeroViewModel(dataDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}

