package com.zhaojy.funny.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhaojy.funny.constant.Constants
import com.zhaojy.funny.data.bean.User
import com.zhaojy.funny.data.livedata.UserLiveData
import com.zhaojy.funny.data.repository.LoginRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody


/**
 *@author: zhaojy
 *@data:On 2019/10/5.
 */
class LoginModel(private val repository: LoginRepository) : ViewModel() {
    var mUserLiveData = UserLiveData.get()

    fun login(phoneNumber: String) {
        val body = RequestBody.create(
            okhttp3.MediaType.parse(
                Constants.MEDIATYPE_JSON
            ), phoneNumber
        )
        viewModelScope.launch {
            try {
                val user: User = repository.login(body)
                mUserLiveData.setValue(user)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

}