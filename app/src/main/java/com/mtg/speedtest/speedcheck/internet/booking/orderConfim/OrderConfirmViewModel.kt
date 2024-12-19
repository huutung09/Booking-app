package com.mtg.speedtest.speedcheck.internet.booking.orderConfim

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mtg.speedtest.speedcheck.internet.booking.api.ApiClient
import com.mtg.speedtest.speedcheck.internet.booking.model.request.CreateOrderRequest
import com.mtg.speedtest.speedcheck.internet.booking.model.response.BaseResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CreateOrderResponse
import com.mtg.speedtest.speedcheck.internet.booking.model.response.VoucherData
import com.mtg.speedtest.speedcheck.internet.booking.model.response.VoucherResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderConfirmViewModel(application: Application) : AndroidViewModel(application) {
    private val voucherList: MutableLiveData<List<VoucherData>> = MutableLiveData<List<VoucherData>>()
    private val selectedVoucher: MutableLiveData<VoucherData> = MutableLiveData<VoucherData>()
    private val paymentUrl: MutableLiveData<String> = MutableLiveData<String>()

    fun getVoucherData(context: Context) {
        viewModelScope.launch {
            try {
                ApiClient.instance.getAllVoucher().enqueue(object:
                    Callback<VoucherResponse> {
                    override fun onResponse(call: Call<VoucherResponse>, response: Response<VoucherResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            val data = response.body()?.data
                            voucherList.postValue(data!!)
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<VoucherResponse>, t: Throwable) {
                        Toast.makeText(
                            context,
                            "${t.message}.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                })
            } catch (e: Exception) {
                Log.e("Logger", "${e.message}")
            }
        }
    }

    fun createOrder(context: Context,userId: String, cartId: List<String>, address: String) {
        viewModelScope.launch {
            try {
                ApiClient.instance.createOrder(CreateOrderRequest(userId, cartId, "673aed15ad0cf16b1480e2eb", selectedVoucher.value?.id, address, "vi" )).enqueue(object:
                    Callback<CreateOrderResponse> {
                    override fun onResponse(call: Call<CreateOrderResponse>, response: Response<CreateOrderResponse>) {
                        if (response.body()?.success.toBoolean()) {
                            paymentUrl.postValue(response.body()?.url!!)
                            Toast.makeText(
                                context,
                                "Order created successfully.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<CreateOrderResponse>, t: Throwable) {
                        Toast.makeText(
                            context,
                            "${t.message}.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                })
            } catch (e: Exception) {
                Log.e("Logger", "${e.message}")
            }
        }
    }


    fun setSelectedVoucher(voucher: VoucherData) {
        selectedVoucher.postValue(voucher)
    }

    fun getSelectedVoucher(): MutableLiveData<VoucherData> {
        return selectedVoucher
    }

    fun getVoucherList(): MutableLiveData<List<VoucherData>> {
        return voucherList
    }

    fun getPaymentUrl(): MutableLiveData<String> {
        return paymentUrl
    }
}