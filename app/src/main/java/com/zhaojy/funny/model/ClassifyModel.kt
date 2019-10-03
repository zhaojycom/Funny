package com.zhaojy.funny.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaojy.funny.bean.ClassifyBean
import com.zhaojy.funny.bean.Plant
import com.zhaojy.funny.data.repository.ClassifyRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody

/**
 * @author: zhaojy
 * @data:On 2019/9/22.
 */
class ClassifyModel(private val repository: ClassifyRepository) : ViewModel() {
    val classifyList = ArrayList<ClassifyBean>()
    val classifyPlantList = ArrayList<Plant>()
    var classifyDataChanged = MutableLiveData(false)
    var classifyPlantChanged = MutableLiveData(0)

    fun getPlantClassifyList() {
        viewModelScope.launch {
            try {
                val tempList = repository.getPlantClassifyList()
                for (item in tempList) {
                    classifyList.add(item)
                }
                classifyDataChanged.value = true
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun getClassifyPlantList(requestBody: RequestBody) {
        viewModelScope.launch {
            try {
                val tempList = repository.getClassifyPlantList(requestBody)
                for (item in tempList) {
                    classifyPlantList.add(item)
                }
                classifyPlantChanged.value = classifyPlantChanged.value?.plus(1)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

}
